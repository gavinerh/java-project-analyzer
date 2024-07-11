package MARMSUI.migration.model;

public class Type {
    public String name;
    public String type;
    public String sqlType;
    public String classPath;

    public Type() {}

    public Type(String name, String type, String sqlType) {
        this.name = name;
        this.type = type;
        this.sqlType = sqlType;
    }
}
