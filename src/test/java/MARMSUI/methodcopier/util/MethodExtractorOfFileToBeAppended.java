package MARMSUI.methodcopier.util;

import MARMSUI.methodcopier.model.MethodDetails;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MethodExtractorOfFileToBeAppended {

    public static void main(String[] args) {
        // Change this to the path of your .java file to be analyzed
        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/codeupdate/QualificationServiceImpl.java";

        execute(filePath);
    }

    public static List<MethodDetails> execute(String filePath) {
        try {
            // Get the list of method details by invoking extractMethodsFromFile
            List<MethodDetails> methods = extractMethodsFromFile(filePath);

            // Print the extracted method details
//            System.out.println("Methods found in the file:");
//            for (MethodDetails method : methods) {
//                System.out.println(method);
//            }
            return methods.isEmpty() ? new ArrayList<>() : methods;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        return new ArrayList<>();
    }
    /**
     * Extracts methods from a .java file and returns a list of MethodDetails objects.
     *
     * @param filePath path to the .java file
     * @return List of MethodDetails containing method name and parameter types
     * @throws FileNotFoundException if the file is not found at the given path
     */
    public static List<MethodDetails> extractMethodsFromFile(String filePath) throws FileNotFoundException {
        // Parse the Java file
        CompilationUnit compilationUnit = StaticJavaParser.parse(new File(filePath));

        // Extract all method declarations
        List<MethodDeclaration> methodDeclarations = compilationUnit.findAll(MethodDeclaration.class);

        // Convert method declarations to MethodDetails objects
        List<MethodDetails> methodDetailsList = new ArrayList<>();
        for (MethodDeclaration method : methodDeclarations) {
            String methodName = method.getNameAsString();
            List<String> parameterTypes = method.getParameters().stream()
                    .map(param -> param.getType().asString())  // Extract parameter types
                    .toList();

            // Create a MethodDetails object and add it to the list
            MethodDetails methodDetails = new MethodDetails(methodName);
            methodDetails.setListOfParameterTypes(parameterTypes);
            methodDetails.setNumOfParameters(parameterTypes.size());
            methodDetailsList.add(methodDetails);
        }

        return methodDetailsList;
    }
}
