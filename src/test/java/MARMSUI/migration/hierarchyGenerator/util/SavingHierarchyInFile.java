package MARMSUI.migration.hierarchyGenerator.util;

import MARMSUI.migration.hierarchyGenerator.model.MethodChain;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SavingHierarchyInFile {
    public static void main(String[] args) {

    }

    public static void saveHierarchyInFile(MethodChain chain, String filePath) {
        StringBuffer stringBuffer = extractChainIntoStringFormat(chain, new StringBuffer(), "1");
        System.out.println(stringBuffer);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String[] arr = stringBuffer.toString().split("\n");
            for(String line : arr) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Lines have been appended to the file.");
        } catch (IOException e) {
            System.err.println("An IOException was caught: " + e.getMessage());
        }
    }

    private static StringBuffer extractChainIntoStringFormat(MethodChain chain, StringBuffer stringBuffer, String initialParentIndex) {
        stringBuffer.append(initialParentIndex + ":" + chain.getClassName() + "." + chain.getMethodName() + "\n");
        String nextParentIndex = initialParentIndex + ".1";
        for(MethodChain child : chain.getChildMethodChains()) {
            extractChainIntoStringFormat(child, stringBuffer, nextParentIndex);
            nextParentIndex = increaseNextParentIndex(nextParentIndex);
        }
        return stringBuffer;
    }

    private static String increaseNextParentIndex(String currentParentIndex) {
        String[] arr = currentParentIndex.split("\\.");
        int last = Integer.parseInt(arr[arr.length - 1]);
        arr[arr.length - 1] = String.valueOf(last + 1);
        return String.join(".", arr);
    }
}
