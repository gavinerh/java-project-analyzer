package MARMSUI.migration.newer1;

import MARMSUI.migration.ExtractClassName;
import MARMSUI.migration.ExtractFieldsFromClass;
import MARMSUI.migration.model.Type;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class CompleteSqlQueryConverter {

    static Map<String, String> mapOfTypes = new HashMap<>();

    public static void main(String[] args) throws Exception {
        mapOfTypes.put("m", "resultMap");
        mapOfTypes.put("t", "resultType");
        mapOfTypes.put("s", "select");
        mapOfTypes.put("u", "update");
        mapOfTypes.put("i", "insert");
        mapOfTypes.put("d", "delete");
        mapOfTypes.put("long", "java.lang.Long");
        mapOfTypes.put("string", "java.lang.String");
        mapOfTypes.put("int", "java.lang.Integer");
        mapOfTypes.put("integer", "java.lang.Integer");
        mapOfTypes.put("date", "java.util.Date");
        Map<String, String> paramObjMap = new HashMap<>();
        File resultMapFileInput = null;
        Scanner scanner = new Scanner(System.in);
        String completeQuery = "";
        String methodName = "";
        String queryType = "";
        String resultTypeOrMap = "";
        String idOfResultMap = "";
        String returnType = "";
        String filePathForResultMap = "";
        String objVariableName = "";
        String tempFilePathForParamObj = "";
        List<Type> parameterList = null;
        List<String> resultMapString = null;
        int counter = 0;
        String response = "";
        boolean printedSqlMsg = false;
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                switch (counter) {
                    case 0:
                        if (!printedSqlMsg) {
                            System.out.println("Enter the sql query:");
                            printedSqlMsg = true;
                        }
                        String line = scanner.nextLine();
                        // Append the current line to the StringBuilder

                        if (line.equals("-")) {
                            counter++;
                            completeQuery = extractSqlQuery(stringBuilder.toString());
                            printedSqlMsg = false;
                            stringBuilder = new StringBuilder();
                        } else {
                            stringBuilder.append(line);
                        }
                        continue;
                    case 1:
                        System.out.println("Enter name of method:");
                        response = scanner.nextLine();
                        methodName = response;
                        counter++;
                        continue;
                    case 2:
                        System.out.println("Enter query type: (s: select, u: update, i: insert, d: delete etc..)");
                        queryType = scanner.nextLine();
                        if (!mapOfTypes.containsKey(queryType)) {
                            continue;
                        }
                        if(queryType.equals("i") || queryType.equals("d") || queryType.equals("u")){
                            counter += 2;
                            resultTypeOrMap = mapOfTypes.get("t");
                            returnType = "int";
                        }
                        queryType = mapOfTypes.get(queryType);
                        counter++;
                        continue;
                    case 3:
                        System.out.println("Enter return type: (m: resultMap, t:resultType)");
                        resultTypeOrMap = mapOfTypes.get(scanner.nextLine());
                        if (resultTypeOrMap.equals("resultMap")) {
                            System.out.println("Enter file path that result map will map to:");
                            filePathForResultMap = scanner.nextLine();
                        } else if (resultTypeOrMap.equals("resultType")) {
                            System.out.println("Enter the return type");
                            returnType = scanner.nextLine();
                            if (!mapOfTypes.containsKey(returnType)) {
                                continue;
                            }
                            returnType = mapOfTypes.get(returnType);
                            counter++;
                        }
                        counter++;
                        continue;
                    case 4:
                        // determine if the file path for resultmap is valid
                        if (!filePathForResultMap.isBlank()) {

                            resultMapFileInput = new File(filePathForResultMap);
                            if (!resultMapFileInput.exists()) {
                                System.out.println("Wrong file path entered");
                                counter--;
                                continue;
                            }
                            counter++;
                            continue;
                        }
                    case 5:
                        // populate the parameter object name and the location of the class file of that obj
                        if (!printedSqlMsg) {
                            System.out.println("If there are object insertion into sql to replace '?', enter the object variable name: (enter (-) if none");
                            printedSqlMsg = !printedSqlMsg;
                        } else {
                            System.out.println("Any more obj variable you want to enter (-) if none");
                        }
                        objVariableName = scanner.nextLine();
                        if (objVariableName.equals("-")) {
                            objVariableName = "";
                            printedSqlMsg = false;
                        } else {
                            counter--;
                            System.out.println("Enter the full path of the object class location: ");
                            tempFilePathForParamObj = scanner.nextLine();
                            File inputStream = null;

                            inputStream = new File(tempFilePathForParamObj);
                            if (!inputStream.exists()) {
                                System.out.println("Entered wrong file path");
                                continue;
                            }

                            paramObjMap.put(objVariableName, tempFilePathForParamObj);
                            objVariableName = "";
                        }
                        counter++;
                        continue;
                    case 6:
                        // enter the params to the sql query
                        if (!printedSqlMsg) {
                            System.out.println("Enter the params to replace the '?' in the prepare statement: (prepareStatement.setLong ...) ");
                            printedSqlMsg = !printedSqlMsg;
                        }
                        String param = scanner.nextLine();
                        if (param.equals("-")) {
                            counter++;
                            parameterList = extractParams(stringBuilder.toString());
                            stringBuilder = new StringBuilder();
                        } else {
                            stringBuilder.append(param);
                        }
                        continue;
                    case 7:
                        // enter the sql columns that will be collected into resultMap
                        System.out.println("Enter the \"VARIABLE\" representing the object that the sql will be mapped to (after resultSet.next()): ");
                        String identifier = scanner.nextLine();
                        if (identifier.isBlank()) {
                            continue;
                        } else if (identifier.equalsIgnoreCase("-")){
                            identifier = "";
                        }
                        if (resultTypeOrMap.equals("resultMap")) {
                            String enteredSqlCols = promptUserToEnterSqlCols(scanner);
                            if (enteredSqlCols.isBlank()) {
                                enteredSqlCols = extractColumnsFromSql(completeQuery);
                            }
                            List<String> sqlCols = generateColumnArr(enteredSqlCols);
                            resultMapString = generateResultMap(scanner, filePathForResultMap, identifier, sqlCols);
                            System.out.println(resultMapString.size());
                        }
                        counter++;
                        break;
                    case 8:
                        // create the java method
                        Map<String, Type> classObjNames = extractClassNameToUseInJavaMtd(paramObjMap); // use this to populate java param declaration of complex obj
//            use parameterList to extract the values inserted in prepared statement '?'
                        printJavaMethod(returnType, filePathForResultMap, methodName, parameterList, classObjNames);
                        System.out.println("\n");
                        // replace ? with actual values
                        String finalQuery = replaceQuestionWithActualParam(completeQuery, parameterList, paramObjMap, scanner);
                        // print sql query
                        printSqlQuery(finalQuery, queryType, methodName, returnType);
                        System.out.println("\nPrinting the resultMap if any: ");
                        // print resultmap
                        printResultMap(resultMapString, methodName, filePathForResultMap);
                        System.out.println("===============================================================");
                        counter++;
                    case 9:
                        // reset all variables
                        if (paramObjMap != null) {
                            paramObjMap.clear();
                        }

                        if (resultMapFileInput != null)
                            resultMapFileInput = null;
                        completeQuery = "";
                        methodName = "";
                        queryType = "";
                        resultTypeOrMap = "";
                        idOfResultMap = "";
                        returnType = "";
                        filePathForResultMap = "";
                        objVariableName = "";
                        tempFilePathForParamObj = "";
                        if (parameterList != null) {
                            parameterList.clear();
                        }
                        if (resultMapString != null) {
                            resultMapString.clear();
                        }
                        counter = 0;
                        response = "";
                        printedSqlMsg = false;
                        continue;
                    default:
                        throw new RuntimeException("Should not get here");
                }
            } catch (Exception e) {
                System.out.println("Error encountered: " + e.getMessage());
                counter = 9;
            }

        }
    }


    private static void printResultMap(List<String> resultMapList, String methodName, String filePathForResultMap) {
        if (resultMapList == null || resultMapList.isEmpty()) {
            return;
        }
        String path = extractTypeWithPath(filePathForResultMap);
        String template = "<resultMap id=\"%s\" type=\"%s\">\n%s</resultMap>";
        StringBuilder stringBuilder = new StringBuilder();
        for (String result : resultMapList) {
            stringBuilder.append(result);
            stringBuilder.append("\n");
        }
        String toPrint = String.format(template, methodName.substring(0, 1).toUpperCase() + methodName.substring(1), path, stringBuilder.toString());
        System.out.println(toPrint);
    }


    private static String extractTypeWithPath(String path) {
        String identifier = "com/sg/sq";
        int start = path.indexOf(identifier);
        int end = path.indexOf(".java", start);
        String truncPath = path.substring(start, end);
        while (truncPath.contains("/")) {
            truncPath = truncPath.replace("/", ".");
        }
        return truncPath;
    }

    private static void printSqlQuery(String query, String queryType, String methodName, String returnType) {
        String template = "<%s id=\"%s\" %s>\n%s</%s>";
        String returnId = "";
        if (queryType.equalsIgnoreCase("select")) {
            if (returnType != null && !returnType.isBlank()) {
                returnId += "resultType=\"";
                returnId += returnType;
                returnId += "\"";
            } else {
                returnId += "resultMap=\"";
                returnId += methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
                returnId += "\"";
            }
        }
        String toPrint = String.format(template, queryType, methodName, returnId, query, queryType);
        System.out.println("Printing the query: ");
        System.out.println(toPrint);
    }

    private static String replaceQuestionWithActualParam(String query, List<Type> paramList, Map<String, String> inputVariableMap, Scanner scanner) throws Exception {
        int count = 0;
        String finalQuery = "";
        for (char c : query.toCharArray()) {
            if (c != '?') {
                finalQuery += c;
            } else {
                finalQuery += replaceQuestion(paramList.get(count), inputVariableMap, scanner);
                count++;
            }
        }
        return finalQuery;
    }

    private static String replaceQuestion(Type type, Map<String, String> inputVarMap, Scanner scanner) throws Exception {
        String template = "#{%s,jdbcType=%s}";
        try {
            if (!type.name.contains(".")) {
                return String.format(template, type.name.trim(), type.sqlType.trim());
            } else {
                String[] split = splitOnDot(type.name); // first is the variable, second is the field name of that variable class
                String fieldName = getFieldName(split[1], inputVarMap.get(split[0]), scanner);
                return String.format(template, split[0] + "." + fieldName, type.sqlType);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static String[] splitOnDot(String name) {
        String[] arr = new String[2];
        int stop = name.indexOf(".");
        arr[0] = name.substring(0, stop).trim();
        arr[1] = name.substring(stop + 1);
        return arr;
    }

    private static String getFieldName(String getter, String filePath, Scanner scanner) throws Exception {
        String identifier = "get";
        int start = getter.toLowerCase().indexOf(identifier) + identifier.length();
        int end = getter.indexOf("(", start);
        String field = getter.substring(start, end).toLowerCase();
        Map<String, Type> map = new HashMap<>();
        extractFieldsFromClass(filePath, map);
        Type type = map.get(field);
        if(type == null) {
//            Scanner scanner = new Scanner(System.in);
            String parentPath = CompleteMapStructMappingPrinter.getUserInput(scanner,"Enter parent class of " + filePath);
//            scanner.close();
            extractFieldsFromClass(parentPath,map);
            return map.get(field).name;
        } else {
            return type.name;
        }
    }

    private static void printJavaMethod(String returnType, String resultMapClassFile, String methodName, List<Type> params, Map<String, Type> map) throws Exception {
        String template = "List<%s> %s(%s);";
        String altTemplate = "%s %s(%s);";
        String paramString = "";
        Map<String, Type> streamlinedParams = removeDuplicate(params, map);
        for (String key : streamlinedParams.keySet()) {
            Type param = streamlinedParams.get(key);
            String temp = "@Param(\"%s\") %s %s,";
            String complete = String.format(temp, param.name.trim(), param.type.trim(), param.name.trim());
            paramString += complete;
        }
        if(!paramString.isBlank()) {
            paramString = paramString.substring(0, paramString.length() - 1);
        }
        String toPrint = "";
        if (!returnType.isBlank() && StringUtils.isBlank(resultMapClassFile)) {
            if(returnType.equals("int")) {
                toPrint = String.format(altTemplate, returnType,methodName,paramString);
            } else {
                toPrint = String.format(template, returnType, methodName, paramString);
            }
        } else if (StringUtils.isNotBlank(resultMapClassFile) && returnType.isBlank()) {
            toPrint = String.format(template, ExtractClassName.extract(new FileInputStream(resultMapClassFile)), methodName, paramString);
        } else {
            throw new RuntimeException("Mismatch in return type and resultMap");
        }
        System.out.println(toPrint);
    }

    private static Map<String, Type> removeDuplicate(List<Type> params, Map<String, Type> complexObj) {
        Map<String, Type> cleaned = new HashMap<>();
        for (Type type : params) {
            if (!type.name.contains(".")) {
                cleaned.put(type.name, type);
            }
        }
        for (String key : complexObj.keySet()) {
            cleaned.put(key, complexObj.get(key));
        }
        return cleaned;
    }

    private static Map<String, Type> extractClassNameToUseInJavaMtd(Map<String, String> map) throws Exception {
        Map<String, Type> typeList = new HashMap<>();
        for (String objName : map.keySet()) {
            String fileName = map.get(objName);
            FileInputStream fileInputStream = new FileInputStream(fileName);
            typeList.put(objName, new Type(objName, ExtractClassName.extract(fileInputStream), null));
        }
        return typeList;
    }

    public static List<String> generateColumnArr(String sql) {
        List<String> listOfCol = new ArrayList<>();
        boolean insideParams = false;
        String column = "";
        for (int i = 0; i < sql.length(); i++) {
            String letter = sql.substring(i, i + 1);
            if (letter.equals("(")) {
                insideParams = true;
                column += letter;
                continue;
            }
            if (letter.equals(")")) {
                insideParams = false;
                column += letter;
                continue;
            }
            column += letter;
            if (letter.equals(",") && !insideParams || i == sql.length() - 1) {
                String trimmedCol = column.trim();
                if (trimmedCol.substring(trimmedCol.length() - 1, trimmedCol.length()).equals(",")) {
                    trimmedCol = trimmedCol.substring(0, trimmedCol.length() - 1);
                }
                listOfCol.add(trimmedCol);
                column = "";
            }

        }
        return listOfCol;
    }

    public static String extractColumnsFromSql(String query) {
        // get the operation string (select, update, ...)
        String operationString = "";
        String trimmedQuery = query.trim();
        String[] arr = trimmedQuery.split(" ");
        for (String val : arr) {
            String trimmed = val.trim();
            if (trimmed.equalsIgnoreCase("select")) {
                operationString = trimmed;
                break;
            } else if (trimmed.equalsIgnoreCase("update")) {
                operationString = trimmed;
                break;
            } else if (trimmed.equalsIgnoreCase("delete")) {
                operationString = trimmed;
                break;
            } else if (trimmed.equalsIgnoreCase("insert")) {
                operationString = trimmed;
                break;
            }
        }
        int start = trimmedQuery.indexOf(operationString) + operationString.length();
        String loweredCase = trimmedQuery.toLowerCase();
        int end = loweredCase.indexOf("from", start);
        String cols = trimmedQuery.substring(start, end);
        return cols;
    }

    private static String promptUserToEnterSqlCols(Scanner scanner) {
        System.out.println("Do you wish to enter your own sql cols? (-) to ignore");
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().equals("-")) {
                return stringBuilder.toString();
            } else {
                stringBuilder.append(line);
            }
        }
    }

    private static List<String> generateResultMap(Scanner scanner, String inputStream, String identifier, List<String> sqlCols) throws Exception {
        if (inputStream == null) {
            throw new RuntimeException("File not even opened");
        }
        System.out.println("Enter setter methods for the resultMap creation: ");
        StringBuilder stringBuilder = new StringBuilder();
        List<String> resultMapString = null;
        while (true) {
            String line = scanner.nextLine();
            if (line.equals("-")) {
                resultMapString = extractStringToResultMap(identifier, inputStream, stringBuilder.toString(), sqlCols);
                stringBuilder.delete(0, stringBuilder.length());
                break;
            } else {
                stringBuilder.append(line);
            }
        }
        return resultMapString;
    }

    private static List<String> extractStringToResultMap(String identifier, String fileInputStream, String setterMtds, List<String> sqlColumns) throws Exception {
        String[] setterArr = setterMtds.split(";");
        Map<String, Type> actualFieldsInClass = new HashMap<>();
        extractFieldsFromClass(fileInputStream, actualFieldsInClass);
        List<String> fields = extractFieldsFromSetter(setterArr, identifier);
        List<Type> correctFieldNames = compareFields(actualFieldsInClass, fields);
        List<String> resultMapString = pairUpSqlColsWithFieldName(sqlColumns, correctFieldNames);
        return resultMapString;
    }

    private static List<String> pairUpSqlColsWithFieldName(List<String> sqlCols, List<Type> fieldNames) {
        if (sqlCols.size() != fieldNames.size()) {
            throw new RuntimeException("Sql cols are not the same length as fieldnames");
        }
        List<String> resultMaps = new ArrayList<>();
        String resultTemplate = "<result column=\"%s\" jdbcType=\"%s\" property=\"%s\"/>";
        for (int i = 0; i < sqlCols.size(); i++) {
            String result = String.format(resultTemplate, sqlCols.get(i), fieldNames.get(i).sqlType, fieldNames.get(i).name);
            resultMaps.add(result);
        }
        return resultMaps;
    }

    private static void extractFieldsFromClass(String fileInputStream, Map<String, Type> map) throws Exception {
        ExtractFieldsFromClass.execute(new FileInputStream(fileInputStream), map);
    }

    public static Map<String, Type> returnsFieldsMap(FileInputStream in) throws Exception {
        // returns a map: key is lower case field name, value is the actual field name
        CompilationUnit cu = StaticJavaParser.parse(in);

        // Prepare a map to hold field names and their types
        Map<String, Type> fieldsMap = new HashMap<>();

        // Visit class declarations in the file
        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(c -> {
            // For each class, visit its fields
            c.getFields().forEach(field -> {
                // For each field, extract its name and type, and put them in the map
                for (VariableDeclarator variable : field.getVariables()) {
                    fieldsMap.put(variable.getNameAsString().toLowerCase(), new Type(variable.getNameAsString(), variable.getTypeAsString(), convertJavaTypeToSql(variable.getTypeAsString())));
                }
            });
        });

        // Close the file input stream
        in.close();

        // Print the extracted fields and their types
        fieldsMap.forEach((name, type) -> System.out.println(name + " : " + type));
        return fieldsMap;
    }

    public static String convertJavaTypeToSql(String type) {
        switch (type) {
            case "String", "java.lang.String, char":
                return "VARCHAR";
            case "Long", "long", "int", "Integer", "java.lang.Integer", "java.lang.Long":
                return "NUMERIC";
            case "Date", "java.util.Date", "java.sql.Date":
                return "TIMESTAMP";
            default:
                throw new RuntimeException("Encountered unmapped type");
        }
    }

    private static List<Type> compareFields(Map<String, Type> actualFields, List<String> fields) throws Exception {
        // could modify to compare the closest field, prob by not choosing by the actual char, tolower for instance
        List<Type> correctFieldNames = new ArrayList<>();
        for (String field : fields) {
            if (actualFields.containsKey(field.toLowerCase())) {
                correctFieldNames.add(actualFields.get(field.toLowerCase()));
            } else {
                System.out.println("field name does not match: " + field);
                Scanner scanner = new Scanner(System.in);
                String parentPath = CompleteMapStructMappingPrinter.getUserInput(scanner,"Enter any parent class file path ");
                ExtractFieldsFromClass.execute(new FileInputStream(parentPath),actualFields);
                if(!actualFields.containsKey(field.toLowerCase())) {
                    throw new RuntimeException("Field name does not match second time: " + field);
                } else {
                    correctFieldNames.add(actualFields.get(field.toLowerCase()));
                }
            }
        }
        return correctFieldNames;
    }

    public static List<String> extractFieldsFromSetter(String[] arr, String identifier) {
        if(identifier.equalsIgnoreCase("-")) {
            return new ArrayList<>();
        }
        identifier += ".set";
        List<String> fields = new ArrayList<>();
        for (String setter : arr) {
            if (setter.contains(identifier)) {
                int start = setter.indexOf(identifier) + identifier.length();
                int end = setter.indexOf("(", start);
                String field = setter.substring(start, end);
                field = field.substring(0, 1).toLowerCase() + field.substring(1);
                fields.add(field);
            }
        }
        return fields;
    }

    private static List<Type> extractParams(String params) {
        if(params.isBlank()) {
            return new ArrayList<>();
        }
        String[] paramArr = params.split(";");
        List<Type> typeList = new ArrayList<>();
        for (String param : paramArr) {
            Type type = new Type();
            if (param.contains("setLong(")) {
                type.type = "long";
                type.sqlType = "NUMERIC";
            } else if (param.contains("setString(")) {
                type.type = "String";
                type.sqlType = "VARCHAR";
            } else if (param.contains("setDate(")) {
                type.sqlType = "TIMESTAMP";
                type.type = "Date";
            } else if (param.contains("setInt(")) {
                type.sqlType = "NUMERIC";
                type.type = "int";
            } else if (param.contains("setDouble(")) {
                type.sqlType = "DECIMAL";
                type.type = "double";
            } else if (param.contains("setTimestamp(")) {
                type.sqlType = "TIMESTAMP";
                type.type = "Timestamp";
            } else if (param.contains("setFloat(")) {
                type.sqlType = "DECIMAL";
                type.type = "float";
            } else {
                throw new RuntimeException("Unable to map the type");
            }
            type.name = extractVariableName(param);
            typeList.add(type);
        }
        return typeList;
    }

    private static String extractVariableName(String param) {
        // extract intID from "pStmt.setLong(1, intID);"
        String trimmed = param.trim();
        int start = trimmed.indexOf(",") + 1;
        int end = trimmed.length() - 1;
        return trimmed.substring(start, end);
    }

    public static String extractSqlQuery(String query) {
        String[] querySplit = query.split(";");
        String completeQuery = "";
        char identifier = '\"';
        boolean enteredParam = false;
        for (String split : querySplit) {
            String partial = "";
            for (char c : split.toCharArray()) {
                if (c == identifier) {
                    enteredParam = !enteredParam;
                    continue;
                }
                if (!enteredParam) {
                    continue;
                }
                if (enteredParam) {
                    partial += c;
                }
            }
            if (!partial.isBlank()) {
                completeQuery += " " + partial;
            }
        }
        return completeQuery;
    }

}
