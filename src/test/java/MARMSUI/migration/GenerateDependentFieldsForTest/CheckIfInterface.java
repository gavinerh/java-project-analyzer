package MARMSUI.migration.GenerateDependentFieldsForTest;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Optional;

public class CheckIfInterface {
    public static boolean isInterface(String classFilePath) {
        try {
            File file = Paths.get(classFilePath).toFile();
            CompilationUnit cu = StaticJavaParser.parse(file);

            // Find the first ClassOrInterfaceDeclaration (top-level type)
            Optional<ClassOrInterfaceDeclaration> coidOpt = cu.findFirst(ClassOrInterfaceDeclaration.class);
            if (coidOpt.isPresent()) {
                ClassOrInterfaceDeclaration decl = coidOpt.get();
                return decl.isInterface();
            }
            return false; // No class/interface found
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            return false; // File not found, cannot determine if it's an interface
        } catch (Exception e) {
            System.err.println("An error occurred while checking if the file is an interface: " + e.getMessage());
            System.out.println("error is in file: " + classFilePath);
            return false; // Error occurred, cannot determine if it's an interface
        }
    }

    public static void main(String[] args) throws Exception {
        boolean result = isInterface("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/helper/CustomerInfoHelper.java");
        System.out.println(result);
    }
}
