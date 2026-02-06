import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestingPurposes {


    public static void main(String[] args) throws Exception {
        String val = "\"NMKPTPPREQ_\",\n" +
                "        \"NMENROLACK_\",\n" +
                "        \"NMpinackKF_\",\n" +
                "        \"NMNtfyCNPPS_\",\n" +
                "        \"NMstopmobile_\",\n" +
                "        \"NMQPPREQUAL_\",\n" +
                "        \"NMNtfyCMLoss_\",\n" +
                "        \"NMESREQUAL_\",\n" +
                "        \"NMHMAMLCNV_\",\n" +
                "        \"NMYUCONVPPS_\",\n" +
                "        \"NMKPTPPUPG_\",\n" +
                "        \"NMNtfyAudRel_\",\n" +
                "        \"NMNtfyCNAlrt_\",\n" +
                "        \"NMNfAdRlPPS_\",\n" +
                "        \"NMNTMLCONVPPS_\",\n" +
                "        \"NMmlTrfMinor_\",\n" +
                "        \"NMmlTrfGuard_\",\n" +
                "        \"NMYUCONVKF_\",\n" +
                "        \"NMTCMLCNVPPS_\",\n" +
                "        \"NMXOMLCONV_\",\n" +
                "        \"NMXOMLCONVPPS_\",\n" +
                "        \"NMpwdackKF_\",\n" +
                "        \"NMESREQUALST_\",\n" +
                "        \"NMOTPPDWG_\",\n" +
                "        \"NMCNSTAUTH_\",\n" +
                "        \"NMKPQPPREQ_\",\n" +
                "        \"NMRUMLCNV_\",\n" +
                "        \"NMQRTPPDWG_\",\n" +
                "        \"NMNTMLCONV_\",\n" +
                "        \"NMHMAMLCNVPPS_\",\n" +
                "        \"NMHAACMLCONV_\",\n" +
                "        \"NMINTTPPUPG_\",\n" +
                "        \"NMQPPUPGRADE_\",\n" +
                "        \"NMQPPDWGES_\",\n" +
                "        \"NMEGREQUAL_\",\n" +
                "        \"NMNTPPDWG1_\",\n" +
                "        \"NMNtfyCDPPS_\",\n" +
                "        \"NMRUMLCNVPPS_\",\n" +
                "        \"NMMLCONVPPS_\",\n" +
                "        \"NMESDWG_\",\n" +
                "        \"NMNtfyPDPA_\",\n" +
                "        \"NMNTPPDWG2_\",\n" +
                "        \"NMQPPDWGEG_\",\n" +
                "        \"NMNtfyPDPPS_\",\n" +
                "        \"NMcorpKFEG_\",\n" +
                "        \"NMEGDWG_\",\n" +
                "        \"NMMLCONV_\",\n" +
                "        \"NMNTPPFDWG_\",\n" +
                "        \"NMLMUAPPS_\",\n" +
                "        \"NMNtfyCDKF_\",\n" +
                "        \"NMKPLPPREQ_\",\n" +
                "        \"NMTPPREQUAL_\",\n" +
                "        \"NMEGUPGRADE_\",\n" +
                "        \"NMGYMLCONV_\",\n" +
                "        \"NMTPPUPGRADE_\",\n" +
                "        \"NMHACMCNVPPS_\",\n" +
                "        \"NMESUPGRADE_\",\n" +
                "        \"NMNtfyCMPPS_\",\n" +
                "        \"NMTCMLCNV_\",\n" +
                "        \"NMRtrOALAck_\",\n" +
                "        \"NMTCLNK_\",\n" +
                "        \"NMRdpnUpdAck_\",\n" +
                "        \"NMTCDLN_\",\n" +
                "        \"NMstpMobPPS_\",\n" +
                "        \"NMXOLNK_\",\n" +
                "        \"NMXODLN_\",\n" +
                "        \"NMHMADLN_\",\n" +
                "        \"NMLNKMIR_\",\n" +
                "        \"NMHMALNK_\",\n" +
                "        \"NMLMUAEMAIL_\",\n" +
                "        \"NMACCRDOBMPPS_\",\n" +
                "        \"NMNotifyCA_\",\n" +
                "        \"NMLNKCHILD_\",\n" +
                "        \"NM1KFMRPPS_\",\n" +
                "        \"NMprofileupd_\",\n" +
                "        \"NMACCRITC_\",\n" +
                "        \"NMACCRNM_\",\n" +
                "        \"NMRdUpAkPPS_\",\n" +
                "        \"NM1KFMRRWD_\",\n" +
                "        \"NMLNKPARENT_\",\n" +
                "        \"NMNTLNK\",\n" +
                "        \"NMprflupPPS_\",\n" +
                "        \"NMROALAkPPS_\",\n" +
                "        \"NMNTDLN_\",\n" +
                "        \"NMXOLNKPPS_\",\n" +
                "        \"NMLNKGUD_\",\n" +
                "        \"NM1KFMRUSED_\",\n" +
                "        \"NMNtfyCAPPS_\",\n" +
                "        \"NMACCRDOBMKF_\",\n" +
                "        \"NMmlTrmMinor_\",\n" +
                "        \"NMENROLAUTH_\",\n" +
                "        \"NMGYMLCONVPPS_\",";
        for(String row : val.split("\n")) {
            String value = row.replaceAll("_", "").trim();
            System.out.println(value);
        }

    }

    private static void extractField(String row, Map<String, String> fieldValueMap, String value) {
        int keyStartIndex = row.indexOf("kfenrolment.") + "kfenrolment.".length();
        int keyEndIndex = row.indexOf(".type=");
        String key = row.substring(keyStartIndex, keyEndIndex);
        fieldValueMap.put(key, value);
    }


    private static List<String> insertAfterBeforeClosingQuotesReturnList(List<String> stringList, String valueToInsert) {
        List<String> resultList = new java.util.ArrayList<>();
        for (String str : stringList) {
            String result = str.replaceAll("\"", "").replaceAll(",", "").trim();
            String finalResult = String.format("%s%s", result, valueToInsert);
            resultList.add(finalResult);
        }
        return resultList;
    }

    private static void insertAfterBeforeClosingQuotes(List<String> stringList, String valueToInsert) {
        for (String str : stringList) {
            String result = str.replaceAll("\"", "").replaceAll(",", "").trim();
            System.out.print(String.format("'%s_',", result));
        }
    }

    public static void insertValueAfterEachString(List<String> stringList, String valueToInsert) {
        for (String str : stringList) {
            String result = str.trim() + valueToInsert;
            System.out.print(result);
        }
    }

    public static void replaceString(String[] arr, List<String> toRemove, List<String> replacement) {
        for (String input : arr) {
            for (int i = 0; i < toRemove.size(); i++) {
                input = input.replace(toRemove.get(i), replacement.get(i));
            }
            System.out.print(input.trim());
        }
    }

    public static long convertToMilliseconds(String timeString) {
        try {
            // Replace masked data with actual values
            // Create SimpleDateFormat with ISO 8601 pattern
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

            // Parse the string to Date
            Date date = sdf.parse(timeString);

            // Convert to milliseconds
            return date.getTime();
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date: " + timeString);
        }
    }

}

