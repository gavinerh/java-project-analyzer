package MARMSUI.migration;

import com.github.javaparser.JavaParser;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CopyRelevantMethodsToBringOver {

    private static Set<String> destinationMethodNamesAlreadyPresent = new HashSet<>();

    public static void main(String[] args) throws Exception {
        String filenameToExamine = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/service/kfEnrollment/KfEnrolmentImpl.java";
        String patternToIdentify = "customerValidator.";
        String fileToExtractMethodFrom = "/Users/macuser/Documents/updated-lsl-app/marms/MARMS/Source Code/Business Components/Common Classes/com/singaporeair/marms/abacus/business/customer/CustomerValidator.java";
        String fileToPasteMethodTo = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/validator/CustomerValidatorDup.java";
        FileInputStream fileInputStream = new FileInputStream(filenameToExamine);
        Set<String> methodNames = new HashSet<>();
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isBlank()) {
                continue;
            }
            if(line.contains(patternToIdentify)) {
                String methodName = extractMethodName(patternToIdentify, line);
                methodNames.add(methodName);
            }
        }
        // populate the existing methods in the destination file
        execute(null, fileToPasteMethodTo, -1);
        Set<String> methodsToCopy = removeMethodsAlreadyPresent(methodNames, destinationMethodNamesAlreadyPresent);
        System.out.println("Only bringing over method count: "+ methodsToCopy.size());
        for(String methodName : methodsToCopy) {
            execute(methodName, fileToExtractMethodFrom, -1);
        }
    }

    private static Set<String> removeMethodsAlreadyPresent(Set<String> methodNames, Set<String> destinationMethodNamesAlreadyPresent) {
        Set<String> methodsToCopy = new HashSet<>();
        for(String methodName : methodNames) {
            if(!destinationMethodNamesAlreadyPresent.contains(methodName)) {
                methodsToCopy.add(methodName);
            }
        }
        return methodsToCopy;
    }

    private static String extractMethodName(String pattern, String line) {
        int start = line.indexOf(pattern) + pattern.length();
        int end = line.indexOf("(", start);
        return line.substring(start, end).trim();
    }

    public static void execute(String methodName, String filePath, int paramSize) throws FileNotFoundException {

        // Path to the Java file to be parsed

        // Parse the file
        FileInputStream in = new FileInputStream(filePath);
        CompilationUnit cu = StaticJavaParser.parse(in);

        // Visit and print the methods in the file
        new MethodVisitor(methodName, paramSize).visit(cu, null);
    }

    /**
     * Simple visitor implementation for visiting MethodDeclaration nodes.
     */
    private static class MethodVisitor extends VoidVisitorAdapter<Void> {
        private String methodName;
        private int paramSize;

        public MethodVisitor(String methodName, int paramSize) {
            this.methodName = methodName;
            this.paramSize = paramSize;
        }

        @Override
        public void visit(MethodDeclaration n, Void arg) {
            if(methodName == null) {
                destinationMethodNamesAlreadyPresent.add(n.getName().asString());
            } else {
                if (n.getName().asString().equals(methodName)) {
                    // Print the method declaration if the name matches
//                    System.out.println("Method Name: " + n.getName());

                    // Print parameters
//                    System.out.print("Parameters: ");
                    if(paramSize == -1) {
//                        n.getParameters().forEach(param -> System.out.print(param.getType() + " " + param.getName() + ", "));
//                        System.out.println("\n");

                        // Optionally, print the entire method declaration
                        System.out.println(n);
                    } else {
                        // compare param size
                        if(n.getParameters().size() == paramSize) {
                            System.out.println(n);
                        }
                    }

                }
                super.visit(n, arg);
            }
        }
    }
}
