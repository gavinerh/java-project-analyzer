package MARMSUI.migration.email;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PopulatingOldDtlsWithNewDtls {
    static String emailDtlsData = "NMGYMLCONV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMGYMLCONV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMGYMLCONV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMGYMLCONV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMGYMLCONV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMGYMLCONV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMGYMLCONVPPS\t1\tCName\tCUS_PERS\tCORRES_NAME\tINT_ID\n" +
            "NMGYMLCONVPPS\t1\tMBRNO\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMGYMLCONVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMHAACMLCONV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMHAACMLCONV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMHAACMLCONV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMHAACMLCONV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMHAACMLCONV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMHACMCNVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMHACMCNVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMHACMCNVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMHACMCNVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMHACMCNVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMHMAMLCNV\t1\tcus_id\tcus_account\tCUS_ID\tCUS_ID\n" +
            "NMHMAMLCNV\t1\tfamily_name\tcus_pers\tfamily_name\tint_id\n" +
            "NMHMAMLCNV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMHMAMLCNV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMHMAMLCNV\t1\ttitle\tcus_pers\tTITLE\tint_id\n" +
            "NMHMAMLCNV\t1\tyear\tcus_pers\tto_char(sysdate, 'YYYY')\tint_id\n" +
            "NMHMAMLCNVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMHMAMLCNVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMHMAMLCNVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMHMAMLCNVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tCUS_ID\n" +
            "NMHMAMLCNVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMHMAMLCNVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMMLCONV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMMLCONV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMMLCONV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMMLCONV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMMLCONV\t1\tPartner_Programme_Name\tCUS_OFFP_INFO\tcase when ffp_prt_cd='GY' then ('') else ffp_mbr_no end\tINT_ID\n" +
            "NMMLCONV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMMLCONVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMMLCONVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMMLCONVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMMLCONVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMMLCONVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMMLCONVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMNTMLCONV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMNTMLCONV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMNTMLCONV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMNTMLCONV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMNTMLCONV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMNTMLCONV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMNTMLCONVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMNTMLCONVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMNTMLCONVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMNTMLCONVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMNTMLCONVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMNTMLCONVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMRUMLCNV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMRUMLCNV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMRUMLCNV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMRUMLCNV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMRUMLCNV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMRUMLCNV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMRUMLCNVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMRUMLCNVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMRUMLCNVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMRUMLCNVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMRUMLCNVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMRUMLCNVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMTCMLCNV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMTCMLCNV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMTCMLCNV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMTCMLCNV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMTCMLCNV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMTCMLCNV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMTCMLCNVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMTCMLCNVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMTCMLCNVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMTCMLCNVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMTCMLCNVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMTCMLCNVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMTKNFAIL\t1\treqBody\tcus_pers\tto_char(sysdate, 'YYYY')\tint_id\n" +
            "NMTKNFAIL\t1\tapiResponse\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMXOMLCONV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMXOMLCONV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMXOMLCONV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMXOMLCONV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMXOMLCONV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMXOMLCONV\t1\tMBRNO\tCUS_ACCOUNT\tCUS_ID\tCUS_ID\n" +
            "NMXOMLCONV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMXOMLCONVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMXOMLCONVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMXOMLCONVPPS\t1\tMBRNO\tCUS_ACCOUNT\tCUS_ID\tCUS_ID\n" +
            "NMXOMLCONVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMXOMLCONVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMXOMLCONVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMXOMLCONVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMYUCONVKF\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMYUCONVKF\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMYUCONVKF\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMYUCONVKF\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMYUCONVKF\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMYUCONVKF\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NMYUCONVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NMYUCONVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NMYUCONVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NMYUCONVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NMYUCONVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NMYUCONVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID";

    static String toConvert = "GYMLCONV-[MBRNO, Partner_Programme_Name, CName]\n" +
            "GYMLCONVPPS-[MBRNO, Partner_Programme_Name, CName]\n" +
            "HMAMLCNV-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "HMAMLCNVPPS-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "HAACMLCONV-[tier, year, title, family_name, cus_id]\n" +
            "XOMLCONV-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "TCMLCNV-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "NTMLCONV-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "MLCONV-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "YUCONVKF-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "RUMLCNV-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "HACMCNVPPS-[tier, year, title, family_name, cus_id]\n" +
            "TCMLCNVPPS-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "NTMLCONVPPS-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "MLCONVPPS-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "YUCONVPPS-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "RUMLCNVPPS-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n" +
            "XOMLCONVPPS-[year, Partner_Programme_Name, title, cus_id, tier, family_name]\n";

    public static void main(String[] args) {
        Map<String,String[]> mapOfPersonalizeNameToArr = new HashMap<>();
        String[] arr = emailDtlsData.split("\n");
        for(String row : arr) {
            String[] rowSplit = row.split("\t");
            String key = rowSplit[2];
            if(!mapOfPersonalizeNameToArr.containsKey(key)) {
                mapOfPersonalizeNameToArr.put(key,rowSplit);
            }
        }
        Map<String,Map<String,String[]>> toConvertMap = extractToConvert(toConvert); // map containing details for toConvert variable
        System.out.println(toConvertMap.size());
        // iterate the toConvertMap and populate the dtls values
        for(String key : toConvertMap.keySet()) {
            Map<String,String[]> values = toConvertMap.get(key);
            for(String innerKey : values.keySet()) {
                String[] innerVal = values.get(innerKey);
                innerVal[2] = innerKey;
                innerVal[0] = key;
                String[] oldArr = mapOfPersonalizeNameToArr.get(innerKey);
                if(oldArr == null) {
                    System.out.println("Reached here");
                }
                mapOldArrToNewArr(oldArr,innerVal,key);
            }
        }
        System.out.println(toConvertMap.size());
        printEmailDtlsRows(toConvertMap);
    }

    private static void printEmailDtlsRows(Map<String,Map<String,String[]>> convertMap) {
        String template = "%s\t%s\t%s\t%s\t%s\t%s";
        for(Map<String,String[]> innerMap : convertMap.values()) {
            for(String[] innerVal : innerMap.values()) {
                String temp = template;
                for(String val : innerVal) {
                    temp = temp.replaceFirst("%s",val);
                }
                System.out.println(temp);
            }
        }
    }

    private static void mapOldArrToNewArr(String[] old, String[] toBeUpdated, String contentId) {
        for(int i=0; i<toBeUpdated.length; i++) {
            if(i==0) {
                toBeUpdated[i] = contentId;
                continue;
            }
            if(i==2) {
                continue;
            }
            toBeUpdated[i] = old[i];
        }
    }

    private static Map<String,Map<String,String[]>> extractToConvert(String val) {
        String[] arr = val.split("\n");
        Map<String,Map<String,String[]>> toConvertMap = new HashMap<>();
        for(String row : arr) {
            String[] split = row.split("-");
            String key = split[0];
            String[] splitSecond = split[1].substring(1,split[1].length()-1).split(",");
            Map<String,String[]> innerMap = new HashMap<>();
            for(String personalizeName : splitSecond) {
                innerMap.put(personalizeName.trim(),new String[6]);
            }
            toConvertMap.put(key,innerMap);
        }
        return toConvertMap;
    }
}
