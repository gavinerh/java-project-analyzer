package MARMSUI;

public class GenerateSqlFromBuffer {
    public static void main(String[] args) {
        StringBuffer buffer = new StringBuffer();
        StringBuffer query = new StringBuffer();
        String internalID = "8987015556";
        StringBuffer sqlBuilder = new StringBuffer();
        buffer.append( "INSERT INTO menu_functions ( " );
        buffer.append( "RCRE_USER_ID, RCRE_DT, MENU_ID, FUNC_FIELD_ID, ");
        buffer.append( "FUNC_FIELD_DESC, SUPERVISOR_FLG ) ");
        buffer.append( " values ( ?, sysdate, ?, ?, ?, ? )" );
        String paramName = "menuFunc";
        String[] arrToReplace = {"createUserId", "menuId", "functionFieldId", "functionFieldDescription", "supervisorFlg"};

        String toPrint = null;
        if(true) {
            toPrint = replaceQuestionMark(buffer.toString(),arrToReplace,paramName);
        } else {
            System.out.println(buffer.toString());
            System.out.println(query.toString());
            System.out.println(sqlBuilder.toString());
        }

        System.out.println(toPrint);
    }

    private static String replaceQuestionMark(String rawSql, String[] arrToReplace, String paramName) {
        String finalString = "";
        int arrCount = 0;
        for(int i=0; i<rawSql.length(); i++) {
            String charac = rawSql.substring(i,i+1);
            if(charac.equals("?")) {
                charac = String.format("#{%s.%s,jdbcType=VARCHAR}", paramName,arrToReplace[arrCount]);
                arrCount++;
            }
            finalString += charac;
        }
        return finalString;
    }
}
