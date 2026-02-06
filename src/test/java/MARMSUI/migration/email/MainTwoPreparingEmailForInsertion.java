package MARMSUI.migration.email;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class MainTwoPreparingEmailForInsertion {
    static boolean avoidPrintingMstr = false;
    static boolean printValuesForRFC = true; // Todo: to change if appropriate
    static boolean useOldDtlsData = true; // Todo: to change if appropriate
    static String rcreUserID = "MARMS";
    static String sqlColumns = "EMAIL_CONTENT_ID\tEMAIL_FROM_ADDR\tEMAIL_REPLYTO_ADDR\tEMAIL_SUBJECT\tEMAIL_HTMLFILE_NAME\tEMAIL_TEXTFILE_NAME\tEMAIL_BODY_FORMAT\tEMAIL_DESC\tUSE_GM_DATA_FLG\tCMT_MIGRATED\tRECIPIENT_TYPE\tBLOCK_EMAIL";
    static String[] emailMstrDialog = new String[]{"Content ID", "From Address", "Reply To", "Recipient Type", "Subject", "HTML File Name", "Text File Name", "Body Format", "Use GM Data", "Is CMT Migrated", "Block Email", "Description"};
    static String[] emailDetailDialog = new String[]{"Content ID", "Seq No.", "Personalize Name", "Table Name", "Column Name", "Key Column Name"};
    // -1: use map to get new content id, 0: take from prod, 1: take from uat, sequence follows the sqlColumns variable
    static int[] dataExtractorSwitch = new int[]{-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    // dataType refer to the dialog page fields following the emailMstrDialog sequence
    static String[] dataType = new String[]{"S", "S", "S", "D", "S", "S", "S", "S", "C", "C", "C", "S"};
    // Todo: might need to change if there are more recipient types
    static Map<String, String> recipientTypeMapForDataTypeD = new HashMap<>() {{
        put("C", "Customer");
        put("B", "Both");
    }};

    static Map<String, List<String>> oldContentIdData = new HashMap<>();
    static Map<String, List<String>> newContentIdData = new HashMap<>();
    static Map<String, String[]> finalContentIdData = new HashMap<>();

    static String templateForMstrDialog = "%s\t%s";

    // Todo: change if appropriate, portray current production dtls
    static String emailDtlsDataOld = "MLCONV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "MLCONV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "MLCONV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "MLCONV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "MLCONV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "MLCONV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NTMLCONVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NTMLCONVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NTMLCONVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NTMLCONVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NTMLCONVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NTMLCONVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "RUMLCNV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "RUMLCNV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "RUMLCNV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "RUMLCNV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "RUMLCNV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "RUMLCNV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "NTMLCONV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "NTMLCONV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "NTMLCONV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "NTMLCONV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "NTMLCONV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "NTMLCONV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "GYMLCONVPPS\t1\tMBRNO\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "GYMLCONVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "GYMLCONVPPS\t1\tCName\tCUS_PERS\tCORRES_NAME\tINT_ID\n" +
            "HMAMLCNVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "HMAMLCNVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "HMAMLCNVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "HMAMLCNVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "HMAMLCNVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "HMAMLCNVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "HAACMLCONV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "HAACMLCONV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "HAACMLCONV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "HAACMLCONV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "HAACMLCONV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "YUCONVKF\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "YUCONVKF\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "YUCONVKF\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "YUCONVKF\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "YUCONVKF\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "YUCONVKF\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "TCMLCNVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "TCMLCNVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "TCMLCNVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "TCMLCNVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "TCMLCNVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "TCMLCNVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "XOMLCONV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "XOMLCONV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "XOMLCONV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "XOMLCONV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "XOMLCONV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "XOMLCONV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "HMAMLCNV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "HMAMLCNV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "HMAMLCNV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "HMAMLCNV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "HMAMLCNV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "HMAMLCNV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "XOMLCONVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "XOMLCONVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "XOMLCONVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "XOMLCONVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "XOMLCONVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "XOMLCONVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "YUCONVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "YUCONVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "YUCONVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "YUCONVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "YUCONVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "YUCONVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "RUMLCNVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "RUMLCNVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "RUMLCNVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "RUMLCNVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "RUMLCNVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "RUMLCNVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "MLCONVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "MLCONVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "MLCONVPPS\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "MLCONVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "MLCONVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "MLCONVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "GYMLCONV\t1\tMBRNO\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "GYMLCONV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "GYMLCONV\t1\tCName\tCUS_PERS\tCORRES_NAME\tINT_ID\n" +
            "HACMCNVPPS\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "HACMCNVPPS\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "HACMCNVPPS\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "HACMCNVPPS\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "HACMCNVPPS\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID\n" +
            "TCMLCNV\t1\ttier\tCUS_ACCOUNT\tTIER_STATUS_IND\tINT_ID\n" +
            "TCMLCNV\t1\tyear\tCUS_PERS\tto_char(sysdate, 'YYYY')\tINT_ID\n" +
            "TCMLCNV\t1\tPartner_Programme_Name\tPRT\tPRT_NAME\tPRT_CD\n" +
            "TCMLCNV\t1\ttitle\tCUS_PERS\tTITLE\tINT_ID\n" +
            "TCMLCNV\t1\tfamily_name\tCUS_PERS\tFAMILY_NAME\tINT_ID\n" +
            "TCMLCNV\t1\tcus_id\tCUS_ACCOUNT\tCUS_ID\tINT_ID";

    // Todo: change if appropriate, for New marms convention of naming
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

    // Todo: change if appropriate, production mstr table data
    static String oldContent = "pinackPPS\tKrisFlyer_Services@singaporeair.com\tno-reply@email.singaporeair.com\tRequest for Phone PIN\tmailerprefack9_pps.html\tmailerprefack9.txt\t2\tSending pin acknowledgement via email to KF members\tN\tN\tC\t\n" +
            "pwdackPps\tKrisFlyer_Services@singaporeair.com\tno-reply@email.singaporeair.com\tYour Password has been reset\tpwdAck_Pps.html\tpasswordAckKF.txt\t2\tSending password acknowledgement via email\tN\tN\tC\tN";

    // Todo: change if appropriate, portray new marms or uat data
    static String newContent = "NMpinackPPS\tKrisFlyer_Services@singaporeair.com\tno-reply@email.singaporeair.com\tRequest for Phone PIN\tmailerprefack9_pps.html\tmailerprefack9.txt\t2\tSending pin acknowledgement via email to KF members\tN\tN\tC\t\n" +
            "NMpwdackPps\tKrisFlyer_Services@singaporeair.com\tno-reply@email.singaporeair.com\tYour Password has been reset\tpwdAck_Pps.html\tpasswordAckKF.txt\t2\tSending password acknowledgement via email\tN\tN\tC\tN";

    // Todo: to change, map old to new content id
    static String oldToNewContentId = "pinackPPS-NMpinackPPS\n" +
            "pwdackPps-NMpwdackPps";


    // no need to change as long as the order of the sql columns are the same
    static Map<Integer, Integer> mapOfDialogFieldKeyToSqlValue = new HashMap<>() {{
        put(0, 0);
        put(1, 1);
        put(2, 2);
        put(4, 3);
        put(5, 4);
        put(6, 5);
        put(7, 6);
        put(11, 7);
        put(8, 8);
        put(9, 9);
        put(3, 10);
        put(10, 11);
    }};

    static Map<String, String> mapOfOldToNewContentId = new HashMap<>();

    public static void main(String[] args) {
        String[] sqlArr = sqlColumns.split("\t");
        // initialise the old and new sql response to map
        initializeContentIdMap(oldContent, oldContentIdData);
        String template = "SELECT * FROM EMAIL_CNT_MSTR WHERE EMAIL_CONTENT_ID IN (%s)";
        String dtlsTemplate = "SELECT * FROM EMAIL_CNT_DTLS WHERE EMAIL_CONTENT_ID IN (%s)";
        StringBuilder builder = new StringBuilder();
        for (String key : oldContentIdData.keySet()) {
            builder.append("'");
            builder.append(key);
            builder.append("',");
        }
        System.out.println(String.format(template, builder.substring(0, builder.length() - 1)));
        System.out.println(String.format(dtlsTemplate, builder.substring(0, builder.length() - 1)));

        initializeContentIdMap(newContent, newContentIdData);
        // map old content id to new content id
        mapOldToNewContentId();
        // create the final content id data map containing the values to be inserted
        dataExtractor(oldContentIdData, newContentIdData);
        // create map of content id key to rows of dtls values
        Map<String, List<List<String>>> dtlsListNew = formatDetailsFieldValue(emailDtlsData);
        Map<String, List<List<String>>> dtlsListOld = formatDetailsFieldValue(emailDtlsDataOld);
        if (printValuesForRFC) {
            if (!useOldDtlsData) {
                printStatementForRFC(finalContentIdData, dtlsListNew, sqlArr, rcreUserID);
            } else {
                printStatementForRFC(finalContentIdData, dtlsListOld, sqlArr, rcreUserID);
            }
            printRollbackForRFC(mapOfOldToNewContentId);
        } else {
            printStatementForEmailSetupDialog(dtlsListNew);
        }

    }

    private static void printRollbackForRFC(Map<String, String> mapOfOldToNewContentId) {
        System.out.println("Printing out rollback values:");
        String templateForRollback = "DELETE FROM EMAIL_CNT_MSTR WHERE EMAIL_CONTENT_ID in (%s);";
        StringBuilder stringBuilder = new StringBuilder();
        Collection<String> contentIds = null;
        if (dataExtractorSwitch[0] == -1) {
            contentIds = mapOfOldToNewContentId.values();
        } else {
            // take old content id
            contentIds = mapOfOldToNewContentId.keySet();
        }
        for (String newContentId : contentIds) {
            stringBuilder.append(formatValuesToInsert(newContentId));
            stringBuilder.append(",");
        }
        System.out.println(templateForRollback.replace("%s", stringBuilder.substring(0, stringBuilder.length() - 1)));
        stringBuilder.delete(0, stringBuilder.length());
        // print out the details rollback values
        String templateForDtlsRollback = "DELETE FROM EMAIL_CNT_DTLS WHERE EMAIL_CONTENT_ID in (%s);";
        for (String newContentId : contentIds) {
            stringBuilder.append(formatValuesToInsert(newContentId));
            stringBuilder.append(",");
        }
        System.out.println(templateForDtlsRollback.replace("%s", stringBuilder.substring(0, stringBuilder.length() - 1)));
    }

    private static void printStatementForRFC(Map<String, String[]> finalContentIdData, Map<String, List<List<String>>> dtlsList, String[] sqlArr, String rcreUserId) {
        String templateForRFC = "INSERT into EMAIL_CNT_MSTR (rcre_user_id, rcre_dt, lchg_user_id, lchg_dt, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (%s, sysdate, null, null, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s);";
        System.out.println("Printing out values:");
        // populate the columns in the template
        try {
            for (String col : sqlArr) {
                templateForRFC = templateForRFC.replaceFirst("%s", col);
            }
            for (String key : finalContentIdData.keySet()) {
//                System.out.println(key);
//                if (key.equals("NMRVELITE")) {
//                    System.out.println("Here");
//                }
                String[] arr = finalContentIdData.get(key);
                String temp = templateForRFC;
                temp = temp.replaceFirst("%s", formatValuesToInsert(rcreUserId));
                String contentId = "";
                for (int i = 0; i < arr.length; i++) {
                    if (i == 0) {
                        contentId = arr[i];
                        temp = replacePlaceholders(temp, formatValuesToInsert(contentId), "%s");
                    } else
                        temp = replacePlaceholders(temp, formatValuesToInsert(arr[i]), "%s");
                    }
                    if (!avoidPrintingMstr)
                        System.out.println(temp);

                    System.out.println("\n");
                }
            } catch(Exception e){
                e.printStackTrace();
            }

        }

        private static String replacePlaceholders (String template, String value, String placeholder){
            int index = template.indexOf(placeholder);
            String firstHalfOfString = template.substring(0, index) + value;
            int afterPlaceholderIndex = index + placeholder.length();
            String secondHalfOfString = template.substring(afterPlaceholderIndex);
            return firstHalfOfString + secondHalfOfString;
        }

        private static String formatValuesToInsert (String val){
            if (StringUtils.isBlank(val) || val.equalsIgnoreCase("null")) {
                return "null";
            }

            if (val.contains("'")) {
                String templateForInsertingSingleQuotes = "q'[%s]'";
                return String.format(templateForInsertingSingleQuotes, val);
            }
            return "'" + val + "'";
        }

        private static void printStatementForEmailSetupDialog (Map < String, List < List < String >>> dtlsList){
            // format the sql values to dialog field values in sequence with emailMstrDialog
            List<List<String>> list = formatSqlValueToDialogFieldValue(finalContentIdData, dataType);


            System.out.println("Printing out values:");
            for (List<String> row : list) {
                String contentId = "";
                for (int i = 0; i < row.size(); i++) {
                    if (i == 0) {
                        contentId = row.get(i);
                        System.out.println("Insert for content id: " + row.get(i));
                        System.out.println("Details for Email Master Dialog:");
                    }
                    System.out.println(String.format(templateForMstrDialog, emailMstrDialog[i], row.get(i)));
                }
                // print out the details dialog
                System.out.println("------------------------------");
                List<List<String>> detailsRow = dtlsList.get(contentId);
                if (detailsRow == null || detailsRow.isEmpty()) {
                    System.out.println("No details dialog to insert");
                } else {
                    System.out.println("Details for Email Detail Dialog:");
                    for (List<String> dtls : detailsRow) {
                        for (int j = 0; j < dtls.size(); j++) {
                            System.out.println(String.format(templateForMstrDialog, emailDetailDialog[j], detailsRow.get(j)));
                        }
                        System.out.println("-----");

                    }
                }
                System.out.println("----------------------------------------------------------");
            }
        }

        private static Map<String, List<List<String>>> formatDetailsFieldValue (String emailDtlsData){
            Map<String, List<List<String>>> map = new HashMap<>();
            String[] arr = emailDtlsData.split("\n");
            for (String row : arr) {
                String[] rowSplit = row.split("\t");
                String contentId = rowSplit[0];
                List<List<String>> existing = map.computeIfAbsent(contentId, k -> new ArrayList<>());
                List<String> list = new ArrayList<>();
                for (int i = 0; i < rowSplit.length; i++) {
                    list.add(rowSplit[i]);
                }
                existing.add(list);
            }
            return map;
        }

        private static List<List<String>> formatSqlValueToDialogFieldValue (Map < String, String[]>
        finalContentIdData, String[]dataType){
            // will contain the list of content ids with the corresponding dialog field values
            List<List<String>> listOfList = new ArrayList<>();
            for (String key : finalContentIdData.keySet()) {
                String[] arr = finalContentIdData.get(key);
                List<String> dialogFieldsInSeq = new ArrayList<>();
                for (int i = 0; i < arr.length; i++) {
                    int sqlIndex = mapOfDialogFieldKeyToSqlValue.get(i);
                    String val = arr[sqlIndex]; // value of sql
                    String dialogFieldName = emailMstrDialog[i]; // dialog field name
                    String type = dataType[i]; // data type of the dialog field
                    if (type.equals("S")) {
                        // string type
                        dialogFieldsInSeq.add(val);
                    } else if (type.equals("C")) {
                        // checkbox type
                        if (StringUtils.isBlank(val) || val.equals("N")) {
                            dialogFieldsInSeq.add("Not Checked");
                        } else {
                            dialogFieldsInSeq.add("Checked");
                        }
                    } else if (type.equals("D")) {
                        // drop down type
                        String mappedVal = recipientTypeMapForDataTypeD.get(val);
                        dialogFieldsInSeq.add(mappedVal);
                    }
                }
                listOfList.add(dialogFieldsInSeq);
            }
            return listOfList;
        }

        private static void mapOldToNewContentId () {
            String[] arr = oldToNewContentId.split("\n");
            for (String row : arr) {
                String[] rowSplit = row.split("-");
                mapOfOldToNewContentId.put(rowSplit[0], rowSplit[1]);
            }
        }


        private static void initializeContentIdMap (String content, Map < String, List < String >> contentIdMap){
            String[] arr = content.split("\n");
            for (String row : arr) {
                StringBuilder stringBuilder = new StringBuilder();
                List<String> list = new ArrayList<>(sqlColumns.split("\t").length);
                String contentId = "";
                int count = 0;
                for (char c : row.toCharArray()) {
                    if (c == '\t') {
                        list.add(stringBuilder.toString());
                        count++;
                        if (count == 1) {
                            contentId = stringBuilder.toString();
                        }
                        stringBuilder.delete(0, stringBuilder.length());
                    } else {
                        stringBuilder.append(c);
                    }
                }
                list.add(stringBuilder.isEmpty() ? "" : stringBuilder.toString());
                contentIdMap.put(contentId, list);
            }
        }

        // to extract data either from the old content map or from the new content map
        private static void dataExtractor
        (Map < String, List < String >> oldContentIdData, Map < String, List < String >> newContentIdData){
            for (String key : mapOfOldToNewContentId.keySet()) {
                String newKey = mapOfOldToNewContentId.get(key);
                List<String> oldData = oldContentIdData.get(key);
                if (oldData == null) {
                    System.out.println(key);
                    continue;
                }
                List<String> newData = newContentIdData.get(newKey);
                String[] arrToAppend = new String[dataExtractorSwitch.length];
                for (int i = 0; i < dataExtractorSwitch.length; i++) {
                    int switchVal = dataExtractorSwitch[i];
                    if (switchVal == -1) {
                        // use map
                        arrToAppend[i] = newKey;
                    } else if (switchVal == 0) {
                        // take from prod
                        arrToAppend[i] = oldData.size() < i + 1 ? "" : oldData.get(i) == null ? "" : oldData.get(i);
                    } else if (switchVal == 1) {
                        // take from uat
                        arrToAppend[i] = newData.size() < i + 1 ? "" : newData.get(i) == null ? "" : newData.get(i);
                    }
                }
                finalContentIdData.put(newKey, arrToAppend);
            }
        }
    }
