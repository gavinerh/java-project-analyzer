package MARMSUI;

import MARMSUI.model.ResultMapModel;

import java.util.ArrayList;
import java.util.List;

public class GenerateStringFormatFromResultMap {
    public static void main(String[] args) {
        String resultMap = "<result column=\"TRANSACTION_TYPE\" jdbcType=\"VARCHAR\" property=\"transactionType\"/>\n" +
                "        <result column=\"TRANS_CD\" jdbcType=\"VARCHAR\" property=\"transCd\"/>\n" +
                "        <result column=\"TRANS_DATE\" jdbcType=\"TIMESTAMP\" property=\"transDate\"/>\n" +
                "        <result column=\"APPROVAL_CD\" jdbcType=\"VARCHAR\" property=\"approvalCd\"/>\n" +
                "        <result column=\"ADJ_APPROVAL_CD\" jdbcType=\"VARCHAR\" property=\"adjApprovalCd\"/>\n" +
                "        <result column=\"NET_PTS_REQ\" jdbcType=\"NUMERIC\" property=\"netPtsReq\"/>\n" +
                "        <result column=\"ACTION_CD\" jdbcType=\"VARCHAR\" property=\"actionCd\"/>\n" +
                "        <result column=\"TKT_MCO_NO\" jdbcType=\"VARCHAR\" property=\"tktMcoNo\"/>\n" +
                "        <result column=\"RDPN_TYPE\" jdbcType=\"VARCHAR\" property=\"rdpnType\"/>\n" +
                "        <result column=\"AWD_ZONE\" jdbcType=\"VARCHAR\" property=\"awdZone\"/>\n" +
                "        <result column=\"PROMO_SAVINGS\" jdbcType=\"NUMERIC\" property=\"promoSavings\"/>\n" +
                "        <result column=\"BILLING_PRT\" jdbcType=\"VARCHAR\" property=\"billingPrt\"/>\n" +
                "        <result column=\"RCRE_PCC\" jdbcType=\"VARCHAR\" property=\"rcrePcc\"/>\n" +
                "        <result column=\"RCRE_SALES_OFF\" jdbcType=\"VARCHAR\" property=\"rcreSalesOff\"/>\n" +
                "        <result column=\"RCRE_AGENT_ID\" jdbcType=\"VARCHAR\" property=\"rcreAgentId\"/>\n" +
                "        <result column=\"TKT_STOCK1\" jdbcType=\"VARCHAR\" property=\"tktStock1\"/>\n" +
                "        <result column=\"ITIN_XREF_ID\" jdbcType=\"VARCHAR\" property=\"itinXrefId\"/>\n" +
                "        <result column=\"PNR_NAME\" jdbcType=\"VARCHAR\" property=\"pnrName\"/>\n" +
                "        <result column=\"ACTION_CD2\" jdbcType=\"VARCHAR\" property=\"actionCd2\"/>\n" +
                "        <result column=\"PNR_REF\" jdbcType=\"VARCHAR\" property=\"pnrRef\"/>\n" +
                "        <result column=\"TURNPTS_BOARD\" jdbcType=\"VARCHAR\" property=\"turnptsBoard\"/>\n" +
                "        <result column=\"TKT_SRC_IND\" jdbcType=\"VARCHAR\" property=\"tktSrcInd\"/>\n" +
                "        <result column=\"AWD_TYPE\" jdbcType=\"VARCHAR\" property=\"awdType\"/>\n" +
                "        <result column=\"TKT_VALIDITY_DT\" jdbcType=\"TIMESTAMP\" property=\"tktValidityDt\"/>\n" +
                "        <result column=\"RDPN_NET_PTS_REQ\" jdbcType=\"NUMERIC\" property=\"rdpnNetPtsReq\"/>\n" +
                "        <result column=\"FORFEIT_PTS\" jdbcType=\"NUMERIC\" property=\"forfeitPts\"/>\n" +
                "        <result column=\"TRANS_PTS\" jdbcType=\"NUMERIC\" property=\"transPts\"/>\n" +
                "        <result column=\"REVERSED_FLG\" jdbcType=\"VARCHAR\" property=\"reversedFlg\"/>\n" +
                "        <result column=\"PROMO_CD\" jdbcType=\"VARCHAR\" property=\"promoCd\"/>\n" +
                "        <result column=\"PROMO_NAME\" jdbcType=\"VARCHAR\" property=\"promoName\"/>\n" +
                "        <result column=\"BATCH_ID\" jdbcType=\"VARCHAR\" property=\"batchId\"/>\n" +
                "        <result column=\"BATCH_DATE\" jdbcType=\"TIMESTAMP\" property=\"batchDate\"/>\n" +
                "        <result column=\"TOT_STOPOVER_PTS\" jdbcType=\"NUMERIC\" property=\"totStopoverPts\"/>\n" +
                "        <result column=\"CERTIFICATE_NUMBER\" jdbcType=\"VARCHAR\" property=\"certificateNumber\"/>\n" +
                "        <result column=\"MMK_IND\" jdbcType=\"VARCHAR\" property=\"mmkInd\"/>\n" +
                "        <result column=\"PYMT_RFND_LC\" jdbcType=\"VARCHAR\" property=\"pymtRfndLc\"/>\n" +
                "        <result column=\"TOTAL_FARE_IN_LC\" jdbcType=\"NUMERIC\" property=\"totalFareInLc\"/>\n" +
                "        <result column=\"FARE_WO_TAX_IN_LC\" jdbcType=\"NUMERIC\" property=\"fareWoTaxInLc\"/>\n" +
                "        <result column=\"TAX_IN_LC\" jdbcType=\"NUMERIC\" property=\"taxInLc\"/>\n" +
                "        <result column=\"NET_FARE_PAID_IN_LC\" jdbcType=\"NUMERIC\" property=\"netFarePaidInLc\"/>\n" +
                "        <result column=\"NET_KF_MILES_VAL_IN_LC\" jdbcType=\"NUMERIC\" property=\"netKfMilesValInLc\"/>\n" +
                "        <result column=\"TRANS_FARE_PAID_IN_LC\" jdbcType=\"NUMERIC\" property=\"transFarePaidInLc\"/>\n" +
                "        <result column=\"TRANS_KF_MILES_VAL_IN_LC\" jdbcType=\"NUMERIC\" property=\"transKfMilesValInLc\"/>\n" +
                "        <result column=\"TOTAL_FARE_IN_SGD\" jdbcType=\"NUMERIC\" property=\"totalFareInSgd\"/>\n" +
                "        <result column=\"FARE_WO_TAX_IN_SGD\" jdbcType=\"NUMERIC\" property=\"fareWoTaxInSgd\"/>\n" +
                "        <result column=\"TAX_IN_SGD\" jdbcType=\"NUMERIC\" property=\"taxInSgd\"/>\n" +
                "        <result column=\"NET_FARE_PAID_IN_SGD\" jdbcType=\"NUMERIC\" property=\"netFarePaidInSgd\"/>\n" +
                "        <result column=\"NET_KF_MILES_VAL_IN_SGD\" jdbcType=\"NUMERIC\" property=\"netKfMilesValInSgd\"/>\n" +
                "        <result column=\"TRANS_FARE_PAID_IN_SGD\" jdbcType=\"NUMERIC\" property=\"transFarePaidInSgd\"/>\n" +
                "        <result column=\"TRANS_KF_MILES_VAL_IN_SGD\" jdbcType=\"NUMERIC\" property=\"transKfMilesValInSgd\"/>\n" +
                "        <result column=\"NET_TAX_PAID_IN_LC\" jdbcType=\"NUMERIC\" property=\"netTaxPaidInLc\"/>\n" +
                "        <result column=\"NET_TAX_PAID_IN_SGD\" jdbcType=\"NUMERIC\" property=\"netTaxPaidInSgd\"/>\n" +
                "        <result column=\"ORIG_CURRENCY_CD\" jdbcType=\"VARCHAR\" property=\"origCurrencyCd\"/>\n" +
                "        <result column=\"INITIAL_ACTION_CD\" jdbcType=\"VARCHAR\" property=\"initialActionCd\"/>\n" +
                "        <result column=\"RFIC_CD\" jdbcType=\"VARCHAR\" property=\"rficCd\"/>\n" +
                "        <result column=\"RFIC_DESC\" jdbcType=\"VARCHAR\" property=\"rficDesc\"/>\n" +
                "        <result column=\"EMD\" jdbcType=\"VARCHAR\" property=\"emd\"/>\n" +
                "        <result column=\"TICKET_NUMBER\" jdbcType=\"VARCHAR\" property=\"ticketNumber\"/>";
        List<ResultMapModel> resultMapModelList = generateListOfColumnTitles(resultMap);
        printStringFormat(resultMapModelList);
    }

    private static void printStringFormat(List<ResultMapModel> resultMapModelList) {
        String toPrint = "String.format(\"";
        int count1 = 0;
        int count2 = 0;
        for(ResultMapModel model : resultMapModelList) {
            if(model.fieldType.equalsIgnoreCase("long")){
                toPrint += "%d";
                count1++;
            } else {
                toPrint += "%s";
                count1++;
            }
        }
        toPrint += "\",";
        for(ResultMapModel model : resultMapModelList) {
            if(model.fieldType.equalsIgnoreCase("long")) {
                toPrint += model.fieldName;
                count2++;
            } else if(model.fieldType.equalsIgnoreCase("Date")) {
                toPrint += model.fieldName + ".toString()";
                count2++;
            } else {
                toPrint += model.fieldName;
                count2++;
            }
            toPrint += ",";
        }
        toPrint = toPrint.substring(0,toPrint.length() - 1) + ");";
        System.out.println(toPrint);
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
}
