package MARMSUI.IdentifyTableNamesFromMapperResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // change line 13 if required
        String baseMapperDir = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/resources/com/sg/sq/marmsui/database/sql/persistence/mappers";
        Map<String, Map<String, String>> tableNames = new HashMap<>();
        for (File file : Objects.requireNonNull(new File(baseMapperDir).listFiles())) {
            if (file.isFile() && file.getName().endsWith(".xml")) {
                String fileNameWithoutExtension = Generate.getFileNameWithoutExtension(file.getAbsolutePath());
                System.out.println("Processing file: " + file.getName());
                Map<String, String> contents = Generate.getUserContentById(file);
                tableNames.put(fileNameWithoutExtension, contents);
            }
        }
        System.out.println("Table names extracted from mapper files:");

        File fileToInspect  = new File("/Users/macuser/Desktop/response_content");
        Map<String, Set<String>> mapperMethods = ReadResponseFileAndExtractMethods.readResponseFile(fileToInspect);

        // identify all the update tables from the mapperMethods map
        printTableNamesThatAreUpdated(tableNames, mapperMethods);
    }

    private static void printTableNamesThatAreUpdated(Map<String, Map<String, String>> tableNames, Map<String, Set<String>> mapperMethods) {
        Set<String> tableNamesUpdated = new HashSet<>();
        for (String mapperName : mapperMethods.keySet()) {
            Set<String> methods = mapperMethods.get(mapperName);
            Map<String,String> tableNameMap = tableNames.get(mapperName);
            if (methods != null && !methods.isEmpty()) {
                for (String method : methods) {
                    String tableName = tableNameMap.get(method);
                    if(tableName != null && !tableName.isBlank()) {
                        tableNamesUpdated.add(tableName);
                    } else {
                        System.out.println("No table name found for method: " + method + " in mapper: " + mapperName);
                    }
                }
            }
        }
        if(!tableNamesUpdated.isEmpty()) {
            System.out.println("Printing out all the updated table names");
        } else {
            System.out.println("No updated table names found.");
        }
        tableNamesUpdated.forEach(System.out::println);
    }
}
