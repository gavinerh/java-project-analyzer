package MARMSUI.migration.hierarchyGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class MethodChain {
    private String parentMethodName;
    private String parentClassName;
    private int paramsCount;
    private List<MethodChain> childMethodChains;

    public MethodChain(String parentMethodName, String parentClassName, int paramsCount) {
        this.parentMethodName = parentMethodName;
        this.parentClassName = parentClassName;
        this.paramsCount = paramsCount;
        this.childMethodChains = new ArrayList<>();
    }

    public void addChildMethodChain(MethodChain childMethodChain) {
        this.childMethodChains.add(childMethodChain);
    }

    public String getParentMethodName() {
        return parentMethodName;
    }

    public void setParentMethodName(String parentMethodName) {
        this.parentMethodName = parentMethodName;
    }

    public String getParentClassName() {
        return parentClassName;
    }

    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    public int getParamsCount() {
        return paramsCount;
    }

    public void setParamsCount(int paramsCount) {
        this.paramsCount = paramsCount;
    }

    public List<MethodChain> getChildMethodChains() {
        return childMethodChains;
    }

    public void setChildMethodChains(List<MethodChain> childMethodChains) {
        this.childMethodChains = childMethodChains;
    }
}
