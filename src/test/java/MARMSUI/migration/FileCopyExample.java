package MARMSUI.migration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCopyExample {
    public static void main(String[] args) throws Exception {
        String toReplacePackagePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/data";

        File[] toReplace = listFiles(toReplacePackagePath);


        Path source = Paths.get("");
        Path destination = Paths.get("");
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File copied successfully.");
    }

    private static File[] listFiles(String sourcePackagePath) {
        File filePackage = new File(sourcePackagePath);
        return filePackage.listFiles();
    }

}
