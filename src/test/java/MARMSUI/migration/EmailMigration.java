package MARMSUI.migration;

public class EmailMigration {
    public static void main(String[] args) {

        String oldContentId = "EGDWG";
        String newHtmlEN = "EG_Softland_to_ES_2024";
        String newHtmlID = "EG_Softland_to_ES_2024_ID";
        String newHtmlCN = "EG_Softland_to_ES_2024_CN_v2";
        String txtFile = "";
        String newIDContentId = "EGDWGID_NEW";
        String newCNContentId = "EGDWGCN_NEW";
        String oldIDContentId = "EGDWGID";
        String oldCNContentId = "EGDWGCN";
        System.out.println("Things to set about the kf member:\n\n");
        System.out.println("select ctry_of_residence,pref_lang_written_1 from cus_pers where int_id = '8991430688'\n" +
                "\n" +
                "update cus_pers set ctry_of_residence = 'CN', pref_lang_written_1 = 'CN' where int_id = '8991430688'\n" +
                "select * from reference_cd where ref_rec_cd = 'pwdackPps' and ref_rec_type = '552'\n");
        String templateForInsertingIntoMstrTable = "INSERT INTO email_cnt_mstr (rcre_user_id, rcre_dt, lchg_user_id, lchg_dt, email_content_id, email_from_addr, \n" +
                "email_replyto_addr, email_subject, email_htmlfile_name, email_textfile_name, email_body_format,email_desc,\n" +
                "use_gm_data_flg,cmt_migrated,recipient_type,block_email,block_email1)\n" +
                "SELECT \n" +
                "    rcre_user_id,                 \n" +
                "    sysdate, \n" +
                "    lchg_user_id, \n" +
                "    null, \n" +
                "    '%s_NEW', \n" +
                "    email_from_addr,email_replyto_addr,\n" +
                "    email_subject,email_htmlfile_name,email_textfile_name,email_body_format,email_desc,use_gm_data_flg,\n" +
                "    cmt_migrated,recipient_type,block_email,block_email1\n" +
                "FROM email_cnt_mstr\n" +
                "WHERE email_content_id = '%s';";
        String sqlForInsertingIntoMstrTable = String.format(templateForInsertingIntoMstrTable, oldContentId, oldContentId);
        System.out.println("\nPrinting out initial data:===========================\n");
        System.out.println(sqlForInsertingIntoMstrTable);
        String templateForInsertingIntoDtlsTable = "insert into email_cnt_dtls (rcre_user_id, rcre_dt, lchg_user_id, lchg_dt, email_content_id,cus_seq_no,\n" +
                "personalize_name,table_name,column_name,keycolumn_name)\n" +
                "select rcre_user_id, rcre_dt, lchg_user_id, lchg_dt, \n" +
                "'%s_NEW'\n" +
                ",cus_seq_no,\n" +
                "personalize_name,table_name,column_name,keycolumn_name\n" +
                "from email_cnt_dtls\n" +
                "where email_content_id = '%s';";
        System.out.println("\ninserting dtls table for new content id:===================================\n");
        String sqlforInsertingIntoDtlsTable = String.format(templateForInsertingIntoDtlsTable, oldContentId, oldContentId);
        System.out.println(sqlforInsertingIntoDtlsTable);
        String sqlForInsertingIntoReference = String.format("insert into reference_cd (rcre_user_id, rcre_dt, lchg_user_id, lchg_dt,ref_rec_type,\n" +
                "ref_type_desc,ref_rec_cd\n" +
                ",ref_rec_ext,ref_desc,ref_seq)\n" +
                "select rcre_user_id, rcre_dt, lchg_user_id, lchg_dt,ref_rec_type,\n" +
                "ref_type_desc,\n" +
                "'%s_NEW'\n" +
                ",ref_rec_ext,ref_desc,ref_seq from reference_cd\n" +
                "where ref_rec_type = '552' and ref_rec_cd = '%s'", oldContentId, oldContentId);
        System.out.println("\nInserting into reference table for other languages (CN, ID)====================================\n");
        System.out.println(sqlForInsertingIntoReference);

        String sqlForUpdatingRefTableForOtherLang = String.format("update reference_cd set ref_desc = '%s' where ref_rec_type = '552' and\n" +
                "ref_rec_cd = '%s_NEW' and ref_rec_ext = 'ID'\n" +
                "update reference_cd set ref_desc = '%s' where ref_rec_type = '552' and\n" +
                "ref_rec_cd = '%s_NEW' and ref_rec_ext = 'CN'", newIDContentId, oldContentId, newCNContentId, oldContentId);
        System.out.println("\nPrinting the sql for updating the ref desc of ref table:================================\n");
        System.out.println(sqlForUpdatingRefTableForOtherLang);
        String sqlForInsertingMstrTableForIDContent = String.format("INSERT INTO email_cnt_mstr (rcre_user_id, rcre_dt, lchg_user_id, lchg_dt, email_content_id, email_from_addr, \n" +
                "email_replyto_addr, email_subject, email_htmlfile_name, email_textfile_name, email_body_format,email_desc,\n" +
                "use_gm_data_flg,cmt_migrated,recipient_type,block_email,block_email1)\n" +
                "SELECT \n" +
                "    rcre_user_id,                 \n" +
                "    sysdate, \n" +
                "    lchg_user_id, \n" +
                "    null, \n" +
                "    '%s_NEW', \n" +
                "    email_from_addr,email_replyto_addr,\n" +
                "    email_subject,email_htmlfile_name,email_textfile_name,email_body_format,email_desc,use_gm_data_flg,\n" +
                "    cmt_migrated,recipient_type,block_email,block_email1\n" +
                "FROM email_cnt_mstr\n" +
                "WHERE email_content_id = '%s';", oldIDContentId,oldIDContentId);
        System.out.println("\nPrinting sql for inserting sql into mstr table for ID content id:=================================");
        System.out.println(sqlForInsertingMstrTableForIDContent);
        String sqlForInsertingMstrTableForCNContent = String.format("INSERT INTO email_cnt_mstr (rcre_user_id, rcre_dt, lchg_user_id, lchg_dt, email_content_id, email_from_addr, \n" +
                "email_replyto_addr, email_subject, email_htmlfile_name, email_textfile_name, email_body_format,email_desc,\n" +
                "use_gm_data_flg,cmt_migrated,recipient_type,block_email,block_email1)\n" +
                "SELECT \n" +
                "    rcre_user_id,                 \n" +
                "    sysdate, \n" +
                "    lchg_user_id, \n" +
                "    null, \n" +
                "    '%s_NEW', \n" +
                "    email_from_addr,email_replyto_addr,\n" +
                "    email_subject,email_htmlfile_name,email_textfile_name,email_body_format,email_desc,use_gm_data_flg,\n" +
                "    cmt_migrated,recipient_type,block_email,block_email1\n" +
                "FROM email_cnt_mstr\n" +
                "WHERE email_content_id = '%s';", oldCNContentId,oldCNContentId);
        System.out.println("\nPrinting sql for inserting sql into mstr table for CN content id:===============================\n");
        System.out.println(sqlForInsertingMstrTableForCNContent);
        String template = "update email_cnt_mstr set email_htmlfile_name = '%s.html' \n" +
                "where email_content_id = '%s_NEW'\n" +
                "update email_cnt_mstr set email_htmlfile_name = '%s', email_textfile_name\n" +
                "= '%s'\n" +
                "where email_content_id = '%s'\n" +
                "update email_cnt_mstr set email_htmlfile_name = '%s', email_textfile_name\n" +
                "= '%s'\n" +
                "where email_content_id = '%s'";
        String sqlForUpdateMstrTableForAll3Lang = String.format(template, newHtmlEN, oldContentId, newHtmlID, txtFile, newIDContentId, newHtmlCN, txtFile, newCNContentId);
        System.out.println("\nPrinting sql updates for all 3 lang:================================\n");
        System.out.println(sqlForUpdateMstrTableForAll3Lang);
    }
}
