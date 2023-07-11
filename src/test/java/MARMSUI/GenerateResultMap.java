package MARMSUI;

public class GenerateResultMap {
    public static void main(String[] args) {
        String[] typesArr = new String[]{"VARCHAR","TIMESTAMP","VARCHAR","NUMERIC","VARCHAR","NUMERIC","VARCHAR","VARCHAR","TIMESTAMP","VARCHAR","VARCHAR","NUMERIC","VARCHAR","TIMESTAMP",                                       "VARCHAR","VARCHAR",    "VARCHAR",     "VARCHAR","VARCHAR","VARCHAR","VARCHAR","VARCHAR","TIMESTAMP"  ,"VARCHAR"  ,     "VARCHAR" ,    "VARCHAR","VARCHAR"};
        String[] columnsArr = new String[]{"TRANS_CD", "RCRE_DT","APPROVAL_CD","NET_PTS_REQ","ACTION_CD","PROMO_SAVINGS","REVERSED_FLG","BATCH_ID","BATCH_DT","EVENT_ID","NA_RDPN_ID","UNIT_REDEEM","NO_OF_GUEST","FULFIL_DT","MEAL_TYPE","WINE_PREF","TRANSPORT_OPT","REMARK","DRESS_CD","VENUE","EVENT_CD","CATEGORY_CD","FULFIL_DT_1","EVENT_DESC_1","EVENT_DESC_2","EVENT_NAME", "EVENT_SUBTYPE"};
//        for(int i=0; i<typesArr.length; i++){
//            String toPrint = String.format("<result column=\"%s\" jdbcType=\"%s\" />",
//                    columnsArr[i],typesArr[i]);
//            System.out.println(toPrint);
//        }
        // print sql statement for mybatis
        String combinedString = "";
//        for(int i=0; i<typesArr.length; i++) {
//            String type = typesArr[i];
//            String toPrint = "";
//            if(typesArr[i].equals("TIMESTAMP")){
//                type = "VARCHAR";
//                toPrint = String.format("trunc(%s) = to_date(#{%s,jdbcType=VARCHAR}, 'YYYY-MM-DD') and ",columnsArr[i],generateFieldName(columnsArr[i]),type);
//            } else {
//                toPrint = String.format("%s = #{%s,jdbcType=%s} and ",columnsArr[i],generateFieldName(columnsArr[i]),type);
//            }
//            combinedString += toPrint;
//        }
//        System.out.println(combinedString);
        // print the sql statement for sql developer
        combinedString = "";
        for(int i=0; i<typesArr.length; i++) {
            String type = typesArr[i];
            if(type.equals("TIMESTAMP")){
                type = "VARCHAR";
                String toPrint = String.format("trunc(%s) = to_date('?', 'YYYY-MM-DD') and ", columnsArr[i]);
                combinedString += toPrint;
            } else {
                String toPrint = String.format("%s = ? and ",columnsArr[i]);
                combinedString += toPrint;
            }
        }
        System.out.println(combinedString);
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
}
