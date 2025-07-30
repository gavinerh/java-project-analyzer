package MARMSUI;

public class GenerateSqlFromBuffer {
    public static void main(String[] args) {
        StringBuffer sqlQuery = new StringBuffer();
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


        sqlQuery.append(" SELECT * FROM ( ");
        sqlQuery.append(" SELECT TIER_STATUS_IND, QLFY_IND, QLFY_START_DT, CUR_MILEAGE, " +
                "		CUR_SECT_CNT, NO_YRS_QLYFIED, QLFY_END_DT, FORCE_QLFY_DT,  " +
                "		FORCE_QLFY_EXTENDED_DT, NO_OF_EXTENDED_MTH, ORIG_EXP, " +
                "		QLFD_DT, TIER_BONUS_AWARD_START_DT, TIER_BONUS_AWARD_END_DT, " +
                "		YRS_IN_QPP, CUR_VAL " +
                " FROM   HIS_CUS_PPS_QUAL HCPQ ");
        sqlQuery.append(" WHERE  HCPQ.INT_ID = ? " +
                " AND 	HCPQ.PRG_CD = ? " +
                " ORDER BY RCRE_DATE DESC )");


        String paramName = "award";
        String[] arrToReplace = {"kfNumber","programCode"};

        String[] arrTypes = {"VARCHAR","VARCHAR"};


        String toPrint = null;
        boolean toReplace = true;
        boolean paramsAreNotNestedObjects = true;
        if (toReplace) {
            if (paramsAreNotNestedObjects) {
                toPrint = replaceQuestionMarkForNonObj(sqlQuery.toString(), arrToReplace, arrTypes);
            } else {
                toPrint = replaceQuestionMark(sqlQuery.toString(), arrToReplace, paramName);
            }
        } else {
            System.out.println(sqlQuery.toString());
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
