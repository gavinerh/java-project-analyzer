package MARMSUI.migration.hierarchyGenerator.model;

import com.github.javaparser.ast.expr.MethodCallExpr;

public class MethodCallDetails {
        private final MethodCallExpr methodCallExpr;
        private final String fieldName;
        private final String fieldType;

        public MethodCallDetails(MethodCallExpr methodCallExpr, String fieldName, String fieldType) {
            this.methodCallExpr = methodCallExpr;
            this.fieldName = fieldName;
            this.fieldType = fieldType;
        }

        public MethodCallExpr getMethodCallExpr() {
            return methodCallExpr;
        }

        public String getFieldName() {
            return fieldName;
        }

        public String getFieldType() {
            return fieldType;
        }

        public String getMethodName() {
            return methodCallExpr.getNameAsString();
        }
    }