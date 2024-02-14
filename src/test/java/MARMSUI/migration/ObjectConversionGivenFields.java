package MARMSUI.migration;

import java.util.ArrayList;
import java.util.List;

public class ObjectConversionGivenFields {
    public static void main(String[] args) {
        // the target and source object must share the same field name
        String fields = "private String lchgDt;\n" +
                "    private String prgCd;\n" +
                "    private String relation;\n" +
                "    private String phAreaCode;\n" +
                "    private String phCountryCode;\n" +
                "    private String phoneNumber;\n" +
                "    private String familyName;\n" +
                "    private String givenName;\n" +
                "    private long seqNo;\n" +
                "    private long intId;";
        String[] fieldArr = fields.split("\n");
        List<String> fieldList = extractFieldNames(fieldArr);
        printSetterMethods(fieldList);
    }

    public static void printSetterMethods(List<String> fieldList) {
        for(String s : fieldList) {
            System.out.println(String.format("target.%s(source.%s());", MapStructNotationToGetSet.generateSetterName(s),MapStructNotationToGetSet.generateGetterName(s)));
        }
    }

    private static List<String> extractFieldNames(String[] fieldArr) {
        List<String> fieldList = new ArrayList<>();
        for(String field : fieldArr) {
            String[] arr = field.trim().split(" ");
            fieldList.add(arr[2].substring(0,arr[2].length()-1));
        }
        return fieldList;
    }
}
