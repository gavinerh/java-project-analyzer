package MARMSUI.mergeConflict;

import MARMSUI.mergeConflict.model.ObjectModel;
import MARMSUI.migration.ForMappersMigration;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileContentAnalyser {
    public static void main(String[] args) {
        Map<String, List<String>> mapOfJavaMappers = new HashMap<>();
        Map<String, Map<String, String>> mapOfXmlMappers = new HashMap<>();
        try {
            String file1 = "/Users/macuser/Downloads/postEmptyString";
            String file2 = "/Users/macuser/Downloads/postNonEmptyString";
            FileInputStream fileInputStream1 = new FileInputStream(file1);
            FileInputStream fileInputStream2 = new FileInputStream(file2);


        } catch (Exception e) {
            throw new RuntimeException("Exception detected");
        }
    }


    public static void extractContents(Map<String, ObjectModel> mapOfTypes, FileInputStream fileInputStream) {
        Scanner scanner = new Scanner(fileInputStream);
        boolean isComment = false;
        boolean isInsideFunction = false;
        boolean isInsideNonFunction = false;
        String conciseContent = "";
        String actualContent = "";
        ObjectModel objectModel = null;
        int isInsideClass = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().startsWith("//")) {
                continue;
            }
            if (isInsideClass == 0 && line.contains(" class ")) {
                isInsideClass = 1;
            }
            char prevChar = '.';
            if (isInsideClass == 0) continue;
            for (char c : line.toCharArray()) {
                if (c == '/') {
                    if (prevChar == '.') {
                        prevChar = '/'; // comment reached initialisation
                    } else if (prevChar == '/') {
                        break;
                    } else if (prevChar == '*') {
                        if (isComment) {
                            isComment = false;
                            prevChar = '.';
                        }
                    }
                    continue;
                }
                if (c == '*') {
                    if (prevChar == '/') {
                        isComment = true;
                        prevChar = '.';
                    }
                    continue;
                }
                if (isComment) continue;
                if (isInsideClass == 1 && c == '{') {
                    isInsideClass = 2;
                }
                if (isInsideClass != 2) continue;

                // extract methods only

                actualContent += c;
            }
        }
    }
}
