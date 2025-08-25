package MARMSUI.migration.ComparingMethodContents;
import MARMSUI.util.WriterToFile;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodInfoExtractor {

    public static class MethodInfo {
        private String methodName;
        private String returnType;
        private List<String> parameterTypes;
        private String methodBody;

        public MethodInfo(String methodName, String returnType, List<String> parameterTypes, String methodBody) {
            this.methodName = methodName;
            this.returnType = returnType;
            this.parameterTypes = parameterTypes;
            this.methodBody = methodBody;
        }

        public String getMethodName() {
            return methodName;
        }

        public String getReturnType() {
            return returnType;
        }

        public List<String> getParameterTypes() {
            return parameterTypes;
        }

        public String getMethodBody() {
            return methodBody;
        }

        @Override
        public String toString() {
            return "MethodInfo{" +
                    "methodName='" + methodName + '\'' +
                    ", returnType='" + returnType + '\'' +
                    ", parameterTypes=" + parameterTypes +
                    ", methodBody='" + methodBody + '\'' +
                    '}';
        }
    }

    /**
     * Extract method information from a Java file
     *
     * @param javaFilePath Path to the Java file
     * @return List of MethodInfo objects
     */
    public static Map<String,MethodInfo> extractMethodInfo(String javaFilePath) throws Exception {
        Map<String,MethodInfo> methods = new HashMap<>();

        // Parse the Java file
        CompilationUnit cu = StaticJavaParser.parse(new File(javaFilePath));

        // Visit all method declarations
        cu.findAll(MethodDeclaration.class).forEach(method -> {
            // Get method name
            String methodName = method.getNameAsString();

            // Get return type
            String returnType = method.getType().asString();

            // Get parameter types
            List<String> parameterTypes = new ArrayList<>();
            method.getParameters().forEach(param -> {
                parameterTypes.add(param.getType().asString());
            });

            // Get method body (if present)
            String methodBody = method.getBody()
                    .map(body -> body.toString())
                    .orElse("");

            // Create and add MethodInfo object
            methods.put(generateUniqueMethodName(methodName,parameterTypes,methods),new MethodInfo(methodName, returnType, parameterTypes, methodBody));
        });

        return methods;
    }

    public static String  generateUniqueMethodName(String methodName, List<String> parameterTypes, Map<String,MethodInfo> map) {
        StringBuilder nameBuilder = new StringBuilder(methodName).append(":");
        int count = 1;
        for(String paramType : parameterTypes) {
            nameBuilder.append(count).append(paramType);
            count++;
        }
        if(map != null && map.containsKey(nameBuilder.toString())) {
            System.out.println("Duplicate method found: " + nameBuilder.toString());
        }
        return nameBuilder.toString();
    }

    public static void main(String[] args) {

        // todo: change file path
        String oldPath = "/Users/macuser/Downloads/marms-UAT_21May_checkpoint 2/MARMS/Source Code/Business Components/EJB/TierHandler/com/singaporeair/marms/abacus/business/customer/tier/TierHandlerBean.java";
        // todo: change file path
        String newPath = "/Users/macuser/Documents/updated-lsl-app/marms/MARMS/Source Code/Business Components/EJB/TierHandler/com/singaporeair/marms/abacus/business/customer/tier/TierHandlerBean.java";
        // todo: change file path of output
        String outputComparisonForOld = "/Users/macuser/Desktop/holdingForTempFiles/method-comparison/old.txt";
        // todo: change file path of output
        String outputComparisonForNew = "/Users/macuser/Desktop/holdingForTempFiles/method-comparison/updated.txt";

        try {
            Map<String,MethodInfo> methodsForOldCode = extractMethodInfo(oldPath);
            Map<String,MethodInfo> methodsForMostUpdatedCode = extractMethodInfo(newPath);
            List<String> consolidatedMethodWithDifferences = new ArrayList<>();

            int count = 0;
            for(String key : methodsForMostUpdatedCode.keySet()) {
                MethodInfo methodInOldCode = methodsForOldCode.get(key);
                MethodInfo methodInMostUpdatedCode = methodsForMostUpdatedCode.get(key);
                if(methodInOldCode == null) {
                    System.out.println("Method not found in old code: " + key);
                    continue;
                }
                List<ComparisonResult> comparisonResultList = MethodBodyComparator.compareMethodBodies(methodInOldCode, methodInMostUpdatedCode);
                if(comparisonResultList != null) {
                    consolidatedMethodWithDifferences.add(key);
                    // save old code first
//                    WriterToFile.saveStringToFile(key,outputComparisonForOld,count != 0);
//                    WriterToFile.saveStringToFile(comparisonResultList.get(0).getContent() + "\n\n\n====================\n\n\n",outputComparisonForOld,true);
//                    WriterToFile.saveStringToFile(key,outputComparisonForNew,count != 0);
//                    WriterToFile.saveStringToFile(comparisonResultList.get(1).getContent() + "\n\n\n====================\n\n\n",outputComparisonForNew,true);
//                    count++;
                }
            }
            System.out.println("Printing out methods with differences: -----------\n");
            consolidatedMethodWithDifferences.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}