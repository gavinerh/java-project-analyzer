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

        sqlQuery.append("  SELECT SUM(AIR_VAL) AS AIR_VAL, SUM(NON_AIR_VAL) AS NON_AIR_VAL FROM (");
        sqlQuery.append("  SELECT");
        sqlQuery.append("  NVL(SUM(DECODE(P.ana_ind, 'A',DECODE(AT.trans_cd, 'TC', AT.PPS_VAL, 'TD', -AT.PPS_VAL, 'SC', AT.PPS_VAL, 'SD', -AT.PPS_VAL),0)),0) AS AIR_VAL, ");
        sqlQuery.append("  NVL(SUM(DECODE(P.ana_ind, 'N',DECODE(AT.trans_cd, 'TC', AT.PPS_VAL, 'TD', -AT.PPS_VAL, 'SC', AT.PPS_VAL, 'SD', -AT.PPS_VAL),0)),0) AS NON_AIR_VAL");
        sqlQuery.append("  FROM AT_TRANS AT  INNER JOIN PRT P ON AT.PRT_CD = P.PRT_CD");
        sqlQuery.append("  WHERE AT.INT_ID = ?");
        sqlQuery.append("  AND AT.PRG_CD = ? ");
        sqlQuery.append("  AND AT.PPS_BUCKET_FLG = 'Y' ");
        sqlQuery.append("  AND AT.FLT_AWD_DT BETWEEN trunc(?) AND trunc(?) ");
        sqlQuery.append("  AND AT.TRANS_CD in ('TC', 'TD', 'SC', 'SD')");
        sqlQuery.append("  AND (P.ANA_IND = 'A' OR P.ANA_IND = 'N'${appendVal}" );
        sqlQuery.append("  UNION ALL");
        sqlQuery.append("  SELECT");
        sqlQuery.append("  NVL(SUM(DECODE(P.ana_ind, 'A', DECODE(PCM.trans_cd, 'MC', PCM.CUR_PPS_VAL, 'MD', -PCM.CUR_PPS_VAL),0)),0) AS AIR_VAL,");
        sqlQuery.append("  NVL(SUM(DECODE(P.ana_ind, 'N', DECODE(PCM.trans_cd, 'MC', PCM.CUR_PPS_VAL, 'MD', -PCM.CUR_PPS_VAL),0)),0) AS NON_AIR_VAL");
        sqlQuery.append("  FROM PRT_CUS_MERGE PCM INNER JOIN PRT P ON PCM.PRT_CD = P.PRT_CD");
        sqlQuery.append("  WHERE PCM.INT_ID = ?");
        sqlQuery.append("  AND PCM.PRG_CD = ? ");
        sqlQuery.append("  AND (PCM.CUR_PPS_PTS != 0 OR PCM.CUR_SECT_CNT != 0 OR PCM.CUR_PPS_VAL != 0)");
        sqlQuery.append("  AND PCM.BATCH_DT BETWEEN trunc(?) AND trunc(?) ");
        sqlQuery.append("  AND PCM.TRANS_CD in ('MC', 'MD')");
        sqlQuery.append("  AND (P.ANA_IND = 'A' OR P.ANA_IND = 'N'${appendVal}" );
        sqlQuery.append("  UNION ALL");
        sqlQuery.append("  SELECT ");
        sqlQuery.append("  NVL(SUM(DECODE(P.ana_ind, 'A',DECODE(PT.trans_cd, 'ZC', PT.PPS_BONUS_VALUE_AWDED, 'ZD', -PT.PPS_BONUS_VALUE_AWDED),0)),0) AS AIR_VAL,");
        sqlQuery.append("  NVL(SUM(DECODE(P.ana_ind, 'N',DECODE(PT.trans_cd, 'ZC', PT.PPS_BONUS_VALUE_AWDED, 'ZD', -PT.PPS_BONUS_VALUE_AWDED),0)),0) AS NON_AIR_VAL");
        sqlQuery.append("  FROM PROMO_TRANS PT INNER JOIN PRT P ON PT.PRT_CD = P.PRT_CD ");
        sqlQuery.append("  WHERE PT.INT_ID = ?");
        sqlQuery.append("  AND PT.PRG_CD = ?  ");
        sqlQuery.append("  AND PT.BATCH_DT BETWEEN trunc(?) AND trunc(?) ");
        sqlQuery.append("  AND PT.TRANS_CD in ('ZC', 'ZD') ");
        sqlQuery.append("  AND PT.PPS_BONUS_VALUE_AWDED > 0  ");
        sqlQuery.append("  AND (P.ANA_IND = 'A' OR P.ANA_IND = 'N'${appendVal})" );

        String paramName = "award";
        String[] arrToReplace = {"intId","progCode","startDate","endDate","intId","progCode","startDate","endDate","intId","progCode","startDate","endDate"};

        String[] arrTypes = {"NUMERIC","VARCHAR","TIMESTAMP","TIMESTAMP","NUMERIC","VARCHAR","TIMESTAMP","TIMESTAMP","NUMERIC","VARCHAR","TIMESTAMP","TIMESTAMP"};


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
