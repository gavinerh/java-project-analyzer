import MARMSUI.model.ResultMapModel;

import java.util.ArrayList;
import java.util.List;

public class ResultMapToVoFieldsAndPopulateAs {
    public static void main(String[] args) {
        String resultMap = "<result column=\"general_identifier\" jdbcType=\"VARCHAR\" />\n" +
                "        <result column=\"TOT_PTS\" jdbcType=\"NUMERIC\" />\n" +
                "        <result column=\"EXP_DT\" jdbcType=\"TIMESTAMP\" />\n" +
                "        <result column=\"NEW_EXP_DT_1\" jdbcType=\"TIMESTAMP\" />";
        List<ResultMapModel> resultMapModelList = generateListOfColumnTitles(resultMap);
        System.out.println(resultMapModelList.size());
        printModifiedResultMap(resultMapModelList, resultMap.split("\n"));
        System.out.println("-----------------------------------------\n");
        String sqlStatement = "select 'AIR_REDEMPTION' as TRANSACTION_TYPE, TRANS_CD as TRANS_CD, A.RCRE_DT as TRANS_DATE,A.APPROVAL_CD as APPROVAL_CD,  A.ADJ_APPROVAL_CD as ADJ_APPROVAL_CD,\n" +
                "        NET_PTS_REQ as NET_PTS_REQ,  ACTION_CD as ACTION_CD,\n" +
                "        TKT_MCO_NO as TKT_MCO_NO,  RDPN_TYPE as RDPN_TYPE,  AWD_ZONE as AWD_ZONE, B.PROMO_SAVINGS  as PROMO_SAVINGS,\n" +
                "        BILLING_PRT as BILLING_PRT, A.RCRE_PCC as RCRE_PCC, A.RCRE_SALES_OFF as RCRE_SALES_OFF,\n" +
                "        A.RCRE_AGENT_ID as RCRE_AGENT_ID, TKT_STOCK1 as TKT_STOCK1,  C.ITIN_XREF_ID  as ITIN_XREF_ID,  PNR_NAME as PNR_NAME,\n" +
                "        ACTION_CD2 as ACTION_CD2, PNR_REF as PNR_REF, TURNPTS_BOARD as TURNPTS_BOARD,\n" +
                "        TKT_SRC_IND as TKT_SRC_IND, AWD_TYPE as AWD_TYPE, TKT_VALIDITY_DT AS TKT_VALIDITY_DT,\n" +
                "        B.RDPN_NET_PTS_REQ AS RDPN_NET_PTS_REQ, FORFEIT_PTS as FORFEIT_PTS, B.TRANS_PTS as TRANS_PTS,\n" +
                "        REVERSED_FLG as REVERSED_FLG, D.PROMO_CD as PROMO_CD, PROMO_NAME as PROMO_NAME, BATCH_ID as BATCH_ID,\n" +
                "        BATCH_DT as BATCH_DATE, TOT_STOPOVER_PTS as TOT_STOPOVER_PTS, C.CERTIFICATE_NUMBER  as CERTIFICATE_NUMBER ,\n" +
                "        A.MMK_IND as MMK_IND, A.PYMT_RFND_LC as PYMT_RFND_LC, A.TOTAL_FARE_IN_LC as TOTAL_FARE_IN_LC,\n" +
                "        A.FARE_WO_TAX_IN_LC as FARE_WO_TAX_IN_LC, A.TAX_IN_LC as TAX_IN_LC, A.NET_FARE_PAID_IN_LC as NET_FARE_PAID_IN_LC,\n" +
                "        A.NET_KF_MILES_VAL_IN_LC as NET_KF_MILES_VAL_IN_LC,   A.TRANS_FARE_PAID_IN_LC as TRANS_FARE_PAID_IN_LC,\n" +
                "        A.TRANS_KF_MILES_VAL_IN_LC as TRANS_KF_MILES_VAL_IN_LC, A.TOTAL_FARE_IN_SGD as TOTAL_FARE_IN_SGD,\n" +
                "        A.FARE_WO_TAX_IN_SGD as FARE_WO_TAX_IN_SGD, A.TAX_IN_SGD as TAX_IN_SGD, A.NET_FARE_PAID_IN_SGD as NET_FARE_PAID_IN_SGD,\n" +
                "        A.NET_KF_MILES_VAL_IN_SGD as NET_KF_MILES_VAL_IN_SGD,\n" +
                "        A.TRANS_FARE_PAID_IN_SGD as TRANS_FARE_PAID_IN_SGD, A.TRANS_KF_MILES_VAL_IN_SGD as TRANS_KF_MILES_VAL_IN_SGD,\n" +
                "        A.NET_TAX_PAID_IN_LC as NET_TAX_PAID_IN_LC, A.NET_TAX_PAID_IN_SGD as NET_TAX_PAID_IN_SGD,\n" +
                "        A.ORIG_CURRENCY_CD as ORIG_CURRENCY_CD, A.initial_action_cd as INITIAL_ACTION_CD, A.rfic_cd as RFIC_CD, A.rfic_desc as RFIC_DESC,\n" +
                "        case when TRANS_CD = 'ED' or TRANS_CD = 'EC' or TRANS_CD = 'FD' or TRANS_CD = 'FC' then TKT_MCO_NO\n" +
                "        else null end as EMD,\n" +
                "        case when TRANS_CD = 'ED' or TRANS_CD = 'EC' or TRANS_CD = 'FD' or TRANS_CD = 'FC' then null\n" +
                "        else TKT_MCO_NO end as TICKET_NUMBER,\n" +
                "        qBkt.NORMAL_PTS as NORMAL_PTS,\n" +
                "        qBkt.EXTENDED_PTS as EXTENDED_PTS, qBkt.BUCKET_DT as EXPIRY_DT, flt.CARRIER_CD as CARRIER_CD, flt.FLT_NO as FLT_NO,\n" +
                "        flt.SUB_CLASS as SUB_CLASS, flt.FLT_DEP_DT_TIME as FLT_DEP_DT, flt.FLT_ARR_DT_TIME as FLT_ARR_DT, flt.OFF_PT as DESTINATION_CD,\n" +
                "        flt.BOARD_PT as ORIGIN_CD, flt.CONNECTION_IND as TRANSIT_IND, distPnr.dist_pnr as DIST_PNR_FLG";

        String[] sqlArr = new String[]{sqlStatement};
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
        } else if (type.equals("DOUBLE")){
            fieldType = "double";
        }else {
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
            System.out.println("@JsonInclude(JsonInclude.Include.NON_EMPTY)");
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
                try{
                    toPrint += sqlSplitByComma.get(j);
                    toPrint += " as ";
                    toPrint += resultMapModelList.get(j).columnName;
                    toPrint += ",";
                } catch (Exception e){
                    System.out.println("error");
                }

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
