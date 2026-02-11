import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestingPurposes {


    public static void main(String[] args) throws Exception {
        String val = "NMLNKGUD\n" +
                "NMNTDLN\n" +
                "NMHMALNK\n" +
                "NMTCLNK\n" +
                "NMXODLN\n" +
                "NMTCDLN\n" +
                "NMNTLNK\n" +
                "NMXOLNK\n" +
                "NMLNKMIR\n" +
                "NMHMADLN";
        for(String row : val.split("\n")) {
            String value = row.replaceAll("\"", "'").replaceAll(",","").trim();
            String template = "DELETE EMAIL_CNT_DTLS WHERE EMAIL_CONTENT_ID = '%s';";
            System.out.println(String.format(template, value, value));
        }

        for(String row : val.split("\n")) {
            String value = row.replaceAll("\"", "'").replaceAll(",","").trim();
            String template = "DELETE EMAIL_CNT_MSTR WHERE EMAIL_CONTENT_ID = '%s'";
            System.out.println(String.format(template, value, value));
        }

    }

    private static void extractField(String row, Map<String, String> fieldValueMap, String value) {
        int keyStartIndex = row.indexOf("kfenrolment.") + "kfenrolment.".length();
        int keyEndIndex = row.indexOf(".type=");
        String key = row.substring(keyStartIndex, keyEndIndex);
        fieldValueMap.put(key, value);
    }


    private static List<String> insertAfterBeforeClosingQuotesReturnList(List<String> stringList, String valueToInsert) {
        List<String> resultList = new java.util.ArrayList<>();
        for (String str : stringList) {
            String result = str.replaceAll("\"", "").replaceAll(",", "").trim();
            String finalResult = String.format("%s%s", result, valueToInsert);
            resultList.add(finalResult);
        }
        return resultList;
    }

    private static void insertAfterBeforeClosingQuotes(List<String> stringList, String valueToInsert) {
        for (String str : stringList) {
            String result = str.replaceAll("\"", "").replaceAll(",", "").trim();
            System.out.print(String.format("'%s_',", result));
        }
    }

    public static void insertValueAfterEachString(List<String> stringList, String valueToInsert) {
        for (String str : stringList) {
            String result = str.trim() + valueToInsert;
            System.out.print(result);
        }
    }

    public static void replaceString(String[] arr, List<String> toRemove, List<String> replacement) {
        for (String input : arr) {
            for (int i = 0; i < toRemove.size(); i++) {
                input = input.replace(toRemove.get(i), replacement.get(i));
            }
            System.out.print(input.trim());
        }
    }

    public static long convertToMilliseconds(String timeString) {
        try {
            // Replace masked data with actual values
            // Create SimpleDateFormat with ISO 8601 pattern
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            // Parse the string to Date
            Date date = sdf.parse(timeString);

            // Convert to milliseconds
            return date.getTime();
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: " + timeString);
        }
    }

}

