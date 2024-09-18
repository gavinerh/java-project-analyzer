package MARMSUI.migration.newer1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReplaceMybatisParamsInSqlQueryToActualVal {
    public static void main(String[] args) throws JsonProcessingException {
        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String jsonString = "";

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
                        objectMap.put(key,mapper.readValue(jsonString, new TypeReference<>() {
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
                    String sqlQueryWithActualValues = replaceMybatisParamsWithActualValues(objectMap, sqlQueryInMybatisFormat);
                    System.out.println("Sql query with actual values: " + sqlQueryWithActualValues);
                    currentExecution++;
                    break;
                case 5:
                    objectMap.clear();
                    jsonString = "";
                    sqlQueryInMybatisFormat = "";
                    currentExecution = 1;
                    break;
            }
        }
    }

    private static String replaceMybatisParamsWithActualValues(Map<String, Map<String, Object>> jsonMap, String sqlQueryInMybatisFormat) {
        StringBuilder builder = new StringBuilder();
        int start = 0;
        while (true) {
            int tempStart = sqlQueryInMybatisFormat.indexOf("#{",start);
            if(tempStart == -1) {
                break;
            }
            builder.append(sqlQueryInMybatisFormat.substring(start, tempStart));
            int tempEnd = sqlQueryInMybatisFormat.indexOf("}",tempStart);
            String valueToReplace = sqlQueryInMybatisFormat.substring(tempStart + 2,tempEnd);
            String replacedValue = extractFieldAndReplaceWithActualValue(jsonMap,valueToReplace);
            builder.append(replacedValue);
            start = tempEnd + 1;
        }
        return builder.toString();
    }

    private static String extractFieldAndReplaceWithActualValue(Map<String,Map<String,Object>> jsonMap, String valueToReplace) {
        int end = valueToReplace.indexOf(",");
        String fieldAndValue = valueToReplace.substring(0,end).trim();
        String[] keyValueSplit = new String[2];
        if(fieldAndValue.contains(".")) {
            int middle = fieldAndValue.indexOf(".");
            keyValueSplit[0] = fieldAndValue.substring(0,middle);
            keyValueSplit[1] = fieldAndValue.substring(middle + 1);
        }
        if(StringUtils.isBlank(keyValueSplit[1])) {
            return fieldAndValue;
        }
        String key = keyValueSplit[0];
        String value = keyValueSplit[1];
        Map<String,Object> map = jsonMap.get(key);
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
            if(line.trim().equals("\n")) {
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
