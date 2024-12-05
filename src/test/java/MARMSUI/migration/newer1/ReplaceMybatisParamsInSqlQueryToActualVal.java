package MARMSUI.migration.newer1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

public class ReplaceMybatisParamsInSqlQueryToActualVal {
    public static void main(String[] args) throws JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String jsonString = "";
        String sqlQueryWithActualValues = "";

//        String val = getUserResponseFromScanner(scanner, "Enter test json obj string: ");
//        Map<String, Object> map = mapper.readValue(val, new TypeReference<>() {
//        });
//        System.out.println(val);

        String sqlQueryInMybatisFormat = "";
        Map<String, Map<String, Object>> objectMap = new HashMap<>();
        int currentExecution = 1;
        while (true) {
            switch (currentExecution) {
                case 3:
                    for (String key : objectMap.keySet()) {
                        jsonString = getUserResponseFromScanner(scanner, "Enter the json string for object " + key + ": \n");
                        objectMap.put(key, mapper.readValue(jsonString, new TypeReference<>() {
                        }));
                    }
                    currentExecution++;
                    break;
                case 1:
                    sqlQueryInMybatisFormat = getUserResponseFromScanner(scanner, "Enter the sql query in mybatis format");
                    currentExecution++;
                    break;
                case 2:
                    String identifier = getUserResponseFromScanner(scanner, "Enter the object identifier for the sql query");
                    if (identifier.equals("")) {
                        currentExecution++;
                        continue;
                    }
                    Map<String, Object> map = new HashMap<>();
                    objectMap.put(identifier, map);
                    break;
                case 4:
                    sqlQueryWithActualValues = replaceMybatisParamsWithActualValues(objectMap, sqlQueryInMybatisFormat);
                    System.out.println("Sql query with actual values: " + sqlQueryWithActualValues);
                    currentExecution++;
                    break;
                case 5:
                    generateSqlColumnToValuePair(sqlQueryWithActualValues);
                    sqlQueryWithActualValues = "";
                    objectMap.clear();
                    jsonString = "";
                    sqlQueryInMybatisFormat = "";
                    currentExecution = 1;
                    break;
            }
        }
    }

    private static void generateSqlColumnToValuePair(String sqlQueryWithActualValues) {
        boolean isUpdate = false;
        if (sqlQueryWithActualValues.trim().toLowerCase().startsWith("update")) {
            isUpdate = true;
        }
        System.out.println("=======================================================");
        System.out.println("Printing column value pairs: ");
        if (isUpdate) {
            generateUpdateKeyValue(sqlQueryWithActualValues);
        } else {
            generateInsertKeyValue(sqlQueryWithActualValues);
        }
    }

    private static void generateInsertKeyValue(String sql) {
        List<String> columnList = new ArrayList<>();
        int start = sql.indexOf("(") + 1;
        int finalEnd = sql.indexOf(")");
        int tempEnd = 0;
        while(true) {
            tempEnd = sql.indexOf(",", start);
            if(tempEnd > finalEnd || tempEnd == -1 ) {
                columnList.add(sql.substring(start + 1, finalEnd).trim());
                break;
            }
            columnList.add(sql.substring(start, tempEnd).trim());
            start = tempEnd + 1;
        }
        // column list populated up till this line

        String remainingSql = sql.substring(finalEnd + 1);
        if(remainingSql.toLowerCase().contains("select") || remainingSql.toLowerCase().contains("from")) {
            start = remainingSql.toLowerCase().indexOf("select") + 6;
            finalEnd = remainingSql.length();
        } else if (remainingSql.toLowerCase().contains("values")) {
            start = remainingSql.toLowerCase().indexOf("(") + 1;
            finalEnd = remainingSql.length();
        } else {
            System.out.println("Cannot parse insert sql query");
            return;
        }
        for(int i=0; i<columnList.size(); i++) {
            tempEnd = remainingSql.indexOf(",", start);
            if(tempEnd > finalEnd || tempEnd == -1 ) {
                tempEnd = finalEnd;
            }
            System.out.println(columnList.get(i) + " : " + remainingSql.substring(start, tempEnd).trim());
            start = tempEnd + 1;
        }
    }

    private static void generateUpdateKeyValue(String sql) {
        int start = sql.toLowerCase().indexOf("set") + 3;
        int end = 0;
        int finalEnd = sql.length();
        while(true) {
            end = findTheEndOfTheKeyAndValue(sql, start, finalEnd);
            String tempString = sql.substring(start,end);
            String[] keyAndValue = tempString.split("=");
            System.out.println(keyAndValue[0].trim() + " : " + keyAndValue[1].trim());
            if(end == finalEnd || end > finalEnd) {
                break;
            }
            start = end + 1;
        }
    }

    private static int findTheEndOfTheKeyAndValue(String sql, int start, int finalEnd) {
        char[] chars = sql.toCharArray();
        boolean isInsideParams = false;
        for(int i=start; i<chars.length; i++) {
            if(chars[i] == '(') {
                isInsideParams = true;
            }
            if(chars[i] == ')') {
                isInsideParams = false;
            }
            if(chars[i] == ',' && !isInsideParams) {
                return i;
            }
        }
        return finalEnd;
    }

    private static String replaceMybatisParamsWithActualValues(Map<String, Map<String, Object>> jsonMap, String sqlQueryInMybatisFormat) {
        StringBuilder builder = new StringBuilder();
        int start = 0;
        while (true) {
            int tempStart = sqlQueryInMybatisFormat.indexOf("#{", start);
            if (tempStart == -1) {
                break;
            }
            builder.append(sqlQueryInMybatisFormat.substring(start, tempStart));
            int tempEnd = sqlQueryInMybatisFormat.indexOf("}", tempStart);
            String valueToReplace = sqlQueryInMybatisFormat.substring(tempStart + 2, tempEnd);
            String replacedValue = extractFieldAndReplaceWithActualValue(jsonMap, valueToReplace);
            builder.append(replacedValue);
            start = tempEnd + 1;
        }
        return builder.toString();
    }

    private static String extractFieldAndReplaceWithActualValue(Map<String, Map<String, Object>> jsonMap, String valueToReplace) {
        int end = valueToReplace.indexOf(",");
        String fieldAndValue = valueToReplace.substring(0, end).trim();
        String[] keyValueSplit = new String[2];
        if (fieldAndValue.contains(".")) {
            int middle = fieldAndValue.indexOf(".");
            keyValueSplit[0] = fieldAndValue.substring(0, middle);
            keyValueSplit[1] = fieldAndValue.substring(middle + 1);
        }
        if (StringUtils.isBlank(keyValueSplit[1])) {
            return fieldAndValue;
        }
        String key = keyValueSplit[0];
        String value = keyValueSplit[1];
        Map<String, Object> map = jsonMap.get(key);
        return map.get(value) == null ? "" : convertObjectToString(map.get(value));
    }

    private static String convertObjectToString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String getUserResponseFromScanner(Scanner scanner, String userPrompt) {
        String val = "";
        System.out.println(userPrompt);
        boolean enteredWhile = false;
//        String line = scanner.nextLine();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().equals("\n")) {
                continue;
            }
//            if (enteredWhile) {
//                System.out.println(userPrompt);
//            } else {
//                enteredWhile = !enteredWhile;
//            }
            if (line.trim().equals("-")) {
                break;
            } else {
                val += line + " ";
            }
        }
        return val.trim();
    }


}
