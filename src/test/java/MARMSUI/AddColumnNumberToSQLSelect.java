package MARMSUI;

public class AddColumnNumberToSQLSelect {
    public static void main(String[] args) {
        String selectColumns = "AT_NON_ACR_TRANS.RCRE_DT,  \tAT_NON_ACR_TRANS.BATCH_DT,  \tAT_NON_ACR_TRANS.TRANS_CD,   \tAT_NON_ACR_TRANS.PRT_CD,   \tAT_NON_ACR_TRANS.FLT_AWD_DT,  \t0,   \tNVL(AT_NON_ACR_TRANS.AWD_DESC,' '),   \tNVL(AT_NON_ACR_TRANS.FLT_NO,0),  \tNVL(AT_NON_ACR_TRANS.ORG_CD,' '),   \tNVL(AT_NON_ACR_TRANS.DES_CD,' '),   \tNVL(AT_NON_ACR_TRANS.TRVL_CLS,' '),  \t0,  \t' ',   \t' ',   \t' ',  \t' ',   \t' ',   \t' ',  \t0,   \tAT_NON_ACR_TRANS.REJ_RSN_CD,  \tNVL(AT_NON_ACR_TRANS.OFFP_PRT_CD, ' '),   \tNVL(AT_NON_ACR_TRANS.OFFP_MBR_NO, ' '),  \t' ',  \t' ',  \t0,  \t' ',  \t' ' \t, 0, ' ', ' ' , ' ',  ' ',  NVL(AT_NON_ACR_TRANS.CD_SHARE_PRT, ' '),  NVL(AT_NON_ACR_TRANS.CD_SHARE_FLT_NO, 0),  NVL(AT_NON_ACR_TRANS.TKT_NO, ' '),  NVL(AT_NON_ACR_TRANS.FAMILY_NAME, ' '),  NVL(AT_NON_ACR_TRANS.GIVEN_NAME, ' '),  null,  ' ',  ' ',  0,  NVL(AT_NON_ACR_TRANS.INPUT_MODE_IND, ' '),  NVL(AT_NON_ACR_TRANS.PRT_REF_CD, ' '),  NVL(AT_NON_ACR_TRANS.BATCH_ID, ' '),  NVL(AT_NON_ACR_TRANS.REVERSED_FLG, ' '),  NVL(NAME_MISMATCH_FLG, ' '), ' '  ,' '  ,0  ,' '  ,0   ,0   ,' ' ";
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
