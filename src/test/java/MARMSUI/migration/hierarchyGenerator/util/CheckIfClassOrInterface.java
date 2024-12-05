package MARMSUI.migration.hierarchyGenerator.util;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

//String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/QualificationService.java";
public class CheckIfClassOrInterface {

    // prints interface or class
    public static void main(String[] args) {
        String val = execute("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/controller/ReserveValController.java");
        System.out.println(val);
    }
    public static String execute(String filePath) {
//        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/QualificationService.java";
        try {
            Optional<String> type = getType(filePath);
            if(!type.isEmpty()) {
                return type.get();
            } else {
                throw new RuntimeException("Type is invalid");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Optional<String> getType(String filePath) throws IOException {
        FileInputStream in = new FileInputStream(filePath);
        CompilationUnit cu = StaticJavaParser.parse(in);

        TypeVisitor visitor = new TypeVisitor();
        cu.accept(visitor, null);

        return visitor.getType();
    }

    private static class TypeVisitor extends VoidVisitorAdapter<Void> {
        private String type;

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            super.visit(n, arg);

            if (n.isInterface()) {
                type = "interface";
            } else {
                type = "class";
            }
        }

        public Optional<String> getType() {
            return Optional.ofNullable(type);
        }
    }
}