package MARMSUI.testCaseCreation;

import model.Variable;

import java.util.*;

public class CreateObjectForFrontend {
    public static void main(String[] args) {
        String request = "-agentId(java.lang.String)\n" +
                "-customerID(java.lang.String)\n" +
                "-programCode(java.lang.String)\n" +
                "-agentID(java.lang.String)\n" +
                "-lastReadDt(java.lang.String)\n" +
                "-familyName(java.lang.String)";
        String[] arr = request.split("\n");
        printInitializationCode(arr);

        System.out.println("\n\n=================================================\n\n");
        printFieldsInTable(arr);

        System.out.println("\n\n=================================================\n\n");
        String fieldsCurrentlyPresentForInputValidation = "progressiveupdate.ktnNo.type=${STRING}\n" +
                "progressiveupdate.contactNoHome.type=${STRING}";
        Set<String> fieldsCurrentlyPresent = new HashSet<>();
        populateCurrentFields(fieldsCurrentlyPresentForInputValidation, fieldsCurrentlyPresent);
        printInputValidationDetails(arr,"progressiveupdate", fieldsCurrentlyPresent);
    }


    private static void printInputValidationDetails(String[] arr, String prependedString, Set<String> fieldsCurrentlyPresent) {
        Map<String, String> nonDuplicateField = new HashMap<>();
        Map<String,String> toAppend = new HashMap<>();
        for(String row : arr) {
            if(!row.contains("[]") && !row.contains("java.util.List") && !extractType(row).startsWith("com.sg.sq.marmsui")) {
                nonDuplicateField.put(String.format("%s.%s.type",prependedString,extractCurrentFieldName(row)),String.format("=${%s}",mapToInputValidator(extractType(row))));
            }
        }
        if(fieldsCurrentlyPresent == null || fieldsCurrentlyPresent.isEmpty()) {
            toAppend.putAll(nonDuplicateField);
        }
        for(String key : nonDuplicateField.keySet()) {
            if(!fieldsCurrentlyPresent.contains(key)) {
                toAppend.put(key,nonDuplicateField.get(key));
            }
        }
        for(String s : toAppend.keySet()) {
            System.out.println(String.format("%s%s",s,nonDuplicateField.get(s)));
        }
    }

    private static void populateCurrentFields(String s, Set<String> currentFields){
        String[] arr = s.split("\n");
        for(String row : arr) {
            int end = row.indexOf("=");
            currentFields.add(row.substring(0,end));
        }
    }

    private static String mapToInputValidator(String type) {
        if(type.contains("boolean")){
            return "BOOLEAN";
        } else if(type.contains("java.util.Date") || type.contains("java.sql.Timestamp")) {
            return "FORMATTED_DATE";
        } else if (type.contains("int") || type.contains("short") || type.contains("double") || type.contains("long")
            || type.contains("Long") || type.contains("Integer") || type.contains("java.lang.Short")){
            return "DECIMAL";
        } else if (type.contains(".String")) {
            return "STRING";
        } else {
            System.out.println(type);
            throw new RuntimeException("Cannot map type");
        }
    }

    private static void printFieldsInTable(String[] requestArr) {
        List<String> fields = new ArrayList<>();
        List<String> types = new ArrayList<>();
        for(String row : requestArr) {
            String type = extractType(row);
            String fieldHierarchy = extractFieldHierarchy(row);
            if(type.contains("[]") || type.contains("java.util.List")){
                fields.add(fieldHierarchy); types.add("Array");
            } else if (type.startsWith("com.sg.sq.marmsui")) {
                fields.add(fieldHierarchy); types.add("Object");
            } else {
                fields.add(fieldHierarchy); types.add(cleanUpType(type));
            }
        }

        for(int i=0; i<fields.size(); i++) {
            System.out.println(i);
        }
        for(int i=0; i<fields.size(); i++) {
            System.out.println(fields.get(i));
        }
        for(int i=0; i<fields.size(); i++) {
            System.out.println(types.get(i));
        }
    }

    private static String cleanUpType(String type) {
        if(type.contains("java.")) {
            int start = 0;
            int count = 0;
            for(char c : type.toCharArray()){
                if(c == '.') {
                    start = count;
                }
                count++;
            }
            if(start == 0) {
                throw new RuntimeException("Cannot be zero");
            }
            start++;
            return type.substring(start);
        }
        return type;
    }

    private static String extractFieldHierarchy(String row) {
        int start = 1;
        int end = row.indexOf("(");
        return row.substring(start,end).replaceAll("-",".");
    }

    private static void printInitializationCode(String[] requestArr) {
        int count = 0;
        String toPrint = "%s %s = new %s();";
        Set<String> varNames = new HashSet<>();
        for(String row : requestArr) {
            String type = extractType(row);
            String currentFieldName = extractCurrentFieldName(row);
            if(type.startsWith("com.sg.sq.marmsui")) {
                if(type.contains("[]")) {
                    int varCountForObj = count;
                    int varCountForArr = count+1;
                    type = extractTypeWithoutArrayDeclaration(type);
//                    String varName = extractVariableNameFromClassName(type);
                    if(!varNames.contains(currentFieldName)){
                        System.out.println(String.format(toPrint, type, currentFieldName,type));
                        varNames.add(currentFieldName);
                    }
                    System.out.println(String.format("%s[] %s%s = new %s[1];",type,"var", varCountForArr,type));

                    System.out.println(String.format("%s%d[0] = %s;", "var", varCountForArr,currentFieldName));
                    System.out.println(String.format("%s.%s(%s%d);",extractPreviousFieldName(row),generateSetterMethod(currentFieldName),"var",varCountForArr));
                } else if (type.startsWith("java.util.List<")) {
                    throw new RuntimeException("Not implemented yet for list");
                } else if (type.startsWith("java.util.Map<")){
                    throw new RuntimeException("Not implemented yet for maps");
                } else if (type.startsWith("com.sg.sq.marmsui")) {
                    if(!varNames.contains(currentFieldName)) {
                        System.out.println(String.format("%s %s = new %s();", type,currentFieldName,type));
                        varNames.add(currentFieldName);
                    }
                    System.out.println(String.format("%s.%s(%s);",extractPreviousFieldName(row),generateSetterMethod(currentFieldName),currentFieldName));
                }
            }
            count++;
        }
    }

    private static String extractVariableNameFromClassName(String type) {
        int start = 0;
        int end = type.length();
        int count = 0;
        for(char c : type.toCharArray()) {
            if(c == '.') {
                start = count;
            }
            count++;
        }
        start++;
        String var = type.substring(start,end);
        return var.substring(0,1).toLowerCase() + var.substring(1);
    }

    private static String generateSetterMethod(String fieldName) {
        return "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }

    private static String extractPreviousFieldName(String row) {
        int prevStart = 0;
        int endStart = 0;
        int count = row.indexOf("(");
        String fieldsOnly = row.substring(0,count);
        count = 0;
        for(char c : fieldsOnly.toCharArray()) {
            if(c == '-') {
                if(prevStart != endStart) {
                    prevStart = endStart;
                }
                endStart = count;
            }
            count++;
        }
        if(endStart == 0 || prevStart == endStart) {
            return "oriVar";
        }
        prevStart++;
        return fieldsOnly.substring(prevStart,endStart);
    }

    private static String extractCurrentFieldName(String row) {
        int start = 0;
        int end = row.indexOf("(");
        int count = 0;
        for(char c : row.toCharArray()) {
            if(c == '-') {
                start = count;
            }
            count++;
        }
        start++;
        return row.substring(start,end);
    }

    private static String extractType(String row) {
        int start = row.indexOf("(") + 1;
        int end = row.indexOf(")", start);
        return row.substring(start,end);
    }

    private static String extractTypeWithoutArrayDeclaration(String type) {
        int end = type.indexOf("[");
        return type.substring(0,end);
    }

    // numeric types, int, long, java.lang.Long
}
