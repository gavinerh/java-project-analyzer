package MARMSUI.migration.mapAllMethodsInProj.util;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClassTypeDetector {

    /**
     * Analyzes a Java file and returns a string describing the type
     * 
     * @param filePath Path to the Java file
     * @return A string indicating whether the class is "concrete", "abstract", "interface", etc.
     */
    public static String getClassType(String filePath) {
        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(new File(filePath));
            
            // Get the first class or interface declaration (assuming one per file)
            if (compilationUnit.findFirst(ClassOrInterfaceDeclaration.class).isPresent()) {
                ClassOrInterfaceDeclaration declaration = compilationUnit.findFirst(ClassOrInterfaceDeclaration.class).get();
                
                if (declaration.isInterface()) {
                    return "interface";
                } else if (declaration.isAbstract()) {
                    return "abstract";
                } else {
                    return "concrete";
                }
            } else {
                // Could be an enum, annotation, or other type
                return "other";
            }
            
        } catch (FileNotFoundException e) {
            return "error: file not found";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
    
    /**
     * Overloaded method to handle a class declaration directly
     * 
     * @param declaration The ClassOrInterfaceDeclaration to analyze
     * @return A string indicating the type
     */
    public static String getClassType(ClassOrInterfaceDeclaration declaration) {
        if (declaration.isInterface()) {
            return "interface";
        } else if (declaration.isAbstract()) {
            return "abstract";
        } else {
            return "class";
        }
    }
    
    /**
     * Analyzes Java source code content directly
     * 
     * @param javaCode The Java source code as a string
     * @return A string indicating the type
     */
    public static String getClassTypeFromCode(String javaCode) {
        try {
            CompilationUnit compilationUnit = StaticJavaParser.parse(javaCode);
            
            if (compilationUnit.findFirst(ClassOrInterfaceDeclaration.class).isPresent()) {
                ClassOrInterfaceDeclaration declaration = compilationUnit.findFirst(ClassOrInterfaceDeclaration.class).get();
                return getClassType(declaration);
            } else {
                return "other";
            }
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
    
    public static void main(String[] args) {
        // Example usage
        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/AdminFee.java";
        System.out.println("Class type: " + getClassType(filePath));
        
        // To analyze multiple files in a directory
        // File dir = new File("src/main/java/your/package");
        // for (File file : dir.listFiles((d, name) -> name.endsWith(".java"))) {
        //     System.out.println(file.getName() + ": " + getClassType(file.getPath()));
        // }
    }
}