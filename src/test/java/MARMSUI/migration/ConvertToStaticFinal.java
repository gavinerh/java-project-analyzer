package MARMSUI.migration;

import java.util.ArrayList;
import java.util.List;

public class ConvertToStaticFinal {
    public static void main(String[] args) {
        String val = "public final static int CREDIT = 1;\n" +
                "public final static String PPS_PRE_QUALIFY_IND = \"PP\";\n" +
                "public final static String INVALID_LIFE_OPERATION = \"N0644\";\n" +
                "null\n" +
                "null\n" +
                "public final static String HCPQ_RECORD_NOT_FOUND = \"Failed to retrieve most recent PPS Qualification Record From History.\";\n" +
                "public final static String PROCESSING = \"N0759\";\n" +
                "public final static String PPS_REQUAL_ACTION = \"PPS_RE\";\n" +
                "public final static String PPS_REINSTATE_ACTION = \"PPS_RI\";\n" +
                "null\n" +
                "public final static String ELITE_RETRO_QUALIFY_ACTION = \"ELITE_RETRO\";\n" +
                "null\n" +
                "null\n" +
                "public final static int DEBIT = 2;\n" +
                "public final static String WELCOME_CARD_STATUS_IND = \"W\";\n" +
                "null\n" +
                "public final static String PLASTIC_CARD = \"L\";\n" +
                "public final static String PPS_TIER = \"PPS\";\n" +
                "public final static String INVALID_TIER_STATUS = \"N0014\";\n" +
                "null\n" +
                "public final static String CPMQ_RECORD_CREATE_FAILED = \"Failed to create PPS qualification record.\";\n" +
                "public final static String PPS_FORCE_REINSTATE_IND = \"PI\";\n" +
                "null\n" +
                "public final static String PPS_FORCE_REQUAL_ACTION = \"PPS_FR\";\n" +
                "public final static String DEFAULT_PPS_TIER = \"Q\";\n" +
                "public final static String ELITE_REINSTATE_ACTION = \"ELITE_RI\";\n" +
                "public final static int BOTH_BUCKET = 4;\n" +
                "null\n" +
                "public final static String ELITE_FORCE_REQUALIFY_IND = \"RE\";\n" +
                "null\n" +
                "public final static String DEFAULT_ELITE_QUALIFY_IND = ELITE_PRE_QUALIFY_IND;\n" +
                "null\n" +
                "public final static String INITIALIZE = \"N0757\";\n" +
                "null\n" +
                "null\n" +
                "public final static String ELITE_REQUALIFY_ACTION =\"ELITE_RE\";\n" +
                "public final static String CMTQ_RECORD_NOT_FOUND = \"Incomplete Qualification Information\\n\" +\n" +
                "null\n" +
                "null\n" +
                "null\n" +
                "public final static String SOFTLAND = \"SL\";\n" +
                "null\n" +
                "public final static String CEMQ_RECORD_NOT_FOUND = \"Failed to retrieve Elite qualification record.\";\n" +
                "null\n" +
                "public final static int GET_TIER_QUAL_INFO = 5;\n" +
                "public final static String PPS_UPGRADE_REINSTATE_IND = \"RE\";\n" +
                "public final static String QUALIFICATION_RECORD_NOT_FOUND = \"N0523\";\n" +
                "null\n" +
                "public final static String ELITE_RE_QUALIFY_IND = \"ER\";\n" +
                "null\n" +
                "public final static String INVALID_ACC_STATUS = \"N0015\";\n" +
                "public final static String GOLD_TIER = \"G\";\n" +
                "null\n" +
                "public final static String CPMQ_RECORD_NOT_FOUND = \"Failed to retrieve PPS qualification record.\";\n" +
                "null\n" +
                "public final static String DEFAULT_DISCTRY_FLAG = \"N\";\n" +
                "public final static int PPS_BUCKET = 2;\n" +
                "public final static String ELITE_PREQUALIFY_ACTION = \"ELITE_PRE\";\n" +
                "null\n" +
                "null\n" +
                "null\n" +
                "public final static String ELITE_FORCE_QUALIFY_IND = \"EF\";\n" +
                "public final static String PPS_FORCE_EXTENTION_IND = \"PX\";\n" +
                "public final static String CARD_ALREADY_ISSUED = \"I\";\n" +
                "public final static String FORCEREQUALFY_QSL = \"SE\";\n" +
                "null\n" +
                "public final static String ELITE_FORCE_EXTENTION_IND = \"EX\";\n" +
                "public final static String PPS_UPGRADE_ACTION = \"PPS_UPGRADE\";\n" +
                "null\n" +
                "public final static String ELITE_DOWNGRADE_ACTION = \"ELITE_DOWN\";\n" +
                "public final static String FORCE_TIER_LOWER_IN_HIERARCHY = \"N0537\";\n" +
                "public final static String HCEQ_RECORD_NOT_FOUND = \"Failed to retrieve most recent Elite Qualification Record From History.\";\n" +
                "null\n" +
                "public final static String CEMQ_RECORD_CREATE_FAILED = \"Failed to create Elite qualification record.\";\n" +
                "null\n" +
                "null\n" +
                "null\n" +
                "public final static String ELITE_PRE_QUALIFY_IND = \"EP\";\n" +
                "null\n" +
                "public final static String PPS_EXTENTION_ACTION = \"PPS_EXT\";\n" +
                "public final static String SOFTLAND_TO_ELITE_UIACTION = \"SOFT_LAND_UI\";\n" +
                "public final static int ELITE_BUCKET = 1;\n" +
                "null\n" +
                "public final static String INVALID_QLFY_END_DATE = \"Invalid Qualification end date\";\n" +
                "null\n" +
                "null\n" +
                "public final static String PPS_SOFTLAND_ACTION = \"PPS_SOFTLAND\";\n" +
                "public final static String PPS_PREQUAL_ACTION = \"PPS_PRE\";\n" +
                "null\n" +
                "public final static String PPS_FORCE_QUALIFY_IND = \"PF\";\n" +
                "public final static String REQUALIFY = \"RQ\";\n" +
                "public final static String SAVING = \"N0760\";\n" +
                "public final static String EXTEND = \"EX\";\n" +
                "public final static String PPS_FORCE_REQUALIFY_IND = \"RE\";\n" +
                "null\n" +
                "public final static String BLANKET_EXTENTION = \"BE\";\n" +
                "null\n" +
                "public final static String ELITE_FORCE_REINSTATE_IND = \"RE\";\n" +
                "public final static String ELITE_SILENT_IND = \"SL\";\n" +
                "null\n" +
                "public final static String SOFT_LAND_QUALIFY_IND = \"SL\";\n" +
                "public final static String PPS_UPGRADESL_IND = \"PL\";\n" +
                "null\n" +
                "public final static String SILVER_TIER = \"S\";\n" +
                "null\n" +
                "null\n" +
                "public final static String LOADING = \"N0758\";\n" +
                "null\n" +
                "public final static String QUALIFIED_TIER = \"Q\";\n" +
                "null\n" +
                "null\n" +
                "null\n" +
                "null\n" +
                "public final static String REINSTATE = \"RI\";\n" +
                "public final static String ELITE_TIER = \"ELITE\";\n" +
                "public final static String SOLITAIRE_TIER = \"T\";\n" +
                "public final static String CARD_ISSUING = \"N0761\";\n" +
                "public final static String ELITE_EXTENTION_ACTION = \"ELITE_EXT\";\n" +
                "null\n" +
                "public final static String BASIC_TIER = \"K\";\n" +
                "public final static String PPS_RE_QUALIFY_IND = \"PR\";\n" +
                "public final static String LIFE_TIER = \"L\";\n" +
                "public final static String DEFAULT_ELITE_TIER = \"S\";\n" +
                "public final static String FORCEREQUALFY = \"BE\";\n" +
                "null\n" +
                "public final static String PPS_DOWNGRADE_ACTION = \"PPS_DOWN\";\n" +
                "public final static String INSUFFICIENT_MILES_FOR_DEBIT = \"N0496\";\n" +
                "null\n" +
                "public final static String DEFAULT_PPS_QUALIFY_IND = PPS_PRE_QUALIFY_IND;\n" +
                "null\n" +
                "null\n" +
                "null\n" +
                "null\n" +
                "null\n" +
                "null\n" +
                "null\n" +
                "public final static String UPGRADE = \"UP\";";
        String[] arr = val.split("\n");
        List<String> values = cleanUpResponses(arr);
        values.stream().forEach(System.out::println);
    }

    private static List<String> cleanUpResponses(String[] arr) {
        List<String> responses = new ArrayList<>();
        for(String val : arr) {
            if(val.contains("null")) {
                continue;
            }
            if(val.contains("final static")) {
                responses.add(convertToStaticFinal(val));
            }
        }
        return responses;
    }

    private static String convertToStaticFinal(String val) {
        String key = "final static";
        int start = val.indexOf(key);
        String finalString = val.substring(0,start).trim();
        start = start + key.length();
        return String.format("%s static final %s", finalString, val.substring(start).trim());
    }
}
