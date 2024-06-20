package MARMSUI.fieldTracker;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

public class MethodCallTracker {
    public static void main(String[] args) throws IOException {
//        FileInputStream in = new FileInputStream("/Users/macuser/Documents/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/service/impl/UpdateCustomerSelectiveServiceImpl");
        CompilationUnit cu = StaticJavaParser.parse(Paths.get("/Users/macuser/Documents/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/service/impl/UpdateCustomerSelectiveServiceImpl.java"));

//        cu.accept(new VoidVisitorAdapter<Void>() {
//            @Override
//            public void visit(MethodDeclaration md, Void arg) {
//                super.visit(md, arg);
//                System.out.println("Method found: " + md.getName());
//
//                // Optionally, print more details about the method
//                System.out.println("Return Type: " + md.getType());
//                System.out.println("Parameters: " + md.getParameters());
//                System.out.println("Modifiers: " + md.getModifiers());
//                // Add any other details you're interested in here
//            }
//        }, null);

        CombinedTypeSolver combinedSolver = new CombinedTypeSolver();
        combinedSolver.add(new ReflectionTypeSolver());
        combinedSolver.add(new JavaParserTypeSolver(new File("/Users/macuser/Documents/lsl-marmsui-profile/src/main/java")));
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedSolver);
        StaticJavaParser.getParserConfiguration().setSymbolResolver(symbolSolver);
        cu.accept(new VoidVisitorAdapter<Void>() {
            @Override
            public void visit(MethodDeclaration md, Void arg) {
                super.visit(md, arg);
                System.out.println("Method found: " + md.getName());

                for (Parameter param : md.getParameters()) {
                    System.out.print("Parameter: " + param.getName() + " - Type: ");
                    try {
                        // Attempt to resolve the parameter type to its fully qualified name
                        ResolvedType resolvedType = param.getType().resolve();
                        System.out.println(resolvedType.describe());
                    } catch (Exception e) {
                        // If resolution fails, fall back to the simple name
                        System.out.println(param.getType().asString());
                        System.out.println(" (Note: Full resolution may require setting up a type solver)");
                    }
                }
            }
        }, null);

    }
}
