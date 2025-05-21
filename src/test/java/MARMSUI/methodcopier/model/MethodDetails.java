package MARMSUI.methodcopier.model;

import java.util.ArrayList;
import java.util.List;

public class MethodDetails {
    private String name;
    private List<String> listOfParameterTypes;
    private int numOfParameters = 0;
    private List<MethodDetails> childMethods;
    private boolean isDetailConfigured = false;

    public MethodDetails(String name) {
        this.name = name;
    }

    public void setChildMethods(List<MethodDetails> childMethods) {
        this.childMethods = childMethods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListOfParameterTypes() {
        return listOfParameterTypes;
    }

    public void setListOfParameterTypes(List<String> listOfParameterTypes) {
        this.listOfParameterTypes = listOfParameterTypes;
    }

    public int getNumOfParameters() {
        return numOfParameters;
    }

    public void setNumOfParameters(int numOfParameters) {
        this.numOfParameters = numOfParameters;
    }

    public void addParameter(String parameter) {
        if(listOfParameterTypes == null) {
            listOfParameterTypes = new ArrayList<>();
        }
        listOfParameterTypes.add(parameter);
    }

    public void addChildMethod(MethodDetails methodDetails) {
        if(childMethods == null) {
            childMethods = new ArrayList<>();
        }
        childMethods.add(methodDetails);
    }

    public List<MethodDetails> getChildMethods() {
        return childMethods;
    }

    public boolean isDetailConfigured() {
        return isDetailConfigured;
    }

    public void setDetailConfigured(boolean detailConfigured) {
        isDetailConfigured = detailConfigured;
    }
}
