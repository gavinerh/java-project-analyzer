package MARMSUI.JsonfieldResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MappingSqlColumnsToJsonField {
    public static void main(String[] args) {
        // do not include the select keyword
        String sql = "'PROMOTION' as TRANSACTION_TYPE,\n" +
                "        PROMO_TRANS.BATCH_DT, PROMO_TRANS.TRANS_CD, PROMO_TRANS.PRT_CD, PROMO_TRANS.PROMO_AWD_DT,\n" +
                "        PROMO_TRANS.BONUS_AWDED , NVL(PROMO_TRANS.PROMO_AWD_DESC,''), 0, '', '', '', 0, '', '', '', 'Y', 'N',\n" +
                "        'N', 0, NVL(PROMO_TRANS.AMDMNT_RSN_CD,''), '', '', NVL(PROMO_TRANS.PROMO_CD,''),\n" +
                "        NVL(PROMO_TRANS.PROMO_AWD_DESC,''), PROMO_TRANS.ELITE_BONUS_MILES_AWDED, NVL(PRT.ANA_IND, 'A') ANA_IND,\n" +
                "        NVL(PROMO_TRANS.PROMO_XREF_ID, '') as promo_xref_id ,0, '', '' ,0 ,'' , NVL(OD_XREF_LINK.TRANS_XREF_ID, ''),\n" +
                "        NVL(BILL_TO_PRT, ''), NVL(CD_SHARE_PRT, ''), 0, NVL(TKT_NO, ''), NVL(FAMILY_NAME, ''), NVL(GIVEN_NAME, ''),\n" +
                "        null, '', '', 0, '', NVL(PRT_REF_CD, ''), NVL(BATCH_ID, ''), NVL(REVERSED_FLG, ''), NVL(NAME_MISMATCH_FLG,\n" +
                "        ''), '' , '' , 0 ,PROMO_TRANS.PPS_BONUS_VALUE_AWDED , '' ,PROMO_TRANS.PPS_PROMO_FLG , '' , '' ,0 ,0 ,0 , '', ''\n" +
                "        ,'', '', '', 0, 0, '', 0, '', ''";
        String resultMap = "<result column=\"TRANSACTION_TYPE\" jdbcType=\"VARCHAR\" property=\"transactionType\"/>\n" +
                "        <result column=\"BATCH_DATE\" jdbcType=\"TIMESTAMP\" property=\"batchDate\"/>\n" +
                "        <result column=\"TRANS_CD\" jdbcType=\"VARCHAR\" property=\"transCd\"/>\n" +
                "        <result column=\"PRT_CD\" jdbcType=\"VARCHAR\" property=\"prtCd\"/>\n" +
                "        <result column=\"TRANS_DATE\" jdbcType=\"TIMESTAMP\" property=\"transDate\"/>\n" +
                "        <result column=\"PTS_AWARDED\" jdbcType=\"NUMERIC\" property=\"ptsAwarded\"/>\n" +
                "        <result column=\"AWD_DESC\" jdbcType=\"VARCHAR\" property=\"awdDesc\"/>\n" +
                "        <result column=\"FLT_NO\" jdbcType=\"NUMERIC\" property=\"fltNo\"/>\n" +
                "        <result column=\"ORIGIN\" jdbcType=\"VARCHAR\" property=\"origin\"/>\n" +
                "        <result column=\"DESTINATION\" jdbcType=\"VARCHAR\" property=\"destination\"/>\n" +
                "        <result column=\"CABIN_CLASS\" jdbcType=\"VARCHAR\" property=\"cabinClass\"/>\n" +
                "        <result column=\"DISTANCE\" jdbcType=\"NUMERIC\" property=\"distance\"/>\n" +
                "        <result column=\"PROP_LOC_CD\" jdbcType=\"VARCHAR\" property=\"propLocCd\"/>\n" +
                "        <result column=\"column14\" jdbcType=\"VARCHAR\" property=\"column14\"/>\n" +
                "        <result column=\"column15\" jdbcType=\"VARCHAR\" property=\"column15\"/>\n" +
                "        <result column=\"FFP_BUCKET_FLG\" jdbcType=\"VARCHAR\" property=\"ffpBucketFlg\"/>\n" +
                "        <result column=\"ELITE_BUCKET_FLG\" jdbcType=\"VARCHAR\" property=\"eliteBucketFlg\"/>\n" +
                "        <result column=\"PPS_BUCKET_FLG\" jdbcType=\"VARCHAR\" property=\"ppsBucketFlg\"/>\n" +
                "        <result column=\"SECTOR_CNT\" jdbcType=\"NUMERIC\" property=\"sectorCnt\"/>\n" +
                "        <result column=\"AMDMNT_RSN_CD\" jdbcType=\"VARCHAR\" property=\"amdmntRsnCd\"/>\n" +
                "        <result column=\"OFFP_PRT_CD\" jdbcType=\"VARCHAR\" property=\"offpPrtCd\"/>\n" +
                "        <result column=\"OFFP_MBR_NO\" jdbcType=\"VARCHAR\" property=\"offpMbrNo\"/>\n" +
                "        <result column=\"PROMO_CD\" jdbcType=\"VARCHAR\" property=\"promoCd\"/>\n" +
                "        <result column=\"PROMO_AWD_DESC\" jdbcType=\"VARCHAR\" property=\"promoAwdDesc\"/>\n" +
                "        <result column=\"ELITE_BONUS_MILES_AWDED\" jdbcType=\"NUMERIC\" property=\"eliteBonusMilesAwded\"/>\n" +
                "        <result column=\"ANA_IND\" jdbcType=\"VARCHAR\" property=\"anaInd\"/>\n" +
                "        <result column=\"PROMO_XREF_ID\" jdbcType=\"VARCHAR\" property=\"promoXrefId\"/>\n" +
                "        <result column=\"PPS_VAL\" jdbcType=\"NUMERIC\" property=\"ppsVal\"/>\n" +
                "        <result column=\"CR_SRC_IND\" jdbcType=\"VARCHAR\" property=\"crSrcInd\"/>\n" +
                "        <result column=\"INTERLINE_IND\" jdbcType=\"VARCHAR\" property=\"interlineInd\"/>\n" +
                "        <result column=\"PPS_XREF_ID\" jdbcType=\"NUMERIC\" property=\"ppsXrefId\"/>\n" +
                "        <result column=\"PPS_ONHOLD_FLG\" jdbcType=\"VARCHAR\" property=\"ppsOnholdFlg\"/>\n" +
                "        <result column=\"TRANS_XREF_ID\" jdbcType=\"VARCHAR\" property=\"transXrefId\"/>\n" +
                "        <result column=\"BILL_TO_PRT\" jdbcType=\"VARCHAR\" property=\"billToPrt\"/>\n" +
                "        <result column=\"CD_SHARE_PRT\" jdbcType=\"VARCHAR\" property=\"cdSharePrt\"/>\n" +
                "        <result column=\"CD_SHARE_FLT_NO\" jdbcType=\"NUMERIC\" property=\"cdShareFltNo\"/>\n" +
                "        <result column=\"TKT_NO\" jdbcType=\"VARCHAR\" property=\"tktNo\"/>\n" +
                "        <result column=\"FAMILY_NAME\" jdbcType=\"VARCHAR\" property=\"familyName\"/>\n" +
                "        <result column=\"GIVEN_NAME\" jdbcType=\"VARCHAR\" property=\"givenName\"/>\n" +
                "        <result column=\"CHK_OUT_DT\" jdbcType=\"TIMESTAMP\" property=\"chkOutDt\"/>\n" +
                "        <result column=\"TOUR_CD\" jdbcType=\"VARCHAR\" property=\"tourCd\"/>\n" +
                "        <result column=\"SERIES\" jdbcType=\"VARCHAR\" property=\"series\"/>\n" +
                "        <result column=\"TOUR_AMT\" jdbcType=\"NUMERIC\" property=\"tourAmt\"/>\n" +
                "        <result column=\"INPUT_MODE_IND\" jdbcType=\"VARCHAR\" property=\"inputModeInd\"/>\n" +
                "        <result column=\"PRT_REF_CD\" jdbcType=\"VARCHAR\" property=\"prtRefCd\"/>\n" +
                "        <result column=\"BATCH_ID\" jdbcType=\"VARCHAR\" property=\"batchId\"/>\n" +
                "        <result column=\"REVERSED_FLG\" jdbcType=\"VARCHAR\" property=\"reversedFlg\"/>\n" +
                "        <result column=\"NAME_MISMATCH_FLG\" jdbcType=\"VARCHAR\" property=\"nameMismatchFlg\"/>\n" +
                "        <result column=\"REF_DESC\" jdbcType=\"VARCHAR\" property=\"refDesc\"/>\n" +
                "        <result column=\"CPS_ID_CR\" jdbcType=\"VARCHAR\" property=\"cpsIdCr\"/>\n" +
                "        <result column=\"FSC_VALUE\" jdbcType=\"NUMERIC\" property=\"fscValue\"/>\n" +
                "        <result column=\"PPS_BONUS_VALUE_AWDED\" jdbcType=\"NUMERIC\" property=\"ppsBonusValueAwded\"/>\n" +
                "        <result column=\"DEF_FSC_CR_IND\" jdbcType=\"VARCHAR\" property=\"defFscCrInd\"/>\n" +
                "        <result column=\"PPS_PROMO_FLG\" jdbcType=\"VARCHAR\" property=\"ppsPromoFlg\"/>\n" +
                "        <result column=\"PAX_ID\" jdbcType=\"VARCHAR\" property=\"paxId\"/>\n" +
                "        <result column=\"AT_TRANS_TKT_NO\" jdbcType=\"VARCHAR\" property=\"atTransTktNo\"/>\n" +
                "        <result column=\"DOLLAR_VAL_IN_SGD\" jdbcType=\"NUMERIC\" property=\"dollarValInSgd\"/>\n" +
                "        <result column=\"AMT_PAID_IN_CASH\" jdbcType=\"NUMERIC\" property=\"amtPaidInCash\"/>\n" +
                "        <result column=\"TOT_AMT_PAID\" jdbcType=\"NUMERIC\" property=\"totAmtPaid\"/>\n" +
                "        <result column=\"ACC_CR_IND\" jdbcType=\"VARCHAR\" property=\"accCrInd\"/>\n" +
                "        <result column=\"PTY_LOC_DESC\" jdbcType=\"VARCHAR\" property=\"ptyLocDesc\"/>\n" +
                "        <result column=\"PPS_BKT_FLG_1\" jdbcType=\"VARCHAR\" property=\"ppsBktFlg1\"/>\n" +
                "        <result column=\"FFP_BKT_FLG_1\" jdbcType=\"VARCHAR\" property=\"ffpBktFlg1\"/>\n" +
                "        <result column=\"ELITE_BKT_FLG_1\" jdbcType=\"VARCHAR\" property=\"eliteBktFlg1\"/>\n" +
                "        <result column=\"PPS_VAL_1\" jdbcType=\"NUMERIC\" property=\"ppsVal1\"/>\n" +
                "        <result column=\"SECTOR_CNT_1\" jdbcType=\"NUMERIC\" property=\"sectorCnt1\"/>\n" +
                "        <result column=\"REF_DESC_1\" jdbcType=\"VARCHAR\" property=\"refDesc1\"/>\n" +
                "        <result column=\"FSC_VALUE_1\" jdbcType=\"NUMERIC\" property=\"fscValue1\"/>\n" +
                "        <result column=\"BKTY_PRESENT\" jdbcType=\"VARCHAR\" property=\"bktyPresent\"/>\n" +
                "        <result column=\"BKTN_PRESENT\" jdbcType=\"VARCHAR\" property=\"bktnPresent\"/>";
        List<String> fieldList = extractFieldFromResultMap(resultMap);
        List<String> columnList = extractColumnDetailsFromSql(sql);
        Map<String, String> mapOfResultMapValues = generateMapOfResultMap(fieldList, resultMap);
        List<String> listOfRequiredFields = extractOnlyUsedSqlCol(fieldList, columnList);
        printOnlyUsedResultMap(listOfRequiredFields, mapOfResultMapValues);
    }

    private static void printOnlyUsedResultMap(List<String> usedFields, Map<String,String> mapOfResultMap) {
        for(String key : usedFields) {
            System.out.println(mapOfResultMap.get(key));
        }
    }

    private static List<String> extractOnlyUsedSqlCol(List<String> fieldList, List<String> columnList) {
        List<String> usefulColList = new ArrayList<>();
        if(fieldList.size() != columnList.size()) {
            throw new RuntimeException("size unequal");
        }
        for(int i=0; i<fieldList.size(); i++) {
            String val = columnList.get(i);
            if(!val.equals("''") && !val.equals("0") && !val.equals("null")) {
                usefulColList.add(fieldList.get(i));
            }
        }
        return usefulColList;
    }

    private static Map<String,String> generateMapOfResultMap(List<String> fieldList, String resultMap) {
        String[] arr = resultMap.split("\n");
        if(fieldList.size() != arr.length) {
            throw new RuntimeException("Size is not equal");
        }
        Map<String,String> map = new HashMap<>();
        for(int i=0; i<fieldList.size(); i++) {
            map.put(fieldList.get(i), arr[i]);
        }
        return map;
    }

    private static List<String> extractColumnDetailsFromSql(String sql) {
        List<String> colList = new ArrayList<>();
        char separator = ',';
        String col = "";
        boolean isInsideParam = false;
        for (char c : sql.toCharArray()) {
            if(c == '\n') {
                continue;
            }
            if (c == '(') {
                isInsideParam = true;
            }
            if (c == ')') {
                isInsideParam = false;
            }
            if (c == separator && !isInsideParam) {
                // reset the col string
                colList.add(col.trim());
                col = "";
            } else {
                col += c;
            }
        }
        colList.add(col); // add in the last result
        return colList;
    }

    private static List<String> extractFieldFromResultMap(String resultMap) {
        String[] arr = resultMap.split("\n");
        String identifier = "property=";
        List<String> list = new ArrayList<>();
        for (String row : arr) {
            int startInd = row.indexOf(identifier) + identifier.length();
            startInd = row.indexOf("\"", startInd) + 1;
            int endInd = row.indexOf("\"", startInd);
            String field = row.substring(startInd, endInd);
            list.add(field);
        }
        return list;
    }
}
