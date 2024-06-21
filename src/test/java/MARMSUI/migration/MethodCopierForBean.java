package MARMSUI.migration;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class MethodCopierForBean {


    public static void main(String[] args) throws Exception {
        Map<String, Integer> innerMethodCallNames = new HashMap<>();
        Map<String, Integer> methodsAlreadyPresent = new HashMap<>();

        String destinationFilePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/VoucherServiceImpl.java";
        String originFilePath = "/Users/macuser/Documents/marms/MARMS/Source Code/Business Components/EJB/VoucherInfoService/com/singaporeair/marms/abacus/business/voucherinfo/VoucherInfoServiceBean.java";

        extractInnerMethodCalls(destinationFilePath, methodsAlreadyPresent, innerMethodCallNames);
        System.out.println(methodsAlreadyPresent);
        // inner method calls variable will after this cleansing contain only relevant methods
        removeMethodsThatAreAleadyPresent(methodsAlreadyPresent, innerMethodCallNames);
        System.out.println(innerMethodCallNames.size());

        // extract methods identified in destination file from the origin file
        for(String methodName : innerMethodCallNames.keySet()) {
            int paramSize = innerMethodCallNames.get(methodName);
            CopyRelevantMethodsToBringOver.execute(methodName,originFilePath, paramSize);
        }
    }

    private static void removeMethodsThatAreAleadyPresent(Map<String, Integer> methodsAlreadyPresent, Map<String, Integer> innerMethodCalls) {
        for (String key : methodsAlreadyPresent.keySet()) {
            if (innerMethodCalls.containsKey(key)) {
                if (innerMethodCalls.get(key).intValue() == methodsAlreadyPresent.get(key).intValue()) {
                    innerMethodCalls.remove(key);
                }
            }
        }
    }

    private static void extractInnerMethodCalls(String filePath, Map<String, Integer> alreadyPresent, Map<String, Integer> innerMtd) throws FileNotFoundException {
        // Path to the Java file you want to analyze

        // Set up a type solver for JavaParser to resolve types
        CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver();
        combinedTypeSolver.add(new ReflectionTypeSolver());
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinedTypeSolver);
        StaticJavaParser.getParserConfiguration().setSymbolResolver(symbolSolver);

        // Parse the Java file
        FileInputStream in = new FileInputStream(filePath);
        CompilationUnit cu = StaticJavaParser.parse(in);

        // Visit and analyze method calls
        cu.accept(new MethodCallVisitor(alreadyPresent, innerMtd), null);
    }


    private static class MethodCallVisitor extends VoidVisitorAdapter<Void> {
        private Map<String, Integer> methodsAlreadyPresent;
        private Map<String, Integer> innerMethods;

        public MethodCallVisitor(Map<String, Integer> methodsAlreadyPresent, Map<String, Integer> innerMethodCalls) {
            this.methodsAlreadyPresent = methodsAlreadyPresent;
            this.innerMethods = innerMethodCalls;
        }

        @Override
        public void visit(ClassOrInterfaceDeclaration n, Void arg) {
            // Store the current class name
            String currentClassName = n.getNameAsString();
            super.visit(n, arg);
        }

        @Override
        public void visit(MethodDeclaration n, Void arg) {
            super.visit(n, arg);
            if (methodsAlreadyPresent != null) {
                methodsAlreadyPresent.put(n.getNameAsString(), n.getParameters().size());
            }
        }

        @Override
        public void visit(MethodCallExpr methodCall, Void arg) {
            super.visit(methodCall, arg);

            // Determine if it's an inner method call
            boolean isInnerMethodCall = methodCall.getScope().isEmpty() || methodCall.getScope().get().isThisExpr();
            if (isInnerMethodCall && innerMethods != null)
                innerMethods.put(methodCall.getNameAsString(), methodCall.getArguments().size());

            // Print parameter types
        }
    }
}


