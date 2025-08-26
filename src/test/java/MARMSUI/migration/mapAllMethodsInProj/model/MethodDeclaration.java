package MARMSUI.migration.mapAllMethodsInProj.model;

import javassist.bytecode.annotation.StringMemberValue;

import java.lang.reflect.Method;
import java.util.List;

public class MethodDeclaration {
    private String name;
    private List<String> parameters;
    private List<MethodDeclaration> innerMethods;
    private int numberOfParams;
    private boolean isUsed;

    public int getNumberOfParams() {
        return numberOfParams;
    }

    public void setNumberOfParams(int numberOfParams) {
        this.numberOfParams = numberOfParams;
    }

    public List<MethodDeclaration> getInnerMethods() {
        return innerMethods;
    }

    public void setInnerMethods(List<MethodDeclaration> innerMethods) {
        this.innerMethods = innerMethods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }
}
