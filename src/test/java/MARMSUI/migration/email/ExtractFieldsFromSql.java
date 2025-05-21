package MARMSUI.migration.email;

import java.util.*;

public class ExtractFieldsFromSql {
    public static void main(String[] args) {
        String val = "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10006','CUSUPD');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10007','ACSUMRY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10008','CUSUPD');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10013','RDPTGRPNE');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10018','MILEEXT');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10020','CUSPWDRSET');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10021','MILEEXT');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10022','PAXMENQ');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10025','PAXMENQ');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10028','PAXMENQ');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10029','SVCLOG');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10032','CARDISSUEHIST');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10033','CARDISSUE');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10034','PPSGIFTMAGDSTR');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10040','CUSUPD');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10043','OTHCOLLATERAL');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10044','CUSUPD');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10045','CUSUPD');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10046','OTHCOLLATERAL');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10049','CUSUPD');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10052','CUSUPD');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10055','CUSUPD');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10056','RETROENQ');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10058','CUSUPD');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10060','ACSUMRY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10061','ACSUMRY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10062','RETROENQ');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10064','SVCRCVY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10066','SVCRCVY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10067','DGTLVCHR');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10069','DGTLVCHR');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10071','DGTLVCHR');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10072','DGTLVCHR');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10073','DGTLVCHR');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10074','ACSUMRY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10076','SVCRCVY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10080','ACSUMRY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10081','ACSUMRY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10082','ACSUMRY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10083','ACSUMRY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10084','ACSUMRY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10088','SVCRCVY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10090','SVCRCVY');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10092','FORCEQUAL');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10094','FORCEQUAL');\n" +
                "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'10096','SVCRCVY');";
        String tableName = "MRM.USR_API_ACL";
//        String columnToExtract = "email_subject";
//        extractColumnValue();
        printUpdateScripts(val,tableName, false);

    }

    private static void printUpdateScripts(String sqlString, String tableName, boolean isRollforward) {
        Set<String> apiCds = new HashSet<>();
        Set<String> menuIds = new HashSet<>();
        for(String row : sqlString.split("\n")) {
            Map<String,String> map = evaluateSqlAndExtract(row);
            String menuId = map.get("menu_id").replaceAll("'","");
            String newMenuId = transformMenuId(menuId);

            String template = "UPDATE %s SET MENU_ID = '%s', LCHG_DT = SYSDATE, LCHG_USER_ID = %s where API_CD = %s and MENU_ID = '%s';";
            String apiCd = map.get("api_cd");
            String rcreUserId = map.get("rcre_user_id");
            if(!apiCds.contains(apiCd)) {
                apiCds.add(apiCd);
            } else {
                throw new RuntimeException("Api code is repeated");
            }
            menuIds.add(menuId);
            if(isRollforward) {
                System.out.println(String.format(template,tableName,newMenuId,rcreUserId,apiCd,menuId));
            } else {
                System.out.println(String.format(template, tableName, menuId, rcreUserId, apiCd, newMenuId));
            }
        }
        System.out.println();
        for(String menu : menuIds) {
            System.out.print(String.format("'%s',",menu));
        }
        System.out.println("\nNo of menu ids: " + menuIds.size());
    }

    private static String transformMenuId(String old) {
        String addition = "NM_";
        if(old.length() > 12) {
            return addition + old.substring(0,old.length()-3);
        } else {
            return addition + old;
        }
    }


    private static void extractColumnValue() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter sql:\n");
            if(insideEachSql(scanner).equals("x")) {
                return;
            }
        }
    }

    private static String insideEachSql(Scanner scanner) {
        String sql = scanner.nextLine();
        Map<String,String> keyValues = evaluateSqlAndExtract(sql);
        if(keyValues == null){
            return "c";
        }
        for(String key : keyValues.keySet()) {
            System.out.println(String.format("%s -- %s", key, keyValues.get(key)));
        }
        return "c";
    }


    private static Map<String,String> evaluateSqlAndExtract(String sql) {
        int start = sql.indexOf("(");
        if(start == -1) {
            System.out.println("First set of open brackets not found");
            return null;
        }
        start += 1;
        int end = sql.indexOf(")",start+1);
        if(end == -1) {
            System.out.println("First set of close brackets not found");
            return null;
        }
        String columnNames = sql.substring(start,end);
        start = sql.indexOf("(", end + 1);
        if(start == -1) {
            System.out.println("Second set of open brackets not found");
            return null;
        }
        start += 1;
        end = sql.indexOf(")", start + 1);
        if(end == -1) {
            System.out.println("Second set of close brackets not found");
            return null;
        }
        String columnValues = sql.substring(start,end);
        String[] columnNameArr = extractValue(columnNames);
        String[] columnValueArr = extractValue(columnValues);
        if(columnValueArr.length != columnNameArr.length) {
            System.out.println("Column count not the same as value count");
            return null;
        }
        Map<String,String> map = new HashMap<>();
        for(int i=0; i<columnValueArr.length; i++) {
            map.put(columnNameArr[i].toLowerCase(),columnValueArr[i]);
        }
        return map;
    }

    private static String[] extractValue(String val) {
        String[] arr = val.split(",");
        for(int i=0; i<arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }
}
