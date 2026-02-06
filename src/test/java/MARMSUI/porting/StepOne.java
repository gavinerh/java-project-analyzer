package MARMSUI.porting;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StepOne {

    static String regex = "'\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}'";
    static Pattern pattern = Pattern.compile(regex);
    static String to_date = "TO_DATE(%s,'YYYY-MM-DD HH24:MI:SS')";


    public static void execute(List<List<String>> columnList, List<List<String>> valueList, List<String> tableList, List<String> individualRows) {

        for (String row : individualRows) {
            extractData(row, columnList, valueList, tableList);
        }
        System.out.println(columnList.size());
    }

    private static void extractData(String row, List<List<String>> keyList, List<List<String>> valueList, List<String> tableList) {
        String upperRow = row.toUpperCase();
        if (upperRow.startsWith("INSERT INTO")) {
            int startIndex = upperRow.indexOf("INSERT INTO") + "INSERT INTO".length();
            int endIndex = upperRow.indexOf("(", startIndex);
            String tableName = upperRow.substring(startIndex, endIndex).trim();
            tableList.add(tableName);

            startIndex = endIndex;
            endIndex = row.indexOf(")", startIndex);

            int columnCount = extractColumnsAndValuesAndReturnCount(keyList, row, startIndex, endIndex);
            startIndex = row.indexOf("(", endIndex);
            endIndex = row.lastIndexOf(")");
            int valuesCount = extractColumnsAndValuesAndReturnCount(valueList,row, startIndex,endIndex);
            if(columnCount != valuesCount) {
                throw new IllegalArgumentException("Column count and value count do not match for table: " + tableName);
            }

        }
    }

    private static int extractColumnsAndValuesAndReturnCount(List<List<String>> keyList, String row, int startIndex, int endIndex) {
        try {
            String columnsSubstring = row.substring(startIndex + 1, endIndex).trim();
            List<String> columnsOrValues = new ArrayList<>();
            int insideParenthesis = 0;
            int singleQuotesCount = 0;
            StringBuilder value = new StringBuilder();
            for (char c : columnsSubstring.toCharArray()) {
                if (c == '(') {
                    insideParenthesis++;
                } else if (c == ')') {
                    insideParenthesis--;
                } else if (c == ',' && insideParenthesis == 0 && isEven(singleQuotesCount)) {
                    String col = checkForDateFormats(value);
                    columnsOrValues.add(col.trim());
                    value.setLength(0);
                    continue;
                }
                if(c == '\'') {
                    singleQuotesCount++;
                }
                value.append(c);
                if(insideParenthesis < 0) {
                    throw new IllegalArgumentException("Mismatched parentheses in columns substring");
                }
            }
            columnsOrValues.add(checkForDateFormats(value));
            keyList.add(columnsOrValues);
            return columnsOrValues.size();
        } catch (Exception e) {
            throw e;
        }
    }

    private static String checkForDateFormats(StringBuilder value) {
        String col = value.toString().trim();
//                col = removeStartAndEndQuotes(col);
        if(pattern.matcher(col).matches()) {
            col = String.format(to_date, col);
        }
        return col;
    }

    public static String removeStartAndEndQuotes(String str) {
        str = str.trim();
        return str.replaceAll("'", "");
    }

    private static boolean isEven(int count) {
        if(count == 0) {
            return true;
        }
        return count % 2 == 0;
    }
}
