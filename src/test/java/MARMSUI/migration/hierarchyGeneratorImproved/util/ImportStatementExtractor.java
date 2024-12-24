package MARMSUI.migration.hierarchyGeneratorImproved.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ImportStatementExtractor {
    public static void main(String[] args) throws IOException {
        String startingFilePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/service/impl/UpdateCustomerSelectiveServiceImpl.java";
        // from the starting file, get all the files that are required from the import statements
        String importPrefix = "import com.sg.sq.marmsui";
        String[] stopLine = new String[]{"public class", "public interface"};
        Set<String> fileSet = new HashSet<>();
//        List<String> essentialFilesPathsImports = extractListOfEssentialFileImports(startingFilePath, importPrefix, stopLine, fileSet);
        String absoluteBasePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/";
        String[] packageNotToSearchInService = new String[]{"accountSummary", "adminfee", "airredemption", "alteaservices", "common", "corporate", "customer", "customerservicing", "dataintegrity", "ecertrevalidation", "ecertservice", "eventlog", "ignoreupgradeonOAL", "milesadmin", "milesconversion", "mua", "nonairredemption", "promotion", "redemptionenquiry", "redemptionupgrade", "reference", "requestcertificate", "requestupgrade", "requestupgradeonOAL", "rewardVouchers", "sslinterface", "starmwservice", "upgradeonsq", "useraccess", "validation"};
        String servicePackagePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/service";
        executeAndExtractFilePaths(startingFilePath, importPrefix, stopLine, absoluteBasePath, fileSet, packageNotToSearchInService,servicePackagePath);
    }

    public static List<String> executeAndExtractFilePaths(String filepath, String importPrefix, String[] stopLine, String absoluteBasePath, Set<String> fileSet, String[] packageNotToSearch, String servicePackagePath) throws IOException {
        extractListOfEssentialFileImports(filepath, importPrefix, stopLine, fileSet, absoluteBasePath);
        // relevant files paths have been extracted, need to make the map of class to interface from the file Set
        populateRemainingClassesFromServicePackage(packageNotToSearch, fileSet, servicePackagePath);
        System.out.println(fileSet);
        return null;
    }

    private static void populateRemainingClassesFromServicePackage(String[] packageNotToSearch, Set<String> set, String servicePackagePath) {
        File serviceDirectory = new File(servicePackagePath);
        for (File file : serviceDirectory.listFiles()) {
            if (file.isDirectory()) {
                if (Arrays.stream(packageNotToSearch).anyMatch(file.getName()::contains)) {
                    continue;
                }
                populateRemainingClassesFromServicePackage(packageNotToSearch, set, file.getAbsolutePath());
            } else {
                if (file.getAbsolutePath().endsWith(".java")) {
                    set.add(file.getAbsolutePath());
                }
            }
        }
    }


    private static String logicForChangingImportDeclarationToPath(String importStatement) {
        int startInd = importStatement.indexOf("com.sg.sq.marmsui");
        String cleaned = importStatement.substring(startInd);
        if (cleaned.endsWith(".*;")) {
            cleaned = cleaned.substring(0, cleaned.length() - 3);
            cleaned = cleaned.replace(".", "/");
        } else {
            cleaned = cleaned.substring(0, cleaned.length() - 1);
            cleaned = cleaned.replace(".", "/") + ".java";
        }
        return cleaned;
    }


    // completed
    public static List<String> extractListOfEssentialFileImports(String filepath, String importPrefix, String[] stopLine, Set<String> set, String basePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filepath);
        Scanner scanner = new Scanner(fileInputStream);
        List<String> essentialFilesPaths = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith(importPrefix)) {
                essentialFilesPaths.add(line);
            }
            if (Arrays.stream(stopLine).anyMatch(line::contains)) {
                break;
            }
        }
        fileInputStream.close();

        // loop through the imports and generate the file paths
        for (String importStatement : essentialFilesPaths) {
            if (importStatement.contains("com.sg.sq.marmsui.constant")) {
                // ignore constants packages
                continue;
            }
            if (!importStatement.endsWith(".*;")) {
                // its just a file import
                String filePath = basePath + logicForChangingImportDeclarationToPath(importStatement);
                if (!set.contains(filePath)) {
                    set.add(filePath);
                    extractListOfEssentialFileImports(filepath, importPrefix, stopLine, set, basePath);
                }
            } else {
                // its a package import
                String path = basePath + logicForChangingImportDeclarationToPath(importStatement);
                File directory = new File(path);
                File[] files = directory.listFiles();
                for (File file : files) {
                    if (file.isDirectory()) {
                        // ignore directories cuz java imports will never import a directory
                        continue;
                    }
                    if (!set.contains(file.getAbsolutePath())) {
                        set.add(file.getAbsolutePath());
                        extractListOfEssentialFileImports(filepath, importPrefix, stopLine, set, basePath);
                    }
                }
            }
        }
        return essentialFilesPaths;
    }
}
