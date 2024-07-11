package MARMSUI.migration;

import MARMSUI.migration.model.Type;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class TestingCodeIntegrity {
    private static int fileSearched = 0;
    public static void main(String[] args) throws Exception {
        // Path to the Java file you want to analyze
        String basePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/vo";
        File basefile = new File(basePath);
        iterateFiles(basefile);
        System.out.println("Total number files searched: " + fileSearched);
    }

    public static void execute(String filePath, Map<String,String> getterSetterMap) {

        try {
            // Parse the file
            FileInputStream in = new FileInputStream(filePath);
            ParseResult<CompilationUnit> parseResult = new JavaParser().parse(in);

            Optional<CompilationUnit> compilationUnit = parseResult.getResult();
            if (compilationUnit.isPresent()) {
                // Create a map to hold getter and setter names


                // Visit and process the methods in the file, populating the map
                new MethodVisitor().visit(compilationUnit.get(), getterSetterMap);

                // Print the map contents
//                getterSetterMap.forEach((key, value) -> System.out.println(key + " - " + value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class MethodVisitor extends VoidVisitorAdapter<Map<String, String>> {
        @Override
        public void visit(MethodDeclaration md, Map<String, String> getterSetterMap) {
            super.visit(md, getterSetterMap);
            // Check if the method is a getter or setter and add it to the map
            if (isGetter(md)) {
                getterSetterMap.put(md.getNameAsString().toLowerCase(), "Getter");
            } else if (isSetter(md)) {
                getterSetterMap.put(md.getNameAsString().toLowerCase(), "Setter");
            }
        }

        private boolean isGetter(MethodDeclaration method) {
            // A simple heuristic: no parameters, name starts with "get", and it returns a value
            return !method.getType().isVoidType()
                    && method.getNameAsString().startsWith("get")
                    && method.getParameters().isEmpty();
        }

        private boolean isSetter(MethodDeclaration method) {
            // A simple heuristic: one parameter, name starts with "set"
            return method.getType().isVoidType()
                    && method.getNameAsString().startsWith("set")
                    && method.getParameters().size() == 1;
        }
    }

    private static void iterateFiles(File file) throws Exception {
        if(file.isDirectory()) {
            File[] innerFiles = file.listFiles();
            for(File file1 : innerFiles) {
                iterateFiles(file1);
            }
        } else {
            //
            fileSearched++;
            Map<String, Type> fieldMap = new HashMap<>();
            ExtractFieldsFromClass.execute(new FileInputStream(file.getAbsolutePath()), fieldMap);

            Map<String,String> getterSetterMap = new HashMap<>();
            execute(file.getAbsolutePath(),getterSetterMap);
            Set<String> fieldNamesLoweredCase = fieldMap.keySet();

            System.out.println("Checking " + file.getAbsolutePath());
            for(String field : fieldNamesLoweredCase) {
                Type type = fieldMap.get(field);
                if(type.type.equals("boolean") || type.type.equals("Hashtable")) {
                    continue;
                }
                String getterName = "get" + field;
                String setterName = "set" + field;
                if(!getterSetterMap.containsKey(getterName) || !getterSetterMap.containsKey(setterName)) {
                    System.out.println("Not found: " + field);
                }
            }
            System.out.println("Finished checking " + file.getAbsolutePath() + "==============================");
        }
    }
}
