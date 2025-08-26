package MARMSUI.migration.mapAllMethodsInProj.util;


import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;

import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImportExtractor {
    public static void main(String[] args) {
        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/migration/EliteParticipantDataMigration.java";
        List<String> importDeclarations = extractImports(filePath);
        System.out.println(importDeclarations);
    }

    public static List<String> extractImports(String filePath) {
        List<String> importList = new ArrayList<>();

        try {
            // Check if file exists
            if (!Files.exists(Paths.get(filePath))) {
                System.out.println("File not found: " + filePath);
                return importList;
            }

            // Parse the Java file
            FileInputStream fileStream = new FileInputStream(filePath);
            CompilationUnit cu = StaticJavaParser.parse(fileStream);
            fileStream.close();

            // Extract import statements
            List<ImportDeclaration> imports = cu.getImports();

            for (ImportDeclaration importDecl : imports) {
                String processedImport = postProcessImportStatement(importDecl.toString().trim());
                if(processedImport != null)
                    importList.add(processedImport);
            }

        } catch (Exception e) {
            System.out.println("Error processing file: " + e.getMessage());
            e.printStackTrace();
        }
        return importList;
    }

    private static String postProcessImportStatement(String importStatement) {
        // Remove the 'import' keyword and the trailing semicolon
        String processed = importStatement.replace("import", "").replace(";", "").trim();
        processed = processed.replace(";", "").trim();
        if(processed.startsWith("com.sg.sq.marmsui")) {
            return processed;
        }
        return null;
    }

}