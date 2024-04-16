package MARMSUI;

public class generateSqlQueryFromPropertiesFile {
    public static void main(String[] args) {
        //
        String keyValuePair = "1B=PASSAGES\n" +
                "\n" +
                "#Added by vinod for MKP-92449 Shangri-La starts\n" +
                "GC=Golden Circle\n" +
                "#Added by vinod for MKP-92449 Shangri-La ends\n" +
                "#Added by vinod for Fliggy Starts\n" +
                "GY=Fliggy";


        String[] arr = keyValuePair.split("\n");
        String rcreUserId = "MARMS";
        String createdDate = "SYSDATE";
        String refRecType = "706"; // to change
        String modifiedStartQuery = "INSERT ALL \n";
        String queryEnd = "SELECT 1 FROM DUAL;";
        String tableName = "REFERENCE_CD";
        String modifiedTemplateForInsert = "INTO " + tableName + " (RCRE_USER_ID, RCRE_DT, LCHG_USER_ID, LCHG_DT, REF_REC_TYPE, REF_TYPE_DESC, REF_REC_CD, REF_REC_EXT, REF_DESC, REF_SEQ) VALUES %s\n";
        String templateForInsert = "('%s',%s,null,null,'%s',null,'%s',null,'%s',null)";
        for(int i=0; i<arr.length; i++){
            String keyValue = arr[i];
            if(keyValue.startsWith("#") || keyValue.trim().isEmpty()){
                continue;
            }
            String[] keyValueArr = keyValue.split("=");

            String toInsert = String.format(templateForInsert,rcreUserId,createdDate,refRecType,keyValueArr[0],keyValueArr[1]);
//            if(i != arr.length - 1) {
//                // add a comma
//                toInsert += ",\n";
//            }
            toInsert = String.format(modifiedTemplateForInsert, toInsert);
            modifiedStartQuery += toInsert;
        }
        modifiedStartQuery += queryEnd;
        System.out.println(modifiedStartQuery);
    }
}
