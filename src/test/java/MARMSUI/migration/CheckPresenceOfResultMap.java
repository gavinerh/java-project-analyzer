package MARMSUI.migration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class CheckPresenceOfResultMap {
    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        Set<String> resultMapSet = new HashSet<>();
        Set<String> resultMapCalledInSelectTags = new HashSet<>();
        try {
            String baseDir = "/Users/macuser/Documents/lsl-marmsui-profile/src/main/resources/com/sg/sq/marmsui/database/sql/persistence/mappers";
            File baseDirFile = new File(baseDir);
            File[] fileList = baseDirFile.listFiles();
            for(File file : fileList) {
                fileInputStream = new FileInputStream(file);
                checkForMissingResultMaps(fileInputStream,resultMapSet,resultMapCalledInSelectTags,file.getPath());
                resultMapSet = new HashSet<>();
                resultMapCalledInSelectTags = new HashSet<>();
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkForMissingResultMaps(FileInputStream fileInputStream, Set<String> resultMapSet, Set<String> resultMapCalledInSelectTags, String fileDir) throws IOException {
        String identificationPattern = "id=\"";
        populateDestinationCurrValXml(fileInputStream,identificationPattern, resultMapSet, resultMapCalledInSelectTags);
        fileInputStream.close();
        printMissingResultMapId(resultMapSet,resultMapCalledInSelectTags, fileDir);
    }

    private static void printMissingResultMapId(Set<String> declaredResultMap, Set<String> requiredResultMap, String fileName){
        System.out.println(String.format("Printing out missing resultMaps from %s: ", fileName));
        for (String res : requiredResultMap) {
            if(!declaredResultMap.contains(res)) {
                System.out.println(res);
            }
        }
        System.out.println("========================================================");
    }

    private static Map<String, String> populateDestinationCurrValXml(FileInputStream fileInputStream, String identifier, Set<String> resultMapSet, Set<String> resultMapInSelect) {
        Map<String, String> mapOfFields = new HashMap<>();
        Scanner sc = new Scanner(fileInputStream);
        String idName = "";
        String sqlQuery = "";
        boolean enteredSql = false;
        String startTagIdentifier = "";
        boolean startTagEnded = false;
        while (sc.hasNextLine()) {
            String l = sc.nextLine();
            if (l.trim().equals("")) continue;
            if (enteredSql) {
                sqlQuery += "\n" + l;
            }
            if(!startTagEnded && startTagIdentifier.trim().equals("select")) {
                extractResultMapIfRequired(resultMapInSelect,l, identifier);
                startTagEnded = checkIfStartTagEnded(l);
            }
            if (l.contains(identifier) && enteredSql == false) {
                startTagIdentifier = extractStartTagIdentifier(l);
                if(startTagIdentifier.trim().equals("resultMap")) {
                    extractResultMapId(resultMapSet, l, identifier);
                }
                startTagEnded = checkIfStartTagEnded(l);
                if(startTagIdentifier.trim().equals("select")){
                    extractResultMapIfRequired(resultMapInSelect,l, identifier);
                }
                enteredSql = true;
                idName = extractFieldName(l, identifier);
                sqlQuery += l;
            }
            if (checkEndOfTags(l, startTagIdentifier)) {
//                sqlQuery += l;
                mapOfFields.put(idName, sqlQuery);
                idName = "";
                sqlQuery = "";
                enteredSql = false;
                startTagIdentifier = "";
            }
        }
        return mapOfFields;
    }

    private static boolean checkIfStartTagEnded(String line) {
        return line.contains(">");
    }

    private static void extractResultMapIfRequired(Set<String> requiredResultMap, String line, String identifier) {
        String resultMapString = "resultMap";
        if(!line.contains(resultMapString)) {
            return;
        }
        int startInd = line.indexOf(resultMapString) + resultMapString.length();
        startInd = line.indexOf("\"", startInd) + 1;
        int endInd = line.indexOf("\"", startInd);
        String id = line.substring(startInd,endInd);
        requiredResultMap.add(id);
    }

    private static void extractResultMapId(Set<String> resultMapSet, String l, String identifier) {
        if(!l.contains(identifier)) {
            throw new RuntimeException("error occurred at: " + l);
        }
        int startInd = l.indexOf(identifier) + identifier.length();
        int endInd = l.indexOf("\"", startInd);
        String id = l.substring(startInd,endInd);
        resultMapSet.add(id);
    }

    private static String extractStartTagIdentifier(String line) {
        int startIndex = line.indexOf("<") + 1;
        int endIndex = line.indexOf("id=\"");
        try {
            return line.substring(startIndex, endIndex).trim();
        } catch (Exception e) {
            System.out.println(line);
            throw new RuntimeException();
        }
    }

    private static boolean checkEndOfTags(String line, String startTagIdentifier) {
        boolean containsEnd = line.contains("</");
        boolean endTagIdentified = false;
        if (line.contains(startTagIdentifier)) {
            endTagIdentified = true;
        }
        return containsEnd && endTagIdentified;
    }

    private static String extractFieldName(String line, String identifier) {
        int startInd = line.indexOf(identifier) + identifier.length();
        int endInd = line.indexOf("\"", startInd);
        return line.substring(startInd, endInd).trim();
    }
}
