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


        sqlQuery.append(" SELECT NVL(SUM(DECODE(trans_cd, 'TC', pts_awded, 'TD', -pts_awded, 'SC', pts_awded, 'SD', -pts_awded)),0) Elite_value ");
        sqlQuery.append(" FROM AT_TRANS  Where Int_Id = ? And Prg_Cd = 'KF' AND Elite_Bucket_Flg = 'Y' And Flt_Awd_Dt Between trunc(?) and trunc(?) ");
        sqlQuery.append(" And Trans_Cd In ('TC', 'TD', 'SC', 'SD') And decode(CD_SHARE_PRT, null, PRT_CD, CD_SHARE_PRT ) IN ('SQ','MI','TR') "); //Added TR for KFPROG-1128 by Saranya
        sqlQuery.append(" UNION ALL ");
        sqlQuery.append(" SELECT    NVL(SUM(DECODE(trans_cd, 'MC', CUR_ELITE_PTS, 'MD', -CUR_ELITE_PTS)),0) Elite_value ");
        sqlQuery.append(" FROM PRT_CUS_MERGE WHERE INT_ID = ? And Prg_Cd = 'KF' And Cur_Elite_Pts != 0  ");
        sqlQuery.append(" And Batch_Dt Between trunc(?) and trunc(?) And Trans_Cd In ('MC', 'MD') AND PRT_CD in ('SQ','MI','TR') ");//Added TR for KFPROG-1128 by Saranya
        sqlQuery.append(" UNION ALL ");
        sqlQuery.append(" SELECT NVL(SUM(DECODE(trans_cd, 'ZC', ELITE_BONUS_MILES_AWDED, 'ZD', -ELITE_BONUS_MILES_AWDED)),0) Elite_value ");
        sqlQuery.append(" FROM PROMO_TRANS WHERE INT_ID = ? AND PRG_CD = 'KF' And Elite_Bonus_Miles_Awded != 0 AND Batch_Dt Between trunc(?) and trunc(?) ");
        sqlQuery.append(" And Trans_Cd In ('ZC', 'ZD') AND PRT_CD in ('SQ','MI','TR') ");


        String paramName = "award";
        String[] arrToReplace = {"internalId", "startDt","endDt","internalId", "startDt","endDt","internalId", "startDt","endDt"};

        String[] arrTypes = {"NUMERIC","TIMESTAMP","TIMESTAMP","NUMERIC","TIMESTAMP","TIMESTAMP","NUMERIC","TIMESTAMP","TIMESTAMP"};


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
