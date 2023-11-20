package MARMSUI;

import java.util.HashMap;
import java.util.Map;

public class ObjectMappingGetSet {
    public static void main(String[] args) {
        String finalFieldName = "searchUserProfile";
        String initialFieldName = "userProfileDetail";
        String fields = "private String organization; // organization\n" +
                "    private String region; // region\n" +
                "    private String department; // department\n" +
                "    private String teamCode; // teamCode\n" +
                "    private String userName; // userName\n" +
                "    private String userID; // userID\n" +
                "    private String function; // function\n" +
                "    private String status; // status\n" +
                "    private String system; // system\n" +
                "    private String role; // role";
        Map<String,String> map = generateGetToSetMap(fields);
        printGetSetStatements(map, finalFieldName, initialFieldName);
    }

    private static void printGetSetStatements(Map<String,String> map, String finalFieldName, String initialFieldName) {
        for(String key : map.keySet()) {
            String getterMethod = generateGetterMethodName(key);
            String setterMethod = generateSetterMethodName(map.get(key));
            String toPrint = String.format("%s.%s(%s.%s);", finalFieldName, setterMethod, initialFieldName, getterMethod);
            System.out.println(toPrint);
        }
    }

    private static String generateGetterMethodName(String field) {
        field = field.substring(0,1).toUpperCase() + field.substring(1);
        return "get" + field + "()";
    }

    private static String generateSetterMethodName(String field) {
        field = field.substring(0,1).toUpperCase() + field.substring(1);
        return "set" + field;
    }

    private static Map<String,String> generateGetToSetMap(String fields) {
        Map<String,String> map = new HashMap<>();
        String[] fieldArr = fields.split("\n");
        for(String row : fieldArr) {
            String[] rowArr = row.trim().split(" ");
            String key = rowArr[2].substring(0, rowArr[2].length()-1);
            String val = rowArr[4];
            map.put(key,val);
        }
        return map;
    }
}
