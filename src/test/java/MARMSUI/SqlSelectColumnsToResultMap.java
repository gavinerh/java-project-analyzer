package MARMSUI;

import java.util.ArrayList;
import java.util.List;

public class SqlSelectColumnsToResultMap {
    public static void main(String[] args) {
        String sqlColumns = "TRANS_CD || BATCH_DT || ADMIN_FEE_TRANS.TOT_PTS || FEES_CD || PRT_CD || ADMIN_FEE_TRANS.FEES_TIER_IND || BATCH_DT || \n" +
                "        ADMIN_FEE_TRANS.FEES_TRANS_XREF_ID || REMARKS || BATCH_ID || SUPERVISOR_ID || PAYMNT_TYPE || TOT_AMT || WAIVED_PTS || \n" +
                "        WAIVED_AMT || EXP_PTS || REFUND_FLG || SUPPRESS_FLG as general_identifier,\n" +
                "        bucketTrans.TOT_PTS as TOT_PTS, bucketTrans.EXP_DT as EXP_DT, bucketTrans.NEW_EXP_DT as NEW_EXP_DT_1";
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
