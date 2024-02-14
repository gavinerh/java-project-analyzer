package MARMSUI.mergeConflict.model;

import java.util.List;

public class ObjectModel {
    public String conciseContent;
    public String actualContent;
    public String typeOfObj; // function, variable etc
    public List<String> paramList; // null if type is not function
    public String nameOfObject;

}
