package MARMSUI.migration.GenerateDependentFieldsForTest;

import MARMSUI.migration.GenerateDependentFieldsForTest.model.ClassDeclaration;

import java.io.File;
import java.util.*;

public class Generator {
    public static void main(String[] args) throws Exception {
        // check if there are duplicate file names in the specified directory
        // Todo: to change according to the starting class
        String starterClass = "TierHandlerUtilTemp";
        // Todo: change when the project is changed
        String basePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui";
        File baseDir = new File(basePath);
        CheckIfThereAreDuplicateFileNames.checkForDuplicateFiles(baseDir, new java.util.HashSet<>());


        // iterate all files to save a copy of all the files, interface to implementations
        Map<String, ClassDeclaration> classDeclarationMap = new java.util.HashMap<>();
        Map<String,ClassDeclaration> interfaceDeclarationMap = new java.util.HashMap<>(); // to fill concrete class declaration when the interface is not added yet
        IterateFilesAndMapAllFiles.iterateFilesAndPopulateDeclarationMap(baseDir, classDeclarationMap, interfaceDeclarationMap);
        IterateFilesAndMapAllFiles.linkInterfaceToConcreteClass(classDeclarationMap,interfaceDeclarationMap);


        // create the hierarchy of the dependent fields to each class
        ClassDeclaration starter = classDeclarationMap.get(starterClass);
        Set<String> mockedFields = new java.util.HashSet<>();
        Set<String> dependentFields = new java.util.HashSet<>();
        List<ClassDeclaration> constructorHierarchy = new ArrayList<>();
        Set<String> visited = new java.util.HashSet<>();

        // iterate the starter constructor, find out all the types required
        ConstructorHierarchy.populateHierarchy(starter,mockedFields,classDeclarationMap,interfaceDeclarationMap,constructorHierarchy,visited);


        // for the concrete type identified, create a mock field in the test class, with @Mock annotation
        System.out.println("Printing out the @Mock annotations: \n");
        String template = "@Mock\nprivate %s %s;";
        for(String field : mockedFields) {
            System.out.println(String.format(template, field, convertClassNameToVariableName(field)));
        }


        // also write the code to generate the constructor order
        ArrangeConstructorOrder.execute(constructorHierarchy, classDeclarationMap, interfaceDeclarationMap);
        ArrangeConstructorOrder.order(constructorHierarchy);


        // maintain a set of the mocked fields, so that we do not duplicate the same field in the test class
        System.out.println("\nPrinting out the dependent fields: \n");
        String templateForInitializingConstructor = "%s = new %s(%s);";
        printOutConstructor(templateForInitializingConstructor, constructorHierarchy);

        // at the same time, find out the hierarchy of the constructor creation, from the last to the starter constructor

        // initialise via the constructor, all the dependent fields in the set up method

        // ...
    }

    private static String convertClassNameToVariableName(String field){
        return field.substring(0,1).toLowerCase() + field.substring(1);
    }

    private static void printOutConstructor(String templateForInitializingConstructor, List<ClassDeclaration> constructorHierarchy) {
        for(ClassDeclaration classDeclaration : constructorHierarchy) {
            String strValueForConstructor = getConstructorStrValue(classDeclaration.getConstructorTypeList());
            String currentClassName = CheckIfThereAreDuplicateFileNames.getFileNameFromPath(classDeclaration.getFilePath());
            System.out.println(String.format(templateForInitializingConstructor,convertClassNameToVariableName(currentClassName),currentClassName,strValueForConstructor));
        }
    }

    private static String getConstructorStrValue(List<String> constructorTypeList) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<constructorTypeList.size(); i++) {
            String type = constructorTypeList.get(i);
            if(i == 0) {
                sb.append(convertClassNameToVariableName(type));
            } else {
                sb.append(", ").append(convertClassNameToVariableName(type));
            }
        }
        return sb.toString();
    }
}
