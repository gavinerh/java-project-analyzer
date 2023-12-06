package MARMSUI;

public class GenerateSqlFromBuffer {
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer sqlBuilder = new StringBuffer();
        StringBuffer SQL2 = new StringBuffer();
        StringBuffer sb = new StringBuffer();
        boolean isONL = true;
        boolean toIncludePrgCd = false;
        boolean toIncludeTransCd = false;
        boolean toIncludeStartTransDt = true;
        boolean toIncludeEndTransDt = true;
        boolean isSTM = false;
        boolean isDSTM = false;
        boolean isINT = false;
        boolean flag = true;
//        StringBuffer sb_NrTransCode = new StringBuffer();
//        sb_NrTransCode.append("'NC','ND'");
        String airTransCode = "'RC','RD'";
        StringBuffer initial = new StringBuffer();

        buffer.append("SELECT TRANS_CD, A.RCRE_DT,A.APPROVAL_CD,  A.ADJ_APPROVAL_CD, NET_PTS_REQ ,  ACTION_CD,  TKT_MCO_NO,  RDPN_TYPE ,  AWD_ZONE, ");
        buffer.append("B.PROMO_SAVINGS  ,  BILLING_PRT, A.RCRE_PCC, A.RCRE_SALES_OFF, A.RCRE_AGENT_ID, TKT_STOCK1,  C.ITIN_XREF_ID   ,  PNR_NAME, ");
        buffer.append("ACTION_CD2, PNR_REF, TURNPTS_BOARD, A.INT_ID, TKT_SRC_IND, AWD_TYPE,TKT_VALIDITY_DT , B.RDPN_NET_PTS_REQ, FORFEIT_PTS, B.TRANS_PTS , REVERSED_FLG, D.PROMO_CD , PROMO_NAME, BATCH_ID , BATCH_DT,TOT_STOPOVER_PTS,C.CERTIFICATE_NUMBER  ");

        //SUMATHI Changes for - MKP91492 - KRISFLYER VALUE BASED REDEMPTION - START
        buffer.append (" ,A.MMK_IND, A.PYMT_RFND_LC, A.TOTAL_FARE_IN_LC, A.FARE_WO_TAX_IN_LC, A.TAX_IN_LC, A.NET_FARE_PAID_IN_LC, A.NET_KF_MILES_VAL_IN_LC, ");
        buffer.append("  A.TRANS_FARE_PAID_IN_LC, A.TRANS_KF_MILES_VAL_IN_LC, A.TOTAL_FARE_IN_SGD, A.FARE_WO_TAX_IN_SGD, A.TAX_IN_SGD, A.NET_FARE_PAID_IN_SGD, ");
        buffer.append(" A.NET_KF_MILES_VAL_IN_SGD, A.TRANS_FARE_PAID_IN_SGD, A.TRANS_KF_MILES_VAL_IN_SGD");
        //Added By Hari for MKP91775 - PwM Start
        buffer.append(" ,A.NET_TAX_PAID_IN_LC, A.NET_TAX_PAID_IN_SGD");
        //Added By Hari for MKP91775 - PwM End
        buffer.append(" , A.ORIG_CURRENCY_CD"); // SUBHA - MKP91492 - Added for the Account Summary screen changes
        buffer.append(" , A.initial_action_cd , A.rfic_cd , A.rfic_desc  ");

        String paramName = "modFunc";
        String[] arrToReplace = {"prgCd", "tierType", "processType"};

        String[] arrTypes = { "VARCHAR", "VARCHAR", "VARCHAR"};


        String toPrint = null;
        boolean toReplace = true;
        boolean paramsAreNotNestedObjects = true;
        if (toReplace) {
            if (paramsAreNotNestedObjects) {
                toPrint = replaceQuestionMarkForNonObj(buffer.toString(), arrToReplace, arrTypes);
            } else {
                toPrint = replaceQuestionMark(buffer.toString(), arrToReplace, paramName);
            }
        } else {
            System.out.println(buffer.toString());
            System.out.println(buffer1.toString());
            System.out.println(sqlBuilder.toString());
            System.out.println(SQL2.toString());
            System.out.println(sb.toString());
            System.out.println(initial.toString());
        }

        System.out.println(toPrint);

    }

    private static String replaceQuestionMarkForNonObj(String sql, String[] params, String[] types) {
        if (types.length != params.length) {
            throw new RuntimeException("Length are not equal");
        }
        String finalString = "";
        int arrCount = 0;
        for (char c : sql.toCharArray()) {
            if (c == '?') {
                finalString += String.format("#{%s,jdbcType=%s}", params[arrCount], types[arrCount]);
                arrCount++;
            } else {
                finalString += c;
            }
        }
//        for (int i = 0; i < sql.length(); i++) {
//            String charac = sql.substring(i, i + 1);
//            if (charac.equals("?")) {
//                charac = String.format("#{%s,jdbcType=%s}", params[arrCount], types[arrCount]);
//                arrCount++;
//            }
//            finalString += charac;
//        }
        return finalString;
    }

    private static String replaceQuestionMark(String rawSql, String[] arrToReplace, String paramName) {
        String finalString = "";
        int arrCount = 0;
        for (int i = 0; i < rawSql.length(); i++) {
            String charac = rawSql.substring(i, i + 1);
            if (charac.equals("?")) {
                charac = String.format("#{%s.%s,jdbcType=VARCHAR}", paramName, arrToReplace[arrCount]);
                arrCount++;
            }
            finalString += charac;
        }
        return finalString;
    }
}
