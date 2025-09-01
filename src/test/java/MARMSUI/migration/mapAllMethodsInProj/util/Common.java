package MARMSUI.migration.mapAllMethodsInProj.util;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Common {
    public static Map<String,String> convertFieldListToMap(List<String> fieldList) {
        Map<String, String> fieldMap = new HashMap<>();
        for (String field : fieldList) {
            String[] parts = field.split(" ");
            if (parts.length == 2) {
                String key = modifyKey(parts[0]);
                fieldMap.put(key, parts[0]); // key: field name, value: field type
            }
        }
        return fieldMap;
    }

    private static String modifyKey(String key) {
        int start = key.lastIndexOf(".") + 1;
        if(start == 0) {
            System.out.println("Error in modifyKey method for key: " + key);
        }
        return key.substring(start);
    }

    public static String getUserInputOnce(Scanner scanner, String printToUser) {
        System.out.println(String.format("%s: ", printToUser));
        if(scanner != null) {
            String line = scanner.nextLine();
            return line;
        }
        return null;
    }

    public static String combineMethodNameAndParamNum(String name, String paramNo) {
        return name + "-" + paramNo;
    }

    public static String[] splitByDot(String name) {
        int middle = name.indexOf(".");
        String[] arr = new String[2];
        arr[0] = name.substring(0,middle);
        arr[1] = name.substring(middle + 1);
        return arr;
    }
}
