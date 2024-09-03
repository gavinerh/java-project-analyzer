package MARMSUI.migration.newer1;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GetParentClassName {

    public static String execute(String classFilePath) {
         try {
            FileInputStream in = new FileInputStream(classFilePath);
            ParseResult<CompilationUnit> parseResult = new JavaParser().parse(in);

            if (parseResult.isSuccessful() && parseResult.getResult().isPresent()) {
                CompilationUnit cu = parseResult.getResult().get();
                ClassVisitor classVisitor = new ClassVisitor();
                cu.accept(classVisitor, null);
                String parentClass = classVisitor.getParentClass();
                return parentClass;
            } else {
                System.out.println("Failed to parse the provided file.");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         return null;
    }

    private static class ClassVisitor extends VoidVisitorAdapter<Void> {
        private String parentClass;

        @Override
        public void visit(ClassOrInterfaceDeclaration cid, Void arg) {
            super.visit(cid, arg);
            if (cid.getExtendedTypes().size() > 0) {
                parentClass = cid.getExtendedTypes(0).getNameAsString();
            } else {
                System.out.println("Class: " + cid.getNameAsString() + " does not extend any class.");
            }
        }

        public String getParentClass() {
            return parentClass;
        }

    }
}