package MARMSUI.migration.email;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExtractFieldsFromSql {
    public static void main(String[] args) {
        String val = "INSERT into EMAIL_CNT_MSTR (rcre_user_id, rcre_dt, lchg_user_id, lchg_dt, email_content_id, email_from_addr,email_replyto_addr, email_subject, email_htmlfile_name, email_textfile_name, email_body_format,email_desc,use_gm_data_flg,cmt_migrated,recipient_type,block_email) VALUES ('MARMS', sysdate, null, null, 'NM_prfupPSID', 'KrisFlyer_Services@singaporeair.com', 'no-reply@email.singaporeair.com', 'Profil Anda telah diperbarui', 'profileupdate_PPS2021_ID_v2.html', 'profileupdate.txt', '1', 'Email for Notification on Profile Changes for KF members', 'N', 'N', 'B', null);";
        String tableName = "email_cnt_mstr";
//        String columnToExtract = "email_subject";
        extractColumnValue();
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
