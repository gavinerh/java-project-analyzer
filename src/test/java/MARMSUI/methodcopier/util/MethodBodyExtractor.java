package MARMSUI.methodcopier.util;

import MARMSUI.methodcopier.model.MethodSignature;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.Type;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MethodBodyExtractor {

    public static void main(String[] args) {
        // Sample list of methods to search for
        // Each method should be represented as a pair of (methodName, parameterTypes)
        // Example: method "calculate" with parameters (int, String)
        List<MethodSignature> methodsToFind = Arrays.asList(
                new MethodSignature("forceQualify", Arrays.asList("QualRequest")));

        // Path to the Java source file you want to parse
        String sourceFilePath = "/Users/macuser/Documents/updated-lsl-app/marms/MARMS/Source Code/Business Components/EJB/QualificationService/com/singaporeair/marms/abacus/business/customer/tier/QualificationServiceBean.java";

//        String sourceFilePath = "src/main/java/com/example/MyClass.java";

        // Path to the existing Java file to append methods into
        String targetFilePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/codeupdate/QualificationServiceImpl.java";

        execute(sourceFilePath,targetFilePath,methodsToFind);
    }

    public static void execute(String sourceFilePath, String targetFilePath,List<MethodSignature> methodsToFind){
        try {
            // Parse the source Java file
            CompilationUnit sourceCU = StaticJavaParser.parse(new File(sourceFilePath));

            // Parse the target file where methods will be appended
            CompilationUnit targetCU = StaticJavaParser.parse(new File(targetFilePath));

            // Append the found methods to the target Java file
            appendMethodsToExistingClass(sourceCU, methodsToFind, targetCU, targetFilePath);
            System.out.println("Methods have been appended to the class in: " + targetFilePath);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    private static void printFullMethodDeclaration(CompilationUnit cu, MethodSignature signature) {
        Optional<MethodDeclaration> methodOpt = cu.findFirst(MethodDeclaration.class, method -> {
            // Check if method name matches
            if (!method.getNameAsString().equals(signature.getMethodName())) {
                return false;
            }

            // Check if parameter types match
            List<String> methodParamTypes = new ArrayList<>();
            method.getParameters().forEach(param -> methodParamTypes.add(param.getType().asString()));

            return methodParamTypes.equals(signature.getParameterTypes());
        });

        if (methodOpt.isPresent()) {
            MethodDeclaration method = methodOpt.get();
            // Print the entire method declaration including the body
            System.out.println(method);
            System.out.println("--------------------------------------------------");
        } else {
            System.out.println("// Method not found: " + signature.getMethodName() + signature.getParameterTypes());
            System.out.println("--------------------------------------------------");
        }
    }

    /**
     * Finds methods matching the provided signatures in the source file
     * and appends them to the target file's class.
     *
     * @param sourceCU       The CompilationUnit representing the source Java file.
     * @param methodSignatures The list of method signatures to find in the source file.
     * @param targetCU       The CompilationUnit representing the target Java file.
     * @param targetFilePath The path to the target Java file to update.
     * @throws IOException If there is an error writing to the file.
     */
    private static void appendMethodsToExistingClass(CompilationUnit sourceCU, List<MethodSignature> methodSignatures, CompilationUnit targetCU, String targetFilePath) throws IOException {
        // Find the class in the target file
        Optional<ClassOrInterfaceDeclaration> targetClassOpt = targetCU.findFirst(ClassOrInterfaceDeclaration.class);

        if (targetClassOpt.isEmpty()) {
            throw new IllegalStateException("No class found in the target file to append methods to.");
        }

        ClassOrInterfaceDeclaration targetClass = targetClassOpt.get();

        // Search for each specified method in the source file
        for (MethodSignature signature : methodSignatures) {
            Optional<MethodDeclaration> methodOpt = sourceCU.findFirst(MethodDeclaration.class, method -> {
                // Check if method name matches
                if (!method.getNameAsString().equals(signature.getMethodName())) {
                    return false;
                }

                // Check if parameter types match
                List<String> methodParamTypes = new ArrayList<>();
                method.getParameters().forEach(param -> methodParamTypes.add(param.getType().asString()));

                return methodParamTypes.equals(signature.getParameterTypes());
            });

            // If the method is found, add it to the target class
            if (methodOpt.isPresent()) {
                MethodDeclaration method = methodOpt.get();

                // Add the method to the target class
                targetClass.addMember(method);
            } else {
                System.err.println("Method not found: " + signature.getMethodName() + signature.getParameterTypes());
            }
        }

        // Write the updated target compilation unit back to the Java file
        try (FileWriter fileWriter = new FileWriter(targetFilePath)) {
            fileWriter.write(targetCU.toString());
        }
    }

    /**
     * A class to represent a method signature.
     */

}