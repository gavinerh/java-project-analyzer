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

        sqlQuery.append("SELECT AirNonAir,  SUM(NVL(Elite_value,0)) AS Val from (");
        sqlQuery.append(" SELECT P.ANA_IND AS AirNonAir, NVL(SUM(DECODE(AT.trans_cd, 'TC', AT.pts_awded, 'TD', -AT.pts_awded, 'SC', AT.pts_awded, 'SD', -AT.pts_awded)),0)  Elite_value ");
        sqlQuery.append(" FROM AT_TRANS AT  ");
        sqlQuery.append(" INNER JOIN PRT P ON  P.PRT_CD = DECODE(AT.CD_SHARE_PRT, NULL, AT.PRT_CD, AT.CD_SHARE_PRT )  ");
        sqlQuery.append(" Where AT.Int_Id = ? And AT.Prg_Cd = 'KF' AND AT.Elite_Bucket_Flg = 'Y' And AT.Flt_Awd_Dt Between trunc(?) and trunc(?) "); //Added TR for KFPROG-1128 by Saranya
        sqlQuery.append(" And AT.Trans_Cd In ('TC', 'TD', 'SC', 'SD') And DECODE(AT.CD_SHARE_PRT, NULL, AT.PRT_CD, AT.CD_SHARE_PRT ) ");//Added by Chandima for KFPROG-3998
        sqlQuery.append(" IN (SELECT REF_REC_CD from REFERENCE_CD where REF_REC_TYPE =?)  ");
        sqlQuery.append(" GROUP BY P.ANA_IND  ");
        sqlQuery.append(" UNION ALL ");
        sqlQuery.append(" SELECT  P.ANA_IND AS AirNonAir,  NVL(SUM(DECODE(PCM.trans_cd, 'MC', PCM.CUR_ELITE_PTS, 'MD', -PCM.CUR_ELITE_PTS)),0) Elite_value  ");
        sqlQuery.append(" FROM PRT_CUS_MERGE PCM INNER JOIN PRT P ON  P.PRT_CD = PCM.PRT_CD ");
        sqlQuery.append(" WHERE PCM.INT_ID =? And PCM.Prg_Cd = 'KF' And PCM.Cur_Elite_Pts != 0   ");
        sqlQuery.append(" And PCM.Batch_Dt Between trunc(?) and trunc(?) And PCM.Trans_Cd In ('MC', 'MD') AND PCM.PRT_CD ");
        sqlQuery.append(" IN (SELECT REF_REC_CD from REFERENCE_CD where REF_REC_TYPE =?) GROUP BY P.ANA_IND ");
        sqlQuery.append(" UNION ALL ");
        sqlQuery.append(" SELECT P.ANA_IND AS AirNonAir, NVL(SUM(DECODE(PT.trans_cd, 'ZC', PT.ELITE_BONUS_MILES_AWDED, 'ZD', -PT.ELITE_BONUS_MILES_AWDED)),0) Elite_value ");
        sqlQuery.append(" FROM PROMO_TRANS PT INNER JOIN PRT P ON  P.PRT_CD = PT.PRT_CD ");
        sqlQuery.append(" WHERE PT.INT_ID =? AND PT.PRG_CD = 'KF' And PT.Elite_Bonus_Miles_Awded != 0   ");//Added TR for KFPROG-1128 by Saranya
        sqlQuery.append(" AND PT.Batch_Dt Between trunc(?) and trunc(?) And PT.Trans_Cd In ('ZC', 'ZD')  ");//Added TR for KFPROG-1128 by Saranya
        sqlQuery.append(" AND PT.PRT_CD IN (SELECT REF_REC_CD from REFERENCE_CD where REF_REC_TYPE = ?) GROUP BY P.ANA_IND ) group by AirNonAir ");//Added by Chandima for KFPROG-3998

        String paramName = "award";
        String[] arrToReplace = {"intId","startDt","endDt","refRecType","intId","startDt","endDt","refRecType","intId","startDt","endDt","refRecType"};

        String[] arrTypes = {"NUMERIC","TIMESTAMP","TIMESTAMP","VARCHAR","NUMERIC","TIMESTAMP","TIMESTAMP","VARCHAR","NUMERIC","TIMESTAMP","TIMESTAMP","VARCHAR"};


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
