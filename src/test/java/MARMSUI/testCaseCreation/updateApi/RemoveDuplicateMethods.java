package MARMSUI.testCaseCreation.updateApi;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RemoveDuplicateMethods {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/macuser/Desktop/response_content");
        Set<String> set = new HashSet<>();
        extractIndividualMethod(set, fileInputStream);
        printSetContents(set, new FileWriter("/Users/macuser/Desktop/response_cleaned.txt"));
    }

    public static void printSetContents(Set<String> set, FileWriter outputFileName) {
        try {
            for (String s : set) {
                outputFileName.write("\n" + s);
            }
            outputFileName.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void extractIndividualMethod(Set<String> set, FileInputStream fileInputStream) throws IOException {
        Scanner scanner = new Scanner(fileInputStream);
        String completeLine = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isBlank()) { // end of method
                if (!completeLine.isBlank()) {
                    set.add(completeLine);
                }
                completeLine = "";
            } else {
                completeLine += line + "\n";
            }
        }
        if(!completeLine.isBlank()) {
            set.add(completeLine);
        }
        fileInputStream.close();
    }

}
