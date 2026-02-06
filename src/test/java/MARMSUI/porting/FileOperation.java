package MARMSUI.porting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileOperation {
    // Method to insert list of strings into a file
    public static void insertStringsToFile(List<String> stringList, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String str : stringList) {
                writer.write(str);
                writer.newLine(); // Add a new line after each string
            }
            System.out.println("Successfully wrote " + stringList.size() + " strings to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void createDirectoryIfNotExists(String directoryPath) {
        java.io.File directory = new java.io.File(directoryPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (created) {
                System.out.println("Directory created: " + directoryPath);
            } else {
                System.err.println("Failed to create directory: " + directoryPath);
            }
        }
    }

    public static File[] listFilesInDirectory(String directoryPath) {
        java.io.File directory = new java.io.File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            return directory.listFiles();
        } else {
            System.err.println("Directory does not exist or is not a directory: " + directoryPath);
            return new java.io.File[0];
        }
    }
}
