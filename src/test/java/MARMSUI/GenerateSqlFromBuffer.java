package MARMSUI;

public class GenerateSqlFromBuffer {
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer sqlBuilder = new StringBuffer();
        StringBuffer SQL2 = new StringBuffer();
        StringBuffer sb = new StringBuffer();
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

        buffer.append(" select * from tier_stat where qual_proc_ind = 'PP' and rule_Active_ind = 'N' ");
        buffer.append(" and rule_ind = 'M' and end_dt = ( select max(end_dt) from tier_stat ");
        //Modified by Vinod for MKP92600 Starts
        //qry.append(" where qual_proc_ind = 'PP' and rule_Active_ind = 'N' and rule_ind = 'M' ) " );
        buffer.append(" where qual_proc_ind = 'PP' and rule_Active_ind = 'N' and rule_ind = 'M' ) AND NVL(QUAL_SCHEME,'N')!='Y' ");
        //Modified by Vinod for MKP92600 Ends


        // To get the in-active rule for Q PR
        buffer.append(" UNION ALL ");
        buffer.append(" select * from tier_stat where qual_proc_ind = 'PR' and rule_Active_ind = 'N' ");
        buffer.append(" and rule_ind = 'M' and tier_status_ind = 'Q' and end_dt = ( select max(end_dt) from tier_stat ");
        buffer.append(" where qual_proc_ind = 'PR' and rule_Active_ind = 'N' and rule_ind = 'M' and tier_status_ind = 'Q' ) ");

        // To get the in-active rule for T PR
        buffer.append(" UNION ALL ");
        buffer.append(" select * from tier_stat where qual_proc_ind = 'PR' and rule_Active_ind = 'N' ");
        buffer.append(" and rule_ind = 'M' and tier_status_ind = 'T' and end_dt = ( select max(end_dt) from tier_stat ");
        //Modified by Vinod for MKP92600 Starts
        //qry.append(" where qual_proc_ind = 'PR' and rule_Active_ind = 'N' and rule_ind = 'M' and tier_status_ind = 'T' ) " );
        buffer.append(" where qual_proc_ind = 'PR' and rule_Active_ind = 'N' and rule_ind = 'M' and tier_status_ind = 'T' ) AND NVL(QUAL_SCHEME,'N')!='Y' ");
        //Modified by Vinod for MKP92600 Ends

        // To get the in-active rule for SL PU
        buffer.append(" UNION ALL ");
        buffer.append(" select * from tier_stat where qual_proc_ind = 'PU' and rule_Active_ind = 'N' ");
        buffer.append(" and rule_ind = 'M' and applicable_qlfy_ind = 'SL' and end_dt = ( select max(end_dt) from tier_stat ");
        //Modified by Vinod for MKP92600 Starts
        //qry.append(" where qual_proc_ind = 'PU' and rule_Active_ind = 'N' and rule_ind = 'M' and applicable_qlfy_ind = 'SL' ) " );
        buffer.append(" where qual_proc_ind = 'PU' and rule_Active_ind = 'N' and rule_ind = 'M' and applicable_qlfy_ind = 'SL' ) AND NVL(QUAL_SCHEME,'N')!='Y' ");
        //Modified by Vinod for MKP92600 Ends

        // To get the in-active rule for Non SL PU
        buffer.append(" UNION ALL ");
        buffer.append(" select * from tier_stat where qual_proc_ind = 'PU' and rule_Active_ind = 'N' ");
        buffer.append(" and rule_ind = 'M' and applicable_qlfy_ind <> 'SL' and end_dt = ( select max(end_dt) from tier_stat ");
        //Modified by Vinod for MKP92600 Starts
        //qry.append(" where qual_proc_ind = 'PU' and rule_Active_ind = 'N' and rule_ind = 'M' and applicable_qlfy_ind <> 'SL' ) " );
        buffer.append(" where qual_proc_ind = 'PU' and rule_Active_ind = 'N' and rule_ind = 'M' and applicable_qlfy_ind <> 'SL' ) AND NVL(QUAL_SCHEME,'N')!='Y' ");
        //Modified by Vinod for MKP92600 Ends
        //Modified by Logesh for MKP92708 Starts
        //qry.append(" AND     NVL(QUAL_SCHEME,'N')!='Y' ");//Added by Vinod for MKP92600

        //Modified by Logesh for MKP92708 Ends
        buffer.append(" ORDER BY TIER_STATUS_IND DESC");

        String paramName = "modFunc";
        String[] arrToReplace = {"startDate", "endDate"};

        String[] arrTypes = {"TIMESTAMP","TIMESTAMP"};


        String toPrint = null;
        boolean toReplace = true;
        boolean paramsAreNotNestedObjects = true;
        if (toReplace) {
            if(paramsAreNotNestedObjects){
                    toPrint = replaceQuestionMarkForNonObj(buffer.toString(),arrToReplace,arrTypes);
            }
            else {
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
        if(types.length != params.length){
            throw new RuntimeException("Length are not equal");
        }
        String finalString = "";
        int arrCount = 0;
        for(char c : sql.toCharArray()) {
            if(c == '?'){
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
