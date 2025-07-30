package MARMSUI.migration.RFCRelated;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GenerateRFCSqlQueries {
    public static void main(String[] args) {
        String tobeIncludedStr = "10000\tlsl-agent-host/loginagentuser\tN\tN\n" +
                "10001\tlsl-agent-host/loginagentuser\tY\tN\n" +
                "10010\tlsl-agent-security-host/security/refreshtoken\tY\tN\n" +
                "10062\tlsl-marmsui-accrual-host/rmc/nonairretroenquiry\tY\tY\n" +
                "10056\tlsl-marmsui-accrual-host/rmc/retroEnquiry\tY\tY\n" +
                "10148\tlsl-marmsui-accrual-host/rmc/rmcinitdata\tY\tN\n" +
                "10041\tlsl-marmsui-profile-host/comboboxlist\tY\tN\n" +
                "40001\tlsl-marmsui-profile-host/common/getadminfees\tY\tN\n" +
                "40002\tlsl-marmsui-profile-host/common/getadminfeesbytier\tY\tN\n" +
                "10027\tlsl-marmsui-profile-host/common/getreferencelists\tY\tN\n" +
                "10006\tlsl-marmsui-profile-host/customer/getcustomerdetails\tY\tY\n" +
                "10017\tlsl-marmsui-profile-host/customer/getpastcustomerdetails\tY\tY\n" +
                "10019\tlsl-marmsui-profile-host/customer/rdpnnomineechangehistory\tY\tY\n" +
                "10020\tlsl-marmsui-profile-host/customer/resetpinpwd\tY\tY\n" +
                "10032\tlsl-marmsui-profile-host/customercollateral/cardissuehistory\tY\tY\n" +
                "10033\tlsl-marmsui-profile-host/customercollateral/cardreissuance\tY\tY\n" +
                "10039\tlsl-marmsui-profile-host/customercollateral/cardreissuanceinit\tY\tY\n" +
                "10046\tlsl-marmsui-profile-host/customercollateral/createotherrequest\tY\tY\n" +
                "10035\tlsl-marmsui-profile-host/customercollateral/createppsgiftmagazine\tY\tY\n" +
                "10051\tlsl-marmsui-profile-host/customercollateral/createppsgiftmagazine\tY\tY\n" +
                "10053\tlsl-marmsui-profile-host/customercollateral/deleteotherrequest\tY\tY\n" +
                "10054\tlsl-marmsui-profile-host/customercollateral/deleteppsgiftmagazine\tY\tY\n" +
                "10043\tlsl-marmsui-profile-host/customercollateral/getotherrequest\tY\tY\n" +
                "10034\tlsl-marmsui-profile-host/customercollateral/getppsgiftmagazine\tY\tY\n" +
                "10038\tlsl-marmsui-profile-host/customercollateral/getreferencedesc\tY\tN\n" +
                "10050\tlsl-marmsui-profile-host/customercollateral/initppsgiftmagazine\tY\tN\n" +
                "10057\tlsl-marmsui-profile-host/customercollateral/updateppsgiftmagazine\tY\tY\n" +
                "10103\tlsl-marmsui-profile-host/customerservicing/addairmiles\tY\tY\n" +
                "10088\tlsl-marmsui-profile-host/customerservicing/airmilesreversal\tY\tY\n" +
                "10110\tlsl-marmsui-profile-host/customerservicing/checkfscvalue\tY\tY\n" +
                "10096\tlsl-marmsui-profile-host/customerservicing/createmilesdeduction\tY\tY\n" +
                "10091\tlsl-marmsui-profile-host/customerservicing/createnonairmilestransaction\tY\tY\n" +
                "10076\tlsl-marmsui-profile-host/customerservicing/createservicerecovery\tY\tY\n" +
                "10065\tlsl-marmsui-profile-host/customerservicing/getairmiles\tY\tY\n" +
                "10079\tlsl-marmsui-profile-host/customerservicing/getmanualmilesdeduction\tY\tY\n" +
                "10077\tlsl-marmsui-profile-host/customerservicing/getnonairmiles\tY\tY\n" +
                "10066\tlsl-marmsui-profile-host/customerservicing/getnonairpromomiles\tY\tY\n" +
                "10064\tlsl-marmsui-profile-host/customerservicing/getservicerecovery\tY\tY\n" +
                "10075\tlsl-marmsui-profile-host/customerservicing/initservicerecovery\tY\tN\n" +
                "10086\tlsl-marmsui-profile-host/customerservicing/milesdeductionreversal\tY\tY\n" +
                "10089\tlsl-marmsui-profile-host/customerservicing/nonairmilesreversal\tY\tY\n" +
                "10090\tlsl-marmsui-profile-host/customerservicing/nonairpromomilesreversal\tY\tY\n" +
                "10095\tlsl-marmsui-profile-host/customerservicing/servicerecoveryreversal\tY\tY\n" +
                "10105\tlsl-marmsui-profile-host/customerservicing/verifypaxmanifest\tY\tY\n" +
                "10071\tlsl-marmsui-profile-host/digitalvouchers/addvoucher\tY\tY\n" +
                "10073\tlsl-marmsui-profile-host/digitalvouchers/extendvoucher\tY\tY\n" +
                "10125\tlsl-marmsui-profile-host/digitalvouchers/getvoucherrules\tY\tN\n" +
                "10069\tlsl-marmsui-profile-host/digitalvouchers/resendvoucher\tY\tY\n" +
                "10067\tlsl-marmsui-profile-host/digitalvouchers/retrievevoucher\tY\tY\n" +
                "10072\tlsl-marmsui-profile-host/digitalvouchers/voidvoucher\tY\tY\n" +
                "10029\tlsl-marmsui-profile-host/eventlog/create\tY\tY\n" +
                "10030\tlsl-marmsui-profile-host/eventlog/createaction\tY\tY\n" +
                "10031\tlsl-marmsui-profile-host/eventlog/geteventlogdetailbyid\tY\tN\n" +
                "10023\tlsl-marmsui-profile-host/eventlog/search\tY\tN\n" +
                "10087\tlsl-marmsui-profile-host/getactivitysummary\tY\tY\n" +
                "10016\tlsl-marmsui-profile-host/mappings/statement\tY\tN\n" +
                "10005\tlsl-marmsui-profile-host/menu/getaccessrights\tY\tN\n" +
                "10004\tlsl-marmsui-profile-host/menu/v3/hierarchy\tY\tN\n" +
                "20004\tlsl-marmsui-profile-host/menu/v3/hierarchy\tY\tN\n" +
                "10021\tlsl-marmsui-profile-host/miles/extendmiles\tY\tY\n" +
                "10060\tlsl-marmsui-profile-host/miles/getkrisshoptier\tY\tY\n" +
                "10061\tlsl-marmsui-profile-host/miles/getkrisshoptransaction\tY\tY\n" +
                "10018\tlsl-marmsui-profile-host/miles/getmilesexpiry\tY\tY\n" +
                "10048\tlsl-marmsui-profile-host/miles/getnewbalance\tY\tN\n" +
                "10007\tlsl-marmsui-profile-host/miles/getstatements\tY\tY\n" +
                "10024\tlsl-marmsui-profile-host/miles/qualification\tY\tY\n" +
                "10134\tlsl-marmsui-profile-host/milesconversion/convertmiles\tY\tY\n" +
                "10124\tlsl-marmsui-profile-host/milesconversion/getquotes\tY\tN\n" +
                "10055\tlsl-marmsui-profile-host/offpotherreward/addoffpdata\tY\tY\n" +
                "10058\tlsl-marmsui-profile-host/offpotherreward/deleteoffpdata\tY\tY\n" +
                "10052\tlsl-marmsui-profile-host/offpotherreward/editoffpdata\tY\tY\n" +
                "10047\tlsl-marmsui-profile-host/offpotherreward/getairlineandnonairpartnerdata\tY\tN\n" +
                "10049\tlsl-marmsui-profile-host/offpotherreward/getoffpviewdata\tY\tY\n" +
                "10044\tlsl-marmsui-profile-host/partnerprogramme/addnewcobrandcard\tY\tY\n" +
                "10045\tlsl-marmsui-profile-host/partnerprogramme/editcobrandcard\tY\tY\n" +
                "10112\tlsl-marmsui-profile-host/partnerprogramme/editcobrandvoucher\tY\tY\n" +
                "10040\tlsl-marmsui-profile-host/partnerprogramme/getcobrandcardsvouchers\tY\tY\n" +
                "10042\tlsl-marmsui-profile-host/partnerprogramme/getcobrandpartnerinformation\tY\tY\n" +
                "10025\tlsl-marmsui-profile-host/paxmanifest/searchpaxbypnr\tY\tN\n" +
                "10022\tlsl-marmsui-profile-host/paxmanifest/searchpaxmanifest\tY\tN\n" +
                "10028\tlsl-marmsui-profile-host/paxmanifest/updatepaxmanifest\tY\tN\n" +
                "10008\tlsl-marmsui-profile-host/progressiveupdate\tY\tY\n" +
                "30001\tlsl-marmsui-profile-host/reference/eventAndSubType\tY\tN\n" +
                "10084\tlsl-marmsui-profile-host/rewardsvouchers/addupdaterewarddetails\tY\tY\n" +
                "10082\tlsl-marmsui-profile-host/rewardsvouchers/extendreward\tY\tY\n" +
                "10074\tlsl-marmsui-profile-host/rewardsvouchers/getallrewardsvouchers\tY\tY\n" +
                "10080\tlsl-marmsui-profile-host/rewardsvouchers/geteligiblerewardrules\tY\tY\n" +
                "10083\tlsl-marmsui-profile-host/rewardsvouchers/getrewardpaxdetails\tY\tY\n" +
                "10081\tlsl-marmsui-profile-host/rewardsvouchers/getrewardruledetails\tY\tY\n" +
                "10078\tlsl-marmsui-profile-host/rewardsvouchers/patchstatusreward\tY\tY\n" +
                "10070\tlsl-marmsui-profile-host/rewardsvouchers/rewardbuttonpermissions\tY\tY\n" +
                "10068\tlsl-marmsui-profile-host/rewardsvouchers/rewardvouchergetrewards\tY\tY\n" +
                "10085\tlsl-marmsui-profile-host/rewardsvouchers/submitretroreward\tY\tY\n" +
                "10013\tlsl-marmsui-profile-host/updateRedemptionNominees\tY\tY\n" +
                "10015\tlsl-marmsui-profile-host/userfunction\tY\tN\n" +
                "10011\tlsl-security-host/security/refreshtoken\tY\tN";

        String inProdStr = "10001\tlsl-agent-host/loginagentuser\tN\n" +
                "10000\tlsl-agent-host/loginagentuser\tN\n" +
                "10005\tlsl-marmsui-profile-host/menu/getaccessrights\tN\n" +
                "10004\tlsl-marmsui-profile-host/menu/v3/hierarchy\tN\n" +
                "20004\tlsl-marmsui-profile-host/menu/v3/hierarchy\tN\n" +
                "10006\tlsl-marmsui-profile-host/customer/getcustomerdetails\tY\n" +
                "10010\tlsl-agent-security-host/security/refreshtoken\tN\n" +
                "10011\tlsl-security-host/security/refreshtoken\tN\n" +
                "10008\tlsl-marmsui-profile-host/progressiveupdate\tY\n" +
                "10013\tlsl-marmsui-profile-host/updateRedemptionNominees\tY\n" +
                "10012\tlsl-customer-host/kfnominees/getredemptionnominees\tY\n" +
                "10014\tlsl-customer-host/getredemptionnomineeadminfee\tN\n" +
                "10044\tlsl-marmsui-profile-host/partnerprogramme/addnewcobrandcard\tY\n" +
                "10040\tlsl-marmsui-profile-host/partnerprogramme/getcobrandcardsvouchers\tY\n" +
                "10045\tlsl-marmsui-profile-host/partnerprogramme/editcobrandcard\tY\n" +
                "10055\tlsl-marmsui-profile-host/offpotherreward/addoffpdata\tY\n" +
                "10052\tlsl-marmsui-profile-host/offpotherreward/editoffpdata\tY\n" +
                "10058\tlsl-marmsui-profile-host/offpotherreward/deleteoffpdata\tY\n" +
                "10049\tlsl-marmsui-profile-host/offpotherreward/getoffpviewdata\tY\n" +
                "10032\tlsl-marmsui-profile-host/customercollateral/cardissuehistory\tY\n" +
                "10033\tlsl-marmsui-profile-host/customercollateral/cardreissuance\tY\n" +
                "10043\tlsl-marmsui-profile-host/customercollateral/getotherrequest\tY\n" +
                "10046\tlsl-marmsui-profile-host/customercollateral/createotherrequest\tY\n" +
                "10034\tlsl-marmsui-profile-host/customercollateral/getppsgiftmagazine\tY\n" +
                "10020\tlsl-marmsui-profile-host/customer/resetpinpwd\tY\n" +
                "10007\tlsl-marmsui-profile-host/miles/getstatements\tY\n" +
                "10018\tlsl-marmsui-profile-host/miles/getmilesexpiry\tY\n" +
                "10021\tlsl-marmsui-profile-host/miles/extendmiles\tY\n" +
                "10056\tlsl-marmsui-accrual-host/rmc/retroEnquiry\tY\n" +
                "10062\tlsl-marmsui-accrual-host/rmc/nonairretroenquiry\tY\n" +
                "10060\tlsl-marmsui-profile-host/miles/getkrisshoptier\tY\n" +
                "10061\tlsl-marmsui-profile-host/miles/getkrisshoptransaction\tY\n" +
                "10024\tlsl-marmsui-profile-host/miles/qualification\tY\n" +
                "10092\tlsl-marmsui-qual-host/forcequal/init\tY\n" +
                "10093\tlsl-marmsui-qual-host/forcequal/cardcreatecode\tN\n" +
                "10094\tlsl-marmsui-qual-host/forcequal/updateforcequal\tY\n" +
                "10074\tlsl-marmsui-profile-host/rewardsvouchers/getallrewardsvouchers\tY\n" +
                "10080\tlsl-marmsui-profile-host/rewardsvouchers/geteligiblerewardrules\tY\n" +
                "10081\tlsl-marmsui-profile-host/rewardsvouchers/getrewardruledetails\tY\n" +
                "10082\tlsl-marmsui-profile-host/rewardsvouchers/extendreward\tY\n" +
                "10083\tlsl-marmsui-profile-host/rewardsvouchers/getrewardpaxdetails\tY\n" +
                "10084\tlsl-marmsui-profile-host/rewardsvouchers/addupdaterewarddetails\tY\n" +
                "10067\tlsl-marmsui-profile-host/digitalvouchers/retrievevoucher\tY\n" +
                "10069\tlsl-marmsui-profile-host/digitalvouchers/resendvoucher\tY\n" +
                "10071\tlsl-marmsui-profile-host/digitalvouchers/addvoucher\tY\n" +
                "10072\tlsl-marmsui-profile-host/digitalvouchers/voidvoucher\tY\n" +
                "10073\tlsl-marmsui-profile-host/digitalvouchers/extendvoucher\tY\n" +
                "10064\tlsl-marmsui-profile-host/customerservicing/getservicerecovery\tY\n" +
                "10076\tlsl-marmsui-profile-host/customerservicing/createservicerecovery\tY\n" +
                "10088\tlsl-marmsui-profile-host/customerservicing/airmilesreversal\tY\n" +
                "10066\tlsl-marmsui-profile-host/customerservicing/getnonairpromomiles\tY\n" +
                "10096\tlsl-marmsui-profile-host/customerservicing/createmilesdeduction\tY\n" +
                "10090\tlsl-marmsui-profile-host/customerservicing/nonairpromomilesreversal\tY\n" +
                "10029\tlsl-marmsui-profile-host/eventlog/create\tY\n" +
                "10025\tlsl-marmsui-profile-host/paxmanifest/searchpaxbypnr\tN\n" +
                "10028\tlsl-marmsui-profile-host/paxmanifest/updatepaxmanifest\tN\n" +
                "10022\tlsl-marmsui-profile-host/paxmanifest/searchpaxmanifest\tN";

        Map<String, Model> toBeIncludedSet = extractFromString(tobeIncludedStr, true);
        Map<String, Model> inProdSet = extractFromString(inProdStr, false);
        System.out.println(toBeIncludedSet.size());

        String insertData = "10148\tlsl-marmsui-accrual-host/rmc/rmcinitdata\n" +
                "10041\tlsl-marmsui-profile-host/comboboxlist\n" +
                "40001\tlsl-marmsui-profile-host/common/getadminfees\n" +
                "40002\tlsl-marmsui-profile-host/common/getadminfeesbytier\n" +
                "10027\tlsl-marmsui-profile-host/common/getreferencelists\n" +
                "10017\tlsl-marmsui-profile-host/customer/getpastcustomerdetails\n" +
                "10019\tlsl-marmsui-profile-host/customer/rdpnnomineechangehistory\n" +
                "10039\tlsl-marmsui-profile-host/customercollateral/cardreissuanceinit\n" +
                "10035\tlsl-marmsui-profile-host/customercollateral/createppsgiftmagazine\n" +
                "10051\tlsl-marmsui-profile-host/customercollateral/createppsgiftmagazine\n" +
                "10053\tlsl-marmsui-profile-host/customercollateral/deleteotherrequest\n" +
                "10054\tlsl-marmsui-profile-host/customercollateral/deleteppsgiftmagazine\n" +
                "10038\tlsl-marmsui-profile-host/customercollateral/getreferencedesc\n" +
                "10050\tlsl-marmsui-profile-host/customercollateral/initppsgiftmagazine\n" +
                "10057\tlsl-marmsui-profile-host/customercollateral/updateppsgiftmagazine\n" +
                "10103\tlsl-marmsui-profile-host/customerservicing/addairmiles\n" +
                "10110\tlsl-marmsui-profile-host/customerservicing/checkfscvalue\n" +
                "10091\tlsl-marmsui-profile-host/customerservicing/createnonairmilestransaction\n" +
                "10065\tlsl-marmsui-profile-host/customerservicing/getairmiles\n" +
                "10079\tlsl-marmsui-profile-host/customerservicing/getmanualmilesdeduction\n" +
                "10077\tlsl-marmsui-profile-host/customerservicing/getnonairmiles\n" +
                "10075\tlsl-marmsui-profile-host/customerservicing/initservicerecovery\n" +
                "10086\tlsl-marmsui-profile-host/customerservicing/milesdeductionreversal\n" +
                "10089\tlsl-marmsui-profile-host/customerservicing/nonairmilesreversal\n" +
                "10095\tlsl-marmsui-profile-host/customerservicing/servicerecoveryreversal\n" +
                "10105\tlsl-marmsui-profile-host/customerservicing/verifypaxmanifest\n" +
                "10125\tlsl-marmsui-profile-host/digitalvouchers/getvoucherrules\n" +
                "10030\tlsl-marmsui-profile-host/eventlog/createaction\n" +
                "10031\tlsl-marmsui-profile-host/eventlog/geteventlogdetailbyid\n" +
                "10023\tlsl-marmsui-profile-host/eventlog/search\n" +
                "10087\tlsl-marmsui-profile-host/getactivitysummary\n" +
                "10016\tlsl-marmsui-profile-host/mappings/statement\n" +
                "10048\tlsl-marmsui-profile-host/miles/getnewbalance\n" +
                "10134\tlsl-marmsui-profile-host/milesconversion/convertmiles\n" +
                "10124\tlsl-marmsui-profile-host/milesconversion/getquotes\n" +
                "10047\tlsl-marmsui-profile-host/offpotherreward/getairlineandnonairpartnerdata\n" +
                "10112\tlsl-marmsui-profile-host/partnerprogramme/editcobrandvoucher\n" +
                "10042\tlsl-marmsui-profile-host/partnerprogramme/getcobrandpartnerinformation\n" +
                "30001\tlsl-marmsui-profile-host/reference/eventAndSubType\n" +
                "10078\tlsl-marmsui-profile-host/rewardsvouchers/patchstatusreward\n" +
                "10070\tlsl-marmsui-profile-host/rewardsvouchers/rewardbuttonpermissions\n" +
                "10068\tlsl-marmsui-profile-host/rewardsvouchers/rewardvouchergetrewards\n" +
                "10085\tlsl-marmsui-profile-host/rewardsvouchers/submitretroreward\n" +
                "10015\tlsl-marmsui-profile-host/userfunction";

        String menuIdStr = "NM_DGTLVCHR\t10125\n" +
                "NM_CUSUPD\t10112\n" +
                "NM_SVCRCVY\t10110\n" +
                "NM_SVCRCVY\t10105\n" +
                "NM_SVCRCVY\t10103\n" +
                "NM_MLCONV\t10134\n" +
                "NM_CUSUPD\t10017\n" +
                "NM_MLCONV\t10124\n" +
                "NM_SVCRCVY\t10095\n" +
                "NM_SVCRCVY\t10091\n" +
                "NM_SVCRCVY\t10089\n" +
                "NM_ACSUMRY\t10087\n" +
                "NM_SVCLOG\t10023\n" +
                "NM_SVCLOG\t10030\n" +
                "NM_SVCLOG\t10031\n" +
                "NM_PPSGIFTMAGD\t10035\n" +
                "NM_CARDISSUEH\t10038\n" +
                "NM_CARDISSUE\t10039\n" +
                "NM_CUSUPD\t10042\n" +
                "NM_SVCRCVY\t10086\n" +
                "NM_CUSUPD\t10047\n" +
                "NM_PPSGIFTMAGD\t10051\n" +
                "NM_OTHCOLLATE\t10053\n" +
                "NM_PPSGIFTMAGD\t10054\n" +
                "NM_ACSUMRY\t10085\n" +
                "NM_PPSGIFTMAGD\t10057\n" +
                "NM_SVCRCVY\t10065\n" +
                "NM_ACSUMRY\t10068\n" +
                "NM_ACSUMRY\t10070\n" +
                "NM_SVCRCVY\t10077\n" +
                "NM_ACSUMRY\t10078\n" +
                "NM_SVCRCVY\t10079\n" +
                "NM_CUSUPD\t10019\n" +
                "NM_PAXMENQ\t10027\n" +
                "NM_RETROENQ\t10148\n" +
                "NM_USRFUNC\t10015";

        Map<String, String> menuMap = extractFromString(menuIdStr);

        Map<String, Model> insertMap = extractFromString(insertData, false);
        System.out.println(insertMap.size());


        populateCustTokenFlg(insertMap, toBeIncludedSet, inProdSet);

        // require 3 values
        String templateMrmuiApiCd = "INSERT INTO MRM.MRM.MRMUI_API_CD (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,API_URL,CUST_TKN_FLAG) values ('MTPGUI',SYSDATE,null,SYSDATE,'%s','%s','%s');";
        // require 2 values
        String templateUsrApiAcl = "Insert into MRM.USR_API_ACL (RCRE_USER_ID,RCRE_DT,LCHG_USER_ID,LCHG_DT,API_CD,MENU_ID) values ('MTPGUI',SYSDATE,null,null,'%s','%s');";

        String templateDeleteUsrApiAcl = "DELETE FROM MRM.USR_API_ACL WHERE API_CD = '%s';";
        String templateDeleteMrmuiApiCd = "DELETE FROM MRM.MRMUI_API_CD WHERE API_CD = '%s';";

        System.out.println("Printing out the mrmui_api_cd sql: ------------------------------");
        printerMrmApiCd(insertMap, templateMrmuiApiCd);
        System.out.println("\n\n\nPrinting out the Usr_API_ACL sql: ------------------------------");
        Set<String> notIncluded = printerUsrApiAcl(insertMap, templateUsrApiAcl, menuMap);

        System.out.println("\n\n Printing out rollback USR_API_ACL -----------------------------");
        printDelete(insertMap,templateDeleteUsrApiAcl,notIncluded);
        System.out.println("\n\n Printing out rollback MRMUI_API_CD -----------------------------");
        printDelete(insertMap,templateDeleteMrmuiApiCd,null);


    }

    private static void printDelete(Map<String, Model> insert, String template, Set<String> notIncluded) {
        for(Model model : insert.values()) {
            if(notIncluded != null && notIncluded.contains(model.getApiCd())) {
                continue;
            }
            System.out.println(String.format(template,model.getApiCd()));
        }
    }

    private static Map<String, String> extractFromString(String val) {
        Map<String, String> map = new HashMap<>();
        String[] arr = val.split("\n");
        for (String row : arr) {
            String[] innerRow = row.split("\t");
            map.put(innerRow[1], innerRow[0]);
        }
        return map;
    }

    private static void printerMrmApiCd(Map<String, Model> insert, String template) {
        for (Model model : insert.values()) {
            System.out.println(String.format(template, model.getApiCd(), model.getApiUrl(), model.getCustToken()));
        }
    }

    private static Set<String> printerUsrApiAcl(Map<String,Model> insert, String template, Map<String, String> menuMap) {
        Set<String> notIncluded = new HashSet<>();
        for (Model model : insert.values()) {
            // todo: add another parameter to insert the menu id
            String menuId = menuMap.get(model.getApiCd());
            if (menuId == null || menuId.isBlank()) {
                notIncluded.add(model.getApiCd());
            } else
                System.out.println(String.format(template, model.getApiCd(), menuMap.get(model.getApiCd())));
        }

        System.out.println("\n\nPrinting sql to check the menu id that is not to be added to acl table:");
        notIncluded.stream().forEach(x -> {
            System.out.print(x + ",");
        });
        return notIncluded;
    }

    private static void populateCustTokenFlg(Map<String, Model> insert, Map<String, Model> raw1, Map<String, Model> raw2) {
        for (String key : insert.keySet()) {
            Model model = insert.get(key);
            Model actual = raw2.get(key);
            if (actual == null) {
                actual = raw1.get(key);
            }
            if (actual == null) {
                throw new RuntimeException(String.format("%s is not found", key));
            }
            model.setCustToken(actual.getCustToken());
        }
    }

    private static Map<String,Model> extractFromString(String val, boolean takeLastRowAsCusToken) {
        String[] arr = val.split("\n");
        Map<String, Model> map = new HashMap<>();
        for (String row : arr) {
            String[] innerRow = row.split("\t");
            Model model = new Model(innerRow[0], innerRow[1]);
            if (innerRow.length < 3) {
                map.put(model.getApiCd(), model);
                System.out.print( model.getApiCd() + ", ");
                continue;
            }
            if (takeLastRowAsCusToken) {
                model.setCustToken(innerRow[innerRow.length - 1]);
            } else {
                model.setCustToken(innerRow[2]);
            }
            map.put(model.getApiCd(), model);
        }
        return map;
    }


    private static class Model {
        private String apiCd;
        private String apiUrl;
        private String custToken;
        private String menuId;

        public String getMenuId() {
            return menuId;
        }

        public void setMenuId(String menuId) {
            this.menuId = menuId;
        }

        public Model(String apiCd, String apiUrl) {
            this.apiCd = apiCd;
            this.apiUrl = apiUrl;
        }

        public String getCustToken() {
            return custToken;
        }

        public void setCustToken(String custToken) {
            this.custToken = custToken;
        }

        public Model() {
        }

        public String getApiCd() {
            return apiCd;
        }

        public void setApiCd(String apiCd) {
            this.apiCd = apiCd;
        }

        public String getApiUrl() {
            return apiUrl;
        }

        public void setApiUrl(String apiUrl) {
            this.apiUrl = apiUrl;
        }
    }
}
