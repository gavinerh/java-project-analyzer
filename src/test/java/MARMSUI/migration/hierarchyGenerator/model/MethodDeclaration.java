package MARMSUI.migration.hierarchyGenerator.model;

import java.util.List;

public class MethodDeclaration {
    public String modifier;
    public String returnType;
    public int parameterCount;
    public String methodName;
    public String variableName;
    public MethodCallDetails methodCallDetails;
    public String variableType;

    public MethodDeclaration(String returnType, int parameterCount) {
        this.returnType = returnType;
        this.parameterCount = parameterCount;
    }
}
