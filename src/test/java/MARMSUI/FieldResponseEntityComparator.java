package MARMSUI;

import java.util.HashSet;
import java.util.Set;

public class FieldResponseEntityComparator {
    public static void main(String[] args) {
        String fieldsStr = "programCode\n" +
                "internalID\n" +
                "sequenceNumber\n" +
                "familyName\n" +
                "givenName\n" +
                "passportNumber\n" +
                "nomineesInternalID\n" +
                "nomineesProgramCode\n" +
                "tktAuthorised\n" +
                "feesTransactionXrefID\n" +
                "supervisorID\n" +
                "supervisorRemarks\n" +
                "lastChangeDate\n" +
                "lastChangeUserID\n" +
                "nomineesCustomerID\n" +
                "createDate\n" +
                "title\n" +
                "birthDate\n" +
                "passportExpiryDate\n" +
                "passportCtryOfIssuance\n" +
                "relationship\n" +
                "isEUresident\n" +
                "isWithinXMonths";
        String actualClassField = "private String programCode;\n" +
                "    private long internalID;\n" +
                "    private int sequenceNumber;\n" +
                "    private String familyName;\n" +
                "    private String givenName;\n" +
                "    private String passportNumber;\n" +
                "    private long nomineesInternalID;\n" +
                "    private String nomineesProgramCode;\n" +
                "    private boolean tktAuthorised;\n" +
                "    private String feesTransactionXrefID;\n" +
                "    private String supervisorID;\n" +
                "    private String supervisorRemarks;\n" +
                "    private String lastChangeDate;\n" +
                "    private String lastChangeUserID;\n" +
                "    private String nomineesCustomerID;\n" +
                "    private String createDate;\n" +
                "    private boolean isWithinXMonths;\n" +
                "    private String title;\n" +
                "    private String birthDate;\n" +
                "    private String passportExpiryDate;\n" +
                "    private String passportCtryOfIssuance;\n" +
                "    private String relationship;\n" +
                "    private String isEUresident;\n" +
                "    private String tktFlag;\n" +
                "    private Timestamp birthDateinDateFormat;\n" +
                "    private Timestamp passportExpiryDateinDateFormat;";
        Set<String> actualFields = new HashSet<>();
        // populate the actual class fields in a set
        populateActualFieldsSet(actualFields, actualClassField);
        compareResponseFieldToActualField(actualFields, fieldsStr);
    }

    private static void populateActualFieldsSet(Set<String> actualFields, String fieldsString) {
        String[] fieldsArr = fieldsString.split("\n");
        for(String fieldSentence : fieldsArr) {
            String[] fieldSentenceSplit = fieldSentence.trim().split(" ");
            String fieldName = fieldSentenceSplit[2].substring(0,fieldSentenceSplit[2].length()-1);
            actualFields.add(fieldName);
        }
    }

    private static void compareResponseFieldToActualField(Set<String> actualFields, String responseFields) {
        String[] responseFieldSplit = responseFields.split("\n");
        for(String fieldOfResponse : responseFieldSplit) {
            if(!actualFields.contains(fieldOfResponse)) {
                throw new RuntimeException(fieldOfResponse + " not found");
            }
        }
    }
}
