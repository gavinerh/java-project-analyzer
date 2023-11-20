package MARMSUI;

public class AddColumnNumberToSQLSelect {
    public static void main(String[] args) {
        String selectColumns = "TRANS_CD,   BATCH_DT, 	PTS_AWDED,  	TRANS_XREF_ID,  	' ',  	PRT_CD,  	' ',  	AWD_DESC,  	' ',  	RCRE_DT,  	' ',  	0,  	' ',  	null,  	null,  	NVL(REMARKS, ' '),   ELITE_PTS,  PPS_PTS,  SECT_CNT,  PPS_VAL  ,BATCH_ID,  NVL(SUPERVISOR_ID, ' '),  ' ',  0,  0,  0,  0,  ' ',  ' '  ,0  ,0";
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
