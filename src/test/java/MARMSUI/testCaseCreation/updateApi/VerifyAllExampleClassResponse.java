package MARMSUI.testCaseCreation.updateApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class VerifyAllExampleClassResponse {
    // copy this to a test class in main project
    // get the example response from response_content file and generate the response
    // via the objectmapper writetostring method

    static Set<String> methodNames = new HashSet<>();
    static Set<String> nonExampleMtdNames = new HashSet<>();
    static List<String> whenCalls = new ArrayList<>();
    static Map<String, String> complexClassDeclaration = new HashMap<>(); // to store method declaration to generate eg. class EventLogVo

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String filename = "/Users/macuser/Desktop/response_content";
        FileInputStream fileInputStream = new FileInputStream(filename);
        List<TestModel> testModelList = iterateFileContentAndPopulateTestModel(fileInputStream);
        System.out.println(testModelList.size());
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        verifyRequestString(testModelList, mapper);
        fileInputStream.close();
        printWhenMethodCalls();
        printComplexMethodCalls();
    }

    private static void verifyRequestString(List<TestModel> testModelList, ObjectMapper mapper) throws JsonProcessingException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        for (TestModel testModel : testModelList) {
            if (testModel.isExampleRequest) {
                generateForExample(mapper, testModel);
            } else {
                generateForNonExample(testModel);
            }
        }
    }

    private static void generateForNonExample(TestModel testModel) {
        // print out the method declaration for non example mapper methods
        String[] paramDataTypes = testModel.requestTypes.split(",");
        String[] paramsArr = testModel.request.split("~");

        // reconstructedMethodName will be used in the when call and when generating the obj from response string
        String reconstructedMethodName = generateMethodName(testModel);
//        printMethodDeclarationForNonExample(testModel, reconstructedMethodName);
        generateRequestForNonExample(paramsArr, paramDataTypes, reconstructedMethodName, testModel);

        generateResponseForNonExample(testModel.response, testModel.returnType, reconstructedMethodName);
    }

    private static void printWhenMethodCalls() {
        System.out.println(String.format("private void initialization(){"));
        System.out.println(String.format("try {"));
        for(String s : whenCalls) {
            System.out.println(s);
        }
        System.out.println(String.format("} catch(Exception e) { \n System.out.println(\"Error in initialization\"); \n}\n}"));
    }

    private static void generateResponseForNonExample(String response, String returnType, String methodName) {

        if (returnType.equals("String") || returnType.equals("java.lang.String")) {
            printMethodDeclarationForNonExample(returnType, methodName);
            System.out.println(String.format("return %s;\n}\n", response));
        } else if (returnType.equals("int") || returnType.endsWith("Integer")) {
            printMethodDeclarationForNonExample(returnType, methodName);
            System.out.println(String.format("return %s;\n}\n", response));
        } else if (returnType.equals("double") || returnType.endsWith("Double")) {
            printMethodDeclarationForNonExample(returnType, methodName);
            System.out.println(String.format("return %s;\n}\n", response));
        } else if (returnType.equals("boolean") || returnType.endsWith("Boolean")) {
            printMethodDeclarationForNonExample(returnType, methodName);
            System.out.println(String.format("return %s;\n}\n", response));
        } else if (returnType.equals("java.sql.Date") || returnType.equals("java.util.Date") || returnType.contains("Date")) {
            printMethodDeclarationForNonExample(returnType, methodName);
            System.out.println(String.format("SimpleDateFormat sdf = new SimpleDateFormat(yyyy-MM-dd HH:mm:ss);"));
            System.out.println(String.format("return sdf.format(%s);\n}\n", response));
        } else if (returnType.equals("long") || returnType.endsWith("Long")) {
            printMethodDeclarationForNonExample(returnType, methodName);
            System.out.println(String.format("return %sl;\n}\n", response));
        } else if (returnType.equals("short") || returnType.endsWith("Short")) {
            printMethodDeclarationForNonExample(returnType, methodName);
            System.out.println(String.format("return Short.valueOf(\"%s\");\n}\n", response));
        } else {
            // complex obj
            System.out.println(generateMethodDeclarationForCmplxClass(response, returnType, methodName));
        }
//        String paramsString = extractParams(new String[]{response},new String[]{returnType});
//        System.out.println(mtdDeclaration);
    }


    private static void generateRequestForNonExample(String[] params, String[] paramTypes, String reconstructedMethodName, TestModel testModel) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (params.length != paramTypes.length) {
            throw new RuntimeException("Params list not equal in length");
        }

        String whenDeclaration = "when(%s.%s(%s)).thenReturn(%s());";

        // only print the verify method for the actual data that is entering the db
        // for stopmail its updateStopMailInfo methd

        // print the when method call here or store it to be called later, but reference the method name here
        String paramsToEnterMethod = "";
        if(!params[0].equals("[]") && !paramTypes[0].equals("[]")) {
            paramsToEnterMethod = extractParams(params, paramTypes);
        }
        String whenCall = String.format(whenDeclaration, testModel.mapperName, testModel.mtdName, paramsToEnterMethod, reconstructedMethodName);
        whenCalls.add(whenCall);
    }

    private static String extractParams(String[] params, String[] paramTypes) {
        String toReturn = "";
        for (int i = 0; i < params.length; i++) {
            String type = paramTypes[i];
            if (type.contains("java.sql.Date") || type.contains("java.util.Date")) {
                toReturn += String.format("sdf.format(%s),", params[i]);
            } else if (type.contains("Long") || type.equals("long")) {
                toReturn += String.format("%sl,", params[i]);
            } else if (type.contains("Short") || type.equals("short")) {
                toReturn += String.format("Short.valueOf(\"%s\"),", params[i]);
            } else if (type.contains("Integer") || type.equals("int") || type.contains("Double") || type.equals("double")) {
                toReturn += String.format("%s,", params[i]);
            } else if (type.contains("String")) {
                toReturn += String.format("%s,", params[i]);
            } else {
                // complex data type
                toReturn += generateComplexClassMtdCall(params[i], type);
            }
        }
        if (!toReturn.equals("")) {
            return toReturn.substring(0, toReturn.length() - 1);
        }
        return toReturn;
    }

    private static String generateComplexClassMtdCall(String param, String type) {
        int count = 0;
        String nameForMethod = "";
        while (true) {
            String countStr = String.valueOf(count);
            nameForMethod = "get" + type + countStr;
            if (!complexClassDeclaration.containsKey(nameForMethod)) {
                complexClassDeclaration.put(nameForMethod, generateMethodDeclarationForCmplxClass(param, type, nameForMethod));
                break;
            }
            count++;
        }
        return nameForMethod + "(),";
    }

    private static String generateMethodDeclarationForCmplxClass(String val, String type, String nameForMethod) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("private %s %s() {\n", type, nameForMethod));
        builder.append(String.format("String val = \"%s\";\n", transformObjectString(val)));
        builder.append("ObjectMapper mapper = new ObjectMapper();\n" +
                "        mapper.setDateFormat(new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\"));\n" +
                "        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);\n" +
                "        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);\n" +
                "        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);\n" +
                "        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);\n");
        builder.append(String.format("%s obj = null;\n" +
                "        try {\n" +
                "            obj = mapper.readValue(val, new TypeReference<%s>(){});\n" +
                "        } catch (Exception e) {\n" +
                "            throw new RuntimeException(\"%s threw exception\");\n" +
                "        }\n",type, type, nameForMethod));
        builder.append("return obj;\n}\n");
        return builder.toString();
    }

    private static String transformObjectString(String val) {
        StringBuffer toReturn = new StringBuffer();
        for(char c : val.toCharArray()) {
            if(c == '\"'){
                toReturn.append("\\");
            }
            toReturn.append(c);
        }
        return toReturn.toString();
    }


    private static void printMethodDeclarationForNonExample(String returnType, String name) {
        System.out.println(String.format("private %s %s(){", returnType, name));
    }

    private static String generateMethodName(TestModel testModel) {
        String methodName = testModel.mtdName;
        String mapper = testModel.mapperName;
        int count = 0;
        String finalName = "";
        while (true) {
            String countStr = String.valueOf(count);
            finalName = "get" + mapper + methodName + countStr;
            ;
            if (!nonExampleMtdNames.contains(finalName)) {
                nonExampleMtdNames.add(finalName);
                return finalName;
            }
            count++;
        }
    }


    private static void generateForExample(ObjectMapper mapper, TestModel testModel) throws JsonProcessingException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Map<String, Object> countryExamples = mapper.readValue(testModel.request, new TypeReference<HashMap<String, Object>>() {
        });
        List<Object> innerList1 = (List) countryExamples.get("oredCriteria");
        Map<String, Object> innerMap1 = (HashMap) innerList1.get(0);
        List<Object> innerList2 = (List) innerMap1.get("criteria");
        printMethodDeclaration(testModel);
        String continuedMethodBody = "example.createCriteria()";
        for (Object o : innerList2) {
            Map<String, Object> innerMap3 = (HashMap) o;
            String conditionStr = (String) innerMap3.get("condition");
            String conditionVal = innerMap3.get("value").toString();
            continuedMethodBody += generateExampleInstance(conditionStr, conditionVal, testModel);
        }
        System.out.println(continuedMethodBody + ";\nreturn example;\n}\n");
    }

    private static void printMethodDeclaration(TestModel testModel) {
        int count = 1;
        String name = "";
        while (true) {
            String countStr = String.valueOf(count);
            name = "get" + testModel.objectClassName + testModel.mtdName + countStr;
            if (!methodNames.contains(name)) {
                methodNames.add(name);
                break;
            }
            count++;
        }
        System.out.println(String.format("private %s %s(){", testModel.objectClassName, name));
        System.out.println(String.format("%s example = new %s();", testModel.objectClassName, testModel.objectClassName));
    }

    private static String generateExampleInstance(String condition, String val, TestModel testModel) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String basePath = "/Users/macuser/Documents/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/database/sql/persistence/model";
        String className = generateTypeFromExampleClass(testModel.objectClassName);
        String actualPath = basePath + "/" + className + ".java";
        String generatedField = transformConditionToField(condition);
        String dataType = extractDateTypeOfField(actualPath, generatedField);
        String methodName = transformFieldToMethod(generatedField);

//        System.out.println(methodName);
        if (dataType.equals("Long") || dataType.equals("long")) {
            val += "l";
        } else if (dataType.equals("Short") || dataType.equals("short")) {
            val = String.format("Short.valueOf(\"%s\")", val);
        } else if (dataType.equals("java.lang.String") || dataType.equals("String")) {
            val = String.format("\"%s\"", val);
        } else if (dataType.isEmpty()) {
            try {
                Long.parseLong(val);
                val += "l";
            } catch (NumberFormatException e) {
                val = String.format("\"%s\"", val);
            }
        }
        return String.format(".%s(%s)", methodName, val);
    }

    private static String extractDateTypeOfField(String path, String fieldToFind) {
        // using the compilation static to retrieve the data type for the fieldName
        CompilationUnit cu = null;
        try {
            cu = StaticJavaParser.parse(Paths.get(path));
        } catch (Exception e) {
            throw new RuntimeException("Cannot compile");
        }
        NodeList<TypeDeclaration<?>> types = cu.getTypes();
        AtomicReference<String> dataType = new AtomicReference<>("");
        for (TypeDeclaration<?> type : types) {
            // Get the fields within this type
            List<FieldDeclaration> fields = type.getFields();

            for (FieldDeclaration fieldDeclaration : fields) {
                NodeList<VariableDeclarator> variableDeclarators = fieldDeclaration.getVariables();
                variableDeclarators.forEach(x -> {
                    if (x.getNameAsString().equals(fieldToFind)) {
                        dataType.set(x.getTypeAsString());
                    }
                });
            }
        }
        return dataType.get();
    }

    private static String generateTypeFromExampleClass(String exampleclass) {
        int end = exampleclass.indexOf("Example");
        return exampleclass.substring(0, end);
    }


    private static String transformFieldToMethod(String field) {
        return String.format("and%sEqualTo", field.substring(0, 1).toUpperCase() + field.substring(1));
    }

    private static String transformConditionToField(String condition) {
        int end = condition.indexOf("=");
        String modified = condition.substring(0, end).trim().toLowerCase();
        String[] arr = modified.split("_");
        String toReturn = "";
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                toReturn += arr[i];
                continue;
            }
            toReturn += arr[i].substring(0, 1).toUpperCase() + arr[i].substring(1);
        }
        return toReturn;
    }

    private static List<TestModel> iterateFileContentAndPopulateTestModel(FileInputStream fileInputStream) throws JsonProcessingException {
        Scanner scanner = new Scanner(fileInputStream);
        List<TestModel> testModelList = new ArrayList<>();
        int count = 0;
        boolean isExampleRequest = false;
        TestModel testModel = null;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                count = 0;
                isExampleRequest = false;
                testModel = new TestModel();
                continue;
            }
            count++;
            if (count == 1) {
                testModel.mapperName = extractMapperName(line);
            } else if (count == 2) {
                testModel.returnType = line;
            } else if (count == 3) {
                if (line.contains("updateStopMailInfo")) {
                    System.out.println("");
                }
                if (line.contains("Example")) {
                    isExampleRequest = true;
                    testModel.mtdName = line;
                    testModel.isExampleRequest = true;
                    continue;
                }
                testModel.mtdName = line;
            } else if (count == 4) {
                testModel.response = line;
            } else if (count == 5) {
                testModel.request = line;
            } else if (count == 6) {
                if (isExampleRequest && line.contains(",")) {
                    throw new RuntimeException("Example method has more than one params");
                }
                if (isExampleRequest) {
                    testModel.objectClassName = line; // contain eg. CusAddnInfoExample
                    testModelList.add(testModel);
                    isExampleRequest = false;
                    testModel = null;
                } else {
                    testModel.requestTypes = line;
                    testModelList.add(testModel);
                    testModel = null;
                }
            }
        }
        return testModelList;
    }

    private static String extractMapperName(String line) {
        String key = "mappers.";
        int start = line.indexOf(key) + key.length();
        String name = line.substring(start).trim();
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    static class TestModel {

        public String request;
        public String objectClassName;
        public String mtdName;
        public String mapperName;
        public boolean isExampleRequest = false;
        public String returnType;
        public String response;
        public String requestTypes;

        public TestModel() {
        }


    }
}
