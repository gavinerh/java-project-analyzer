package MARMSUI.migration.hierarchyGenerator;

import MARMSUI.migration.hierarchyGenerator.model.MethodInfo;
import MARMSUI.migration.hierarchyGenerator.model.ParameterInfo;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappingAllMethodsInClass {
    // store all method declaration in the class into a map
    // key: className + methodName + no. of parameters, value: method declaration
    static Map<String, List<MethodInfo>> methodDeclarationMap;
    static String className;

    //    public static void main(String[] args) {
//        methodDeclarationMap = new HashMap<>();
//    }
    public static void main(String[] args) {
        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/QualificationServiceImpl.java";
        Map<String,List<MethodInfo>>map = getMethodDeclarationMap(filePath);
        System.out.println(map.size());
    }

    // call this method from outside to get the method declaration map
    public static Map<String,List<MethodInfo>> getMethodDeclarationMap(String filePath) {
        methodDeclarationMap = new HashMap<>();
        className = extractClassName(filePath);
        execute(filePath);
        return methodDeclarationMap;
    }

    public static void execute(String filePath) {
        if(methodDeclarationMap == null) {
            methodDeclarationMap = new HashMap<>();
        }
//        filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/QualificationServiceImpl.java";

        try (FileInputStream in = new FileInputStream(filePath)) {
            CompilationUnit cu = StaticJavaParser.parse(in);

            List<MethodInfo> methodInfos = new ArrayList<>();
            cu.accept(new MethodVisitor(), methodInfos);

            // Print the extracted method information
            for (MethodInfo methodInfo : methodInfos) {
                if(methodInfo.getModifiers().isEmpty()) {
                    System.out.println(String.format("There are no modifiers declared for %s method in %s", methodInfo.getMethodName(),filePath));
                }
                storeMethodInfoInMap(methodInfo, methodInfo.getMethodName(), extractClassName(filePath));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error in MappingAllMethodsInClass: " + e);
        }
    }

    public static String extractClassName(String filePath) {
        int start = filePath.lastIndexOf("/") + 1;
        int end = filePath.indexOf(".java");
        return filePath.substring(start, end);
    }

    private static class MethodVisitor extends VoidVisitorAdapter<List<MethodInfo>> {
        @Override
        public void visit(MethodDeclaration md, List<MethodInfo> collector) {
            super.visit(md, collector);

            MethodInfo methodInfo = new MethodInfo();
            methodInfo.setMethodName(md.getNameAsString());
            methodInfo.setReturnType(md.getType().asString());
            methodInfo.setClassName(className);

            List<String> modifiers = new ArrayList<>();
            md.getModifiers().forEach(modifier -> modifiers.add(modifier.getKeyword().asString()));
            methodInfo.setModifiers(modifiers);

            List<ParameterInfo> parameters = new ArrayList<>();
            for (Parameter param : md.getParameters()) {
                ParameterInfo paramInfo = new ParameterInfo();
                paramInfo.setType(param.getType().asString());
                paramInfo.setName(param.getNameAsString());
                parameters.add(paramInfo);
            }
            methodInfo.setParameters(parameters);

            collector.add(methodInfo);
        }
    }

    private static void storeMethodInfoInMap(MethodInfo methodInfo, String methodName, String className) {
        if(methodDeclarationMap.containsKey(methodName)) {
            List<MethodInfo> methodInfoList = methodDeclarationMap.get(methodName);
            methodInfoList.add(methodInfo);
        } else {
            List<MethodInfo> methodInfoList = new ArrayList<>();
            methodInfoList.add(methodInfo);
            methodDeclarationMap.put(methodName, methodInfoList);
        }
    }

}
