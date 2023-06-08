public class ResultMapModel {
    public String type;
    public String columnName;
    public String fieldName;
    public String fieldType;

    public ResultMapModel(String type, String columnName) {
        this.type = type;
        this.columnName = columnName;
    }

    public ResultMapModel() {
    }
}
