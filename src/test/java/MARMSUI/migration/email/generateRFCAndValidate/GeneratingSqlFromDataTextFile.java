package MARMSUI.migration.email.generateRFCAndValidate;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class GeneratingSqlFromDataTextFile {
    private static String dtlsFilePath = "/Users/macuser/Desktop/holdingForTempFiles/stored-email-requests/dtls_rfc.txt";
    private static String mstrFilePath = "/Users/macuser/Desktop/holdingForTempFiles/stored-email-requests/mstr_rfc.txt";

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Start printing DTLS==========================");
        String[] dtlsColumnNames = new String[]{"RCRE_USER_ID", "RCRE_DT", "EMAIL_CONTENT_ID", "CUS_SEQ_NO", "PERSONALIZE_NAME", "TABLE_NAME", "COLUMN_NAME", "KEYCOLUMN_NAME"};
        List<String> invalidLinesDtls = readDataFileAndCallPrint(dtlsFilePath, new int[]{4, 5, 6, 7, 8, 9}, dtlsColumnNames, "EMAIL_CNT_DTLS", 10, 4); // indexForColumn represent the index of int array passed to the method
        System.out.println("\n\nStart printing MSTR==========================");
        String[] mstrColumnNames = new String[]{"RCRE_USER_ID", "RCRE_DT", "EMAIL_CONTENT_ID", "EMAIL_FROM_ADDR", "EMAIL_REPLYTO_ADDR", "EMAIL_SUBJECT", "EMAIL_HTMLFILE_NAME", "EMAIL_TEXTFILE_NAME", "EMAIL_BODY_FORMAT", "EMAIL_DESC", "USE_GM_DATA_FLG", "CMT_MIGRATED", "RECIPIENT_TYPE", "BLOCK_EMAIL"};
        List<String> invalidLinesMstr = readDataFileAndCallPrint(mstrFilePath, new int[]{4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, mstrColumnNames, "EMAIL_CNT_MSTR", 16, 4);
        System.out.println("end of main");
    }


    public static List<String> readDataFileAndCallPrint(String filePath, int[] indexToUse, String[] columnNames, String tableName, int length, int indexForContentId) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Scanner scanner = new Scanner(fileInputStream);
        List<String> invalidLines = new ArrayList<>();
        List<String> finalValuesList = new ArrayList<>();
        Set<String> uniqueValues = new HashSet<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                String[] parts = line.split("\t");
                if (parts.length < length) {
                    int numberOfIndexToInitialize = length - parts.length;
                    parts = Arrays.copyOf(parts, length);
                    initializeInvalidIndexes(parts, numberOfIndexToInitialize);
                }
                List<String> valuesList = new ArrayList<>();
                valuesList.add("MTPGUI");
                valuesList.add("SYSDATE");
                for (int index : indexToUse) {
                    valuesList.add(parts[index]);
                }
                populateContentListForRollback(uniqueValues, finalValuesList, parts[indexForContentId]);
                printInsertQueries(columnNames, valuesList.toArray(new String[0]), tableName);
            } catch (Exception e) {
                invalidLines.add(line);
            }
        }
        printDeleteQueries(finalValuesList, tableName);
        scanner.close();
        return invalidLines;
    }

    private static void initializeInvalidIndexes(String[] parts, int numberOfIndexToInitialize) {
        int startIndex = parts.length - numberOfIndexToInitialize;
        for (int i = startIndex; i < parts.length; i++) {
            parts[i] = "";
        }
    }

    private static void printDeleteQueries(List<String> contentIds, String tableName) {
        String template = "DELETE FROM %s WHERE EMAIL_CONTENT_ID = %s;";
        for (String contentId : contentIds) {
            String formattedContentId = formatValues(contentId);
            String finalQuery = String.format(template, tableName, formattedContentId);
            System.out.println(finalQuery);
        }
    }

    private static void printInsertQueries(String[] columnNames, String[] values, String tableName) {
        String template = "INSERT INTO %s (%s) VALUES (%s);";
        StringBuilder columnsBuilder = new StringBuilder();
        StringBuilder valuesBuilder = new StringBuilder();
        for (int i = 0; i < columnNames.length; i++) {
            columnsBuilder.append(columnNames[i]);
            if (i != columnNames.length - 1) {
                columnsBuilder.append(", ");
            }
        }
        for (int i = 0; i < values.length; i++) {
            valuesBuilder.append(formatValues(values[i]));
            if (i != columnNames.length - 1) {
                valuesBuilder.append(", ");
            }
        }
        String finalQuery = String.format(template, tableName, columnsBuilder.toString(), valuesBuilder.toString());

        for (String value : values) {
            finalQuery.replace("%s", value);
        }
        System.out.println(finalQuery);
    }


    private static String formatValues(String val) {
        if (StringUtils.isBlank(val) || val.equalsIgnoreCase("null")) {
            return "null";
        }

        if(val.equals("SYSDATE")) {
            return val;
        }
        if (val.contains("'")) {
            String templateForInsertingSingleQuotes = "q'[%s]'";
            return String.format(templateForInsertingSingleQuotes, val);
        }
        return "'" + val + "'";
    }

    private static void populateContentListForRollback(Set<String> uniqueValues, List<String> finalValuesList, String contentId) {
        if (uniqueValues == null) {
            uniqueValues = new HashSet<>();
        }
        if (finalValuesList == null) {
            finalValuesList = new ArrayList<>();
        }
        if (!uniqueValues.contains(contentId)) {
            uniqueValues.add(contentId);
            finalValuesList.add(contentId);
        }
    }
}
