package MARMSUI.migration.GenerateDependentFieldsForTest;

import MARMSUI.migration.GenerateDependentFieldsForTest.model.ClassDeclaration;

import java.io.File;
import java.util.*;

public class IterateFilesAndMapAllFiles {
    public static void main(String[] args) throws Exception {
        String basePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui";
        Map<String, ClassDeclaration> classDeclarationMap = new HashMap<>();
        Map<String,ClassDeclaration> interfaceDeclarationMap = new HashMap<>(); // to fill concrete class declaration when the interface is not added yet
        File baseDir = new File(basePath);
        iterateFilesAndPopulateDeclarationMap(baseDir,classDeclarationMap, interfaceDeclarationMap);
        linkInterfaceToConcreteClass(classDeclarationMap, interfaceDeclarationMap);
        System.out.println(classDeclarationMap.size());
    }

    public static void linkInterfaceToConcreteClass(Map<String, ClassDeclaration> classDeclarationMap, Map<String, ClassDeclaration> interfaceDeclarationMap) {
        for(ClassDeclaration classDeclaration : classDeclarationMap.values()) {
            if(classDeclaration.getInterfaceNames() != null && !classDeclaration.getInterfaceNames().isEmpty()) {
                for(String interfaceName : classDeclaration.getInterfaceNames()) {
                    ClassDeclaration interfaceDeclaration = interfaceDeclarationMap.get(interfaceName);
                    if(interfaceDeclaration != null) {
                        // Link the interface to the concrete class
                        interfaceDeclaration.setImplementationName(CheckIfThereAreDuplicateFileNames.getFileNameFromPath(classDeclaration.getFilePath()));
                    }
                }
            }
        }
    }

    public static void iterateFilesAndPopulateDeclarationMap(File dir, Map<String, ClassDeclaration> classDeclarationMap, Map<String,ClassDeclaration> interfaceDeclaration) throws Exception {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        iterateFilesAndPopulateDeclarationMap(file, classDeclarationMap, interfaceDeclaration);
                    } else {
                        // Logic to process the file and extract class declarations
                        // This could involve parsing the file and creating ClassDeclaration objects
                        if(!file.getAbsolutePath().contains(".java")) {
                            continue;
                        }
                        if(CheckIfInterface.isInterface(file.getAbsolutePath())) {
                            ClassDeclaration inter = new ClassDeclaration();
                            inter.setIsInterface("Y");
                            inter.setFilePath(file.getAbsolutePath());
                            interfaceDeclaration.put(CheckIfThereAreDuplicateFileNames.getFileNameFromPath(file.getAbsolutePath()), inter);
                        } else {
                            // concrete class
                            ClassDeclaration classDeclaration = new ClassDeclaration();
                            classDeclaration.setIsInterface("N");
                            classDeclaration.setFilePath(file.getAbsolutePath());
                            classDeclaration.setInterfaceNames(PrintInterfaceTheClassImplements.getImplementedInterfaces(file.getAbsolutePath()));
                            classDeclaration.setConstructorTypeList(populateTypeListFromConstructor(file.getAbsolutePath()));
                            classDeclarationMap.put(CheckIfThereAreDuplicateFileNames.getFileNameFromPath(file.getAbsolutePath()), classDeclaration);
                        }
                    }
                }
            }
        }
    }

    private static List<String> populateTypeListFromConstructor(String filePath) throws Exception {
        // Logic to extract constructor types from the file
        // This could involve parsing the file and extracting constructor parameter types
        // For simplicity, returning an empty list here
//        String constructor = PrintConstructor.getConstructorLogicFromClass(filePath);
        List<List<String>> typeList = PrintConstructor.getConstructorParameterTypes(filePath);
        if(typeList.isEmpty()) {
            return new ArrayList<>();
        } else {
            // Assuming we want the first constructor's parameter types
            return typeList.get(0); // or handle multiple constructors as needed
        }
    }
}
