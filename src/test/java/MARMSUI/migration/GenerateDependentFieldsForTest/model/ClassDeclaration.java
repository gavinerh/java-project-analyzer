package MARMSUI.migration.GenerateDependentFieldsForTest.model;

import java.util.ArrayList;
import java.util.List;

public class ClassDeclaration {
    private List<String> constructorTypeList; // if implementation dont need fill this
    private String filePath;
    private String isInterface;
    private String implementationName; // if concrete class, dont need to fill this
    private List<String> interfaceNames; // if interface, dont need to fill this
    private final List<ClassDeclaration> dependentList = new ArrayList<>();
    private int position;

    public int getPosition() {
        position = extractHierarchyPosition(this);
        return position;
    }

    public int getCalculatedPosition() {
        return position;
    }

    private List<ClassDeclaration> extractHierarchy() {
        return this.dependentList;
    }

    private int extractHierarchyPosition(ClassDeclaration classDeclaration) {
        // iterate the child class declaration that is part of the parent class
        if(classDeclaration.extractHierarchy().isEmpty()) {
            return 0; // no hierarchy position
        } else {
            // there are at least 1 inner class declaration
            int childPosition = 0;
            for(ClassDeclaration child : classDeclaration.extractHierarchy()) {
                childPosition += extractHierarchyPosition(child);
            }
            return childPosition + 1; // increment the hierarchy position
        }
    }

    public void addHierarchyPosition(ClassDeclaration position) {
        this.dependentList.add(position);
    }

    public List<String> getInterfaceNames() {
        return interfaceNames;
    }

    public void setInterfaceNames(List<String> interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    public List<String> getConstructorTypeList() {
        return constructorTypeList;
    }

    public void setConstructorTypeList(List<String> constructorTypeList) {
        this.constructorTypeList = constructorTypeList;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getIsInterface() {
        return isInterface;
    }

    public void setIsInterface(String isInterface) {
        this.isInterface = isInterface;
    }

    public String getImplementationName() {
        return implementationName;
    }

    public void setImplementationName(String implementationName) {
        this.implementationName = implementationName;
    }
}
