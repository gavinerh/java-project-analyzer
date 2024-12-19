package MARMSUI.migration.hierarchyGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class MethodChain {
    private String methodName;
    private String className;
    private int paramsCount;
    private List<MethodChain> parentMethodChains; // if the size is 0, then it is the root method chain
    private List<MethodChain> childMethodChains;

    public void removeLastChild() {
        this.childMethodChains.remove(this.childMethodChains.size() - 1);
    }


    public MethodChain(String methodName, String className) {
        this.methodName = methodName;
        this.className = className;
        this.childMethodChains = new ArrayList<>();
        this.parentMethodChains = new ArrayList<>();
    }

    public void addParentMethodChain(MethodChain parentMethodChain) {
        this.parentMethodChains.add(parentMethodChain);
    }

    public List<MethodChain> getParentMethodChains() {
        return parentMethodChains;
    }

    public void setParentMethodChains(List<MethodChain> parentMethodChains) {
        this.parentMethodChains = parentMethodChains;
    }

    public void addChildMethodChain(MethodChain childMethodChain) {
        this.childMethodChains.add(childMethodChain);
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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
