package MARMSUI.migration.hierarchyGeneratorImproved;

import MARMSUI.migration.hierarchyGenerator.model.MethodChain;
import MARMSUI.migration.hierarchyGenerator.util.ExtractHierarchyFromFile;

import java.util.Scanner;
import java.util.Stack;

public class PrintHierarchy {
    public static void main(String[] args) {
        // Todo: change the file path
        MethodChain chain = ExtractHierarchyFromFile.extractHierarchyFromFile("/Users/macuser/Desktop/hierarchy-generator/updatecust-map");
        extractHierarchyGivenEndMethodDetails(chain);
    }
    private static void extractHierarchyGivenEndMethodDetails(MethodChain chain) {
        String className = "";
        String methodName = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            className = getUserInput("Enter class Name: ", scanner);
            methodName = getUserInput("Enter method Name: ", scanner);
            if (className == null || methodName == null) {
                continue;
            }
            MethodChain dupChain = new MethodChain(null, null);
            // Todo: need to consider multiple parents calling this method u enter into program to search
//            extractHierarchyFromChain(className, methodName, chain, hierarchyList);
            extractHierarchyFromChainModified(className, methodName, chain, dupChain);
            System.out.println(dupChain);
            printHierarchyInFlatFormat(dupChain, "start ");
            break;
        }
        scanner.close();
    }

    private static void printHierarchyInFlatFormat(MethodChain methodChain, String toPrint) {
        if(methodChain.getChildMethodChains().isEmpty()) {
            System.out.println(toPrint);
        }
        for(MethodChain chain : methodChain.getChildMethodChains()) {
            printHierarchyInFlatFormat(chain, toPrint + "-> " + chain.getClassName() + "." + chain.getMethodName());
        }
    }

    private static String getUserInput(String toPrintToConsole, Scanner scanner) {
        System.out.print(toPrintToConsole);
        String val = scanner.nextLine();
        if (val.equals("-")) {
            return null;
        }
        return val;
    }


    private static boolean extractHierarchyFromChainModified(String className, String methodName, MethodChain chain, MethodChain newChain) {
        if (newChain.getClassName() == null && newChain.getMethodName() == null) {
            // initialise the dup chain
            newChain.setClassName(chain.getClassName());
            newChain.setMethodName(chain.getMethodName());
        }
        boolean flag = false;
        if (chain.getClassName().equalsIgnoreCase(className) && chain.getMethodName().equalsIgnoreCase(methodName)) {
//            newChain.addChildMethodChain(chain);
            return true;
        } else {
            for (MethodChain child : chain.getChildMethodChains()) {
                MethodChain dupChild = new MethodChain(child.getMethodName(), child.getClassName());
                newChain.addChildMethodChain(dupChild);
                boolean val = extractHierarchyFromChainModified(className, methodName, child, dupChild);
                if(val) {
                    flag = true;
                }
                if (!val) {
                    newChain.removeLastChild();
                }
            } // after adding flag to preserve the state that the method was found, need to add a condition to remove the last child if the method was not found
            // Todo: Test with more complex data
            return flag;
        }
    }
}
