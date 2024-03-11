import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class TestingPurposes {


    public static void main(String[] args) throws IOException {
        CompilationUnit cu = StaticJavaParser.parse(Paths.get("/Users/macuser/Documents/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/database/sql/persistence/mappers/CusAccountMapper.java"));

        // Get the types in the source file
        NodeList<TypeDeclaration<?>> types = cu.getTypes();


        String fullDeclarations = "";
        for (TypeDeclaration<?> type : types) {
            // Get the methods within this type
            List<MethodDeclaration> methods = type.getMethods();
            List<FieldDeclaration> fields = type.getFields();

            if(fields.isEmpty()) {
                System.out.println("Empty fields");
            }
            for (FieldDeclaration fieldDeclaration : fields) {
                NodeList<VariableDeclarator> variableDeclarators = fieldDeclaration.getVariables();

                variableDeclarators.forEach(x -> {
                    System.out.println(x.getName());
                    System.out.println(x.getType());
                });

            }

//            for (MethodDeclaration method : methods) {
//                fullDeclarations += method.getDeclarationAsString();
//                NodeList<Parameter> params = method.getParameters();
//                System.out.println(method.getTypeAsString());
//                System.out.println(method.getName());
//                System.out.println(method.getNameAsString());
////                printParams(params);
//                fullDeclarations += ";";
//            }
        }
        System.out.println(fullDeclarations);
        try {

        } catch (Exception e) {
            System.out.println(e);
//            e.printStackTrace();
        }
    }

    private static void printParams(NodeList<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            System.out.println(String.format("type: %s", parameter.getType()));
        }
    }

    private static void test() throws Exception {
        System.out.println("Hello");
        throw new Exception("No particular error");
    }


}