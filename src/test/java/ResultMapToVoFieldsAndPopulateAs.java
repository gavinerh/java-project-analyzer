import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ResultMapToVoFieldsAndPopulateAs {
    public static void main(String[] args) {
        String resultMap = "<result column=\"CREATED_DATE\" jdbcType=\"TIMESTAMP\" property=\"\" />\n" +
                "        <result column=\"BATCH_DATE\" jdbcType=\"TIMESTAMP\" property=\"\"/>\n" +
                "        <result column=\"TRANS_CD\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"PRT_CD\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"AWARD_DATE\" jdbcType=\"TIMESTAMP\" property=\"\"/>\n" +
                "        <result column=\"PTS_AWARDED\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"AWD_DESC\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"FLT_NO\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"ORG_CD\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"DES_CD\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"TRVL_CLS\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"DIST_TRVL\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"PROP_LOC_CD\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"FFP_BUCKET_FLG\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"ELITE_BUCKET_FLG\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"PPS_BUCKET_FLG\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"SECT_CNT\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"AMDMNT_RSN_CD\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"OFFP_PRT_CD\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"OFFP_MBR_NO\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"PROMO_CD\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"PROMO_AWD_DESC\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"ELITE_BONUS_MILES_AWDED\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"ANA_IND\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"PROMO_XREF_ID\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"PPS_VAL\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"CR_SRC_IND\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"INTERLINE_IND\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"PPS_XREF_ID\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"PPS_ONHOLD_FLG\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"TRANS_XREF_ID\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"BILL_TO_PRT\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"CD_SHARE_PRT\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"CD_SHARE_FLT_NO\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"TKT_NO\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"FAMILY_NAME\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"GIVEN_NAME\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"CHK_OUT_DT\" jdbcType=\"TIMESTAMP\" property=\"\"/>\n" +
                "        <result column=\"TOUR_CD\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"SERIES\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"TOUR_AMT\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"INPUT_MODE_IND\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"PRT_REF_CD\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"BATCH_ID\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"REVERSED_FLG\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"NAME_MISMATCH_FLG\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"REF_DESC\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"CPS_ID_CR\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"FSC_VALUE\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"PPS_BONUS_VALUE_AWDED\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"DEF_FSC_CR_IND\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"PPS_PROMO_FLG\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"PAX_ID\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"TRANS_TKT_NO\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"DOLLAR_VAL_IN_SGD\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"AMT_PAID_IN_CASH\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"TOT_AMT_PAID\" jdbcType=\"NUMERIC\" property=\"\"/>\n" +
                "        <result column=\"ACC_CR_IND\" jdbcType=\"VARCHAR\" property=\"\"/>\n" +
                "        <result column=\"TRANSACTION_TYPE\" jdbcType=\"VARCHAR\" property=\"\"/>";
        List<ResultMapModel> resultMapModelList = generateListOfColumnTitles(resultMap);
        System.out.println(resultMapModelList.size());
        printModifiedResultMap(resultMapModelList, resultMap.split("\n"));
        System.out.println("-----------------------------------------\n");
        String sqlStatement = "SELECT AT_TRANS.RCRE_DT, AT_TRANS.BATCH_DT,AT_TRANS.TRANS_CD,AT_TRANS.PRT_CD,\n" +
                "        AT_TRANS.FLT_AWD_DT,AT_TRANS.PTS_AWDED,NVL(AT_TRANS.AWD_DESC,' '),\n" +
                "        NVL(AT_TRANS.FLT_NO,0),NVL(AT_TRANS.ORG_CD,' '),NVL(AT_TRANS.DES_CD,' '),\n" +
                "        NVL(AT_TRANS.TRVL_CLS,' '),NVL(AT_TRANS.DISTANCE_TRVL,0), ' ',\n" +
                "        AT_TRANS.FFP_BUCKET_FLG,AT_TRANS.ELITE_BUCKET_FLG,AT_TRANS.PPS_BUCKET_FLG,\n" +
                "        AT_TRANS.SECT_CNT,NVL(AT_TRANS.AMDMNT_RSN_CD,' '), NVL(AT_TRANS.OFFP_PRT_CD, ' '),\n" +
                "        NVL(AT_TRANS.OFFP_MBR_NO, ' '),' ', ' ', 0,\n" +
                "        NVL(PRT.ANA_IND, 'A') ANA_IND,\n" +
                "        NVL(AT_TRANS.PROMO_XREF_ID, ' '),AT_TRANS.PPS_VAL, AT_TRANS.CR_SRC_IND,\n" +
                "        AT_TRANS.INTERLINE_IND, NVL(PPS_XREF_ID, 0), NVL(PPS_ONHOLD_FLG, ' ')\n" +
                "        , NVL(OD_XREF_LINK.TRANS_XREF_ID, ' '),NVL(BILL_TO_PRT, ' '),\n" +
                "        NVL(CD_SHARE_PRT, ' '), NVL(CD_SHARE_FLT_NO, 0), NVL(TKT_NO, ' '),\n" +
                "        NVL(FAMILY_NAME, ' '),NVL(GIVEN_NAME, ' '), null, ' ',' ', 0, NVL(INPUT_MODE_IND, ' '),\n" +
                "        NVL(PRT_REF_CD, ' '),NVL(BATCH_ID, ' '),NVL(REVERSED_FLG, ' '),\n" +
                "        NVL(NAME_MISMATCH_FLG, ' '),REFERENCE_CD.REF_DESC, NVL(CPS_ID_CR, ' '),\n" +
                "        AT_TRANS.FSC_VALUE, 0, AT_TRANS.DEF_FSC_CR_IND, ' ', NVL(AT_TRANS.PAX_ID, ' '),\n" +
                "        NVL(AT_TRANS.TKT_NO, ' '), AT_TRANS.DOLLAR_VAL_IN_SGD, AT_TRANS.AMT_PAID_IN_CASH,\n" +
                "        AT_TRANS.TOT_AMT_PAID, AT_TRANS.ACC_CR_IND, 'Air_Transaction'";
        String sql2 = "SELECT NAT_TRANS.RCRE_DT,NAT_TRANS.BATCH_DT,NAT_TRANS.TRANS_CD,\n" +
                "        NAT_TRANS.PRT_CD,NAT_TRANS.AWD_DT,NAT_TRANS.PTS_AWDED,\n" +
                "        NVL(NAT_TRANS.AWD_DESC,' '), 0, ' ', ' ', ' ', 0,\n" +
                "        NVL(NAT_TRANS.PROP_LOC_CD, ' '), 'Y', 'N', 'N', 0,\n" +
                "        NVL(NAT_TRANS.AMDMNT_RSN_CD,' '), ' ', ' ', ' ', ' ',\n" +
                "        0,\n" +
                "        NVL(PRT.ANA_IND, 'A') ANA_IND, NVL(NAT_TRANS.PROMO_XREF_ID, ' '),\n" +
                "        NAT_TRANS.PPS_VAL, ' ', ' ', 0, ' '\n" +
                "        ,NVL(OD_XREF_LINK.TRANS_XREF_ID, ' '), ' ', ' ', 0, ' ',\n" +
                "        NVL(FAMILY_NAME, ' '), NVL(GIVEN_NAME, ' '),CHK_OUT_DT,\n" +
                "        NVL(TOUR_CD, ' '), NVL(SERIES, ' '),NVL(TOUR_AMT, 0),\n" +
                "        NVL(INPUT_MODE_IND, ' '),NVL(PRT_REF_CD, ' '),\n" +
                "        NVL(BATCH_ID, ' '), NVL(REVERSED_FLG, ' '),\n" +
                "        NVL(NAME_MISMATCH_FLG, ' '), ' ', ' ', 0, 0, ' ', ' ', ' ', ' ', 0, 0, 0,\n" +
                "        ' ', 'Non_Air_Transaction'";
        String sql3 = "SELECT PROMO_TRANS.RCRE_DT, PROMO_TRANS.BATCH_DT, PROMO_TRANS.TRANS_CD,\n" +
                "        PROMO_TRANS.PRT_CD, PROMO_TRANS.PROMO_AWD_DT, PROMO_TRANS.BONUS_AWDED ,\n" +
                "        NVL(PROMO_TRANS.PROMO_AWD_DESC,' '),0, ' ', ' ', ' ', 0, ' ',\n" +
                "        'Y','N','N',0,NVL(PROMO_TRANS.AMDMNT_RSN_CD,' '),' ', ' ',\n" +
                "        NVL(PROMO_TRANS.PROMO_CD,' '),\tNVL(PROMO_TRANS.PROMO_AWD_DESC,' '),\n" +
                "        PROMO_TRANS.ELITE_BONUS_MILES_AWDED, NVL(PRT.ANA_IND, 'A') ANA_IND,\n" +
                "        NVL(PROMO_TRANS.PROMO_XREF_ID, ' '), 0, ' ', ' ', 0, ' '\n" +
                "        , NVL(OD_XREF_LINK.TRANS_XREF_ID, ' '),NVL(BILL_TO_PRT, ' '),\n" +
                "        NVL(CD_SHARE_PRT, ' '), 0, NVL(TKT_NO, ' '),NVL(FAMILY_NAME, ' '),\n" +
                "        NVL(GIVEN_NAME, ' '), null, ' ', ' ', 0, ' ', NVL(PRT_REF_CD, ' '),\n" +
                "        NVL(BATCH_ID, ' '), NVL(REVERSED_FLG, ' '), NVL(NAME_MISMATCH_FLG, ' '),\n" +
                "        ' ', ' ', 0, PROMO_TRANS.PPS_BONUS_VALUE_AWDED, ' ',\n" +
                "        PROMO_TRANS.PPS_PROMO_FLG, ' ', ' ', 0, 0, 0, ' ', 'Promotion'";
        String[] sqlArr = new String[]{sqlStatement, sql2, sql3};
        addAsStatementToSqlString(sqlArr, resultMapModelList);
    }

    private static List<ResultMapModel> generateListOfColumnTitles(String resultMap) {
        String[] sentenceArr = resultMap.split("\n");
        List<ResultMapModel> resultMapModelList = new ArrayList<>();
        for(String sentence : sentenceArr) {
            // get the column name
            int startIndex = sentence.trim().indexOf("\"") + 1;
            int endIndex = sentence.trim().indexOf("\"",startIndex);
            ResultMapModel resultMapModel = new ResultMapModel();
            resultMapModel.columnName = sentence.trim().substring(startIndex,endIndex);
            // get the fieldName
            resultMapModel.fieldName = generateFieldName(resultMapModel.columnName);
            // get the type
            startIndex = sentence.trim().indexOf("\"", endIndex+1)+1;
            endIndex = sentence.trim().indexOf("\"",startIndex);
            resultMapModel.type = sentence.trim().substring(startIndex,endIndex);
            resultMapModel.fieldType = generateFieldType(resultMapModel.type);
            resultMapModelList.add(resultMapModel);
        }
        return resultMapModelList;
    }

    private static String generateFieldName(String columnName) {
        String[] splitField = columnName.toLowerCase().split("_");
        String fieldName = "";
        for(int i=0; i<splitField.length; i++) {
            if(i == 0) {
                fieldName += splitField[0];
            }else{
                fieldName += splitField[i].substring(0,1).toUpperCase();
                fieldName += splitField[i].substring(1);
            }
        }
        return fieldName;
    }

    private static String generateFieldType(String type) {
        String fieldType = null;
        if(type.equals("VARCHAR")){
            fieldType = "String";
        } else if (type.equals("TIMESTAMP")) {
            fieldType = "Date";
        } else if (type.equals("NUMERIC")) {
            fieldType = "long";
        } else {
            throw new RuntimeException("Wrong type provided");
        }
        return fieldType;
    }

    private static void printModifiedResultMap(List<ResultMapModel> resultMapModelList, String[] sentences) {
        if(resultMapModelList.size() != sentences.length) {
            throw new RuntimeException("Different length detected");
        }
        for(int i=0; i<sentences.length; i++) {
            ResultMapModel resultMapModel = resultMapModelList.get(i);
            String toPrint = String.format("private %s %s;", resultMapModel.fieldType, resultMapModel.fieldName);
            System.out.println(toPrint);
        }
        System.out.println("----------------------------------------\n");
        for(int i=0; i<sentences.length; i++) {
            ResultMapModel resultMapModel = resultMapModelList.get(i);
            String toPrint = String.format("<result column=\"%s\" jdbcType=\"%s\" property=\"%s\"/>",
                    resultMapModel.columnName, resultMapModel.type, resultMapModel.fieldName);
            System.out.println(toPrint);
        }
    }

    private static void addAsStatementToSqlString(String[] sqlArr, List<ResultMapModel> resultMapModelList) {
        for(int i=0; i<sqlArr.length; i++) {
            String toPrint = "";
            List<String> sqlSplitByComma = splitSqlByComma(sqlArr[i]);
            for(int j=0; j<sqlSplitByComma.size(); j++){
                toPrint += sqlSplitByComma.get(j);
                toPrint += " as ";
                toPrint += resultMapModelList.get(j).columnName;
                toPrint += ",";
            }
            System.out.println(toPrint);
            System.out.println("--------------------------------\n");
        }
    }

    private static List<String> splitSqlByComma(String sql) {
        List<String> list = new ArrayList<>();
        boolean toSkipComma = false;
        String item = "";
        for(int i=0; i<sql.toCharArray().length; i++) {
            String c = sql.substring(i,i+1);
            if(c.equals("(")) {
                toSkipComma = true;
            }
            if(c.equals(")")) {
                toSkipComma = false;
            }
            if(!c.equals(",")) {
                item += c;
            } else {
                // char is comma
                if(toSkipComma) {
                    item += c;
                } else {
                    list.add(item);
                    item = "";
                }
            }
        }
        list.add(item);
        return list;
    }
}
