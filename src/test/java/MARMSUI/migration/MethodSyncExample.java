package MARMSUI.migration;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.LineComment;
import org.apache.commons.lang3.StringUtils;

import java.nio.file.Paths;
import java.util.*;

public class MethodSyncExample {

    // Helper function to get all method signatures as strings, or store MethodDeclaration itself
    public static Map<String, MethodDeclaration> getMethodMap(ClassOrInterfaceDeclaration cls) {
        Map<String, MethodDeclaration> map = new HashMap<>();
        for (MethodDeclaration m : cls.getMethods()) {
            String sig = m.getSignature().toString(); // includes name & param types
            map.put(sig, m);
        }
        return map;
    }

    public static void main(String[] args) throws Exception {
        // Load class1.java
        String toReplace = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/data/EliteParticipantData.java";
        String refactored = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/codeupdate/service/impl/data/EliteParticipantDataNew.java";
        CompilationUnit cu1 = StaticJavaParser.parse(Paths.get(toReplace));
        // Load class2.java
        CompilationUnit cu2 = StaticJavaParser.parse(Paths.get(refactored));

        String className1 = extractClassName(toReplace);
        String className2 = extractClassName(refactored);

        // Assume the first class in each file
        ClassOrInterfaceDeclaration class1 = cu1.getClassByName(className1).get();
        ClassOrInterfaceDeclaration class2 = cu2.getClassByName(className2).get();

        Map<String, MethodDeclaration> methods1 = getMethodMap(class1);
        Map<String, MethodDeclaration> methods2 = getMethodMap(class2);

        // Find common signatures
        Set<String> common = new HashSet<>(methods1.keySet());
        common.retainAll(methods2.keySet());

        // Delete from class1 methods that exist in class2 (by signature)
        for (String sig : common) {
            methods1.get(sig).remove();
        }

        // Copy method from class2 to class1
        for (String sig : common) {
            MethodDeclaration methodFromClass2 = methods2.get(sig).clone();
            // Add the comment above the method
            methodFromClass2.setComment(new LineComment("refactored method"));
            class1.addMember(methodFromClass2);
        }

        // Write the modified class1 to a new file
        System.out.println(cu1.toString()); // or Files.write(Paths.get("class1_modified.java"), cu1.toString().getBytes());
    }

    private static String extractClassName(String filePath) {
        if(StringUtils.isBlank(filePath)) {
            throw new RuntimeException("File path cannot be empty");
        }
        int count = 0;
        int potentialLastSlash = 0;
        if(!filePath.contains(".java")) {
            throw new RuntimeException("File path do not contain .java");
        }
        if(!filePath.contains("/") && filePath.length() > 7) {
            return getClassName(filePath);
        }
        for(char c : filePath.toCharArray()) {
            if(c == '/') {
                potentialLastSlash = count;
            }
            count++;
        }
        return getClassName(filePath.substring(potentialLastSlash+1));
    }

    private static String getClassName(String raw) {
        int end = raw.indexOf(".java");
        return raw.substring(0,end);
    }
}