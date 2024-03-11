package MARMSUI.testCaseCreation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GenerateSignatureMap {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/Users/macuser/Desktop/expectedJson.json";
        Map<String,Integer> signatureMap = generateMap(fileName);
        printSignatureMap(signatureMap);
    }

    private static void printSignatureMap(Map<String,Integer> map) {
        System.out.println("Map<String, Integer> map = new HashMap<>();");
        for(String key : map.keySet()) {
            System.out.println(String.format("map.put(\"%s\",%d);",key,map.get(key)));
        }
    }

    private static Map<String,Integer> generateMap(String fileName) throws FileNotFoundException {
        Map<String,Integer> map = new HashMap<>();
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fileInputStream);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            extractLine(line, map);
        }
        return map;
    }

    private static void extractLine(String line, Map<String,Integer> map) {
        for(int i=0; i<line.length(); i++) {
            String c = line.substring(i,i+1);
            if(map.containsKey(c)) {
                int count = map.get(c);
                count++;
                map.put(c,count);
            } else {
                map.put(c, 1);
            }
        }
    }
}
