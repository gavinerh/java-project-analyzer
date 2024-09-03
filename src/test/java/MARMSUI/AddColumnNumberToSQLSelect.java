package MARMSUI;

public class AddColumnNumberToSQLSelect {
    public static void main(String[] args) {
        String selectColumns = "TRANS_CD, A.RCRE_DT,A.APPROVAL_CD,  A.ADJ_APPROVAL_CD, NET_PTS_REQ ,  ACTION_CD,  TKT_MCO_NO,  RDPN_TYPE ,  AWD_ZONE, B.PROMO_SAVINGS  ,  BILLING_PRT, A.RCRE_PCC, A.RCRE_SALES_OFF, A.RCRE_AGENT_ID, TKT_STOCK1,  C.ITIN_XREF_ID   ,  PNR_NAME, ACTION_CD2, PNR_REF, TURNPTS_BOARD, A.INT_ID, TKT_SRC_IND, AWD_TYPE,TKT_VALIDITY_DT , B.RDPN_NET_PTS_REQ, FORFEIT_PTS, B.TRANS_PTS , REVERSED_FLG, D.PROMO_CD , PROMO_NAME, BATCH_ID , BATCH_DT,TOT_STOPOVER_PTS,C.CERTIFICATE_NUMBER   ,A.MMK_IND, A.PYMT_RFND_LC, A.TOTAL_FARE_IN_LC, A.FARE_WO_TAX_IN_LC, A.TAX_IN_LC, A.NET_FARE_PAID_IN_LC, A.NET_KF_MILES_VAL_IN_LC,   A.TRANS_FARE_PAID_IN_LC, A.TRANS_KF_MILES_VAL_IN_LC, A.TOTAL_FARE_IN_SGD, A.FARE_WO_TAX_IN_SGD, A.TAX_IN_SGD, A.NET_FARE_PAID_IN_SGD,  A.NET_KF_MILES_VAL_IN_SGD, A.TRANS_FARE_PAID_IN_SGD, A.TRANS_KF_MILES_VAL_IN_SGD ,A.NET_TAX_PAID_IN_LC, A.NET_TAX_PAID_IN_SGD , A.ORIG_CURRENCY_CD , A.initial_action_cd , A.rfic_cd , A.rfic_desc  ";
        String toPrint = addColumnNumberToSelect(selectColumns);
        System.out.println(toPrint);
    }

    private static String addColumnNumberToSelect(String selectColumns) {
        boolean insideParenthesis = false;
        String cleansedString = "";
        int count = 1;
        for (int i = 0; i < selectColumns.length(); i++) {
            String c = selectColumns.substring(i, i + 1);
            if (c.equalsIgnoreCase("(")) {
                insideParenthesis = true;
            }
            if (c.equalsIgnoreCase(")")) {
                insideParenthesis = false;
            }
            if (c.equals(" ") || c.equals("\t")) {
                continue;
            }
            if (selectColumns.substring(i, i + 1).equalsIgnoreCase(",")) {
                if (insideParenthesis) {
                    cleansedString += c;
                    continue;
                }
                cleansedString += String.format(" as column%d, ", count);
                count++;
            } else {
                cleansedString += c;
            }

        }
        cleansedString += String.format(" as column%d",count);
        return cleansedString;
    }
}
