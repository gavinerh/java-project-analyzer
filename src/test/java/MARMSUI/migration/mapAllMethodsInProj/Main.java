package MARMSUI.migration.mapAllMethodsInProj;

import MARMSUI.migration.mapAllMethodsInProj.model.ClassDeclaration;
import MARMSUI.migration.mapAllMethodsInProj.model.MethodDeclaration;
import MARMSUI.migration.mapAllMethodsInProj.util.*;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static String rootPath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java";
    static String packagePrefix = "com.sg.sq.marmsui";

    public static void main(String[] args) {

        File[] files = new File(rootPath).listFiles();
        Map<String, ClassDeclaration> classDeclarationMap = new HashMap<>();
        // iterate through all files and subdirectories

        mapAllFiles(new File(rootPath),classDeclarationMap);
//        String path = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/controller/ForceQualificationController.java";
//        Map<String,ClassDeclaration> classDeclarationMap = new HashMap<>();
//        processJavaFile(new File(path),classDeclarationMap);
        System.out.println(classDeclarationMap.size());
        // iterate through the map and check for classes with implementedInterfaces
        // check the implemented interfaces with the import statement, pass in AdminFeeImpl file path to test
        // update the file path of the implementedInterfaces field, use the map, go to the interface classDeclaration and set the concreteClassPath field
    }

    private static void mapAllFiles(File file, Map<String, ClassDeclaration> classDeclarationMap) {
        if(file.isDirectory()) {
            if(file.getAbsolutePath().endsWith("vo") || file.getAbsolutePath().startsWith("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/database/sql/persistence/model")) {
                return; // skip value object directories
            }
            for(File f : file.listFiles()) {
                mapAllFiles(f,classDeclarationMap);
            }
        } else if(file.getName().endsWith(".java")) {
            // process the java file
            processJavaFile(file, classDeclarationMap);
        }

    }

    private static void processJavaFile(File file, Map<String, ClassDeclaration> classDeclarationMap) {
        System.out.println("processing file: " + file.getAbsolutePath());
        // check if it is an interface or class
        String type = ClassTypeDetector.getClassType(file.getAbsolutePath());
        ClassDeclaration classDeclaration = null;
        if(type.equals("interface")) {
            classDeclaration = new ClassDeclaration();
            classDeclaration.setInterface(true);
            classDeclaration.setName(file.getAbsolutePath());
        } else if (type.equals("abstract") || type.equals("concrete")) {
            classDeclaration = new ClassDeclaration();
            classDeclaration.setInterface(false);
            classDeclaration.setName(file.getAbsolutePath());
            classDeclaration.setFieldList(FieldExtractor.extractProjectFields(file.getAbsolutePath(),rootPath,packagePrefix));
            classDeclaration.setImportList(ImportExtractor.extractImports(file.getAbsolutePath()));
            String interfacesImplemented = InterfaceImplementationChecker.getImplementedInterfaces(file.getAbsolutePath());
            // use the import statements to determine the full path of the interfaces

            classDeclaration.setImplementedInterfaces(interfacesImplemented == null ? new ArrayList<>() : List.of(interfacesImplemented));
            MethodExtractorAdvanced.ClassAnalysisResult result = MethodExtractorAdvanced.analyzeClass(file.getAbsolutePath(),rootPath);
            List<MethodExtractorAdvanced.MethodAnalysisResult> methodResult = result.getMethodResults();
            populateMethodDeclarations(methodResult, classDeclaration);
        }
        String key = getClassNameFromPath(file.getAbsolutePath());
        classDeclarationMap.put(key,classDeclaration);
        // if class check if it implements any interfaces
        // if class extract fields
    }

    private static void populateMethodDeclarations(List<MethodExtractorAdvanced.MethodAnalysisResult> methodResults, ClassDeclaration classDeclaration) {
        List<MethodDeclaration> methodDeclarations = new ArrayList<>();
        for(MethodExtractorAdvanced.MethodAnalysisResult methodResult : methodResults) {
            MethodDeclaration methodDeclaration = new MethodDeclaration();
            methodDeclaration.setName(methodResult.getMethodName());
            methodDeclaration.setParameters(extractParameterTypesFromSignature(methodResult.getMethodSignature()));
            List<MethodDeclaration> innerMethodSignatures = new ArrayList<>();
            methodDeclaration.setInnerMethods(innerMethodSignatures);
            populateInnerMethods(innerMethodSignatures,methodResult.getInternalMethodCalls()); // set inner methods
            // set the fields methods
            populateFieldMethodsIntoInnerMethod(innerMethodSignatures,methodResult.getProjectFieldMethodCalls());
            methodDeclarations.add(methodDeclaration);
        }
        classDeclaration.setMethodList(methodDeclarations);
    }


    private static void populateFieldMethodsIntoInnerMethod(List<MethodDeclaration> innerMethodSignatures, Map<String,MethodExtractorAdvanced.FieldMethodCalls> projectFieldMethodCalls) {
        for(String key : projectFieldMethodCalls.keySet()) {
            if(key.toLowerCase().endsWith("logger")) {
                continue;
            }
            MethodExtractorAdvanced.FieldMethodCalls fieldMethodCalls = projectFieldMethodCalls.get(key);
            String type = fieldMethodCalls.getFieldType();
            Map<String,MethodDeclaration> fieldMethods = new HashMap<>();
            for(MethodExtractorAdvanced.MethodCallInfo methodCallForField : fieldMethodCalls.getMethodCalls()) {
                MethodDeclaration methodDeclaration = new MethodDeclaration();
                methodDeclaration.setName(type + "." + methodCallForField.getMethodName());
                methodDeclaration.setNumberOfParams(methodCallForField.getParameterCount());
                fieldMethods.put(type + "." + methodCallForField.getMethodName() + "-" + methodCallForField.getParameterCount(),methodDeclaration);
            }
            innerMethodSignatures.addAll(fieldMethods.values());
        }
    }

    private static void populateInnerMethods(List<MethodDeclaration> innerMethod,  List<MethodExtractorAdvanced.MethodCallInfo> methodCallInfoList) {
        for(MethodExtractorAdvanced.MethodCallInfo methodCallInfo : methodCallInfoList) {
            MethodDeclaration methodDeclaration = new MethodDeclaration();
            methodDeclaration.setNumberOfParams(methodCallInfo.getParameterCount());
            methodDeclaration.setName(methodCallInfo.getMethodName());
            innerMethod.add(methodDeclaration);
        }
    }


    private static List<String> extractParameterTypesFromSignature(String methodSignature) {
        if(!methodSignature.contains("@")) {
            List<String> parameterTypes = new ArrayList<>();
            int start = methodSignature.indexOf('(');
            int end = methodSignature.indexOf(')');
            if (start != -1 && end != -1 && end > start) {
                String params = methodSignature.substring(start + 1, end).trim();
                if (!params.isEmpty()) {
                    String[] paramArray = params.split(",");
                    for (String param : paramArray) {
                        String[] parts = param.trim().split(" ");
                        if (parts.length >= 2) {
                            parameterTypes.add(parts[0]); // Assuming the first part is the type
                        }
                    }
                }
            }
            return parameterTypes;
        } else {
            List<String> types = new ArrayList<>();
            int start = methodSignature.indexOf('(');
            int end = methodSignature.lastIndexOf(')');
            String values = methodSignature.substring(start + 1,end);
            String[] splitByComma = values.split(",");
            for(String param : splitByComma) {
                String[] parts = param.trim().split(" ");
                if(parts.length >= 2) {
                    String type = parts[parts.length - 2];
                    types.add(type);
                }
            }
            return types;
        }
    }

    private static String getClassNameFromPath(String filePath) {
        // Remove the root path and the .java extension
        String relativePath = filePath.replace(rootPath + File.separator, "").replace(".java", "");
        // Replace file separators with dots to form the fully qualified class name
        return relativePath.replace(File.separator, ".");
    }
}
