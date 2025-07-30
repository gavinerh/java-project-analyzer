package MARMSUI.methodcopier.model;

import java.util.List;

public class MethodSignature {
        private String methodName;
        private List<String> parameterTypes;

        public MethodSignature(String methodName, List<String> parameterTypes) {
            this.methodName = methodName;
            this.parameterTypes = parameterTypes;
        }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(List<String> parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    @Override
        public String toString() {
            return "MethodSignature{" +
                    "methodName='" + methodName + '\'' +
                    ", parameterTypes=" + parameterTypes +
                    '}';
        }
    }