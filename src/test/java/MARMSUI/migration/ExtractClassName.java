package MARMSUI.migration;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExtractClassName {
    public static String extract(FileInputStream in) throws Exception {
        CompilationUnit cu = StaticJavaParser.parse(in);

        // List to hold class names
        List<String> classNames = new ArrayList<>();

        // Visit and add the names of all classes in the file to the list
        cu.accept(new ClassVisitor(), classNames);

        // Close the file input stream
        in.close();
        return classNames.get(0);

    }

    /**
     * Simple visitor implementation for visiting class declarations.
     */
    private static class ClassVisitor extends VoidVisitorAdapter<List<String>> {
        @Override
        public void visit(ClassOrInterfaceDeclaration n, List<String> classNames) {
            // Add the class name to the list
            classNames.add(n.getNameAsString());
            // Don't forget to call super, it makes sure you visit all nodes in the AST
            super.visit(n, classNames);
        }
    }
}
