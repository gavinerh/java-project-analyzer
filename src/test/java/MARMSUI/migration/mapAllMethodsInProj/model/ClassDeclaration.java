package MARMSUI.migration.mapAllMethodsInProj.model;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassDeclaration {
    private String name;
    private boolean isInterface;
    private List<String> implementedInterfaces;
    private List<MethodDeclaration> methodList;
    private List<String> importList; // to help the fields find the respective class files
    private List<String> fieldList;
    private String concreteClassPath;
    private Map<String,String> fieldMap;
    private Map<String, MethodDeclaration> methodMap;

    public Map<String, MethodDeclaration> getMethodMap() {
        return methodMap;
    }

    public void setMethodMap(Map<String, MethodDeclaration> methodMap) {
        this.methodMap = methodMap;
    }

    public Map<String, String> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, String> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public String getConcreteClassPath() {
        return concreteClassPath;
    }

    public void setConcreteClassPath(String concreteClassPath) {
        this.concreteClassPath = concreteClassPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public List<String> getImplementedInterfaces() {
        return implementedInterfaces;
    }

    public void setImplementedInterfaces(List<String> implementedInterfaces) {
        this.implementedInterfaces = implementedInterfaces;
    }

    public List<MethodDeclaration> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<MethodDeclaration> methodList) {
        this.methodList = methodList;
    }

    public List<String> getImportList() {
        return importList;
    }

    public void setImportList(List<String> importList) {
        this.importList = importList;
    }

    public List<String> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<String> fieldList) {
        this.fieldList = fieldList;
    }
}
