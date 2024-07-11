package MARMSUI;

public class AddColumnNumberToSQLSelect {
    public static void main(String[] args) {
        String selectColumns = "SEQ_NO, TIER_STATUS_IND, TIER_STATUS_PTS_REQ,           TIER_STATUS_SECT_REQ, TIER_STATUS_MIN_PRD,           TIER_HIERARCHY, TIER_STATUS_PTS_OFFSET, TIER_STATUS_SECT_OFFSET,           TIER_STATUS_ACCUM_PTS_REQ, TIER_STATUS_ACCUM_SECT_REQ,           TIER_STATUS_ACCUM_PTS_OFFSET, TIER_STATUS_ACCUM_SECT_OFFSET,           RULE_ACTIVE_IND, APPLICABLE_QLFY_IND,           TIER_QUAL_PRD, TIER_STATUS_VAL_REQ, TIER_STATUS_ACCUM_VAL_REQ,           RULE_IND, START_DT, END_DT, YRS_IN_TIER,           TIER_STATUS_VAL_OFFSET, TIER_STATUS_ACCUM_VAL_OFFSET, QUAL_SCHEME , PER_EM_PPS ";
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
