package MARMSUI.migration;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RemoveM_TagFromFields {
    static Map<String,String> mapOfOldToNewVariableDeclaration = new HashMap<>();
    public static void main(String[] args) throws IOException {
        String basePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/vo";
        File basefile = new File(basePath);
        iterateFiles(basefile);
    }

    private static void iterateFiles(File file) throws IOException {
        if(file.isDirectory()) {
            File[] innerFiles = file.listFiles();
            for(File file1 : innerFiles) {
                iterateFiles(file1);
            }
        } else {
            // using the javaparser to find all the field variables with the m_ starter string
            FileInputStream fileInputStream = new FileInputStream(file);
            Map<String,String> mapOfOldToNew = execute(fileInputStream, file.getName());
            // print corrected file
            fileInputStream.close();
            if(!mapOfOldToNew.isEmpty()) {
                System.out.println("Printing file contents: -----------------:");
                printWholeFile(file, mapOfOldToNew);
            } else {
                System.out.println("File " + file.getName() + " has nothing to print------------------");
            }
        }
    }

    private static void printWholeFile(File file, Map<String,String> map) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            boolean hasPrinted = false;
            if(!line.isBlank()) {
                for(String key : map.keySet()) {
                    if(line.contains(key)) {
                        System.out.println(line.replace(key, map.get(key)));
                        hasPrinted = true;
                        break;
                    }
                }
                if(!hasPrinted) {
                    System.out.println(line);
                }
            } else {
                System.out.println();
            }
        }
        scanner.close();
        scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private static Map<String,String> execute(FileInputStream fileInputStream, String fileName) throws FileNotFoundException {
        System.out.println("Viewing " + fileName + " =============================");
        if(fileName.equals("PassengerInfo.java")) {
            System.out.println("Reached here");
        }
        CompilationUnit cu = StaticJavaParser.parse(fileInputStream);

        // Prepare a map to hold field names and their types
        Map<String, String> fieldsMap = new HashMap<>();

        // Visit class declarations in the file
        cu.findAll(ClassOrInterfaceDeclaration.class).forEach(c -> {
            // For each class, visit its fields
            c.getFields().forEach(field -> {
                // For each field, extract its name and type, and put them in the map
                for (VariableDeclarator variable : field.getVariables()) {
                    if(variable.getNameAsString().startsWith("m_") || variable.getNameAsString().startsWith("M_")) {
                        fieldsMap.put(variable.getNameAsString(), modifyFieldName(variable.getNameAsString()));
                    }
                }
            });
        });

        // Close the file input stream
        return fieldsMap;
    }

    private static String modifyFieldName(String name) {
        String removedTag = "";
        if(name.startsWith("m_")) {
            removedTag = name.replace("m_", "");
        } else if (name.startsWith("M_")) {
            removedTag = name.replace("M_", "");
        } else {

        }
        try {
            return removedTag.substring(0,1).toLowerCase() + removedTag.substring(1);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(name);
            return null;
        }
    }
}
