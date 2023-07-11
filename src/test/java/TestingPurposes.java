import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TestingPurposes {
    public static void main(String[] args) {
        String val = " ";
        System.out.println(val.trim().isEmpty());

    }

    private static String[] extractFieldsFromStatement(String sentence) {
        int firstIndex = sentence.indexOf("\"") + 1;
        int finalIndex = sentence.indexOf("\"", firstIndex);
        String setField = sentence.substring(firstIndex,finalIndex);
        firstIndex = 0;
        finalIndex++;
        firstIndex = sentence.indexOf("\"", finalIndex) + 1;
        finalIndex = sentence.indexOf("\"", firstIndex);
        String getField = sentence.substring(firstIndex, finalIndex);
        return new String[]{setField,getField};
    }

    private static String getGetStatement(String field) {
        String temp = "get";
        field = field.substring(0,1).toUpperCase() + field.substring(1);
        temp += field;
        return temp;
    }

    private static String getSetStatement(String field) {
        String temp = "set";
        field = field.substring(0,1).toUpperCase() + field.substring(1);
        temp += field;
        return temp;
    }
}
