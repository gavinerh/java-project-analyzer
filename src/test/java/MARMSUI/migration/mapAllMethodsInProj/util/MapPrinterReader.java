package MARMSUI.migration.mapAllMethodsInProj.util;

import MARMSUI.migration.mapAllMethodsInProj.model.ClassDeclaration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MapPrinterReader {

    static ObjectMapper mapper = new ObjectMapper();

    public static void printMapToFile(Map<String, ClassDeclaration> classDeclarationMap, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, false);
        for(String key : classDeclarationMap.keySet()) {
            ClassDeclaration classDeclaration = classDeclarationMap.get(key);
            fileWriter.write(key + "\n");
            fileWriter.write(mapper.writeValueAsString(classDeclaration));
            fileWriter.write("\n\n");
        }
        fileWriter.close();
    }

    public static Map<String, ClassDeclaration> readMapFromFile(String fileName) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fileInputStream);
        int counter = 0;
        String key = "";
        Map<String,ClassDeclaration> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.isBlank()) {
                counter = 0;
                continue;
            }
            if(counter == 0) {
                key = line.trim();
                counter++;
                continue;
            }
            if(counter == 1) {
                mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
                mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
                mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
                mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                ClassDeclaration obj = null;
                try {
                    obj = mapper.readValue(line, new TypeReference<ClassDeclaration>(){});
                } catch (Exception e) {
                    System.out.println("Error serializing ClassDeclaration line for key: " + key + " | " + e.getMessage());
                }
                map.put(key,obj);
            }
        }
        return map;
    }
}
