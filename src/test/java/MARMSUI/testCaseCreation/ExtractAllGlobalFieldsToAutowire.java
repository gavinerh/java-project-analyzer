package MARMSUI.testCaseCreation;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class ExtractAllGlobalFieldsToAutowire {

    static Map<String, String> mapOfClassNameToPath = new HashMap<>();
    static Set<String> setOfTypesToAutowire = new HashSet<>();

    public static void main(String[] args) {
        String basePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java";
        File file = new File(basePath);
        iterateAllFiles(file);
        String classNameToSearch = "QualificationServiceImpl";
        extractFields(mapOfClassNameToPath.get(classNameToSearch), classNameToSearch);
        System.out.println(setOfTypesToAutowire.size() + "\n =================================\nFields to autowire:\n");
        printRelevantFields("/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/database/sql/persistence/mappers");
    }

    private static void printRelevantFields(String basePath) {
        StringBuilder autowired = new StringBuilder();
        StringBuilder contextConfig = new StringBuilder();
        for(String field : setOfTypesToAutowire) {
            if(field.equals("String")){
                continue;
            }
            if(!field.contains("Mapper")) {
                printAutowired(field, autowired);
                contextConfig.append(String.format("%s.class,",field));
            }
            if(mapOfClassNameToPath.get(field) == null) {
                continue;
            }
            if(mapOfClassNameToPath.get(field).startsWith(basePath)) {
                printMockBean(field);
            }
        }
        System.out.println(autowired.toString());
        System.out.println(contextConfig.toString().substring(0,contextConfig.length()-1));
    }

    private static void printAutowired(String field, StringBuilder builder) {
        builder.append(String.format("%s\n%s %s;\n", "@Autowired", field,generateFieldName(field)));
    }

    private static void printMockBean(String field) {
        System.out.println(String.format("%s\n%s %s;", "@MockBean",field, generateFieldName(field)));
    }

    private static String generateFieldName(String field) {
        return field.substring(0,1).toLowerCase() + field.substring(1);
    }

    private static void extractFields(String fileName, String className) {
        setOfTypesToAutowire.add(className);
        CompilationUnit cu = null;
        try {
            cu = StaticJavaParser.parse(Paths.get(fileName));
        } catch (Exception e) {
            System.out.println("Cannot compile " + className);
            return;
        }

        // Get the types in the source file
        NodeList<TypeDeclaration<?>> types = cu.getTypes();

        String fullDeclarations = "";
        for (TypeDeclaration<?> type : types) {
            // Get the fields within this type
            List<FieldDeclaration> fields = type.getFields();

            for (FieldDeclaration fieldDeclaration : fields) {
                NodeList<VariableDeclarator> variableDeclarators = fieldDeclaration.getVariables();
                variableDeclarators.forEach(x -> {
                    String name = extractClassName(x.getTypeAsString());
                    String path = mapOfClassNameToPath.get(name);
                    if(path == null) {
                        name = removeImplFromFileName(name);
                    }
                    if(!setOfTypesToAutowire.contains(name)) {
                        extractFields(mapOfClassNameToPath.get(name),name);
                    }
                });
            }
        }
    }

    private static String removeImplFromFileName(String name) {
        int end = name.indexOf("Impl");
        return name.substring(0,end);
    }

    private static String extractClassName(String name) {
        if(!name.endsWith("Impl") && !name.endsWith("impl")) {
            return name += "Impl";
        }
        return name;
    }

    private static void iterateAllFiles(File file) {
        if(file.getName().equals(".DS_Store")){
            System.out.println("reached here");
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File innerFile : files) {
                iterateAllFiles(innerFile);
            }
        } else {
            String fileName = removeFileExtension(file.getName());
            mapOfClassNameToPath.put(fileName, file.getAbsolutePath());
        }
    }

    private static String removeFileExtension(String name) {
        String pattern = ".java";
        int end = name.indexOf(pattern);
        if(end == -1) {
            throw new RuntimeException("Should not be -1");
        }
        return name.substring(0,end);
    }
}
