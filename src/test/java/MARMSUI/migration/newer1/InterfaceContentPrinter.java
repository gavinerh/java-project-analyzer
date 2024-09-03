package MARMSUI.migration.newer1;

import MARMSUI.migration.model.Type;
import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterfaceContentPrinter {

    public static void execute(String filePath, Map<String,String> map) {
        try {
            FileInputStream in = new FileInputStream(filePath);
            CompilationUnit cu = StaticJavaParser.parse(in);
            cu.accept(new InterfaceVisitor(map), null);
            System.out.println(map.size());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class InterfaceVisitor extends VoidVisitorAdapter<Void> {
        private Map<String,String> map;
        public InterfaceVisitor(Map<String,String> map) {
            this.map = map;
        }
        @Override
        public void visit(ClassOrInterfaceDeclaration cid, Void arg) {
            super.visit(cid, arg);
            if (cid.isInterface()) {
                System.out.println("Interface Name: " + cid.getName());
                List<BodyDeclaration<?>> members = cid.getMembers();
                if (members != null) {
                    for (BodyDeclaration<?> member : members) {
                        if (member instanceof MethodDeclaration) {
                            MethodDeclaration md = (MethodDeclaration) member;
//                            System.out.println("Method: " + md.getDeclarationAsString());
                            map.put(md.getNameAsString(),md.getDeclarationAsString());
                        } else if (member instanceof FieldDeclaration) {
                            FieldDeclaration fd = (FieldDeclaration) member;
                            System.out.println("Field: " + fd);
                        }
                    }
                }
            }
        }
    }
}