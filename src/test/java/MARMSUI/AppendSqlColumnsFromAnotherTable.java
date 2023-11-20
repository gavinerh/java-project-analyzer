package MARMSUI;

import java.util.ArrayList;
import java.util.List;

public class AppendSqlColumnsFromAnotherTable {
    public static void main(String[] args) {
        String columns = "TRANS_CD as TRANS_CD,\n" +
                "        BATCH_DT as BATCH_DATE, ADMIN_FEE_TRANS.TOT_PTS as PTS_AWDED, '' as TRANS_XREF_ID, FEES_CD as FEES_CD, PRT_CD as PRT_CD,\n" +
                "        ADMIN_FEE_TRANS.FEES_TIER_IND as FEES_TIER_IND, '' as AWD_DESC, '' as column9, BATCH_DT as TRANS_DATE, '' as column11,\n" +
                "        0 as column12, ADMIN_FEE_TRANS.FEES_TRANS_XREF_ID as FEES_TRANS_XREF_ID, null as FRM_BUCKET_DT, null as NEW_EXP_DT, REMARKS as\n" +
                "        REMARKS, 0 as ELITE_PTS, 0 as PPS_PTS, 0 as SECT_CNT, 0 as PPS_VAL, BATCH_ID as BATCH_ID, NVL(SUPERVISOR_ID,'')\n" +
                "        as SUPERVISOR_ID,\n" +
                "        PAYMNT_TYPE as PAYMNT_TYPE, TOT_AMT as TOT_AMT, NVL(WAIVED_PTS,0) as WAIVED_PTS, NVL(WAIVED_AMT,0) as\n" +
                "        WAIVED_AMT, EXP_PTS as EXP_PTS, REFUND_FLG as REFUND_FLG, SUPPRESS_FLG as SUPPRESS_FLG, 0 as EXTENDED_PTS,\n" +
                "        0 as BAL_EXTENDED, 'MISC' as TRANSACTION_TYPE, \n" +
                "        TRANS_CD || BATCH_DT || ADMIN_FEE_TRANS.TOT_PTS || FEES_CD || PRT_CD || ADMIN_FEE_TRANS.FEES_TIER_IND || BATCH_DT || \n" +
                "        ADMIN_FEE_TRANS.FEES_TRANS_XREF_ID || REMARKS || BATCH_ID || SUPERVISOR_ID || PAYMNT_TYPE || TOT_AMT || WAIVED_PTS || \n" +
                "        WAIVED_AMT || EXP_PTS || REFUND_FLG || SUPPRESS_FLG as general_identifier,\n" +
                "        bucketTrans.TOT_PTS as TOT_PTS, bucketTrans.EXP_DT as EXP_DT, bucketTrans.NEW_EXP_DT as NEW_EXP_DT_1";
        List<String> columnList = generateColArr(columns,0);
        String joinedTableName = "mainQuery";
        printColumns(joinedTableName,columnList);
    }

    private static void printColumns(String tableName, List<String> colList){
        String toPrint = "";
        for(String col : colList) {
            String toAppend = String.format("%s.%s as %s, ", tableName, col, col);
            toPrint += toAppend;
        }
        System.out.println(toPrint);
    }


    private static List<String> generateColArr(String col, int size) {
        List<String> list = new ArrayList<>();
        if(col.isEmpty() && size != 0) {
            for(int i=1; i<size+1; i++) {
                list.add(String.format("column%d", i));
            }
            return list;
        }
        if(col.isEmpty() && size == 0){
            throw new RuntimeException("Col is empty and size is not given");
        }
        int isInsideParenthesis = 0;
        String stringToExtract = "";
        boolean toBreak = false;
        for(int i=0; i<col.length(); i++) {
            char c = col.charAt(i);
            if(c == '(') {
                isInsideParenthesis++;
            }
            if(c == ')'){
                isInsideParenthesis--;
            }
            if(c == ',' && isInsideParenthesis == 0) {
                toBreak = true;
            }
            if(!toBreak) {
                stringToExtract += c;
            }
            if(toBreak) {
                list.add(extractColumnName(stringToExtract.trim()));
                toBreak = false;
                stringToExtract = "";
            }
        }
        list.add(extractColumnName(stringToExtract.trim()));
        return list;
    }

    private static String extractColumnName(String name) {
        int startIndex = name.indexOf("as");
        if(startIndex == -1) {
            startIndex = name.indexOf("AS");
        }
        startIndex += 2;
        return name.substring(startIndex).trim();
    }
}
