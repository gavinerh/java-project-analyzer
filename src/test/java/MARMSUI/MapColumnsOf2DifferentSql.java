package MARMSUI;

import java.util.ArrayList;
import java.util.List;

public class MapColumnsOf2DifferentSql {
    public static void main(String[] args) {
        String col1 = "TRANS_CD as TRANS_CD,   BATCH_DT as BATCH_DATE, \tPTS_AWDED as PTS_AWDED,  \tTRANS_XREF_ID as TRANS_XREF_ID,\n" +
                "'' AS FEES_CD,  \tPRT_CD AS PRT_CD,  \t'' AS FEES_TIER_IND,  \tAWD_DESC AS AWD_DESC,  \t\n" +
                "'' AS COLUMN9,  \tRCRE_DT as RCRE_DT, '' as column11, 0 as column12, '' as FEES_TRANS_XREF_ID, null as FRM_BUCKET_DT, \n" +
                "null as NEW_EXP_DT, NVL(REMARKS,'') as REMARKS, ELITE_PTS as ELITE_PTS, PPS_PTS as PPS_PTS, SECT_CNT as SECT_CNT, \n" +
                "PPS_VAL as PPS_VAL, BATCH_ID as BATCH_ID, NVL(SUPERVISOR_ID,'') as SUPERVISOR_ID, '' as PAYMNT_TYPE,\n" +
                "0 as TOT_AMT, 0 as WAIVED_PTS, 0 as WAIVED_AMT, 0 as EXP_PTS, '' as REFUND_FLG, '' as SUPPRESS_FLG, 0 as EXTENDED_PTS, 0\n" +
                " as BAL_EXTENDED";
        String col2 = "";

        List<String> col1Arr = generateColArr(col1, 0);
        List<String> col2Arr = generateColArr(col2, col1Arr.size());
        printMappingDetails(col1Arr,col2Arr);

    }

    private static void printMappingDetails(List<String> col1, List<String> col2) {
        if(col1.size() != col2.size()) {
            throw new RuntimeException("List are of different size");
        }
        for(int i=0; i<col1.size(); i++) {
            System.out.println(String.format("%s --> %s", col1.get(i),col2.get(i)));
        }
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
