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

        sqlQuery.append( "INSERT INTO CUS_FAMILY_INFO ( " );
        sqlQuery.append( "RCRE_USER_ID, RCRE_DT, INT_ID, " );
        sqlQuery.append( "RELNSHIP_IND, SEQ_NO, " );
        sqlQuery.append( "TITLE, FAMILY_NAME, GIVEN_NAME, " );
        sqlQuery.append( "GENDER, DOB, FAMILY_MBR_INT_ID, FAMILY_MBR_PRG_CD, GUARDIAN_CONSENT, GUARDIAN_LINK, LINK_DT" ); //Added by Mohan for KFPROG-1418
        sqlQuery.append( " ) VALUES ( " );
        sqlQuery.append( "?, sysdate, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? " ); //Added by Mohan for KFPROG-1418
        sqlQuery.append( ")" );


        String paramName = "rsrv";
        String[] arrToReplace = {"agentId", "internalId", "relationshipind", "seqno", "title", "familyname", "givenname", "gender", "dob", "familyMbrIntId", "familyMbrPrgCd", "guardianConsent", "guardianLink", "linkDt"};

        String[] arrTypes = {"VARCHAR", "VARCHAR", "NUMERIC", "VARCHAR", "VARCHAR", "NUMERIC"};


        String toPrint = null;
        boolean toReplace = true;
        boolean paramsAreNotNestedObjects = false;
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
