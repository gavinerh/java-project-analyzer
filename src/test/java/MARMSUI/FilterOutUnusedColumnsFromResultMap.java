package MARMSUI;

import java.util.ArrayList;
import java.util.List;

public class FilterOutUnusedColumnsFromResultMap {
    // given a list of result map columns, remove the columns that is intentionally 0 or empty
    // from the sql select columns
    // MUST REMOVE COLUMNS WITH COLUMN NUMBER MANUALLY FROM SQL QUERY
    public static void main(String[] args) {
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



        String selectSql = "'XD', PTS_EXT_EXP_TRANS.BATCH_DT,\n" +
                "        TRANS_BUCKET_PTS.NORMAL_PTS, PTS_EXT_EXP_TRANS.TRANS_XREF_ID, '',\n" +
                "        PTS_EXT_EXP_TRANS.PRT_CD, '',\n" +
                "        PTS_EXT_EXP_TRANS.BATCH_DT,\n" +
                "        '', TRANS_BUCKET_PTS.FRM_BUCKET_DT, null, NVL(REMARKS,''),\n" +
                "        0, 0, 0, 0, BATCH_ID, NVL(SUPERVISOR_ID,''), '', 0, 0, 0, 0, '', '',\n" +
                "        TRANS_BUCKET_PTS.EXTENDED_PTS, 0, 'MISC' as TRANSACTION_TYPE, 'Expired Miles\n" +
                "        '||to_char(FRM_BUCKET_DT, 'Mon YY'), '', '', 0, null, null";
        List<String> columnsList = generateColumnArr(selectSql);
        List<String> filteredColumnsList = columnsList;
//                filterOutColumnsWithColumnNumber(columnsList);
        List<String> columnListFromResultMap = retrieveColumnNameFromResultMapString(resultMap);
        List<String> validated = filterOutResultMapThatIsNotUsed(filteredColumnsList, columnListFromResultMap, resultMap);
        System.out.println(columnsList.size());
        printValidatedResultMap(validated);
    }

    private static List<String> filterOutColumnsWithColumnNumber(List<String> columnsList) {
        String pattern = "[()0-9A-Za-z\\s',_.]*column[\\d]{1,2}";
        List<String> filtered = new ArrayList<>();
        for(String s : columnsList) {
            if(s.trim().toLowerCase().matches(pattern)) {
                System.out.println(s.trim());
                continue;
            }
            filtered.add(s.trim());
        }
        return filtered;
    }

    private static void printValidatedResultMap(List<String> list) {
        list.stream().forEach(System.out::println);
    }

    private static List<String> filterOutResultMapThatIsNotUsed(List<String> colList, List<String> resultMapNames, String resultMap) {
        System.out.println("Printing out columns not used: -----");
        String defaultStringPattern = "[0']+[\\s]*[a-zA-Z0-9\\s_]*";
        String[] resultMapArr = resultMap.split("\n");
        List<String> validatedResultMap = new ArrayList<>();
        if(resultMapNames.size() != colList.size() || resultMapArr.length != resultMapNames.size()){
            throw new RuntimeException("Size is not similar");
        }
        for(int i=0; i<colList.size(); i++) {
            if(colList.get(i).trim().matches(defaultStringPattern)) {
                System.out.println(colList.get(i));
                continue;
            }
            if (colList.get(i).trim().equals("null")) {
                System.out.println(colList.get(i));
                continue;
            }
            validatedResultMap.add(resultMapArr[i]);
        }
        return validatedResultMap;
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
