package MARMSUI.methodcopier;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class PrintListOfMethodUsed {





    public static void execute(List<MethodSpec> methodSpecs, String javaFilePath) {
        // Method specifications to search for
//        List<MethodSpec> methodSpecs = Arrays.asList(
//                new MethodSpec("methodName1", Arrays.asList("int", "String")),
//                new MethodSpec("methodName2", Arrays.asList("double"))
//        );

        // Path to the Java source file
//        String javaFilePath = "/Users/macuser/Documents/updated-lsl-app/marms/MARMS/Source Code/Business Components/EJB/QualificationService/com/singaporeair/marms/abacus/business/customer/tier/QualificationServiceBean.java";

        try {
            // Parse the Java source file
            CompilationUnit compilationUnit = StaticJavaParser.parse(new File(javaFilePath));

            // Find the class or interface in the source file
            compilationUnit.findAll(ClassOrInterfaceDeclaration.class).forEach(classOrInterface -> {
                System.out.println("Class Name: " + classOrInterface.getName());
                // Find methods matching the provided specifications
                classOrInterface.findAll(MethodDeclaration.class).stream()
                        .filter(method -> matchesSpecification(method, methodSpecs))
                        .forEach(method -> {
                            System.out.println("\n\n");
                            System.out.println(method.getDeclarationAsString(true, true, true));
                            System.out.println(method.getBody().map(Object::toString).orElse("No body found"));
                            System.out.println("\n\n");
                        });
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Helper method to check if a method matches the given specifications
    private static boolean matchesSpecification(MethodDeclaration method, List<MethodSpec> methodSpecs) {
        return methodSpecs.stream().anyMatch(spec -> {
            boolean nameMatches = spec.name.equals(method.getNameAsString());
            boolean parametersMatch = method.getParameters().stream()
                    .map(p -> p.getType().asString())
                    .toList()
                    .equals(spec.argumentTypes);
            return nameMatches && parametersMatch;
        });
    }
}

// Class representing the method specification to search for
class MethodSpec {
    String name;
    List<String> argumentTypes;

    public MethodSpec(String name, List<String> argumentTypes) {
        this.name = name;
        this.argumentTypes = argumentTypes;
    }
}