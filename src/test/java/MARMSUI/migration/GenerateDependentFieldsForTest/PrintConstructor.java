package MARMSUI.migration.GenerateDependentFieldsForTest;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.Parameter;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PrintConstructor {
    public static List<List<String>> getConstructorParameterTypes(String classFilePath) throws Exception {
        File file = Paths.get(classFilePath).toFile();
        CompilationUnit cu = StaticJavaParser.parse(file);

        // Get first top-level class (not interface, not enum)
        Optional<ClassOrInterfaceDeclaration> optClass = cu.findFirst(ClassOrInterfaceDeclaration.class, c -> !c.isInterface());
        if (!optClass.isPresent()) {
            return List.of();
        }
        ClassOrInterfaceDeclaration clazz = optClass.get();

        // For each constructor, collect list of parameter types in order
        return clazz.getConstructors().stream()
                .map((ConstructorDeclaration ctor) -> ctor.getParameters().stream()
                        .map((Parameter param) -> param.getType().asString())
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws Exception {

        List<List<String>> typesPerConstructor = getConstructorParameterTypes("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/util/TimeService.java");
        if(typesPerConstructor.isEmpty()) {
            System.out.println("No constructors or class found.");
        } else {
            int idx = 1;
            for(List<String> paramTypes : typesPerConstructor) {
                System.out.println("Constructor " + idx + " parameter types: " + paramTypes);
                idx++;
            }
        }
    }

}
