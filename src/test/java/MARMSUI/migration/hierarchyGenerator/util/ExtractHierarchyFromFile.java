package MARMSUI.migration.hierarchyGenerator.util;

import MARMSUI.migration.hierarchyGenerator.model.MethodChain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExtractHierarchyFromFile {
    public static void main(String[] args) {
        MethodChain chain = extractHierarchyFromFile("/Users/macuser/Desktop/hierarchy-generator/map");
        System.out.println(chain);

    }

    public static MethodChain extractHierarchyFromFile(String filePath) {
        Map<String,MethodChain> map = readFromFile(filePath);
        // get the parent
        for(String key : map.keySet()) {
            generateHierarchy(key, map);
        }
        return map.get("1");
    }

    private static void generateHierarchy(String key, Map<String,MethodChain> map) {
        String parentKey = generateParentKey(key);
        if(parentKey == null) {
            return;
        }
        MethodChain parent = map.get(parentKey);
        // attach child chain to parent chain and delete child chain
        parent.addChildMethodChain(map.get(key));
    }

    private static String generateParentKey(String current) {
        if(!current.contains(".")) {
            return null;
        }
        String[] arr = current.split("\\.");
        return removeLastElement(arr);
    }

    private static String removeLastElement(String[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < arr.length - 1; i++) {
            if(i != 0) {
                stringBuilder.append(".");
            }
            stringBuilder.append(arr[i]);
        }
        return stringBuilder.toString();
    }

    private static String[] splitBySth(String delimiter, String str) {
        return str.split(delimiter);
    }

    private static Map<String,MethodChain> readFromFile(String filePath) {
        Map<String,MethodChain> map = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String classPlusMtd = line.split(":")[1];
                map.put(line.split(":")[0], new MethodChain(splitBySth("\\.",classPlusMtd)[0],splitBySth("\\.",classPlusMtd)[1]));
            }
        } catch (IOException e) {
            System.err.println("An IOException was caught: " + e.getMessage());
        }
        return map;
    }
}
