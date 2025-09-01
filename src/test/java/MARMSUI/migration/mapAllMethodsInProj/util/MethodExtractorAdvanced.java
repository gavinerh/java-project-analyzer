package MARMSUI.migration.mapAllMethodsInProj.util;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class MethodExtractorAdvanced {

    /**
     * Main method to analyze a Java file
     */
    public static ClassAnalysisResult analyzeClass(String filePath, String projectRoot) {
        try {
            // Set up symbol solver for type resolution
            CombinedTypeSolver typeSolver = configureTypeSolver(projectRoot);
            JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
            StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);

            // Parse the Java file
            CompilationUnit cu = StaticJavaParser.parse(new FileInputStream(filePath));

            // Get the class name and package
            String className = extractClassName(cu);
            String packageName = extractPackageName(cu);

            // Get all fields in the class for reference
            Map<String, FieldInfo> fields = extractClassFields(cu, packageName);

            // Create the class analysis result object
            ClassAnalysisResult result = new ClassAnalysisResult(className, packageName);

            // Extract all method declarations
            List<MethodDeclaration> methods = extractMethods(cu);

            // Analyze each method
            analyzeMethodsAndAddToResult(methods, fields, result);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return new ClassAnalysisResult("ERROR", "");
        }
    }

    /**
     * Configure and set up the type solver with reflection and Java parser
     */
    private static CombinedTypeSolver configureTypeSolver(String projectRoot) {
        CombinedTypeSolver typeSolver = new CombinedTypeSolver();
        typeSolver.add(new ReflectionTypeSolver());
        typeSolver.add(new JavaParserTypeSolver(new File(projectRoot)));
        return typeSolver;
    }

    /**
     * Extract class name from compilation unit
     */
    private static String extractClassName(CompilationUnit cu) {
        return cu.findFirst(com.github.javaparser.ast.body.ClassOrInterfaceDeclaration.class)
                .map(c -> c.getNameAsString())
                .orElse("UnknownClass");
    }

    /**
     * Extract package name from compilation unit
     */
    private static String extractPackageName(CompilationUnit cu) {
        if (cu.getPackageDeclaration().isPresent()) {
            return cu.getPackageDeclaration().get().getNameAsString();
        } else {
            return "";
        }
    }

    /**
     * Extract all fields from the class with their types
     */
    private static Map<String, FieldInfo> extractClassFields(CompilationUnit cu, String packageName) {
        Map<String, FieldInfo> fields = new HashMap<>();
        cu.findAll(FieldDeclaration.class).forEach(field -> {
            field.getVariables().forEach(variable -> {
                String typeName = field.getElementType().asString();
                boolean isProjectType = isProjectType(typeName, packageName);
                fields.put(variable.getNameAsString(), new FieldInfo(typeName, isProjectType));
            });
        });
        return fields;
    }

    /**
     * Extract all method declarations from the compilation unit
     */
    private static List<MethodDeclaration> extractMethods(CompilationUnit cu) {
        List<MethodDeclaration> methods = new ArrayList<>();
        cu.findAll(MethodDeclaration.class).forEach(methods::add);
        return methods;
    }

    /**
     * Analyze all methods and add their analysis results to the class result
     */
    private static void analyzeMethodsAndAddToResult(List<MethodDeclaration> methods,
                                                     Map<String, FieldInfo> fields, ClassAnalysisResult result) {
        for (MethodDeclaration method : methods) {
            MethodAnalysisResult methodResult = new MethodAnalysisResult(
                    method.getNameAsString(),
                    method.getSignature().getParameterTypes().toString()
            );

            // Create a visitor to find method calls within this method
            MethodCallVisitor visitor = new MethodCallVisitor(fields);
            method.accept(visitor, null);

            // Get the results
            methodResult.setInternalMethodCalls(visitor.getInternalMethodCalls());
            methodResult.setProjectFieldMethodCalls(visitor.getProjectFieldMethodCalls());

            // Add to class result
            result.addMethodResult(methodResult);
        }
    }

    /**
     * Determine if a type is likely from the project rather than a standard library
     */
    private static boolean isProjectType(String typeName, String packageName) {
        List<String> commonExternalPackages = getCommonExternalPackages();

        // Check for primitive types
        if (isPrimitiveType(typeName)) {
            return false;
        }

        // If the type starts with the same package as the current class, it's likely from our project
        if (!packageName.isEmpty() && typeName.startsWith(packageName)) {
            return true;
        }

        // Check if it's from a common external package
        for (String externalPackage : commonExternalPackages) {
            if (typeName.startsWith(externalPackage)) {
                return false;
            }
        }

        // If there are no package indicators, it might be a project class
        if (!typeName.contains(".")) {
            return true;
        }

        // For everything else, assume it could be from the project
        return true;
    }

    /**
     * Get a list of common external package prefixes
     */
    private static List<String> getCommonExternalPackages() {
        return Arrays.asList(
                "java.", "javax.", "com.sun.", "sun.", "org.xml.", "org.w3c.",
                "org.omg.", "org.ietf.", "org.jcp.", "org.junit.", "org.mockito.",
                "org.apache.", "org.springframework.", "com.google.", "io.netty.",
                "lombok"
        );
    }

    /**
     * Check if a type name is a primitive Java type
     */
    private static boolean isPrimitiveType(String typeName) {
        return typeName.equals("int") || typeName.equals("boolean") || typeName.equals("char") ||
                typeName.equals("byte") || typeName.equals("short") || typeName.equals("long") ||
                typeName.equals("float") || typeName.equals("double") || typeName.equals("void");
    }

    /**
     * Class to store field type information
     */
    public static class FieldInfo {
        private String typeName;
        private boolean isProjectType;

        public FieldInfo(String typeName, boolean isProjectType) {
            this.typeName = typeName;
            this.isProjectType = isProjectType;
        }

        public String getTypeName() {
            return typeName;
        }

        public boolean isProjectType() {
            return isProjectType;
        }
    }

    /**
     * Class to represent a method call with parameter count
     */
    public static class MethodCallInfo {
        private String methodName;
        private int parameterCount;

        public MethodCallInfo(String methodName, int parameterCount) {
            this.methodName = methodName;
            this.parameterCount = parameterCount;
        }

        public String getMethodName() {
            return methodName;
        }

        public int getParameterCount() {
            return parameterCount;
        }

        @Override
        public String toString() {
            return methodName + "(" + parameterCount + " params)";
        }
    }

    /**
     * Class to represent the results of analyzing a method
     */
    public static class MethodAnalysisResult {
        private String methodName;
        private String methodSignature;
        private List<MethodCallInfo> internalMethodCalls = new ArrayList<>();
        private Map<String, FieldMethodCalls> projectFieldMethodCalls = new HashMap<>();
        private boolean isUsed;

        public MethodAnalysisResult(String methodName, String methodSignature) {
            this.methodName = methodName;
            this.methodSignature = methodSignature;
        }

        public boolean isUsed() {
            return isUsed;
        }

        public void setUsed(boolean used) {
            isUsed = used;
        }

        public String getMethodName() {
            return methodName;
        }

        public String getMethodSignature() {
            return methodSignature;
        }

        public List<MethodCallInfo> getInternalMethodCalls() {
            return internalMethodCalls;
        }

        public void setInternalMethodCalls(List<MethodCallInfo> internalMethodCalls) {
            this.internalMethodCalls = internalMethodCalls;
        }

        public Map<String, FieldMethodCalls> getProjectFieldMethodCalls() {
            return projectFieldMethodCalls;
        }

        public void setProjectFieldMethodCalls(Map<String, FieldMethodCalls> projectFieldMethodCalls) {
            this.projectFieldMethodCalls = projectFieldMethodCalls;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Method: ").append(methodSignature).append("\n");

            // Internal methods
            sb.append("  Internal method calls: ").append(internalMethodCalls.size()).append("\n");
            for (MethodCallInfo call : internalMethodCalls) {
                sb.append("    - ").append(call.toString()).append("\n");
            }

            // Field methods
            sb.append("  Project field method calls: ").append(projectFieldMethodCalls.size()).append("\n");
            for (Map.Entry<String, FieldMethodCalls> entry : projectFieldMethodCalls.entrySet()) {
                FieldMethodCalls fieldCalls = entry.getValue();
                sb.append("    - Field: ").append(entry.getKey())
                        .append(" [Type: ").append(fieldCalls.getFieldType()).append("]\n");

                for (MethodCallInfo call : fieldCalls.getMethodCalls()) {
                    sb.append("      * ").append(call.toString()).append("\n");
                }
            }

            return sb.toString();
        }
    }

    /**
     * Class to store method calls on a specific field
     */
    public static class FieldMethodCalls {
        private String fieldName;
        private String fieldType;
        private List<MethodCallInfo> methodCalls = new ArrayList<>();

        public FieldMethodCalls(String fieldName, String fieldType) {
            this.fieldName = fieldName;
            this.fieldType = fieldType;
        }

        public void addMethodCall(MethodCallInfo methodCall) {
            methodCalls.add(methodCall);
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getFieldType() {
            return fieldType;
        }

        public List<MethodCallInfo> getMethodCalls() {
            return methodCalls;
        }
    }

    /**
     * Class to represent the results of analyzing a class
     */
    public static class ClassAnalysisResult {
        private String className;
        private String packageName;
        private List<MethodAnalysisResult> methodResults = new ArrayList<>();

        public ClassAnalysisResult(String className, String packageName) {
            this.className = className;
            this.packageName = packageName;
        }

        public void addMethodResult(MethodAnalysisResult methodResult) {
            methodResults.add(methodResult);
        }

        public String getClassName() {
            return className;
        }

        public String getPackageName() {
            return packageName;
        }

        public List<MethodAnalysisResult> getMethodResults() {
            return methodResults;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Class: ").append(packageName).append(".").append(className).append("\n\n");

            for (MethodAnalysisResult methodResult : methodResults) {
                sb.append(methodResult.toString()).append("\n");
            }

            return sb.toString();
        }
    }

    /**
     * Visitor to collect method calls with parameter counts
     */
    private static class MethodCallVisitor extends VoidVisitorAdapter<Void> {
        private final Map<String, FieldInfo> fields;
        private final List<MethodCallInfo> internalMethodCalls = new ArrayList<>();
        private final Map<String, FieldMethodCalls> projectFieldMethodCalls = new HashMap<>();

        public MethodCallVisitor(Map<String, FieldInfo> fields) {
            this.fields = fields;
        }

        @Override
        public void visit(MethodCallExpr methodCall, Void arg) {
            super.visit(methodCall, arg);

            String methodName = methodCall.getNameAsString();
            int parameterCount = methodCall.getArguments().size();

            // Check if the method call has a scope (called on an object)
            if (methodCall.getScope().isPresent()) {
                String scopeName = methodCall.getScope().get().toString();

                if (scopeName.equals("this")) {
                    // This is an internal call with explicit "this" qualifier
                    internalMethodCalls.add(new MethodCallInfo(methodName, parameterCount));
                } else if (fields.containsKey(scopeName)) {
                    FieldInfo fieldInfo = fields.get(scopeName);

                    // Only include if it's a project type
                    if (fieldInfo.isProjectType) {
                        if (!projectFieldMethodCalls.containsKey(scopeName)) {
                            projectFieldMethodCalls.put(scopeName,
                                    new FieldMethodCalls(scopeName, fieldInfo.typeName));
                        }
                        projectFieldMethodCalls.get(scopeName)
                                .addMethodCall(new MethodCallInfo(methodName, parameterCount));
                    }
                }
            } else {
                // Method call without scope is internal
                internalMethodCalls.add(new MethodCallInfo(methodName, parameterCount));
            }
        }

        public List<MethodCallInfo> getInternalMethodCalls() {
            return internalMethodCalls;
        }

        public Map<String, FieldMethodCalls> getProjectFieldMethodCalls() {
            return projectFieldMethodCalls;
        }
    }

    /**
     * Example usage
     */
    public static void main(String[] args) {
        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/data/ClubQualData.java";
        String projectRoot = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java";

        ClassAnalysisResult result = analyzeClass(filePath, projectRoot);
        System.out.println(result);

        // Find methods that call other methods with more than 3 parameters
        findMethodsWithManyParameters(result);
    }

    /**
     * Find and print methods that call other methods with more than 3 parameters
     */
    private static void findMethodsWithManyParameters(ClassAnalysisResult result) {
        System.out.println("\nMethods calling methods with more than 3 parameters:");
        for (MethodAnalysisResult methodResult : result.getMethodResults()) {
            // Check internal calls
            for (MethodCallInfo call : methodResult.getInternalMethodCalls()) {
                if (call.getParameterCount() > 3) {
                    // Commented code
                    // System.out.println("- " + methodResult.getMethodName() +
                    //         " calls " + call.getMethodName() +
                    //         " with " + call.getParameterCount() + " parameters");
                }
            }

            // Check field calls
            for (FieldMethodCalls fieldCalls : methodResult.getProjectFieldMethodCalls().values()) {
                for (MethodCallInfo call : fieldCalls.getMethodCalls()) {
                    if (call.getParameterCount() > 3) {
                        // Commented code
                        // System.out.println("- " + methodResult.getMethodName() +
                        //         " calls " + fieldCalls.getFieldName() + "." + call.getMethodName() +
                        //         " with " + call.getParameterCount() + " parameters");
                    }
                }
            }
        }
    }
}