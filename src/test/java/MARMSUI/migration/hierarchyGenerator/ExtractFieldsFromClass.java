package MARMSUI.migration.hierarchyGenerator;

import MARMSUI.migration.model.Type;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtractFieldsFromClass {
    public static void main(String[] args) throws Exception {
        Map<String,Type> map = new HashMap<>();
        execute(new FileInputStream("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/QualificationServiceImpl.java"),map);
        System.out.println(map.size());
    }

    public static void execute(FileInputStream in, Map<String,Type> classFieldsInfo) throws Exception {

        // Create a JavaParser instance with default configuration
        JavaParser parser = new JavaParser(new ParserConfiguration());

        // Parse the file
        CompilationUnit cu = parser.parse(in).getResult().orElseThrow(() -> new RuntimeException("Unable to parse the file."));

        // Map to hold class names and their fields (name and type)

        // Visit and collect the fields of all classes in the file
        cu.accept(new ClassVisitor(), classFieldsInfo);

        // Close the file input stream
        in.close();

        // Print the class names and their fields (name and type)

    }

    /**
     * Simple visitor implementation for visiting class declarations.
     */
    private static class ClassVisitor extends VoidVisitorAdapter<Map<String, Type>> {
        @Override
        public void visit(ClassOrInterfaceDeclaration n, Map<String, Type> classFieldsInfo) {
            // List to hold field info (type and name) for the current class
            List<String> fieldsInfo = new ArrayList<>();

            // Iterate over all fields in the class
            for (FieldDeclaration field : n.getFields()) {
                // For each field, create a string with its type and name
                String fieldInfo = field.getElementType() + " " + field.getVariables().get(0).getName();
                // Add the field info to the list
                fieldsInfo.add(fieldInfo);
                String type = field.getElementType().asString();
                String fieldName = field.getVariables().get(0).getNameAsString();
                Type type1 = new Type(fieldName,type,convertJavaTypeToSql(type));
                classFieldsInfo.put(fieldName.toLowerCase(),type1);
            }


            // Don't forget to call super, it makes sure you visit all nodes in the AST
            super.visit(n, classFieldsInfo);
        }
    }

//    public static void main(String[] args) throws Exception {
//        FileInputStream fileInputStream = new FileInputStream("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/vo/collateral/Card.java");
//        Map<String,Type> map = returnsFieldsMap(fileInputStream);
//        System.out.println(map.size());
//        Map<String,Type> map1 = returnsFieldsMap(new FileInputStream("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/vo/event/EventLogVo.java"));
//        System.out.println(map1.size());
//    }
//    public static Map<String,Type> returnsFieldsMap(FileInputStream in) throws Exception {
//        // returns a map: key is lower case field name, value is the actual field name
//        CompilationUnit cu = StaticJavaParser.parse(in);
//
//        // Prepare a map to hold field names and their types
//        Map<String, Type> fieldsMap = new HashMap<>();
//
//        // Visit class declarations in the file
//        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(c -> {
//            // For each class, visit its fields
//            c.getFields().forEach(field -> {
//                // For each field, extract its name and type, and put them in the map
//                for (VariableDeclarator variable : field.getVariables()) {
//                    fieldsMap.put(variable.getNameAsString().toLowerCase(), new Type(variable.getNameAsString(),variable.getTypeAsString(),convertJavaTypeToSql(variable.getTypeAsString())));
//                }
//            });
//        });
//
//        // Close the file input stream
//        in.close();
//
//        // Print the extracted fields and their types
//        fieldsMap.forEach((name, type) -> System.out.println(name + " : " + type));
//        return fieldsMap;
//    }

    public static String convertJavaTypeToSql(String type) {
        switch (type) {
            case "String", "java.lang.String":
                return "VARCHAR";
            case "Long", "long", "int", "Integer", "java.lang.Integer", "java.lang.Long":
                return "NUMERIC";
            case "Date", "java.util.Date", "java.sql.Date", "java.sql.Timestamp", "Timestamp":
                return "TIMESTAMP";
            case "java.lang.Float", "float", "double", "java.lang.Double":
                return "DECIMAL";
            default:
                System.out.println("Encountered type unknown: " + type);
                return "UNKNOWN";
        }
    }
}
