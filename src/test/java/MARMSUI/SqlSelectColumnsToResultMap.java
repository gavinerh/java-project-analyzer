package MARMSUI;

import java.util.ArrayList;
import java.util.List;

public class SqlSelectColumnsToResultMap {
    public static void main(String[] args) {
        String sqlColumns = "RSRV_VAL, EXTENDED_RSRV_VAL, EXP_DT, QLFY_START_DT, ORIG_QLFY_END_DT, ACTUAL_QLFY_END_DT,\n" +
                "        ACTUAL_PPS_VAL, VALID_FLG";
        List<String> columnNameArr = generateColumnArr(sqlColumns);
        List<String> cleansedColName = new ArrayList<>();
        // identify the columnName
        for(String column : columnNameArr) {
           cleansedColName.add(getColumnName(column));
        }
        generateResultMap(cleansedColName);
    }

    private static void generateResultMap(List<String> cleansedColNames) {
        for(String col : cleansedColNames) {
            String template = String.format("<result column=\"%s\" jdbcType=\"\" />", col);
            System.out.println(template);
        }
    }

    private static String generateProperty(String columnName) {
        return null;
    }

    private static List<String> generateColumnArr(String sql) {
        List<String> listOfCol = new ArrayList<>();
        boolean insideParams = false;
        String column = "";
        for(int i=0; i<sql.length(); i++) {
            String letter = sql.substring(i,i+1);
            if(letter.equals("(")) {
                insideParams = true;
                column += letter;
                continue;
            }
            if(letter.equals(")")){
                insideParams = false;
                column += letter;
                continue;
            }
            column += letter;
            if(letter.equals(",") && !insideParams || i == sql.length()-1) {
                String trimmedCol = column.trim();
                if(trimmedCol.substring(trimmedCol.length()-1,trimmedCol.length()).equals(",")) {
                    trimmedCol = trimmedCol.substring(0,trimmedCol.length()-1);
                }
                listOfCol.add(trimmedCol);
                column = "";
            }

        }
        return listOfCol;
    }

    private static String getColumnName(String column) {
        String[] columnSplit = column.split(" ");
        boolean containAs = false;
        for(String s : columnSplit) {
            if(s.trim().equalsIgnoreCase("as")) {
                containAs = true;
            }
        }
        if(containAs) {
            return columnSplit[columnSplit.length-1];
        }
        return column;
    }
}
