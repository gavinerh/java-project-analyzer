package MARMSUI;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;

public class PackageMethodCallFinder extends VoidVisitorAdapter<Void> {
    private final String TARGET_PACKAGE = "MARMSUI.migration";  // change this to your package

//    @Override
//    public void visit(MethodCallExpr n, Void arg) {
//        n.getScope().ifPresent(scope -> {
//            try {
//                ResolvedType scopeType = scope.calculateResolvedType();
//                if (scopeType.describe().startsWith(TARGET_PACKAGE)) {
//                    System.out.println("Method Call Detected in package: " + n.getName().asString());
//                }
//            } catch (RuntimeException e) {
//                // Log exception or handle it suitable to your requirements
//                e.printStackTrace();
//                throw new RuntimeException();
//            }
//        });
//        super.visit(n, arg);
//    }
//
//    public static void main(String[] args) throws FileNotFoundException {
//        TypeSolver typeSolver = new ReflectionTypeSolver();
//        ParserConfiguration parserConfiguration = new ParserConfiguration()
//                .setSymbolResolver(new JavaSymbolSolver(typeSolver));
//        StaticJavaParser.setConfiguration(parserConfiguration);
//
//        FileInputStream in = new FileInputStream("/Users/macuser/eclipse-workspace/java-reflections/src/test/java/MARMSUI/migration/CopyOnlyNonDuplicates.java");
//        CompilationUnit cu = StaticJavaParser.parse(in);
//
//        // Visit and print methods calls within the package
//        new PackageMethodCallFinder().visit(cu, null);
//    }

    static Set<String> variables = new HashSet<>();

    @Override
    public void visit(FieldDeclaration n, Void arg) {
        super.visit(n, arg);
        n.getVariables().forEach(var -> {
            System.out.println(var.getName());
            variables.add(var.getName().asString());
        });
    }

    @Override
    public void visit(MethodDeclaration n, Void arg) {
        super.visit(n, arg);
        System.out.println("Inspecting method: " + n.getName().asString());
        n.accept(new MethodCallVisitor(), null);
    }

    static class MethodCallVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(MethodCallExpr n, Void arg) {
            String scope = n.getScope().isPresent() ? n.getScope().get().toString() : null;
            if (scope == null || scope != null && variables.contains(scope)) {
                System.out.println("variable name: " + scope);
                System.out.println("Method Call Detected: " + n.getName().asString());
            } else {
            }
            super.visit(n, arg);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream in = new FileInputStream("/Users/macuser/Documents/updated-lsl-app/lsl-customer/src/main/java/com/sg/sq/lsl/service/impl/ApiValidationServiceImpl.java");
        CompilationUnit cu = StaticJavaParser.parse(in);

        // Visit and inspect the specified method
        new PackageMethodCallFinder().visit(cu, null);
    }


}