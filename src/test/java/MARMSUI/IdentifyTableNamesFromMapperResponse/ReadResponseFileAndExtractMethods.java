package MARMSUI.IdentifyTableNamesFromMapperResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadResponseFileAndExtractMethods {
    public static void main(String[] args) throws FileNotFoundException {
        testFetchingFileName("com.sg.sq.marmsui.database.sql.persistence.mappers.TierHandlerUtilMapper");
        File fileToInspect  = new File("/Users/macuser/Desktop/response_content");
        Map<String,Set<String>> map = readResponseFile(fileToInspect);
        System.out.println(map.size());
    }

    public static Map<String, Set<String>> readResponseFile(File file) throws FileNotFoundException {
        // returns map of key mapper name, value of set of methods for that mapper
        Map<String,Set<String>> mapperMethods = new HashMap<>();
        int loopCount = 0;
        Scanner scanner = new Scanner(file);
        String fileName = null;
        while(scanner.hasNextLine()) {
            loopCount++;
            String currentLine = scanner.nextLine();
            if(currentLine.isBlank()) {
                loopCount = 0;
                continue;
            }
            if(loopCount == 1) {
                fileName = extractFileName(currentLine.trim());
            }
            if(loopCount == 3) {
                String methodName = currentLine.trim();
                Set<String> methods = mapperMethods.get(fileName);
                if(methods == null) {
                    methods = new HashSet<>();
                    methods.add(methodName);
                    mapperMethods.put(fileName,methods);
                } else {
                    methods.add(methodName);
                }
            }
        }
        scanner.close();
        return mapperMethods;
    }

    private static String extractFileName(String filePathWithPackageName) {
        int startIndex = -2;
        int lastIndex = 0;
        while(true) {
            startIndex = filePathWithPackageName.indexOf(".",lastIndex);
            if(startIndex == -1) {
                break;
            } else {
                lastIndex = startIndex + 1;
            }
        }
        // use the last index to extract the file name
        String extracted = filePathWithPackageName.substring(lastIndex);
        return extracted;
    }

    private static void testFetchingFileName(String fileName) {
        String correctAns = "TierHandlerUtilMapper";
        String extracted = extractFileName(fileName);
        if(!extracted.equals(correctAns)) {
            throw new RuntimeException("The extracted file name is incorrect: " + extracted);
        }
    }
}
