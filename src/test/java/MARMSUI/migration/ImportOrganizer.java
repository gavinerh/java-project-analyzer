package MARMSUI.migration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ImportOrganizer {
    public static void main(String[] args) {
        // this method will edit the import statements from the source to the destination
        // will create a map of the destination file
        String fileToEdit = "/Users/macuser/Documents/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/service/impl/FraudDetailsService.java";


        String basePackageOfSource = "/Users/macuser/Documents/updated-agent-security/lsl-customer/src/main/java";
        String basePackageOfDestination = "/Users/macuser/Documents/lsl-marmsui-profile/src/main/java";
        Map<String, List<String>> mapOfClasses = new HashMap<>();
        compileClassAndRespectiveDirectory(mapOfClasses, basePackageOfDestination);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(fileToEdit);
            printImportStatements(fileInputStream, mapOfClasses);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printImportStatements(FileInputStream fileInputStream, Map<String,List<String>> map) throws IOException {
        Scanner sc = new Scanner(fileInputStream);
        List<String> fileNamesToSearch = new ArrayList<>();
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.trim().contains("import") && line.trim().contains("com.sg.sq.lsl")) {
                fileNamesToSearch.addAll(extractFileNameToImport(line));
            }
        }
        fileInputStream.close();
        for(String name : fileNamesToSearch) {
            if(map.get(name) == null) {
                System.out.println(name + " was not found");
            } else {
                System.out.println(String.format("import %s.%s;", map.get(name).get(0),name));
            }
        }
    }

    private static List<String> extractFileNameToImport(String line) {
        List<String> list = new ArrayList<>();
        int start = line.indexOf("import");
        String cleanedString = line.substring(start);
        String[] arr = cleanedString.split(";");
        for(String row : arr) {
            if(row.contains("com.sg.sq.lsl")) {
                String[] innerSplit = row.split("\\.");
                String name = innerSplit[innerSplit.length-1];
                list.add(name);
            }
        }
        return list;
    }

    private static void compileClassAndRespectiveDirectory(Map<String, List<String>> map, String basePackageOfDestination) {
        // call a recursive method to call the next file directory: pass in the file object
        File file = new File(basePackageOfDestination);
        getFileObject(file, map, basePackageOfDestination);
    }

    private static void getFileObject(File currentFile, Map<String, List<String>> map, String basePath) {
        if (!currentFile.isDirectory()) {
            if (!map.containsKey(currentFile.getName())) {
                List<String> list = new ArrayList<>();
                list.add(extractRelativePath(basePath, currentFile.getPath()));
                map.put(extractFileName(currentFile.getName(), ".java"), list);
            } else {
                List<String> existingList = new ArrayList<>();
                System.out.println(currentFile.getName() + " was detected again");
                existingList.add(currentFile.getPath());
            }
        } else {
            // is a directory
            File[] fileList = currentFile.listFiles();
            for (File file : fileList) {
                getFileObject(file, map, basePath);
            }
        }

    }

    private static String extractRelativePath(String basePath, String absolutePath) {
        int startInd = basePath.length() + 1;
        String relativePath = absolutePath.substring(startInd);
        return replaceSlashWithDot(relativePath);
    }

    private static String replaceSlashWithDot(String path) {
        String finalPath = "";
        String[] pathArr = path.split("/");
        for (int i = 0; i < pathArr.length - 1; i++) {
            finalPath += pathArr[i];
            if(i != pathArr.length - 2) {
                finalPath += ".";
            }
        }
        return finalPath;
    }

    public static String extractFileName(String name, String endExt) {
        int endInd = name.indexOf(endExt);
        return name.substring(0,endInd);
    }
}
