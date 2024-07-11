package MARMSUI.migration;

import java.util.ArrayList;
import java.util.List;

public class ConvertToStaticFinal {
    public static void main(String[] args) {
        String val = "public final static String QUALIFIED_TIER = \"Q\";\n" +
                "public final static String PPS_EXTENTION_ACTION = \"PPS_EXT\";\n" +
                "public final static String PPS_REINSTATE_ACTION = \"PPS_RI\";\n" +
                "public final static String PPS_REQUAL_ACTION = \"PPS_RE\";\n" +
                "public final static String ELITE_PRE_QUALIFY_IND = \"EP\";\n" +
                "null\n" +
                "public final static String SOFTLAND_TO_ELITE_UIACTION = \"SOFT_LAND_UI\";\n" +
                "null\n" +
                "public final static String CMTQ_RECORD_NOT_FOUND = \"Incomplete Qualification Information\\n\" +\n" +
                "public final static String ELITE_REQUALIFY_ACTION = \"ELITE_RE\";\n" +
                "public final static String ELITE_RESET_ACTION = \"ELITE_RESET\";\n" +
                "public final static String ELITE_TIER = \"ELITE\";\n" +
                "public final static String SOFTLAND = \"SL\";\n" +
                "public final static String SOLITAIRE_TIER = \"T\";\n" +
                "public final static String PPS_AUTO_EXTENTION_ACTION = \"PPS_AUTO_EXT\";\n" +
                "public final static String CEMQ_RECORD_NOT_FOUND = \"Failed to retrieve Elite qualification record.\";\n" +
                "null\n" +
                "public final static String PPS_PREQUAL_ACTION = \"PPS_PRE\";\n" +
                "public final static String ELITE_EXTENTION_ACTION = \"ELITE_EXT\";\n" +
                "public final static String PPS_TIER = \"PPS\";\n" +
                "null\n" +
                "public final static String PPS_RE_QUALIFY_IND = \"PR\";\n" +
                "public final static String BASIC_TIER = \"K\";\n" +
                "public final static String LIFE_TIER = \"L\";\n" +
                "public final static String CPMQ_RECORD_NOT_FOUND = \"Failed to retrieve PPS qualification record.\";\n" +
                "public final static String PPS_RESET_ACTION = \"PPS_RESET\";\n" +
                "public final static String ELITE_PREQUALIFY_ACTION = \"ELITE_PRE\";\n" +
                "null\n" +
                "public final static String PPS_DOWNGRADE_ACTION = \"PPS_DOWN\";\n" +
                "null\n" +
                "public final static String ELITE_REINSTATE_ACTION = \"ELITE_RI\";\n" +
                "public final static String SOFTLAND_TO_ELITE_ACTION = \"SOFT_LAND\";\n" +
                "null\n" +
                "public final static String SOFT_LAND_QUALIFY_IND = \"SL\";\n" +
                "public final static String PPS_UPGRADE_ACTION = \"PPS_UPGRADE\";\n" +
                "public final static String ELITE_DOWNGRADE_ACTION = \"ELITE_DOWN\";\n" +
                "public final static String SILVER_TIER = \"S\";";
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
