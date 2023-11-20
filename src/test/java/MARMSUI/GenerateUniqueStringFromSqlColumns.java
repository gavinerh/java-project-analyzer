package MARMSUI;

public class GenerateUniqueStringFromSqlColumns {
    public static void main(String[] args) {
        String columns = "TRANS_CD as TRANS_CD,\n" +
                "        BATCH_DT as BATCH_DATE, ADMIN_FEE_TRANS.TOT_PTS as PTS_AWDED, FEES_CD as FEES_CD, PRT_CD as PRT_CD," +
                "        ADMIN_FEE_TRANS.FEES_TIER_IND as FEES_TIER_IND, BATCH_DT as TRANS_DATE,ADMIN_FEE_TRANS.FEES_TRANS_XREF_ID as FEES_TRANS_XREF_ID,REMARKS as " +
                "        REMARKS, BATCH_ID as BATCH_ID, SUPERVISOR_ID as SUPERVISOR_ID,PAYMNT_TYPE as PAYMNT_TYPE, TOT_AMT as TOT_AMT, WAIVED_PTS as WAIVED_PTS, WAIVED_AMT as" +
                "        WAIVED_AMT, EXP_PTS as EXP_PTS, REFUND_FLG as REFUND_FLG, SUPPRESS_FLG as SUPPRESS_FLG";
        String[] columnArr = generateColumnArr(columns);
        generateUniqueString(columnArr);
    }

    private static void generateUniqueString(String[] arr) {
        String toPrint = "";
        for(String col : arr) {
            int endIndex = col.indexOf(" as");
            toPrint += col.substring(0,endIndex);
            toPrint += " || ";
        }
        System.out.println(toPrint);
    }

    private static String[] generateColumnArr(String s) {
        String[] arr = s.split(",");
        for(int i=0; i<arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }
}
