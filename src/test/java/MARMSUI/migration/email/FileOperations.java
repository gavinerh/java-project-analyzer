package MARMSUI.migration.email;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileOperations {

    public static void createDirectoryIfNotExists(String directory) {
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("error");
            }
        }
    }

    public static void createFileIfNotExists(String filename) {
        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("error");
            }
        }
    }

    public static String loadContentNoExceptionThrown(String propertyPath, String filename) {
        try {
            return loadContent(propertyPath, filename);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String loadContent(String propertyPath, String filename) throws Exception {
        try {
            if (filename != null && !filename.isBlank()) {
                if (propertyPath != null) {
                    String separator = "/";
                    filename = propertyPath + separator + filename;
                }
                return loadFile(filename);
            } else {
                throw new Exception("Email template file is blank");
            }
        } catch (Exception e) {
            throw new Exception("Email template file is not present in filesystem");
        }
    }

    private static String loadFile(String filename) throws Exception {
        String result = null;
        try {
            InputStream inputStream = new FileInputStream(filename);
            result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new Exception("Email template file is not present in filesystem");
        }
        return result;
    }

    public static void overwriteFile(String content, String directory, String filename) {
        FileOperations.createDirectoryIfNotExists(directory);
        FileOperations.createFileIfNotExists(directory + "/" + filename);
        try {
            Path path = Paths.get(directory + "/" + filename);
            Files.write(path,
                    content.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
