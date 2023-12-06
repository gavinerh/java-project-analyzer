package MARMSUI;

public class ReplaceVariableInExampleClass {
    public static void main(String[] args) {
        String declaration = "private static final String ";
        String patternToReplace = "\"phoneNumber\"";
        String replacementVariable = "PHONE";
        String toReplace = "public Criteria andPhoneNumberEqualTo(String value) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER =\", value, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpublic Criteria andPhoneNumberNotEqualTo(String value) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER <>\", value, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpublic Criteria andPhoneNumberGreaterThan(String value) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER >\", value, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpublic Criteria andPhoneNumberGreaterThanOrEqualTo(String value) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER >=\", value, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpublic Criteria andPhoneNumberLessThan(String value) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER <\", value, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpublic Criteria andPhoneNumberLessThanOrEqualTo(String value) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER <=\", value, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpublic Criteria andPhoneNumberLike(String value) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER like\", value, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpublic Criteria andPhoneNumberNotLike(String value) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER not like\", value, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpublic Criteria andPhoneNumberIn(List<String> values) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER in\", values, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpublic Criteria andPhoneNumberNotIn(List<String> values) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER not in\", values, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpublic Criteria andPhoneNumberBetween(String value1, String value2) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER between\", value1, value2, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}\n" +
                "\n" +
                "\t\tpublic Criteria andPhoneNumberNotBetween(String value1, String value2) {\n" +
                "\t\t\taddCriterion(\"PHONE_NUMBER not between\", value1, value2, \"phoneNumber\");\n" +
                "\t\t\treturn (Criteria) this;\n" +
                "\t\t}";
        String[] strArr = toReplace.split("\n");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<strArr.length; i++) {
            String row = strArr[i];
            boolean availability = row.contains(patternToReplace);
            if(availability) {
                row = row.replace(patternToReplace,replacementVariable);
            }
            stringBuilder.append(row);
            stringBuilder.append("\n");
        }
        System.out.println(String.format("%s%s = %s;", declaration,replacementVariable,patternToReplace));
        System.out.println();
        System.out.println(stringBuilder.toString());
    }
}
