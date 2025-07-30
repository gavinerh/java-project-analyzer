package MARMSUI.migration.GenerateDependentFieldsForTest;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PrintInterfaceTheClassImplements {
    public static List<String> getImplementedInterfaces(String classFilePath) throws Exception {
        File file = Paths.get(classFilePath).toFile();
        CompilationUnit cu = StaticJavaParser.parse(file);

        // Get the first class declaration (not interface, not enum)
        Optional<ClassOrInterfaceDeclaration> optClass = cu.findFirst(ClassOrInterfaceDeclaration.class, c -> !c.isInterface());

        if (optClass.isPresent()) {
            ClassOrInterfaceDeclaration clazz = optClass.get();
            // Return list of interface names implemented by this class
            return clazz.getImplementedTypes()
                    .stream()
                    .map(type -> type.getNameAsString())
                    .collect(Collectors.toList());
        }
        // Return empty list if no class found or it implements nothing
        return List.of();
    }

    public static void main(String[] args) throws Exception {

        List<String> interfaces = getImplementedInterfaces("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/AdminFeeImpl.java");
        if (interfaces.isEmpty()) {
            System.out.println("No interfaces implemented or no class found.");
        } else {
            System.out.println("Implemented interfaces:");
            interfaces.forEach(System.out::println);
        }
    }
}
