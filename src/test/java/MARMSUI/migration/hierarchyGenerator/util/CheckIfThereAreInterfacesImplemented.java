package MARMSUI.migration.hierarchyGenerator.util;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/QualificationServiceImpl.java";
public class CheckIfThereAreInterfacesImplemented {

    public static void main(String[] args) throws IOException {


        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/QualificationService.java";
        List<String> interfaces = execute(filePath);
        if (interfaces != null) {
            System.out.println("Implemented interfaces:");
            interfaces.forEach(System.out::println);
        } else {
            System.out.println("No interfaces implemented.");
        }
    }

    public static List<String> execute(String filePath) throws IOException {
        try {
            List<String> interfaces = getImplementedInterfaces(filePath);
            if (interfaces.isEmpty()) {
                return null;
            } else {
                return interfaces;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static List<String> getImplementedInterfaces(String filePath) throws IOException {
        FileInputStream in = new FileInputStream(filePath);
        CompilationUnit cu = StaticJavaParser.parse(in);

        InterfaceCollector collector = new InterfaceCollector();
        cu.accept(collector, null);

        return collector.getImplementedInterfaces();
    }

    private static class InterfaceCollector extends VoidVisitorAdapter<Void> {
        private final List<String> implementedInterfaces = new ArrayList<>();

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            super.visit(n, arg);

            if (n.isInterface()) {
                return;
            }

            n.getImplementedTypes().forEach(type -> implementedInterfaces.add(type.getNameAsString()));
        }

        public List<String> getImplementedInterfaces() {
            return implementedInterfaces;
        }
    }
}