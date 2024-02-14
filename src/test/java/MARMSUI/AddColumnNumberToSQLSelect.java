package MARMSUI;

public class AddColumnNumberToSQLSelect {
    public static void main(String[] args) {
        String selectColumns = "PROMO_TRANS.BATCH_DT, PROMO_TRANS.TRANS_CD, PROMO_TRANS.PRT_CD, PROMO_TRANS.PROMO_AWD_DT,\n" +
                "        PROMO_TRANS.BONUS_AWDED , NVL(PROMO_TRANS.PROMO_AWD_DESC,''), 0, '', '', '', 0, '', '', '', 'Y', 'N',\n" +
                "        'N', 0, NVL(PROMO_TRANS.AMDMNT_RSN_CD,''), '', '', NVL(PROMO_TRANS.PROMO_CD,''),\n" +
                "        NVL(PROMO_TRANS.PROMO_AWD_DESC,''), PROMO_TRANS.ELITE_BONUS_MILES_AWDED, NVL(PRT.ANA_IND, 'A') ANA_IND,\n" +
                "        NVL(PROMO_TRANS.PROMO_XREF_ID, '') as promo_xref_id ,0, '', '' ,0 ,'' , NVL(OD_XREF_LINK.TRANS_XREF_ID, ''),\n" +
                "        NVL(BILL_TO_PRT, ''), NVL(CD_SHARE_PRT, ''), 0, NVL(TKT_NO, ''), NVL(FAMILY_NAME, ''), NVL(GIVEN_NAME, ''),\n" +
                "        null, '', '', 0, '', NVL(PRT_REF_CD, ''), NVL(BATCH_ID, ''), NVL(REVERSED_FLG, ''), NVL(NAME_MISMATCH_FLG,\n" +
                "        ''), '' , '' , 0 ,PROMO_TRANS.PPS_BONUS_VALUE_AWDED , '' ,PROMO_TRANS.PPS_PROMO_FLG , '' , '' ,0 ,0 ,0 , '', ''\n" +
                "        ,'', '', '', 0, 0, '', 0, '', ''";
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
