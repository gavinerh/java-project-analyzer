package MARMSUI.migration.mapAllMethodsInProj.model;

import javassist.bytecode.annotation.StringMemberValue;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public class MethodDeclaration {
    private String name;
    private List<String> parameters;
    private List<MethodDeclaration> innerMethods;
    private int numberOfParams;
    private boolean isUsed;
    private Set<String> callingMethods;
    private boolean isViewed;

    public boolean isViewed() {
        return isViewed;
    }

    public void setViewed(boolean viewed) {
        isViewed = viewed;
    }

    public Set<String> getCallingMethods() {
        return callingMethods;
    }

    public void setCallingMethods(Set<String> callingMethods) {
        this.callingMethods = callingMethods;
    }

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
