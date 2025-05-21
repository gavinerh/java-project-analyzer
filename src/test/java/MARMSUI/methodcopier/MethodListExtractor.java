package MARMSUI.methodcopier;


import MARMSUI.methodcopier.model.MethodDetails;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodListExtractor {
    static Path javaFilePathGlobal = Paths.get("/Users/macuser/Documents/updated-lsl-app/marms/MARMS/Source Code/Business Components/EJB/QualificationService/com/singaporeair/marms/abacus/business/customer/tier/QualificationServiceBean.java");

    public static void main(String[] args) {
        Map<String, MethodDetails> mapOfMethodsToSaveToFile = new HashMap<>();
        printListOfMethods(javaFilePathGlobal, mapOfMethodsToSaveToFile);
        System.out.println(mapOfMethodsToSaveToFile);
    }

    private static void printListOfMethods(Path javaFilePath, Map<String, MethodDetails> mapOfMethods) {
        try {
            // Path to the Java file you want to parse
//            Path javaFilePath = Paths.get("/Users/macuser/Documents/updated-lsl-app/marms/MARMS/Source Code/Business Components/EJB/QualificationService/com/singaporeair/marms/abacus/business/customer/tier/QualificationServiceBean.java");

            // Parse the Java file
            CompilationUnit compilationUnit = StaticJavaParser.parse(javaFilePath);

            // Retrieve the class declaration
            ClassOrInterfaceDeclaration classDeclaration = compilationUnit
                    .findFirst(ClassOrInterfaceDeclaration.class)
                    .orElseThrow(() -> new IllegalArgumentException("No class found in the provided file!"));

            // Iterate over all the methods in the class
            for (MethodDeclaration method : classDeclaration.getMethods()) {
                // Print the method name and parameter types
                System.out.print("Method: " + method.getName() + " | Parameters: ");
                MethodDetails methodDetails = new MethodDetails(method.getNameAsString());
                methodDetails.setDetailConfigured(true);
                printParameterTypes(method.getParameters(), methodDetails);


                // Find all the method calls within the current method
                List<MethodCallExpr> methodCalls = method.findAll(MethodCallExpr.class);

                if (methodCalls.isEmpty()) {
                    System.out.println("  No methods called inside this method.");
                } else {
                    System.out.println("  Methods called:");
                    for (MethodCallExpr call : methodCalls) {
                        // Check if the called method exists in the current class
                        boolean isMethodInCurrentClass = classDeclaration.getMethods().stream()
                                .anyMatch(classMethod -> classMethod.getNameAsString().equals(call.getNameAsString()));

                        if (isMethodInCurrentClass) {
                            // Print the called method name and cross-check the argument types
                            System.out.print("    " + call.getName() + " | Argument Types: ");
                            crossCheckArgumentTypes(call, method, methodDetails,mapOfMethods);
                        }
                    }
                }

                System.out.println(); // Blank line for readability
                // adding parent method to the map
                verifyIfMethodIsDuplicated(mapOfMethods,methodDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void verifyIfMethodIsDuplicated(Map<String, MethodDetails> mapOfMethods, MethodDetails methodDetails) {
        MethodDetails stored = mapOfMethods.get(getKey(methodDetails));
        if (mapOfMethods.containsKey(getKey(methodDetails))) {
            updateStored(stored,methodDetails);
//            System.out.println("Method is not added " + methodDetails.getName() + ", it has " + methodDetails.getNumOfParameters() + " parameters");
        } else {
            mapOfMethods.put(getKey(methodDetails), methodDetails);
        }
    }

    private static void updateStored(MethodDetails stored, MethodDetails newCreated) {
        if(!stored.isDetailConfigured()) {
            stored.setListOfParameterTypes(newCreated.getListOfParameterTypes());
            stored.setDetailConfigured(true);
            stored.setChildMethods(newCreated.getChildMethods());
        }
    }

    private static String getKey(MethodDetails methodDetails) {
        return methodDetails.getName() + "-" + methodDetails.getNumOfParameters();
    }

    private static void printParameterTypes(List<?> parameters, MethodDetails parentMethod) {
        if (parameters.isEmpty()) {
            System.out.print("None");
        } else {
            parentMethod.setNumOfParameters(parameters.size());
            for (int i = 0; i < parameters.size(); i++) {
                if (parameters.get(i) instanceof Parameter) {
                    // For method declarations: print the parameter types
                    System.out.print(((Parameter) parameters.get(i)).getType());
                    parentMethod.addParameter(((Parameter) parameters.get(i)).getType().asString());
                } else {
                    System.out.print("Unknown");
                }
                if (i < parameters.size() - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(); // Move to the next line after printing parameter types
    }

    private static void crossCheckArgumentTypes(MethodCallExpr methodCall, MethodDeclaration parentMethod, MethodDetails parentMethodToConfigure, Map<String,MethodDetails> methodDetailsMap) {
        List<Expression> arguments = methodCall.getArguments();
        MethodDetails childMethod = new MethodDetails(methodCall.getNameAsString());
        childMethod.setNumOfParameters(arguments.size());
        if (arguments.isEmpty()) {
            System.out.print("None");
        } else {
            for (int i = 0; i < arguments.size(); i++) {
                Expression argument = arguments.get(i);
                // Check if the argument correlates with a known variable in the parent method
                boolean typeMatched = false;

                if (argument.isNameExpr()) {
                    String argumentName = argument.asNameExpr().getNameAsString();
                    // Check the parent method's parameters or variables for a type match
                    if (parentMethod.getParameters().stream()
                            .anyMatch(param -> param.getNameAsString().equals(argumentName)
                                    && param.getType().toString().equals("String"))) { // Cross-check type (e.g., String)
                        typeMatched = true;
                    }
                }

                if (typeMatched) {
                    System.out.print("MatchedType");
                    childMethod.addParameter("MatchedType");
                } else {
                    String type = inferType(argument); // Infer type if no direct match is found
                    childMethod.addParameter(type);
                }

                if (i < arguments.size() - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(); // Move to the next line after printing the argument types
        verifyIfChildMethodIsInMap(methodDetailsMap,childMethod, parentMethodToConfigure);
    }

    private static void verifyIfChildMethodIsInMap(Map<String,MethodDetails> methodDetailsMap, MethodDetails child, MethodDetails parent) {
        MethodDetails storedChild = methodDetailsMap.get(getKey(child));
        if(storedChild != null) {
            parent.addChildMethod(storedChild);
        } else {
            // will let the parent reference child method that is easily referenced from the map
            methodDetailsMap.put(getKey(child),child);
            parent.addChildMethod(child);
        }
    }

    private static String inferType(Expression argument) {
        if (argument.isLiteralExpr()) {
            if (argument.asLiteralExpr().isStringLiteralExpr()) {
                System.out.print("StringLiteral");
                return "StringLiteral";
            } else if (argument.asLiteralExpr().isIntegerLiteralExpr()) {
                System.out.print("IntegerLiteral");
                return "IntegerLiteral";
            } else if (argument.asLiteralExpr().isBooleanLiteralExpr()) {
                System.out.print("BooleanLiteral");
                return "BooleanLiteral";
            } else {
                System.out.print("OtherLiteral");
                return "OtherLiteral";
            }
        } else if (argument.isMethodCallExpr()) {
            System.out.print("MethodCall");
            return "OtherLiteral";
        } else {
            System.out.print("Unknown");
            return "Unknown";
        }
    }
}
