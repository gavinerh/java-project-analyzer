package MARMSUI.migration.GenerateDependentFieldsForTest;

import java.io.File;
import java.util.Set;

public class CheckIfThereAreDuplicateFileNames {
    public static void main(String[] args) {
        String basePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui";
        // Logic to check for duplicate file names in the specified directory
        // This can be implemented using Java's File API or any other method to traverse the directory structure
        File baseDir = new File(basePath);
        Set<String> fileNames = new java.util.HashSet<>();
        checkForDuplicateFiles(baseDir,fileNames);
    }



    public static void checkForDuplicateFiles(File dir, Set<String> fileNames) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        checkForDuplicateFiles(file, fileNames);
                    } else {
                        // Logic to check for duplicates
                        // This could involve maintaining a Set of file names and checking for duplicates
                        extractFileNameFromPath(file.getAbsolutePath(), fileNames);
                    }
                }
            }
        }
    }

    public static void extractFileNameFromPath(String filePath, Set<String> fileNames) {
        String fileName = getFileNameFromPath(filePath);
        determineIfFileNameExists(fileName, fileNames);
    }

    public static String getFileNameFromPath(String filePath) {
        int position = 0;
        int lastPosition = 0;
        if(!filePath.contains(".java")) {
            return null;
        }
        for(char c : filePath.toCharArray()) {
            if(c == '/') {
                lastPosition = position;
            }
            position++;
        }
        // -5 to remove ".java"
        return filePath.substring(lastPosition + 1, filePath.length() - 5);
    }

    private static void determineIfFileNameExists(String fileName, Set<String> fileNames) {
        if(fileNames.contains(fileName)) {
            System.out.println("Duplicate file name found: " + fileName);
            throw new RuntimeException("Remove duplicate file name: " + fileName);
        } else {
            fileNames.add(fileName);
        }
    }
}
