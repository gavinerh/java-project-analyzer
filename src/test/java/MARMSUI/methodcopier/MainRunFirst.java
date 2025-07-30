package MARMSUI.methodcopier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainRunFirst {
    public static void main(String[] args) throws FileNotFoundException {
        // for partially migrated code to spring boot
        String clsName = "ppsParticipantData";
        String fileName = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/TierHandlerUtilTemp.java";
        Set<String> methodSet = new HashSet<>();
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fileInputStream);
        StringBuilder tempLine = new StringBuilder();
        String methodName = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.contains(clsName)) {
                // check if the line contains a method call
                if (!tempLine.isEmpty()) {
                    tempLine.append(line);
                    // find the number of parameters in the mtd call
                    if (line.contains(";")) {
                        int count = findParameterCount(tempLine.toString());
                        methodSet.add(methodName + "-" + count);
                        tempLine.delete(0, tempLine.length());
                        methodName = "";
                    }
                    continue;
                }
                // extract the method name
                int startIndex = line.indexOf(clsName) + clsName.length() + 1; // +1 for the dot
                int endIndex = line.indexOf("(", startIndex);
                if (endIndex == -1 || endIndex < startIndex) {
                    if (!line.contains("private"))
                        throw new RuntimeException("End index is smaller than start or is -1");
                    continue;
                }
                methodName = line.substring(startIndex, endIndex).trim();
                startIndex = endIndex + 1;
                endIndex = line.indexOf(";", startIndex);
                if (endIndex == -1) {
                    // not end of the line yet
                    tempLine.append(line.substring(startIndex));
                } else {
                    // end of the line
                    tempLine.append(line.substring(startIndex, endIndex));
                    // find the number of parameters in the mtd call
                    int count = findParameterCount(tempLine.toString());
                    methodSet.add(methodName + "-" + count);
                    tempLine.delete(0, tempLine.length());
                    methodName = "";
                }

            }
        }
        for(String val : methodSet) {
            System.out.println(val);
        }
    }

    private static int findParameterCount(String val) {
        if (val.isBlank()) {
            return 0;
        }
        int count = 1;
        for (char c : val.toCharArray()) {
            if (c == ',') {
                count++;
            }
        }
        return count;
    }
}
