package MARMSUI.migration.mapAllMethodsInProj.util;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.File;
import java.util.Optional;
import java.util.stream.Collectors;

public class InterfaceImplementationChecker {

    /**
     * Checks if a class implements any interfaces and returns their names.
     * 
     * @param javaFilePath Path to the Java file to analyze
     * @return String containing interface names if implemented, null otherwise
     */
    public static String getImplementedInterfaces(String javaFilePath) {
        try {
            // Parse the Java file
            CompilationUnit compilationUnit = StaticJavaParser.parse(new File(javaFilePath));
            
            // Find the first class declaration (assuming one class per file)
            Optional<ClassOrInterfaceDeclaration> classDeclaration = compilationUnit.findFirst(ClassOrInterfaceDeclaration.class, 
                    declaration -> !declaration.isInterface());
            
            if (classDeclaration.isPresent()) {
                ClassOrInterfaceDeclaration cls = classDeclaration.get();
                
                // Check if the class implements any interfaces
                if (cls.getImplementedTypes().isEmpty()) {
                    return null;
                } else {
                    // Join all interface names with commas
                    return cls.getImplementedTypes().stream()
                            .map(type -> type.getNameAsString())
                            .collect(Collectors.joining(", "));
                }
            } else {
                // No class found
                return null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Example usage
    public static void main(String[] args) {
        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/AdminFeeImpl.java";
        String interfaces = getImplementedInterfaces(filePath);
        System.out.println(interfaces != null ? 
                "Class implements: " + interfaces : 
                "Class does not implement any interfaces");
    }
}