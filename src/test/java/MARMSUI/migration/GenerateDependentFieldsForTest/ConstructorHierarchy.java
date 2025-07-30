package MARMSUI.migration.GenerateDependentFieldsForTest;

import MARMSUI.migration.GenerateDependentFieldsForTest.model.ClassDeclaration;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ConstructorHierarchy {
    public static void populateHierarchy(ClassDeclaration starter, Set<String> mockedFields, Map<String,ClassDeclaration> classDeclarationMap, Map<String,ClassDeclaration> interfaceDeclarationMap, List<ClassDeclaration> constructorHierarchy, Set<String> visited) {
        String currentClassName = CheckIfThereAreDuplicateFileNames.getFileNameFromPath(starter.getFilePath());
//        if(visited.contains(currentClassName)) {
//            return; // already visited this class, avoid infinite recursion
//        }
        constructorHierarchy.add(starter);
        for(String constructorField : starter.getConstructorTypeList()) {
            ClassDeclaration classDeclaration = getClassDeclaration(mockedFields, classDeclarationMap, interfaceDeclarationMap, visited, constructorField);
            if(classDeclaration == null) {
                continue;
            }
            populateHierarchy(classDeclaration,mockedFields,classDeclarationMap,interfaceDeclarationMap,constructorHierarchy,visited);
        }
    }

    private static ClassDeclaration getClassDeclaration(Set<String> mockedFields, Map<String, ClassDeclaration> classDeclarationMap, Map<String, ClassDeclaration> interfaceDeclarationMap, Set<String> visited, String constructorField) {
        try {
            if(visited.contains(constructorField)) {
                return null;
            }
            ClassDeclaration classDeclaration = classDeclarationMap.get(constructorField);
            if(classDeclaration == null) {
                // if its an interface, then find the concrete class that implements it
                ClassDeclaration inter = interfaceDeclarationMap.get(constructorField);
                if (inter == null) {
                    // probably is like OKHttpClient, no implementation class or interface files
                    visited.add(constructorField);
                    mockedFields.add(constructorField);
                    return null;
                }
                classDeclaration = classDeclarationMap.get(inter.getImplementationName());
                if(classDeclaration == null) {
                    visited.add(constructorField); // probably some mapper class
                    mockedFields.add(constructorField);
                    return null;
                }
                visited.add(inter.getImplementationName());
                mockedFields.add(inter.getImplementationName());
            } else {
                visited.add(constructorField);
                mockedFields.add(constructorField);
            }
            return classDeclaration;
        } catch (Exception e) {
            System.out.println("Error while getting class declaration for: " + constructorField);
            throw new RuntimeException("Error while getting class declaration for: " + constructorField, e);
        }
    }
}
