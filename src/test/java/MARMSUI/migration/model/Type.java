package MARMSUI.migration.model;

import java.util.Set;

public class Type {
    public String name;
    public String type;
    public String sqlType;
    public String classPath;
    public String xmlVariable;
    public String javaVariable;
    public String javaFilePath;
    public String xmlFilePath;
    public String filePath;
    public Set<String> fieldNames;


    public Type() {}

    public Type(String name, String type, String sqlType) {
        this.name = name;
        this.type = type;
        this.sqlType = sqlType;
    }
}
