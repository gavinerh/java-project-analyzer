package MARMSUI.migration;

import java.io.*;
import java.util.*;

public class RemoveDuplicateStringFromExampleModel {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> fileNamesToInspect = generateFileNamesToInspect("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/database/sql/persistence/model");
        String filename = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/database/sql/persistence/model/CityExample.java";
        String pathToDup = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/database/sql/persistence/dup";
        removeDuplicate(fileNamesToInspect,pathToDup);
//        String[] arr = generateConstantVariable("addCriterion(\"CTY_CD =\", valxue, \"ctyCd\");","addCriterion(");
//        System.out.println(arr);

    }

    private static List<String> generateFileNamesToInspect(String directory) {
        File[] files = new File(directory).listFiles();
        List<String> fileList = new ArrayList<>();
        for(File file : files) {
            if(file.getName().endsWith("Example.java")) {
                fileList.add(file.getAbsolutePath());
            }
        }
        return fileList;
    }

    private static void removeDuplicate(List<String> filenames, String dupDirectory) throws FileNotFoundException {
        Scanner scanner = null;
        for(String filename : filenames) {
            Map<String,String> constants = new HashMap<>();
            File file = new File(filename);
            scanner = new Scanner(new FileInputStream(filename));
            String pattern = "addCriterion(";
            populateMapAndGenerateDupFile(pattern,scanner,constants,extractFileName(file.getName()),dupDirectory);
        }
    }

    private static String extractFileName(String fileNameWithExt) {
        int endInd = fileNameWithExt.indexOf(".");
        return fileNameWithExt.substring(0,endInd);
    }

    private static void populateMapAndGenerateDupFile(String pattern,Scanner scanner,Map<String,String> constants, String fileName, String pathToDup) {
        StringBuilder contents = new StringBuilder();
        StringBuilder classDeclaration = new StringBuilder();
        boolean insideClassDeclaration = false;
        while(scanner.hasNextLine()) {
            String currentRow = scanner.nextLine();
            if(!insideClassDeclaration && currentRow.contains("public class")) {
                insideClassDeclaration = true;
                classDeclaration.append(currentRow + "\n");
                continue;
            }
            if(currentRow.trim().startsWith(pattern) && currentRow.trim().contains("value")) {
                String[] mapValues = generateConstantVariable(currentRow,pattern);
                if(mapValues != null) {
                    if(!constants.containsKey(mapValues[1])) {
                        constants.put(mapValues[1],mapValues[0]);
                    }
                    // replace value in string
                    String replacedVal = currentRow.replace("\"" + mapValues[1] + "\"", mapValues[0]);
                    contents.append(replacedVal + "\n");
                } else {
                    contents.append(currentRow + "\n");
                }
            } else {
                if(!insideClassDeclaration) {
                    classDeclaration.append(currentRow + "\n");
                } else {
                    contents.append(currentRow + "\n");
                }
            }
        }
        String template = "private static final String %s = \"%s\";\n";
        String constantsToAppend = generateConstantString(template,constants);
        printToFile(classDeclaration.toString(),constantsToAppend,contents.toString(), fileName, pathToDup);
    }

    private static void printToFile(String classDeclaration, String constants, String contents, String oldFileName, String newDirectory) {
        try {
            // Create a File object
            String newFilePath = newDirectory + "/" + oldFileName + "Dup.java";
            File file = new File(newFilePath);

            // Check if file exists; if not, create a new one
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }

            // Use FileWriter to write the string into the file
            FileWriter writer = new FileWriter(file, true);
            writer.write(classDeclaration);
            writer.write(constants);
            writer.write(contents);
            System.out.println("String written to the file successfully.");

            // Close the writer
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static String generateConstantString(String template, Map<String,String> constants) {
        StringBuilder stringBuilder = new StringBuilder();
        for(String key : constants.keySet()) {
            String toAppend = String.format(template,constants.get(key),key);
            stringBuilder.append(toAppend);
        }
        return stringBuilder.toString();
    }

    private static String[] generateConstantVariable(String val, String pattern) {
        // receives e.g. addCriterion("CTY_CD =", value, "ctyCd");
        // take the CTY_CD and return
        String[] splitByComma = val.split(",");
        if(splitByComma.length < 2)  {
            return null;
        }
        int startInd = val.indexOf(pattern) + pattern.length();
        startInd = val.indexOf("\"",startInd) + 1;
        int endInd = val.indexOf(" ", startInd);
        String withinFirstQuotes = val.substring(startInd,endInd);
        String lastVal = splitByComma[splitByComma.length-1];
        startInd = lastVal.indexOf("\"") + 1;
        endInd = lastVal.indexOf("\"", startInd);
        try {
            return new String[]{withinFirstQuotes,lastVal.substring(startInd,endInd)};
        } catch (Exception e) {
            System.out.println("error");
        }
        return null;
    }
}
