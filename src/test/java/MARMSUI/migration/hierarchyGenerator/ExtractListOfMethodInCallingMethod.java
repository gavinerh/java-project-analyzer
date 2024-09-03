package MARMSUI.migration.hierarchyGenerator;

import MARMSUI.migration.hierarchyGenerator.model.MethodCallDetails;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/QualificationServiceImpl.java";
//        String methodName = "getDetailsFromServerAndInitialize";

public class ExtractListOfMethodInCallingMethod {

    public static void main(String[] args) {
        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/QualificationServiceImpl.java";
        String methodName = "getDetailsFromServer";

        try {
            List<MARMSUI.migration.hierarchyGenerator.model.MethodDeclaration> methodCallDetails = execute(filePath, methodName);
            methodCallDetails.forEach(detail -> {
                if (detail.methodCallDetails.getFieldName() != null) {
                    System.out.println("Method called: " + detail.methodCallDetails.getMethodName() + " on field: " + detail.methodCallDetails.getFieldName() + " of type: " + detail.methodCallDetails.getFieldType());
                } else {
                    System.out.println("Method called: " + detail.methodCallDetails.getMethodName());
                }
                System.out.println("Number of parameters: " + detail.methodCallDetails.getMethodCallExpr().getArguments().size());
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<MARMSUI.migration.hierarchyGenerator.model.MethodDeclaration> execute(String currentFilePath, String methodNameUnderInspection) throws IOException {
        List<MethodCallDetails> methodCallDetails = analyzeMethodCalls(currentFilePath,methodNameUnderInspection);
        List<MARMSUI.migration.hierarchyGenerator.model.MethodDeclaration> methodDeclarations = new ArrayList<>();
        for(MethodCallDetails detail : methodCallDetails) {
            MARMSUI.migration.hierarchyGenerator.model.MethodDeclaration declaration = new MARMSUI.migration.hierarchyGenerator.model.MethodDeclaration(null,detail.getMethodCallExpr().getArguments().size());
            declaration.methodName = detail.getMethodName();
            declaration.variableName = detail.getFieldName();
            declaration.variableType = detail.getFieldType();
            declaration.methodCallDetails = detail;
            methodDeclarations.add(declaration);
        }
        return methodDeclarations;
    }

    public static List<MethodCallDetails> analyzeMethodCalls(String directoryPath, String methodName) throws IOException {
        Map<String, MethodDeclaration> methodDeclarations = new HashMap<>();
        Map<String, FieldDeclaration> fieldDeclarations = new HashMap<>();

        // Parse all Java files in the directory
        try (Stream<Path> paths = Files.walk(Paths.get(directoryPath))) {
            List<Path> javaFiles = paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".java"))
                    .collect(Collectors.toList());

            for (Path javaFile : javaFiles) {
                FileInputStream in = new FileInputStream(javaFile.toFile());
                CompilationUnit cu = StaticJavaParser.parse(in);

                MethodAndFieldCollector collector = new MethodAndFieldCollector();
                cu.accept(collector, null);
                methodDeclarations.putAll(collector.getMethodDeclarations());
                fieldDeclarations.putAll(collector.getFieldDeclarations());
            }
        }

        // Find and collect method calls in the specified method
        List<MethodCallDetails> methodCallDetailsList = new ArrayList<>();
        for (MethodDeclaration methodDeclaration : methodDeclarations.values()) {
            if (methodDeclaration.getNameAsString().equals(methodName)) {
                MethodCallCollector methodCallCollector = new MethodCallCollector(methodDeclarations, fieldDeclarations);
                methodDeclaration.accept(methodCallCollector, null);
                methodCallDetailsList.addAll(methodCallCollector.getCalledMethods());
                break;
            }
        }

        return methodCallDetailsList;
    }

    private static class MethodAndFieldCollector extends VoidVisitorAdapter<Void> {
        private final Map<String, MethodDeclaration> methodDeclarations = new HashMap<>();
        private final Map<String, FieldDeclaration> fieldDeclarations = new HashMap<>();

        @Override
        public void visit(MethodDeclaration n, Void arg) {
            super.visit(n, arg);
            methodDeclarations.put(n.getNameAsString(), n);
        }

        @Override
        public void visit(FieldDeclaration n, Void arg) {
            super.visit(n, arg);
            n.getVariables().forEach(var -> fieldDeclarations.put(var.getNameAsString(), n));
        }

        public Map<String, MethodDeclaration> getMethodDeclarations() {
            return methodDeclarations;
        }

        public Map<String, FieldDeclaration> getFieldDeclarations() {
            return fieldDeclarations;
        }
    }

    private static class MethodCallCollector extends VoidVisitorAdapter<Void> {
        private final Map<String, MethodDeclaration> methodDeclarations;
        private final Map<String, FieldDeclaration> fieldDeclarations;
        private final List<MethodCallDetails> calledMethods = new ArrayList<>();

        public MethodCallCollector(Map<String, MethodDeclaration> methodDeclarations, Map<String, FieldDeclaration> fieldDeclarations) {
            this.methodDeclarations = methodDeclarations;
            this.fieldDeclarations = fieldDeclarations;
        }

        @Override
        public void visit(MethodCallExpr n, Void arg) {
            super.visit(n, arg);

            // Check if the method call is to a field variable or an inner method
            if (n.getScope().isPresent()) {
                String scopeName = n.getScope().get().toString();
                if (fieldDeclarations.containsKey(scopeName)) {
                    FieldDeclaration fieldDeclaration = fieldDeclarations.get(scopeName);
                    String fieldType = fieldDeclaration.getElementType().asString();
                    calledMethods.add(new MethodCallDetails(n, scopeName, fieldType));
                } else if (methodDeclarations.containsKey(scopeName)) {
                    calledMethods.add(new MethodCallDetails(n, null, null));
                }
            } else {
                // Direct call to an inner method
                calledMethods.add(new MethodCallDetails(n, null, null));
            }
        }

        public List<MethodCallDetails> getCalledMethods() {
            return calledMethods;
        }
    }

}