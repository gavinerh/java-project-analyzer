package MARMSUI.migration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CopyFilecontentsFromDupToOrig {
    public static void main(String[] args) {
        String directory = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/database/sql/persistence/dup";
        File[] files = new File(directory).listFiles();
        String origDirectory = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/database/sql/persistence/model";
        for(File file : files) {
            String fileName = file.getName();
            String origFileName = removeDupName(fileName);
            String origFilePath = origDirectory + "/" + origFileName;
            System.out.println(origFilePath);
            System.out.println(origFileName + " -- " + fileName);
            copyFilecontent(directory + "/" + fileName,origFilePath);
        }
//        copyFilecontent("","/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/database/sql/persistence/model/VwCusPpsQualExample.java");
    }

    private static String removeDupName(String name) {
        return name.replace("Dup", "");
    }

    private static void copyFilecontent(String sourceFile, String destinationFile) {
        try {
            // Copy the source file to the destination file
            Files.copy(new File(sourceFile).toPath(), new File(destinationFile).toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("File copied successfully! The destination file content was overwritten.");
        } catch (IOException e) {
            // Handle exceptions (e.g., file not found, access denied, etc.)
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
