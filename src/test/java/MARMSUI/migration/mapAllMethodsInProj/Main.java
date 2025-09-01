package MARMSUI.migration.mapAllMethodsInProj;

import MARMSUI.migration.mapAllMethodsInProj.model.ClassDeclaration;
import MARMSUI.migration.mapAllMethodsInProj.model.MethodDeclaration;
import MARMSUI.migration.mapAllMethodsInProj.util.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static final String rootPath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java";
    public static final String packagePrefix = "com.sg.sq.marmsui";
    public static final String excludedPackage = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/database/sql/persistence/model";
    private static final String metadataFileName = "/Users/macuser/Desktop/hierarchy-generator/qual-map-metadata";
    private static final String metadataForMappedProject = "";

    // todo: change if necessary
    private static boolean updateMetadataFileContent = false;

    public static void main(String[] args) throws IOException {

        Map<String, ClassDeclaration> classDeclarationMap;

        if (updateMetadataFileContent) {
            classDeclarationMap = new HashMap<>();
            // iterate through all files and subdirectories
            mapAllFiles(new File(rootPath), classDeclarationMap);

            // iterate through the map and check for classes with implementedInterfaces
            ConnectInterfacesToImplClass.iterateMapAndConnectInterfacesToImpl(classDeclarationMap);
            printMapToFile(classDeclarationMap);
        } else {
            // extract map from file
            classDeclarationMap = MapPrinterReader.readMapFromFile(metadataFileName);
        }

        // check if the classes in the map has multiple similar method name with same number of params
        checkIfAnyClassesHasDuplicateMethodNamesWithSameParamCount(classDeclarationMap);

        // convert field list to field map
        convertFieldListToFieldMapInAllClasses(classDeclarationMap);

        System.out.println(classDeclarationMap.size());

        // link up all the methods in the concrete classes
        iterateAllMethodsAndMarkVisited(classDeclarationMap);

        // todo: try to solve why static methods are not picked up
        System.out.println(classDeclarationMap.size());
        // possible next use cases for these project
        // - can extract the hierarchy
        // - can find all the methods that are not used in the project
        // - can extend the output to print out all the methods called in the hierarchy and feed into gen ai
        extendPotentialUseCase(classDeclarationMap);
    }

    private static void extendPotentialUseCase(Map<String, ClassDeclaration> classDeclarationMap) {
        Scanner scanner = new Scanner(System.in);
        ClassDeclaration classDeclaration = null;
        String key = null;
        Object[] arr = getStarterClassName(classDeclarationMap, scanner);
        classDeclaration = (ClassDeclaration) arr[0];
        key = (String) arr[1];
        MethodDeclaration starterMethod = getStarterMethod(scanner, "Enter starter method", classDeclaration.getMethodMap());
        Map<String, Set<String>> mapOfMethodsRequiredWithClassKey = new HashMap<>();
        iterateInnerMethodsAndIdentifyAllMethodsRequired(starterMethod, classDeclaration, classDeclarationMap, mapOfMethodsRequiredWithClassKey, key);
        System.out.println(mapOfMethodsRequiredWithClassKey.size());
        printMethodsRequired(mapOfMethodsRequiredWithClassKey);
    }

    private static void printMethodsRequired(Map<String, Set<String>> map) {
        for (String key : map.keySet()) {
            System.out.println("Printing out for class: " + key);
            Set<String> methodDeclaration = map.get(key);
            for (String val : methodDeclaration) {
                System.out.println(val);
            }
            System.out.println("\n\n");
        }
    }

    private static void iterateInnerMethodsAndIdentifyAllMethodsRequired(MethodDeclaration starterMtd, ClassDeclaration starterClass, Map<String, ClassDeclaration> classDeclarationMap, Map<String, Set<String>> mapOfMethodsRequiredWithClassKey, String key) {
        if (starterMtd.getInnerMethods().isEmpty()) {
            return;
        }
        // starterMtd e.g. getTierSummary of TierHandlerServiceImpl class
        // iterate through the starterMethod, find all the inner methods, if its ext mtd, directly use the classDec map to find and put into map
        for (MethodDeclaration inner : starterMtd.getInnerMethods()) {
            if (inner.getName().contains(".")) {
                // external methods identified, find the key to enter the external class
                if (operationsIfExtMethodFound(starterClass, classDeclarationMap, mapOfMethodsRequiredWithClassKey, inner))
                    continue; // no need to find method listing, since its iterated before
            } else {
                // internal method found
                String combinedMethodKey = Common.combineMethodNameAndParamNum(inner.getName(), String.valueOf(inner.getNumberOfParams()));
                Set<String> set = mapOfMethodsRequiredWithClassKey.get(key);
                if (set == null) {
                    set = new HashSet<>();
                    mapOfMethodsRequiredWithClassKey.put(key, set);
                }
                if (!set.contains(combinedMethodKey)) {
                    set.add(combinedMethodKey);
                } else {
                    continue;
                }
                // find the method in the from the same class declaration
                MethodDeclaration nextMethodDeclaration = starterClass.getMethodMap().get(combinedMethodKey);
                if(combinedMethodKey.contains("calculateEliteGoldMilesForAirAndNonAir")) {
                    System.out.println("here");
                }
                iterateInnerMethodsAndIdentifyAllMethodsRequired(nextMethodDeclaration, starterClass, classDeclarationMap, mapOfMethodsRequiredWithClassKey, key);
            }
        }
        // if inner method is found, call this method again
        // inner methods of getTierSummary method is captured in the queue, now we will find them and identify if there are any ext methods

    }

    private static boolean operationsIfExtMethodFound(ClassDeclaration starterClass, Map<String, ClassDeclaration> classDeclarationMap, Map<String, Set<String>> mapOfMethodsRequiredWithClassKey, MethodDeclaration inner) {
        String nextClsKey = null;
        String[] arr = null;
        String nextStarterMtdKey = null;
        ClassDeclaration nextClassDeclaration = null;
        MethodDeclaration nextMethodDeclaration = null;
        try {
            arr = Common.splitByDot(inner.getName());
            nextClsKey = starterClass.getFieldMap().get(arr[0]);
            if (nextClsKey == null) {
                throw new RuntimeException("ClassKey cannot be null");
            }
            nextClsKey = validateIfIsInterface(nextClsKey, classDeclarationMap);
            Set<String> set = mapOfMethodsRequiredWithClassKey.get(nextClsKey);
            if (set == null) {
                set = new HashSet<>();
                mapOfMethodsRequiredWithClassKey.put(nextClsKey, set);
            }
            nextStarterMtdKey = Common.combineMethodNameAndParamNum(arr[1], String.valueOf(inner.getNumberOfParams()));
            if (!set.contains(nextStarterMtdKey)) {
                set.add(nextStarterMtdKey);
            } else {
                return true;
            }
            nextClassDeclaration = classDeclarationMap.get(nextClsKey);
            nextMethodDeclaration = nextClassDeclaration.getMethodMap().get(nextStarterMtdKey);
            iterateInnerMethodsAndIdentifyAllMethodsRequired(nextMethodDeclaration, nextClassDeclaration, classDeclarationMap, mapOfMethodsRequiredWithClassKey, nextClsKey);
            return false;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private static String validateIfIsInterface(String clsKey, Map<String, ClassDeclaration> map) {
        if(clsKey.endsWith("Mapper")) {
            return clsKey;
        }
        ClassDeclaration classDeclaration = map.get(clsKey);
        if (classDeclaration.isInterface()) {
            return classDeclaration.getConcreteClassPath();
        }
        return clsKey;
    }


    private static MethodDeclaration getStarterMethod(Scanner scanner, String userMsg, Map<String, MethodDeclaration> map) {
        MethodDeclaration methodDeclaration = null;
        while (methodDeclaration == null) {
            String name = Common.getUserInputOnce(scanner, userMsg);
            String paramNum = Common.getUserInputOnce(scanner, "Enter number of parameters");
            String combinedKey = Common.combineMethodNameAndParamNum(name, paramNum);
            methodDeclaration = map.get(combinedKey);
        }
        return methodDeclaration;
    }

    private static Object[] getStarterClassName(Map<String, ClassDeclaration> classDeclarationMap, Scanner scanner) {
        ClassDeclaration classDeclaration = null;
        String key = "com.sg.sq.marmsui.service.impl.TierHandlerServiceImpl"; // todo: to remove setting the variablr
        while (classDeclaration == null) {
            if (key == null)
                key = Common.getUserInputOnce(scanner, "Enter class name with package name:");
            if (!classDeclarationMap.containsKey(key)) {
                System.out.println("Class name is not found: " + key);
                key = null;
            } else {
                classDeclaration = classDeclarationMap.get(key);
            }
        }
        return new Object[]{classDeclaration, key};
    }


    private static void iterateAllMethodsAndMarkVisited(Map<String, ClassDeclaration> classDeclarationMap) {
//        // todo: for testing only
//        String key = "com.sg.sq.marmsui.service.impl.data.QualificationData";
//        ClassDeclaration classDeclaration = classDeclarationMap.get(key);
//        for(MethodDeclaration methodDeclaration : classDeclaration.getMethodList()) {
//            markVisitedMethods(classDeclarationMap, methodDeclaration, classDeclaration, key);
//        }
        for (String key : classDeclarationMap.keySet()) {
            ClassDeclaration classDeclaration = classDeclarationMap.get(key);
            if (key.contains("CardData")) {
                System.out.println("re");
            }
            if (classDeclaration == null) {
                continue;
            }
            if (classDeclaration.isInterface()) {
                continue;
            }
            // todo: find a way to mark all the methods in this class and its methods as visited
            for (MethodDeclaration methodDeclaration : classDeclaration.getMethodList()) {
                markVisitedMethods(classDeclarationMap, methodDeclaration, classDeclaration, key);
            }
        }
    }

    private static void markVisitedMethods(Map<String, ClassDeclaration> classDeclarationMap, MethodDeclaration methodDeclaration, ClassDeclaration classDeclaration, String key) {
        methodDeclaration.setViewed(true); // visited by method, does not mean method is used in project
        List<MethodDeclaration> innerMethods = methodDeclaration.getInnerMethods();
        if (innerMethods != null && !innerMethods.isEmpty()) {
            for (MethodDeclaration innerMethod : innerMethods) {
                if (innerMethod.getName().contains(".")) {
                    markingExternalMethods(classDeclarationMap, methodDeclaration, classDeclaration, key, innerMethod);
                } else {
                    // this is a normal method
                    markMethodFromSameClass(methodDeclaration, classDeclaration, innerMethod);
                }
            }
        }
    }

    private static void markMethodFromSameClass(MethodDeclaration methodDeclaration, ClassDeclaration classDeclaration, MethodDeclaration innerMethod) {
        List<MethodDeclaration> classMethods = classDeclaration.getMethodList();
        if (classMethods == null || classMethods.isEmpty()) {
            System.out.println("Class methods should not be empty for class: " + classDeclaration.getName());
            return;
        }
        for (MethodDeclaration classMethod : classMethods) {
            if (classMethod.getName().equals(innerMethod.getName()) && classMethod.getNumberOfParams() == innerMethod.getNumberOfParams()) {
                // mark as visited and add the calling method
                classMethod.setUsed(true);
                Set<String> callingMethods = classMethod.getCallingMethods();
                if (callingMethods == null) {
                    callingMethods = new HashSet<>();
                    classMethod.setCallingMethods(callingMethods);
                }
                callingMethods.add(methodDeclaration.getName());
            }
        }
    }

    private static void markingExternalMethods(Map<String, ClassDeclaration> classDeclarationMap, MethodDeclaration methodDeclaration, ClassDeclaration classDeclaration, String key, MethodDeclaration innerMethod) {
        // this is a field method
        if (innerMethod.getName().startsWith("QualificationService")) {
            System.out.println("reached");
        }
        String[] parts = innerMethod.getName().split("\\.");
        if (parts.length != 2) {
            return;
        }
        String fieldType = parts[0];
        String methodName = parts[1];
        if (classDeclaration.getFieldMap() == null || classDeclaration.getFieldMap().isEmpty()) {
            System.out.println("Field map is empty for class: " + classDeclaration.getName());
            return;
        }
        String fieldClassPath = classDeclaration.getFieldMap().get(fieldType);
        if (fieldClassPath == null) {
            System.out.println("Field class path is null for field type: " + fieldType + " in class: " + classDeclaration.getName());
            return;
        }
        ClassDeclaration fieldClassDeclaration = classDeclarationMap.get(fieldClassPath);
        if (fieldClassDeclaration == null) {
            System.out.println("Field class declaration is null for field class path: " + fieldClassPath + " in class: " + classDeclaration.getName());
            return;
        }
        // from here check the concrete class methods as viewed if it is an interface
        if (fieldClassDeclaration.isInterface()) {
            if (fieldClassDeclaration.getConcreteClassPath() == null) {
                System.out.println("Concrete class path is null for interface: " + fieldClassDeclaration.getName() + " in class: " + classDeclaration.getName());
            } else {
                markingImplMethods(classDeclarationMap, methodDeclaration, classDeclarationMap.get(fieldClassDeclaration.getConcreteClassPath()), key, innerMethod);
                return;
            }
        }
        List<MethodDeclaration> fieldClassMethods = fieldClassDeclaration.getMethodList();
        if (fieldClassMethods == null || fieldClassMethods.isEmpty()) {
            System.out.println("Field class methods is empty for field class path: " + fieldClassPath + " in class: " + classDeclaration.getName());
            return;
        }
        MethodDeclaration fieldClassMethod = findMethodByNameAndParamCount(fieldClassMethods, methodName, innerMethod.getNumberOfParams());
        fieldClassMethod.setUsed(true);
        Set<String> callingMethods = fieldClassMethod.getCallingMethods();
        if (callingMethods == null) {
            callingMethods = new HashSet<>();
            fieldClassMethod.setCallingMethods(callingMethods);
        }
        callingMethods.add(key + "." + methodDeclaration.getName());
    }

    private static void markingImplMethods(Map<String, ClassDeclaration> classDeclarationMap, MethodDeclaration callingMethodDeclaration, ClassDeclaration implClassDeclaration, String key, MethodDeclaration innerMethod) {
        // from the inner method, get the method method name to search from the implClassDeclaration methodList
        String name = innerMethod.getName();
        // extract the method name from the innerMethod name after the .
        name = name.substring(name.indexOf(".") + 1);
        // the extracted method name is the method to search in the implClassDeclaration methodList
        MethodDeclaration methodDeclarationOfImpl = findMethodByNameAndParamCount(implClassDeclaration.getMethodList(), name, innerMethod.getNumberOfParams());
        // if found, mark as viewed and add the calling method
        methodDeclarationOfImpl.setUsed(true);
        Set<String> callingMethods = methodDeclarationOfImpl.getCallingMethods();
        if (callingMethods == null) {
            callingMethods = new HashSet<>();
            methodDeclarationOfImpl.setCallingMethods(callingMethods);
        }
        callingMethods.add(key + "." + callingMethodDeclaration.getName());
    }

    private static MethodDeclaration findMethodByNameAndParamCount(List<MethodDeclaration> methodList, String methodName, int paramCount) {
        for (MethodDeclaration methodDeclaration : methodList) {
            if (methodDeclaration.getName().equals(methodName) && methodDeclaration.getParameters().size() == paramCount) {
                return methodDeclaration;
            }
        }
        return null;
    }

    private static void convertFieldListToFieldMapInAllClasses(Map<String, ClassDeclaration> classDeclarationMap) {
        for (String key : classDeclarationMap.keySet()) {
            ClassDeclaration classDeclaration = classDeclarationMap.get(key);
            if (classDeclaration == null) {
                continue;
            }
            if (classDeclaration.getFieldList() == null || classDeclaration.getFieldList().isEmpty()) {
                continue;
            }
            classDeclaration.setFieldMap(Common.convertFieldListToMap(classDeclaration.getFieldList()));
        }
    }

    private static void printMapToFile(Map<String, ClassDeclaration> classDeclarationMap) throws IOException {
        MapPrinterReader.printMapToFile(classDeclarationMap, metadataFileName);
    }

    private static void checkIfAnyClassesHasDuplicateMethodNamesWithSameParamCount(Map<String, ClassDeclaration> classDeclarationMap) {
        Set<String> duplicates = new HashSet<>();
        for (String key : classDeclarationMap.keySet()) {
            ClassDeclaration classDeclaration = classDeclarationMap.get(key);
            if (classDeclaration == null) {
                continue;
            }
            List<MethodDeclaration> methodList = classDeclaration.getMethodList();
            for (MethodDeclaration methodDeclaration : methodList) {
                String methodName = methodDeclaration.getName();
                int paramCount = methodDeclaration.getParameters().size();
                String combinedKey = methodName + "-" + paramCount;
                if (!duplicates.contains(combinedKey)) {
                    duplicates.add(combinedKey);
                } else {
                    System.out.println("Duplicate method found in class: " + key + " | Method: " + methodName + " | Param count: " + paramCount);
                }
            }
            duplicates.clear();
        }
    }


    private static void mapAllFiles(File file, Map<String, ClassDeclaration> classDeclarationMap) {
        if (file.isDirectory()) {
            if (file.getAbsolutePath().endsWith("vo") || file.getAbsolutePath().startsWith(excludedPackage)) {
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
            MethodExtractorAdvanced.ClassAnalysisResult result = MethodExtractorAdvanced.analyzeClass(file.getAbsolutePath(), rootPath);
            List<MethodExtractorAdvanced.MethodAnalysisResult> methodResult = result.getMethodResults();
            populateMethodDeclarations(methodResult, classDeclaration);
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
        Map<String, MethodDeclaration> declarationMap = new HashMap<>();
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
            if (declarationMap.containsKey(methodDeclaration.getName() + "-" + methodDeclaration.getParameters().size())) {
                throw new RuntimeException("Duplicate method name - size");
            }
            declarationMap.put(methodDeclaration.getName() + "-" + methodDeclaration.getParameters().size(), methodDeclaration);
        }
        classDeclaration.setMethodList(methodDeclarations);
        classDeclaration.setMethodMap(declarationMap);
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
        if (paramsArray.length == 1 && paramsArray[0].trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(paramsArray).toList();
    }


    private static String getClassNameFromPath(String filePath) {
        // Remove the root path and the .java extension
        String relativePath = filePath.replace(rootPath + File.separator, "").replace(".java", "");
        // Replace file separators with dots to form the fully qualified class name
        return relativePath.replace(File.separator, ".");
    }
}
