package MARMSUI.migration.propertymigration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CleanUatFile {
    public static void main(String[] args) throws IOException {

        // only run this if the source prod file and uat file needs changing
        
        String cleanedFile = "/Users/macuser/Desktop/holdingForTempFiles/cleaned-prod";
        String toclean = "/Users/macuser/Desktop/holdingForTempFiles/config.txt";
        String fileContent = ComparisonOfPropertiesFile.extractStringFromFile(toclean);
        List<String> cleaned = cleanUp(fileContent);
        System.out.println(cleaned.get(cleaned.size()-1));
        saveTofile(cleanedFile,cleaned);
    }

    public static void saveTofile(String filePath, List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine(); // Add a new line after each string
            }
            System.out.println("List of strings has been saved to " + filePath);
        } catch (IOException e) {
            System.err.println("An error occurred while saving the list to the file:");
            e.printStackTrace();
        }
    }
    public static List<String> cleanUp(String content) {
        Map<String,String> keyValue = new HashMap<>();
        List<String> cleanedList = new ArrayList<>();
        String[] arr = content.split("\n");
        for(String str : arr) {
            if(str.trim().startsWith("#")) {
                continue;
            }
            String[] splitEquals = splitOnEquals(str);
            if(splitEquals[1].contains("${")) {
                // replace the brackets value
                replacePlaceholder(splitEquals,keyValue);
            } else {
                if(keyValue.containsKey(splitEquals[0]) && !keyValue.get(splitEquals[0]).equals(splitEquals[1])) {
                    System.out.println("different value for key " + splitEquals[0]);
                } else {
                    keyValue.put(splitEquals[0],splitEquals[1]);
                }
            }
            cleanedList.add(splitEquals[0].isBlank() && splitEquals[1].isBlank() ? "" : String.format("%s=%s",splitEquals[0],splitEquals[1]));
        }
        return cleanedList;
    }

    private static void replacePlaceholder(String[] split, Map<String,String> map) {
        String[] extractKeyAndEnd = extractKeyAndEndString(split[1],"${","}");
        String valueToReplace = String.format("${%s}",extractKeyAndEnd[0]);
        String valueReplacement = map.get(extractKeyAndEnd[0]);
        String finalValue = valueToReplace.replace(valueToReplace,valueReplacement);
        split[1] = finalValue + extractKeyAndEnd[1];
    }

    private static String[] extractKeyAndEndString(String value, String start, String end) {
        int startInd = value.indexOf(start) + start.length();
        int endInd = value.indexOf(end,startInd);
        String[] arr = new String[2];
        arr[0] = value.substring(startInd,endInd);
        arr[1] = value.substring(endInd+1);
        return arr;
    }

    public static String[] splitOnEquals(String content) {
        String[] split = content.trim().split("=");
        if(split.length == 1) {
            return new String[]{split[0].trim(), ""};
        }
        for(int i=0; i<split.length; i++) {
            split[i] = split[i].trim();
        }
        return split;
    }
}
