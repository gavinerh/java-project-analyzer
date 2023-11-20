package MARMSUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidateColumnNameIsMatchingResultMapInSeq {
    public static void main(String[] args) {
        // must manually remove the columns with the column-Number
        String sql = "TRANS_CD as TRANS_CD,\n" +
                "        BATCH_DT as BATCH_DATE, ADMIN_FEE_TRANS.TOT_PTS as PTS_AWDED, '' as TRANS_XREF_ID, FEES_CD as FEES_CD, PRT_CD as\n" +
                "        PRT_CD,\n" +
                "        ADMIN_FEE_TRANS.FEES_TIER_IND as FEES_TIER_IND, BATCH_DT as TRANS_DATE, \n" +
                "         ADMIN_FEE_TRANS.FEES_TRANS_XREF_ID as FEES_TRANS_XREF_ID, null as FRM_BUCKET_DT, null as\n" +
                "        NEW_EXP_DT, REMARKS as\n" +
                "        REMARKS, 0 as ELITE_PTS, 0 as PPS_PTS, 0 as SECT_CNT, 0 as PPS_VAL, BATCH_ID as BATCH_ID, NVL(SUPERVISOR_ID,'')\n" +
                "        as SUPERVISOR_ID,\n" +
                "        PAYMNT_TYPE as PAYMNT_TYPE, TOT_AMT as TOT_AMT, NVL(WAIVED_PTS,0) as WAIVED_PTS, NVL(WAIVED_AMT,0) as\n" +
                "        WAIVED_AMT, EXP_PTS as EXP_PTS, REFUND_FLG as REFUND_FLG, SUPPRESS_FLG as SUPPRESS_FLG, 0 as PTS_EXTENDED,\n" +
                "        0 as BAL_EXTENDED, 'MISC' as TRANSACTION_TYPE, '' AS AWD_DESC,\n" +
                "        (Select FEES_DESC\n" +
                "        from ADMIN_FEES where ADMIN_FEE_TRANS.FEES_CD = ADMIN_FEES.FEES_CD\n" +
                "        and ADMIN_FEES.FEES_TIER_IND = ADMIN_FEE_TRANS.FEES_TIER_IND AND ADMIN_FEES.PRT_CD = ADMIN_FEE_TRANS.PRT_CD\n" +
                "        AND ADMIN_FEES.START_DT &lt; ADMIN_FEE_TRANS.BATCH_DT fetch first 1 row only) AS DESC_2,\n" +
                "        TRANS_CD || BATCH_DT || ADMIN_FEE_TRANS.TOT_PTS || FEES_CD || PRT_CD || ADMIN_FEE_TRANS.FEES_TIER_IND ||\n" +
                "        BATCH_DT ||\n" +
                "        ADMIN_FEE_TRANS.FEES_TRANS_XREF_ID || REMARKS || BATCH_ID || SUPERVISOR_ID || PAYMNT_TYPE || TOT_AMT ||\n" +
                "        WAIVED_PTS ||\n" +
                "        WAIVED_AMT || EXP_PTS || REFUND_FLG || SUPPRESS_FLG as general_identifier,\n" +
                "        case\n" +
                "        when TRANS_CD = 'FD' and FEES_CD is not null and FEES_CD = 'EXTM' then bucketTrans.TOT_PTS\n" +
                "        else 0\n" +
                "        end as TOT_PTS,\n" +
                "        case\n" +
                "        when TRANS_CD = 'FD' and FEES_CD is not null and FEES_CD = 'EXTM' then bucketTrans.EXP_DT\n" +
                "        else null end as EXPIRY_DT,\n" +
                "        case\n" +
                "        when TRANS_CD = 'FD' and FEES_CD is not null and FEES_CD = 'EXTM' then bucketTrans.NEW_EXP_DT\n" +
                "        else null end as NEW_EXP_DT_1";




        String resultMap = "<result column=\"TRANS_CD\" jdbcType=\"VARCHAR\" property=\"transCd\"/>\n" +
                "        <result column=\"BATCH_DATE\" jdbcType=\"TIMESTAMP\" property=\"batchDate\"/>\n" +
                "        <result column=\"PTS_AWDED\" jdbcType=\"NUMERIC\" property=\"ptsAwded\"/>\n" +
                "        <result column=\"TRANS_XREF_ID\" jdbcType=\"VARCHAR\" property=\"transXrefId\"/>\n" +
                "        <result column=\"FEES_CD\" jdbcType=\"VARCHAR\" property=\"feesCd\"/>\n" +
                "        <result column=\"PRT_CD\" jdbcType=\"VARCHAR\" property=\"prtCd\"/>\n" +
                "        <result column=\"FEES_TIER_IND\" jdbcType=\"VARCHAR\" property=\"feesTierInd\"/>\n" +
                "        <result column=\"TRANS_DATE\" jdbcType=\"TIMESTAMP\" property=\"transDate\"/>\n" +
                "        <result column=\"FEES_TRANS_XREF_ID\" jdbcType=\"VARCHAR\" property=\"feesTransXrefId\"/>\n" +
                "        <result column=\"FRM_BUCKET_DT\" jdbcType=\"TIMESTAMP\" property=\"frmBucketDt\"/>\n" +
                "        <result column=\"NEW_EXP_DT\" jdbcType=\"TIMESTAMP\" property=\"newExpDt\"/>\n" +
                "        <result column=\"REMARKS\" jdbcType=\"VARCHAR\" property=\"remarks\"/>\n" +
                "        <result column=\"ELITE_PTS\" jdbcType=\"NUMERIC\" property=\"elitePts\"/>\n" +
                "        <result column=\"PPS_PTS\" jdbcType=\"NUMERIC\" property=\"ppsPts\"/>\n" +
                "        <result column=\"SECT_CNT\" jdbcType=\"NUMERIC\" property=\"sectCnt\"/>\n" +
                "        <result column=\"PPS_VAL\" jdbcType=\"NUMERIC\" property=\"ppsVal\"/>\n" +
                "        <result column=\"BATCH_ID\" jdbcType=\"VARCHAR\" property=\"batchId\"/>\n" +
                "        <result column=\"SUPERVISOR_ID\" jdbcType=\"VARCHAR\" property=\"supervisorId\"/>\n" +
                "        <result column=\"PAYMNT_TYPE\" jdbcType=\"VARCHAR\" property=\"paymntType\"/>\n" +
                "        <result column=\"TOT_AMT\" jdbcType=\"NUMERIC\" property=\"totAmt\"/>\n" +
                "        <result column=\"WAIVED_PTS\" jdbcType=\"NUMERIC\" property=\"waivedPts\"/>\n" +
                "        <result column=\"WAIVED_AMT\" jdbcType=\"NUMERIC\" property=\"waivedAmt\"/>\n" +
                "        <result column=\"EXP_PTS\" jdbcType=\"NUMERIC\" property=\"expPts\"/>\n" +
                "        <result column=\"REFUND_FLG\" jdbcType=\"VARCHAR\" property=\"refundFlg\"/>\n" +
                "        <result column=\"SUPPRESS_FLG\" jdbcType=\"VARCHAR\" property=\"suppressFlg\"/>\n" +
                "        <result column=\"PTS_EXTENDED\" jdbcType=\"NUMERIC\" property=\"ptsExtended\"/>\n" +
                "        <result column=\"BAL_EXTENDED\" jdbcType=\"NUMERIC\" property=\"balExtended\"/>\n" +
                "        <result column=\"TRANSACTION_TYPE\" jdbcType=\"VARCHAR\" property=\"transactionType\"/>\n" +
                "        <result column=\"AWD_DESC\" jdbcType=\"VARCHAR\" property=\"awdDesc\"/>\n" +
                "        <result column=\"DESC_2\" jdbcType=\"VARCHAR\" property=\"desc2\"/>\n" +
                "        <result column=\"general_identifier\" jdbcType=\"VARCHAR\" property=\"generalIdentifier\"/>\n" +
                "        <result column=\"TOT_PTS\" jdbcType=\"NUMERIC\" property=\"totPts\"/>\n" +
                "        <result column=\"EXPIRY_DT\" jdbcType=\"TIMESTAMP\" property=\"expiryDt\"/>\n" +
                "        <result column=\"NEW_EXP_DT_1\" jdbcType=\"TIMESTAMP\" property=\"newExpDt1\"/>";
        List<String> columnList = generateColumnArr(sql);
        List<String> columnNameList = generateColumnName(columnList);
        List<String> columnListFromResultMap = retrieveColumnNameFromResultMapString(resultMap);
        compareColumns(columnNameList, columnListFromResultMap);
        System.out.println("Complete without error, all columns are aligned");
        System.out.println("Completed resultMap:-------");
        String[] arr = resultMap.split("\n");
        Arrays.stream(arr).forEach(System.out::println);
    }

    private static void compareColumns(List<String> list1, List<String> list2) {
        if(list1.size() != list2.size()) {
            throw new RuntimeException("Size is different");
        }
        for(int i=0; i<list1.size(); i++) {
            if(!list1.get(i).toUpperCase().equals(list2.get(i).toUpperCase())) {
                System.out.println(String.format("List 1: %s ----- List 2: %s", list1.get(i), list2.get(i)));
                throw new RuntimeException("Column names are different");
            }
        }
    }

    private static List<String> retrieveColumnNameFromResultMapString(String resultMap) {
        List<String> list = new ArrayList<>();
        String[] resultMapArr = resultMap.split("\n");
        for(String row : resultMapArr) {
            int startIndex = row.indexOf("column");
            startIndex = row.indexOf("\"", startIndex) + 1;
            int endIndex = row.indexOf("\"", startIndex);
            list.add(row.substring(startIndex,endIndex).trim());
        }
        return list;
    }

    private static List<String> generateColumnName(List<String> list) {
        List<String> columnNames = new ArrayList<>();
        for(String s : list) {
            String name = getColumnName(s).trim();
            if(name.contains("COLUMN")) {
                continue;
            }
            columnNames.add(name);
        }
        return columnNames;
    }

    private static String getColumnName(String column) {
        String[] columnSplit = column.split(" ");
        boolean containAs = false;
        for(String s : columnSplit) {
            if(s.trim().equalsIgnoreCase("as")) {
                containAs = true;
            }
        }
        if(containAs) {
            return columnSplit[columnSplit.length-1];
        }
        return column;
    }

    private static List<String> generateColumnArr(String sql) {
        List<String> listOfCol = new ArrayList<>();
        boolean insideParams = false;
        String column = "";
        for(int i=0; i<sql.length(); i++) {
            String letter = sql.substring(i,i+1);
            if(letter.equals("(")) {
                insideParams = true;
                column += letter;
                continue;
            }
            if(letter.equals(")")){
                insideParams = false;
                column += letter;
                continue;
            }
            column += letter;
            if(letter.equals(",") && !insideParams || i == sql.length()-1) {
                String trimmedCol = column.trim();
                if(trimmedCol.substring(trimmedCol.length()-1,trimmedCol.length()).equals(",")) {
                    trimmedCol = trimmedCol.substring(0,trimmedCol.length()-1);
                }
                listOfCol.add(trimmedCol);
                column = "";
            }

        }
        return listOfCol;
    }
}
