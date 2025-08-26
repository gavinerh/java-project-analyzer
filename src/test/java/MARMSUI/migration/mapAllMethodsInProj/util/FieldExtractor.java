package MARMSUI.migration.mapAllMethodsInProj.util;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class FieldExtractor {

    /**
     * Extracts fields from a Java class file that are from the project's own classes
     *
     * @param filePath Path to the Java file to analyze
     * @param projectRootPath Path to the project's root directory
     * @param projectPackagePrefix The package prefix for project classes (e.g., "com.mycompany")
     * @return List of fields with their fully qualified types (only project-specific types)
     */
    public static List<String> extractProjectFields(String filePath, String projectRootPath, String projectPackagePrefix) {
        List<String> fieldsWithFullPath = new ArrayList<>();

        try {
            // Setup type solvers
            CombinedTypeSolver typeSolver = new CombinedTypeSolver();

            // Add reflection solver for standard library
            typeSolver.add(new ReflectionTypeSolver());

            // Add project source code solver
            typeSolver.add(new JavaParserTypeSolver(new File(projectRootPath)));

            // Configure JavaParser with the type solver
            JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
            StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);

            // Parse the file
            File file = new File(filePath);
            FileInputStream in = new FileInputStream(file);
            CompilationUnit cu = StaticJavaParser.parse(in);
            in.close();

            // Visit all field declarations
            cu.findAll(FieldDeclaration.class).forEach(field -> {
                for (VariableDeclarator variable : field.getVariables()) {
                    try {
                        // Resolve the type using the symbol solver
                        String resolvedType = field.getElementType().resolve().describe();

                        // Only include fields from project classes (excluding Java standard library and external libs)
                        if (resolvedType.startsWith(projectPackagePrefix)) {
                            fieldsWithFullPath.add(resolvedType + " " + variable.getNameAsString());
                        }
                    } catch (Exception e) {
                        // Skip fields that can't be resolved
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fieldsWithFullPath;
    }

    /**
     * Alternative method that doesn't filter by package prefix but instead
     * only includes non-primitive fields from the project classpath
     */
    public static List<String> extractAllProjectFields(String filePath, String projectRootPath) {
        List<String> fieldsWithFullPath = new ArrayList<>();

        try {
            // Setup type solvers
            CombinedTypeSolver typeSolver = new CombinedTypeSolver();
            typeSolver.add(new ReflectionTypeSolver());
            typeSolver.add(new JavaParserTypeSolver(new File(projectRootPath)));

            JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
            StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);

            File file = new File(filePath);
            FileInputStream in = new FileInputStream(file);
            CompilationUnit cu = StaticJavaParser.parse(in);
            in.close();

            cu.findAll(FieldDeclaration.class).forEach(field -> {
                for (VariableDeclarator variable : field.getVariables()) {
                    try {
                        String resolvedType = field.getElementType().resolve().describe();

                        // Exclude primitive types, Java standard library and common external libraries
                        if (!isPrimitiveOrStandardLibrary(resolvedType)) {
                            fieldsWithFullPath.add(resolvedType + " " + variable.getNameAsString());
                        }
                    } catch (Exception e) {
                        // Skip fields that can't be resolved
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return fieldsWithFullPath;
    }

    private static boolean isPrimitiveOrStandardLibrary(String type) {
        return type.equals("int") ||
                type.equals("boolean") ||
                type.equals("short") ||
                type.equals("long") ||
                type.equals("float") ||
                type.equals("double") ||
                type.equals("byte") ||
                type.equals("char") ||
                type.startsWith("java.") ||
                type.startsWith("javax.") ||
                type.startsWith("com.sun.") ||
                type.startsWith("org.w3c.") ||
                type.startsWith("org.xml.");
    }

    public static void main(String[] args) {
//        if (args.length < 3) {
//            System.out.println("Usage: ProjectFieldExtractor <file-path> <project-root-path> <project-package-prefix>");
//            return;
//        }

        String filePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/migration/EliteParticipantDataMigration.java";
        String rootPath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java";
        String packagePrefix = "com.sg.sq.marmsui";

        List<String> fields = extractProjectFields(filePath, rootPath, packagePrefix);

        System.out.println("Project-specific fields:");
        for (String field : fields) {
            System.out.println("- " + field);
        }
    }
}