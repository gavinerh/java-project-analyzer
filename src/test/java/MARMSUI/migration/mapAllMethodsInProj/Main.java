package MARMSUI.migration.mapAllMethodsInProj;

import MARMSUI.migration.mapAllMethodsInProj.model.ClassDeclaration;
import MARMSUI.migration.mapAllMethodsInProj.model.MethodDeclaration;
import MARMSUI.migration.mapAllMethodsInProj.util.*;

import java.io.File;
import java.util.*;

public class Main {

    static String rootPath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java";
    static String packagePrefix = "com.sg.sq.marmsui";
    static String[] whitelistedInterfaces = new String[]{"Serializable", "Cloneable", "Comparable", "java.io.Serializable", "HandlerInterceptor", "WebMvcConfigurer"};

    public static void main(String[] args) {

        File[] files = new File(rootPath).listFiles();
        Map<String, ClassDeclaration> classDeclarationMap = new HashMap<>();
        // iterate through all files and subdirectories
//        mapAllFiles(new File(rootPath), classDeclarationMap);

//        // todo: for testing purposes only, process specific files
        String path = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/controller/ForceQualificationController.java";
        processJavaFile(new File(path), classDeclarationMap);
//        path = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/QualificationService.java";
//        processJavaFile(new File(path), classDeclarationMap);

        // iterate through the map and check for classes with implementedInterfaces
//        iterateMapAndConnectInterfacesToImpl(classDeclarationMap);
//        System.out.println(classDeclarationMap.size());

        // check the implemented interfaces with the import statement, pass in AdminFeeImpl file path to test
        // update the file path of the implementedInterfaces field, use the map, go to the interface classDeclaration and set the concreteClassPath field

//        String params = "@RequestHeader(name = \"agent_token\") String agentToken, @RequestHeader(name = \"cust_token\") String custToken, @RequestBody Map<String, Object> request";
//        List<String> types = new ArrayList<>();
//        extractParameterTypesFromMapParams(params, types);
//        params = "Map<String,List<String>> req, String userId";
//        extractParameterTypesFromMapParams(params, types);
//        params = "Map<String, List<Map<String, Object>>> req, String userId,  int count";
//        extractParameterTypesFromMapParams(params, types);
//        System.out.println(types.size());
    }

    private static void iterateMapAndConnectInterfacesToImpl(Map<String, ClassDeclaration> classDeclarationMap) {
        for (String key : classDeclarationMap.keySet()) {
            ClassDeclaration classDeclaration = classDeclarationMap.get(key);
            List<String> implementedInterfacesModified = new ArrayList<>();
            if (classDeclaration == null) {
                System.out.println("Class declaration is null for key: " + key);
                continue;
            }
            if (!classDeclaration.isInterface() && !classDeclaration.getImplementedInterfaces().isEmpty()) {
                List<String> importList = classDeclaration.getImportList();
                for (String interfaceName : classDeclaration.getImplementedInterfaces()) {
                    if (List.of(whitelistedInterfaces).contains(interfaceName)) {
                        implementedInterfacesModified.add(interfaceName);
                        continue;
                    }
                    try {
                        String fullPath = extractInterfaceFullPathFromImplImports(importList, interfaceName);
                        if (fullPath == null) {
                            fullPath = checkCurrentDirectoryForFile(key, interfaceName);
                        }
                        if (fullPath == null) {
                            throw new RuntimeException("Could not locate interface: " + interfaceName);
                        }
                        implementedInterfacesModified.add(fullPath);
                        ClassDeclaration interfaceClassDeclaration = classDeclarationMap.get(fullPath);
                        if (interfaceClassDeclaration == null) {
                            throw new RuntimeException("Could not find interface class declaration for: " + fullPath + " implemented in class: " + classDeclaration.getName());
                        }
                        interfaceClassDeclaration.setConcreteClassPath(key);
                    } catch (Exception e) {
                        throw e;
                    }
                }
                classDeclaration.setImplementedInterfaces(implementedInterfacesModified);
            }
        }
    }

    private static String checkCurrentDirectoryForFile(String implClassPath, String interfaceName) {
        String implDir = implClassPath.contains(".") ? implClassPath.substring(0, implClassPath.lastIndexOf(".")) : implClassPath;
        String possiblePath = rootPath + File.separator + implDir.replace(".", File.separator) + File.separator + interfaceName + ".java";
        File file = new File(possiblePath);
        if (file.exists()) {
            return implDir + "." + interfaceName;
        }
        return null;
    }

    private static String extractInterfaceFullPathFromImplImports(List<String> importList, String interfaceName) {
        List<String> possibleImports = new ArrayList<>();
        String fullInterfacePath = null;
        boolean isFound = false;
        for (String importStatement : importList) {
            if (importStatement.contains(interfaceName)) {
                isFound = true;
                fullInterfacePath = importStatement;
                break;
            }
            if (importStatement.endsWith(".*")) {
                possibleImports.add(importStatement.substring(0, importStatement.length() - 2) + "." + interfaceName);
            }
        }
        if (!isFound) {
            fullInterfacePath = locateFileInPossibleImports(possibleImports);
        }
        return fullInterfacePath;
    }

    private static String locateFileInPossibleImports(List<String> possibleImports) {
        for (String importPath : possibleImports) {
            String filePath = rootPath + File.separator + importPath.replace(".", File.separator) + ".java";
            File file = new File(filePath);
            if (file.exists()) {
                return filePath.replace(rootPath + File.separator, "").replace(File.separator, ".").replace(".java", "");
            }
        }
        return null;
    }

    private static void mapAllFiles(File file, Map<String, ClassDeclaration> classDeclarationMap) {
        if (file.isDirectory()) {
            if (file.getAbsolutePath().endsWith("vo") || file.getAbsolutePath().startsWith("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/database/sql/persistence/model")) {
                return; // skip value object directories
            }
            for (File f : file.listFiles()) {
                mapAllFiles(f, classDeclarationMap);
            }
        } else if (file.getName().endsWith(".java")) {
            // process the java file
            processJavaFile(file, classDeclarationMap);
        }

    }

    private static void processJavaFile(File file, Map<String, ClassDeclaration> classDeclarationMap) {
//      todo: add this when debugging:  System.out.println("processing file: " + file.getAbsolutePath());
        // check if it is an interface or class
        String type = ClassTypeDetector.getClassType(file.getAbsolutePath());
        ClassDeclaration classDeclaration = null;
        if (type.equals("interface")) {
            classDeclaration = new ClassDeclaration();
            classDeclaration.setInterface(true);
            classDeclaration.setName(file.getAbsolutePath());
        } else if (type.equals("abstract") || type.equals("concrete")) {
            classDeclaration = new ClassDeclaration();
            classDeclaration.setInterface(false);
            classDeclaration.setName(file.getAbsolutePath());
            classDeclaration.setFieldList(FieldExtractor.extractProjectFields(file.getAbsolutePath(), rootPath, packagePrefix));
            classDeclaration.setImportList(ImportExtractor.extractImports(file.getAbsolutePath()));
            String interfacesImplemented = InterfaceImplementationChecker.getImplementedInterfaces(file.getAbsolutePath());
            // use the import statements to determine the full path of the interfaces

            classDeclaration.setImplementedInterfaces(interfacesImplemented == null ? new ArrayList<>() : List.of(interfacesImplemented));
            MethodExtractorAdvanced.ClassAnalysisResult result = MethodExtractorAdvanced.analyzeClass(file.getAbsolutePath(), rootPath);
            List<MethodExtractorAdvanced.MethodAnalysisResult> methodResult = result.getMethodResults();
            populateMethodDeclarations(methodResult, classDeclaration);
        }
        String key = getClassNameFromPath(file.getAbsolutePath());
        classDeclarationMap.put(key, classDeclaration);
        // if class check if it implements any interfaces
        // if class extract fields
    }

    private static void populateMethodDeclarations(List<MethodExtractorAdvanced.MethodAnalysisResult> methodResults, ClassDeclaration classDeclaration) {
        List<MethodDeclaration> methodDeclarations = new ArrayList<>();
        for (MethodExtractorAdvanced.MethodAnalysisResult methodResult : methodResults) {
            MethodDeclaration methodDeclaration = new MethodDeclaration();
            methodDeclaration.setName(methodResult.getMethodName());
            methodDeclaration.setParameters(extractParameterTypesFromSignature(methodResult.getMethodSignature()));
            List<MethodDeclaration> innerMethodSignatures = new ArrayList<>();
            methodDeclaration.setInnerMethods(innerMethodSignatures);
            populateInnerMethods(innerMethodSignatures, methodResult.getInternalMethodCalls()); // set inner methods
            // set the fields methods
            populateFieldMethodsIntoInnerMethod(innerMethodSignatures, methodResult.getProjectFieldMethodCalls());
            methodDeclarations.add(methodDeclaration);
        }
        classDeclaration.setMethodList(methodDeclarations);
    }


    private static void populateFieldMethodsIntoInnerMethod(List<MethodDeclaration> innerMethodSignatures, Map<String, MethodExtractorAdvanced.FieldMethodCalls> projectFieldMethodCalls) {
        for (String key : projectFieldMethodCalls.keySet()) {
            if (key.toLowerCase().endsWith("logger")) {
                continue;
            }
            MethodExtractorAdvanced.FieldMethodCalls fieldMethodCalls = projectFieldMethodCalls.get(key);
            String type = fieldMethodCalls.getFieldType();
            Map<String, MethodDeclaration> fieldMethods = new HashMap<>();
            for (MethodExtractorAdvanced.MethodCallInfo methodCallForField : fieldMethodCalls.getMethodCalls()) {
                MethodDeclaration methodDeclaration = new MethodDeclaration();
                methodDeclaration.setName(type + "." + methodCallForField.getMethodName());
                methodDeclaration.setNumberOfParams(methodCallForField.getParameterCount());
                fieldMethods.put(type + "." + methodCallForField.getMethodName() + "-" + methodCallForField.getParameterCount(), methodDeclaration);
            }
            innerMethodSignatures.addAll(fieldMethods.values());
        }
    }

    private static void populateInnerMethods(List<MethodDeclaration> innerMethod, List<MethodExtractorAdvanced.MethodCallInfo> methodCallInfoList) {
        for (MethodExtractorAdvanced.MethodCallInfo methodCallInfo : methodCallInfoList) {
            MethodDeclaration methodDeclaration = new MethodDeclaration();
            methodDeclaration.setNumberOfParams(methodCallInfo.getParameterCount());
            methodDeclaration.setName(methodCallInfo.getMethodName());
            innerMethod.add(methodDeclaration);
        }
    }


    private static List<String> extractParameterTypesFromSignature(String methodSignature) {
        int startIndex = methodSignature.indexOf('[');
        int endIndex = methodSignature.lastIndexOf(']');
        String[] paramsArray = methodSignature.substring(startIndex + 1, endIndex).split(",");
        if(paramsArray.length == 1 && paramsArray[0].trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(paramsArray).toList();
//        if (!methodSignature.contains("@")) {
//            List<String> parameterTypes = new ArrayList<>();
//            int start = methodSignature.indexOf('(');
//            int end = methodSignature.indexOf(')');
//            if (start != -1 && end != -1 && end > start) {
//                String params = methodSignature.substring(start + 1, end).trim();
//                if (!params.isEmpty()) {
//                    if (params.contains("Map ") || params.contains("Map<")) {
//                        extractParameterTypesFromMapParams(params, parameterTypes);
//                    } else {
//                        String[] paramArray = params.split(",");
//                        for (String param : paramArray) {
//                            String[] parts = param.trim().split(" ");
//                            if (parts.length >= 2) {
//                                parameterTypes.add(parts[0]); // Assuming the first part is the type
//                            }
//                        }
//                    }
//                }
//            }
//            return parameterTypes;
//        } else {
//            List<String> types = new ArrayList<>();
//            int start = methodSignature.indexOf('(');
//            int end = methodSignature.lastIndexOf(')');
//            String values = methodSignature.substring(start + 1, end);
//            if (values.contains("Map ") || values.contains("Map<")) {
//                extractParamsForMapAndForAnnotation(values, types); // contain @ and Map<
//            } else {
//                String[] splitByComma = values.split(",");
//                for (String param : splitByComma) {
//                    String[] parts = param.trim().split(" ");
//                    if (parts.length >= 2) {
//                        String type = parts[parts.length - 2];
//                        types.add(type);
//                    }
//                }
//            }
//            return types;
//        }

    }

    private static void extractParamsForMapAndForAnnotation(String values, List<String> types) {
        int startIndex = 0;
        int endIndex = 0;
        int mapIndex = 0;
        while(true) {

        }
    }

    private static void extractParameterTypesFromMapParams(String values, List<String> types) {
        values = values.trim();
        int angleBracketCount = 0;
        StringBuilder stringBuilder = new StringBuilder();
        boolean identifyingType = true;
        boolean commaReachedNextWordNotReached = false;
        for (char c : values.toCharArray()) {
            if(commaReachedNextWordNotReached && c != ' ') {
                identifyingType = true;
                commaReachedNextWordNotReached = false;
            }
            if (c == '<') {
                angleBracketCount++;
                stringBuilder.append(c);
                continue;
            }
            if (c == '>') {
                angleBracketCount--;
                stringBuilder.append(c);
                continue;
            }
            if (c == ' ') {
                if (angleBracketCount != 0) {
                    // add cuz its inside the type declaration
                    stringBuilder.append(c);
                } else {
                    identifyingType = false;
                }
                continue;
            }
            if (c == ',') {
                if (angleBracketCount > 0) {
                    stringBuilder.append(c);
                } else {
                    // comma signify transition to another param
                    types.add(stringBuilder.toString().trim());
                    stringBuilder.delete(0, stringBuilder.length());
                    commaReachedNextWordNotReached = true;
                }
                continue;
            }
            if (identifyingType) {
                stringBuilder.append(c);
            }

        }
        if (!stringBuilder.isEmpty()) {
            types.add(stringBuilder.toString().trim());
        }
    }

    private static String getClassNameFromPath(String filePath) {
        // Remove the root path and the .java extension
        String relativePath = filePath.replace(rootPath + File.separator, "").replace(".java", "");
        // Replace file separators with dots to form the fully qualified class name
        return relativePath.replace(File.separator, ".");
    }
}
