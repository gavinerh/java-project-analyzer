package MARMSUI.clock;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class ExtractColumnFromOneUnionToAnotherUnion {
    public static void main(String[] args) {
        String sqlToHaveColumnameAdded = "'NON_ACCRUAL' as TRANSACTION_TYPE, AT_NON_ACR_TRANS.BATCH_DT,  AT_NON_ACR_TRANS.TRANS_CD,  AT_NON_ACR_TRANS.PRT_CD,\n" +
                "        AT_NON_ACR_TRANS.FLT_AWD_DT, 0,  NVL(AT_NON_ACR_TRANS.AWD_DESC,''),  NVL(AT_NON_ACR_TRANS.FLT_NO,0),  NVL(AT_NON_ACR_TRANS.ORG_CD,''),\n" +
                "        NVL(AT_NON_ACR_TRANS.DES_CD,''), NVL(AT_NON_ACR_TRANS.TRVL_CLS,''), 0,  '', '', '', '',  '', '', 0, AT_NON_ACR_TRANS.REJ_RSN_CD,\n" +
                "        NVL(AT_NON_ACR_TRANS.OFFP_PRT_CD, ''),  NVL(AT_NON_ACR_TRANS.OFFP_MBR_NO, ''), '', '', 0, '', '', 0, '', '', 0, '', '', '',  NVL(AT_NON_ACR_TRANS.CD_SHARE_PRT, ''),\n" +
                "        NVL(AT_NON_ACR_TRANS.CD_SHARE_FLT_NO, 0),  NVL(AT_NON_ACR_TRANS.TKT_NO, ''),  NVL(AT_NON_ACR_TRANS.FAMILY_NAME, ''),  NVL(AT_NON_ACR_TRANS.GIVEN_NAME, ''),\n" +
                "        null, '', '', 0, NVL(AT_NON_ACR_TRANS.INPUT_MODE_IND, ''),  NVL(AT_NON_ACR_TRANS.PRT_REF_CD, ''), NVL(AT_NON_ACR_TRANS.BATCH_ID, ''),\n" +
                "        NVL(AT_NON_ACR_TRANS.REVERSED_FLG, ''),  NVL(NAME_MISMATCH_FLG, ''), '' ,'', 0, 0, '', '', '', '',  0, 0, 0, '', '', '', '', '', 0, 0, '', 0, '', ''";

        String originSqlWithColName = "'AIR_ACCRUAL' as TRANSACTION_TYPE,\n" +
                "        AT_TRANS.BATCH_DT as BATCH_DATE, AT_TRANS.TRANS_CD AS TRANS_CD, AT_TRANS.PRT_CD AS PRT_CD, AT_TRANS.FLT_AWD_DT\n" +
                "        AS TRANS_DATE,\n" +
                "        AT_TRANS.PTS_AWDED as PTS_AWARDED, NVL(AT_TRANS.AWD_DESC,'') AS AWD_DESC, NVL(AT_TRANS.FLT_NO,0) AS FLT_NO,\n" +
                "        NVL(AT_TRANS.ORG_CD,'') AS ORIGIN,\n" +
                "        NVL(AT_TRANS.DES_CD,'') AS DESTINATION, NVL(AT_TRANS.TRVL_CLS,'') AS CABIN_CLASS, NVL(AT_TRANS.DISTANCE_TRVL,0)\n" +
                "        AS DISTANCE, '' AS PROP_LOC_CD, '' AS COLUMN14, '' AS COLUMN15,\n" +
                "        AT_TRANS.FFP_BUCKET_FLG AS FFP_BUCKET_FLG, AT_TRANS.ELITE_BUCKET_FLG AS ELITE_BUCKET_FLG,\n" +
                "        AT_TRANS.PPS_BUCKET_FLG AS PPS_BUCKET_FLG, AT_TRANS.SECT_CNT AS SECTOR_CNT,\n" +
                "        NVL(AT_TRANS.AMDMNT_RSN_CD,'') as AMDMNT_RSN_CD, NVL(AT_TRANS.OFFP_PRT_CD, '') AS OFFP_PRT_CD,\n" +
                "        NVL(AT_TRANS.OFFP_MBR_NO, '') AS OFFP_MBR_NO, '' AS PROMO_CD, '' AS PROMO_AWD_DESC, 0 AS\n" +
                "        ELITE_BONUS_MILES_AWDED,\n" +
                "        NVL(PRT.ANA_IND, 'A') as ANA_IND, NVL(AT_TRANS.PROMO_XREF_ID, '') AS PROMO_XREF_ID ,AT_TRANS.PPS_VAL AS PPS_VAL,\n" +
                "        AT_TRANS.CR_SRC_IND AS CR_SRC_IND,\n" +
                "        AT_TRANS.INTERLINE_IND AS INTERLINE_IND, NVL(at_trans.PPS_XREF_ID, 0) AS PPS_XREF_ID , NVL(PPS_ONHOLD_FLG, '')\n" +
                "        AS PPS_ONHOLD_FLG , NVL(OD_XREF_LINK.TRANS_XREF_ID, '') AS TRANS_XREF_ID,\n" +
                "        NVL(BILL_TO_PRT, '') AS BILL_TO_PRT, NVL(CD_SHARE_PRT, '') AS CD_SHARE_PRT, NVL(CD_SHARE_FLT_NO, 0) AS\n" +
                "        CD_SHARE_FLT_NO, NVL(TKT_NO, '') AS TKT_NO, NVL(FAMILY_NAME, '') AS FAMILY_NAME,\n" +
                "        NVL(GIVEN_NAME, '') AS GIVEN_NAME, null AS CHK_OUT_DT, '' AS TOUR_CD, '' AS SERIES, 0 AS TOUR_AMT,\n" +
                "        NVL(INPUT_MODE_IND, '') AS INPUT_MODE_IND, NVL(PRT_REF_CD, '') AS PRT_REF_CD, NVL(BATCH_ID, '') AS BATCH_ID,\n" +
                "        NVL(REVERSED_FLG, '') AS REVERSED_FLG, NVL(NAME_MISMATCH_FLG, '') AS NAME_MISMATCH_FLG, REFERENCE_CD.REF_DESC AS\n" +
                "        REF_DESC ,NVL(CPS_ID_CR, '') AS CPS_ID_CR\n" +
                "        ,AT_TRANS.FSC_VALUE as FSC_VALUE, 0 AS PPS_BONUS_VALUE_AWDED, AT_TRANS.DEF_FSC_CR_IND AS DEF_FSC_CR_IND,'' AS\n" +
                "        PPS_PROMO_FLG, NVL(AT_TRANS.PAX_ID, '') AS PAX_ID, NVL(AT_TRANS.TKT_NO, '') AS AT_TRANS_TKT_NO,\n" +
                "        AT_TRANS.DOLLAR_VAL_IN_SGD AS DOLLAR_VAL_IN_SGD, AT_TRANS.AMT_PAID_IN_CASH AS AMT_PAID_IN_CASH,\n" +
                "        AT_TRANS.TOT_AMT_PAID AS TOT_AMT_PAID, AT_TRANS.ACC_CR_IND AS ACC_CR_IND,\n" +
                "        '' as PTY_LOC_DESC,\n" +
                "        bktY.PPS_BUCKET_FLG as PPS_BKT_FLG_1, bktY.FFP_BUCKET_FLG as FFP_BKT_FLG_1, bktY.ELITE_BUCKET_FLG as\n" +
                "        ELITE_BKT_FLG_1, bktY.PPS_VAL as PPS_VAL_1,\n" +
                "        bktY.SECTOR_CNT as SECTOR_CNT_1, bktY.REF_DESC as REF_DESC_1, bktY.FSC_VALUE as FSC_VALUE_1,\n" +
                "        case\n" +
                "        when bktY.TRANS_CD is not null or bktY.PPS_BUCKET_FLG is not null or bktY.FFP_BUCKET_FLG is not null\n" +
                "        or bktY.ELITE_BUCKET_FLG is not null or bktY.PPS_VAL is not null or bktY.SECTOR_CNT is not null\n" +
                "        then 'Y'\n" +
                "        else 'N' end as BKTY_PRESENT,\n" +
                "        case\n" +
                "        when bktN.TRANS_CD is not null or bktN.PPS_BUCKET_FLG is not null or bktN.FFP_BUCKET_FLG is not null\n" +
                "        or bktN.ELITE_BUCKET_FLG is not null or bktN.PPS_VAL is not null or bktN.SECTOR_CNT is not null\n" +
                "        then 'Y'\n" +
                "        else 'N' end as BKTN_PRESENT";
        List<String> names = extractColumnNamesFromSqlQuery(originSqlWithColName);
        System.out.println(names.size());
        String val = appendColumnNameAfterColumn(sqlToHaveColumnameAdded,names);
        System.out.println(val);
        // validate the generated string is correct

    }

    private static String appendColumnNameAfterColumn(String sql, List<String> names) {
        List<String> splitColumnNames = extractColumns(sql);
        if(splitColumnNames.size() != names.size()) {
            throw new RuntimeException("Size is not the same");
        }
        System.out.println(splitColumnNames.size());
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<splitColumnNames.size(); i++) {
            stringBuilder.append(splitColumnNames.get(i)); stringBuilder.append(" as ");
            stringBuilder.append(names.get(i));
            stringBuilder.append(", ");
        }

        return stringBuilder.toString().substring(0,stringBuilder.length()-2);
    }

    private static List<String> extractColumnNamesFromSqlQuery(String sql) {
        List<String> colNameWithAs = extractColumns(sql);
        List<String> names = extractSelfDeclaredColName(colNameWithAs);
        if(names.size() != colNameWithAs.size()) {
            throw new RuntimeException("Length is not the same");
        }
        return names;
    }

    private static List<String> extractSelfDeclaredColName(List<String> colList) {
        List<String> names = new ArrayList<>(colList.size());
        for(String col : colList) {
            int startInd = col.toLowerCase().indexOf(" as ");
            if(startInd == -1) {
                startInd = col.toLowerCase().indexOf("\nas ");
            }
            if(startInd == -1) {
                startInd = col.toLowerCase().indexOf(" as\n");
            }
            if(startInd == -1) {
                throw new RuntimeException("Column name does not have 'as' keyword");
            }
            names.add(extractValueAfterIdentifer(col, startInd + 3));
        }
        return names;
    }

    private static String extractValueAfterIdentifer(String col, int startIndex) {
        return col.substring(startIndex).trim();
    }

    private static List<String> extractColumns(String sql) {
        int isInsideParams = 0;
        int start = 0;
        int count = -1;
        List<String> colList = new ArrayList<>();
        for(char c : sql.toCharArray()) {
            count++;
            if(c == '(') {
                isInsideParams++;
                continue;
            }
            if(c == ')') {
                isInsideParams--;
                continue;
            }
            if(c == ',' && isInsideParams == 0) {
                // reached the end of one column
                colList.add(sql.substring(start,count));
                start = count + 1;
            }
        }
        colList.add(sql.substring(start,count+1));
        return colList;
    }
}
