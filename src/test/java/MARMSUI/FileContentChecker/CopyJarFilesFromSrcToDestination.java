package MARMSUI.FileContentChecker;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CopyJarFilesFromSrcToDestination {
    public static void main(String[] args) throws IOException {
        String source = "/Users/macuser/.m2/repository";
        String destination = "/Users/macuser/Desktop/filesToTransfer";
        String fileToCopyString =  "com.ibm:com.ibm.mqjms:jar:5.3.07, com.ibm:com.ibm.mq:jar:5.3.07, javax.naming:jndi:jar:1.2.1, javax.resource:connector:jar:1.0, javax.transaction:jta:jar:1.0.1B, com.ibm:com.ibm.disthub2.dhbcore:jar:7.1.0.0, com.ibm:com.ibm.mqjms:jar:5.3.07";
        String[] fileNames = fileToCopyString.split(",");
        for(String fileName : fileNames) {
            extractFiles(fileName.trim(),source,destination);
        }
    }

    private static void extractFiles(String fileName, String source, String destination) throws IOException {
        String[] colonSep = fileName.split(":");
        String directory = colonSep[0];
        String artifactId = colonSep[1];
        String fileType = colonSep[2];
        String version = colonSep[3];

        File sourceFile = new File(source);
        File fileToExtractVersionAndJar = iterateThroughFiles(sourceFile, directory, "");
        if(fileToExtractVersionAndJar == null) {
            System.out.println(String.format("%s is not found", fileName));
            return;
        }
        File fileToExtractJar = lookInFileWithArtifactIdAndVersion(fileToExtractVersionAndJar, artifactId, version);
        if(fileToExtractJar == null){
            System.out.println(String.format("%s is not found", fileName));
            return;
        }
        File jarFile = extractJarFile(fileToExtractJar, generateFileName(artifactId,version,fileType));
        if(jarFile == null) {
            System.out.println(String.format("%s is not found", fileName));
            return;
        }
        File destFile = new File(destination + "/" + generateFileName(artifactId,version,fileType));
//        copyFileFromSrcToDest(jarFile,destFile);
        FileUtils.copyFile(jarFile,destFile);
    }

    private static File extractJarFile(File file, String nameToSearch) {
        for(File f : file.listFiles()) {
            if(f.getName().equals(nameToSearch)) {
                return f;
            }
        }
        return null;
    }

    private static String generateFileName(String artifact, String version, String fileType) {
        String name = String.format("%s-%s.%s", artifact,version,fileType);
        return name;
    }

    private static File lookInFileWithArtifactIdAndVersion(File file, String artifactId, String version) {
        for(File f : file.listFiles()) {
            if(f.getName().contains(artifactId)) {
                return lookInFileWithArtifactIdAndVersion(f,artifactId,version);
            } else if (f.getName().contains(version)) {
                return f;
            }
        }
        return null;
    }

    private static File iterateThroughFiles(File file, String directory, String directoryIteratedThrough) {
        String directoryToSearch = "";
        if(!directoryIteratedThrough.isEmpty()) {
            String notSearched = extractRemainingDirectoryNotSearched(directory, directoryIteratedThrough);
            directoryToSearch = extractNextDirectory(notSearched);
        } else {
            directoryToSearch = extractNextDirectory(directory);
        }
        if(directoryToSearch == null || directory.isEmpty()) {
            return file;
        }
        for(File f : file.listFiles()) {
            if(f.getName().equals(directoryToSearch)) {
                String iterated = generateDirectoryIteratedThrough(directoryIteratedThrough, directoryToSearch);
                return iterateThroughFiles(f,directory,iterated);
            }
        }
        return null;
    }

    private static String generateDirectoryIteratedThrough(String iteratedThrough, String toSearch) {
        if(iteratedThrough.equals("")){
            return toSearch;
        } else {
            return iteratedThrough + "." + toSearch;
        }
    }

    private static String extractRemainingDirectoryNotSearched(String full, String iteratedAlrdy) {
        if(full.equals(iteratedAlrdy)) {
            return null;
        }
        int start = full.indexOf(iteratedAlrdy) + iteratedAlrdy.length() + 1;
        return full.substring(start);
    }

    private static String extractNextDirectory(String directory) {
        if(directory == null) {
            return null;
        }
        String name = "";
        for(char c : directory.toCharArray()) {
            if(c == '.'){
                return name;
            } else {
                name += c;
            }
        }
        return name;
    }
}
