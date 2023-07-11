import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ResultMapToVoFieldsAndPopulateAs {
    public static void main(String[] args) {
        String resultMap = "<result column=\"TRANS_CD\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"RCRE_DT\" jdbcType=\"TIMESTAMP\" />\n" +
                "<result column=\"APPROVAL_CD\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"NET_PTS_REQ\" jdbcType=\"NUMERIC\" />\n" +
                "<result column=\"ACTION_CD\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"PROMO_SAVINGS\" jdbcType=\"NUMERIC\" />\n" +
                "<result column=\"REVERSED_FLG\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"BATCH_ID\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"BATCH_DT\" jdbcType=\"TIMESTAMP\" />\n" +
                "<result column=\"EVENT_ID\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"NA_RDPN_ID\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"UNIT_REDEEM\" jdbcType=\"NUMERIC\" />\n" +
                "<result column=\"NO_OF_GUEST\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"FULFIL_DT\" jdbcType=\"TIMESTAMP\" />\n" +
                "<result column=\"MEAL_TYPE\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"WINE_PREF\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"TRANSPORT_OPT\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"REMARK\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"DRESS_CD\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"VENUE\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"EVENT_CD\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"CATEGORY_CD\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"FULFIL_DT_1\" jdbcType=\"TIMESTAMP\" />\n" +
                "<result column=\"EVENT_DESC_1\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"EVENT_DESC_2\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"EVENT_NAME\" jdbcType=\"VARCHAR\" />\n" +
                "<result column=\"EVENT_SUBTYPE\" jdbcType=\"VARCHAR\" />";
        List<ResultMapModel> resultMapModelList = generateListOfColumnTitles(resultMap);
        System.out.println(resultMapModelList.size());
        printModifiedResultMap(resultMapModelList, resultMap.split("\n"));
        System.out.println("-----------------------------------------\n");
        String sqlStatement = "SELECT TRANS_CD, A.RCRE_DT,A.APPROVAL_CD, NET_PTS_REQ ,  ACTION_CD,  \n" +
                "B.PROMO_SAVINGS, REVERSED_FLG, BATCH_ID , BATCH_DT, \n" +
                "B.EVENT_ID,B.NA_RDPN_ID, UNIT_REDEEM,NO_OF_GUEST,\n" +
                "FULFIL_DT, MEAL_TYPE, WINE_PREF, TRANSPORT_OPT,\n" +
                "REMARK ,DRESS_CD , VENUE, EVENT_CD , CATEGORY_CD, \n" +
                "FULFIL_DT, EVENT_DESC_1, EVENT_DESC_2, EVENT_NAME,\n" +
                "EVNT_SUBTYPE";

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
