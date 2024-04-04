package MARMSUI;

public class GenerateSqlFromBuffer {
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer sqlBuilder = new StringBuffer();
        StringBuffer SQL2 = new StringBuffer();
        StringBuffer sb = new StringBuffer();
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


        sqlQuery.append( "select PRG_CD, " );
        sqlQuery.append( "ACCUM_AT_PTS, LAST_AT_ACT_DT, ACCUM_NAT_PTS, " );
        sqlQuery.append( "LAST_NAT_ACT_DT, CUR_BAL, TOT_AIR_RDM_PTS, " );
        sqlQuery.append( "TOT_NONAIR_RDM_PTS, TOT_MISC_CR_PTS, " );
        sqlQuery.append( "TOT_MISC_DR_PTS, TOT_EXPIRED_PTS, LAST_STMT_PTS, " );
        sqlQuery.append( "LAST_STMT_DT, ONLINE_STMT_PTS, ONLINE_STMT_DT, OD_BAL, LAST_NON_AT_ACT_DT " );
        sqlQuery.append( "from MILEAGE_SUM where INT_ID=? and PRG_CD = ? " );

        String paramName = "modFunc";
        String[] arrToReplace = {"intId", "prgCd"};

        String[] arrTypes = { "VARCHAR", "VARCHAR"};


        String toPrint = null;
        boolean toReplace = true;
        boolean paramsAreNotNestedObjects = true;
        if (toReplace) {
            if (paramsAreNotNestedObjects) {
                toPrint = replaceQuestionMarkForNonObj(sqlQuery.toString(), arrToReplace, arrTypes);
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
