package MARMSUI.migration;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ForCopyingMissingFiles {
    public static void main(String[] args) {
        String sourceBaseBaseDir = "/Users/macuser/Documents";
        String destBaseBaseDir = "/Users/macuser/Desktop";
        String baseSourceDir = "/Users/macuser/Documents/lsl-marmsui-profile/src/main";
        String baseDestinationDir = "/Users/macuser/Desktop/lsl-marmsui-profile/src/main";
        // traversing the directory
        File baseDir = new File(baseSourceDir);
        checkForMissingFiles(baseDir, sourceBaseBaseDir, destBaseBaseDir);
    }

    private static void checkForMissingFiles(File file, String sourceBaseBaseDir, String desBaseBaseDir) {
        File[] files = file.listFiles();
        for(File innerFile : files) {
            if(innerFile.isDirectory()) {
                checkForMissingFiles(innerFile, sourceBaseBaseDir,desBaseBaseDir);
            } else {
                checkIfDestinationHasFile(innerFile,sourceBaseBaseDir,desBaseBaseDir);
            }
        }
    }

    private static void checkIfDestinationHasFile(File file,  String sourceBaseBaseDir, String desBaseBaseDir) {
        String sourceFilePath = file.getAbsolutePath();
        String desFilePath = getDestinationFilePath(sourceFilePath,sourceBaseBaseDir,desBaseBaseDir);
        File des = new File(desFilePath);
        try {
            if(!des.exists()) {
                des.createNewFile();
                FileUtils.copyFile(file, des);
            }
        }catch (IOException e) {
            throw new RuntimeException("File io error");
        }
    }

    private static String getDestinationFilePath(String sourcePath, String sourceBase, String desBase) {
        return sourcePath.replace(sourceBase,desBase);
    }
}
