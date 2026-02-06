package MARMSUI.migration.newer1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForceQualMigration {
    public static void main(String[] args) throws JsonProcessingException {
        String jsonString = "{\"tierStatus\": \"Q\",\n" +
                "                    \"chkChangeQualStart\": {\n" +
                "                        \"description\": \"Change Qual start Date\",\n" +
                "                        \"isEnabled\": \"N\",\n" +
                "                        \"value\": \"N\",\n" +
                "                        \"visible\": \"Y\",\n" +
                "                        \"type\": \"checkbox\"\n" +
                "                    },\n" +
                "                    \"chkCardIssBySQ\": {\n" +
                "                        \"description\": \"Card Already Issued by SQ\",\n" +
                "                        \"isEnabled\": \"Y\",\n" +
                "                        \"value\": \"N\",\n" +
                "                        \"visible\": \"Y\",\n" +
                "                        \"type\": \"checkbox\"\n" +
                "                    },\n" +
                "                    \"chkRecalReserveVal\": {\n" +
                "                        \"description\": \"Recalculate Reserve Value\",\n" +
                "                        \"isEnabled\": \"N\",\n" +
                "                        \"value\": \"N\",\n" +
                "                        \"visible\": \"N\",\n" +
                "                        \"type\": \"checkbox\"\n" +
                "                    },\n" +
                "                    \"lblPre\": {\n" +
                "                        \"description\": \"(Pre Requalification)\",\n" +
                "                        \"isEnabled\": \"N\",\n" +
                "                        \"value\": null,\n" +
                "                        \"visible\": \"N\",\n" +
                "                        \"type\": null\n" +
                "                    },\n" +
                "                    \"chkIncrementQppYears\": {\n" +
                "                        \"description\": \"Increment QPP Year Qualified\",\n" +
                "                        \"isEnabled\": \"Y\",\n" +
                "                        \"value\": \"N\",\n" +
                "                        \"visible\": \"Y\",\n" +
                "                        \"type\": \"checkbox\"\n" +
                "                    },\n" +
                "                    \"chkReinstateRsrvVal\": {\n" +
                "                        \"description\": \"Re-instate Reserve Value\",\n" +
                "                        \"isEnabled\": \"Y\",\n" +
                "                        \"value\": \"N\",\n" +
                "                        \"visible\": \"Y\",\n" +
                "                        \"type\": \"checkbox\"\n" +
                "                    },\n" +
                "                    \"chkRequalDisable\": {\n" +
                "                        \"description\": \"Disable Early Requalification\",\n" +
                "                        \"isEnabled\": \"N\",\n" +
                "                        \"value\": \"N\",\n" +
                "                        \"visible\": \"Y\",\n" +
                "                        \"type\": \"checkbox\"\n" +
                "                    },\n" +
                "                    \"chkAwardTier\": {\n" +
                "                        \"description\": \"Award Tier Bonus\",\n" +
                "                        \"isEnabled\": \"Y\",\n" +
                "                        \"value\": \"Y\",\n" +
                "                        \"visible\": \"Y\",\n" +
                "                        \"type\": \"checkbox\"\n" +
                "                    },\n" +
                "                    \"chkReinstate\": {\n" +
                "                        \"description\": \"Reinstate Cumulative Value\",\n" +
                "                        \"isEnabled\": \"Y\",\n" +
                "                        \"value\": \"N\",\n" +
                "                        \"visible\": \"N\",\n" +
                "                        \"type\": \"checkbox\"\n" +
                "                    },\n" +
                "                    \"chkYears\": {\n" +
                "                        \"description\": \"Increment Years\",\n" +
                "                        \"isEnabled\": \"Y\",\n" +
                "                        \"value\": \"Y\",\n" +
                "                        \"visible\": \"Y\",\n" +
                "                        \"type\": \"checkbox\"\n" +
                "                    },\n" +
                "                    \"chkPostRecalReserveVal\": {\n" +
                "                        \"description\": \"Recalculate Reserve Value\",\n" +
                "                        \"isEnabled\": \"N\",\n" +
                "                        \"value\": \"N\",\n" +
                "                        \"visible\": \"N\",\n" +
                "                        \"type\": \"checkbox\"\n" +
                "                    },\n" +
                "                    \"lblPost\": {\n" +
                "                        \"description\": \"(Post Requalification)\",\n" +
                "                        \"isEnabled\": \"N\",\n" +
                "                        \"value\": null,\n" +
                "                        \"visible\": \"N\",\n" +
                "                        \"type\": null\n" +
                "                    },\n" +
                "                    \"txtCardCode\": {\n" +
                "                        \"description\": \"Card Create Code\",\n" +
                "                        \"isEnabled\": \"Y\",\n" +
                "                        \"value\": null,\n" +
                "                        \"visible\": \"Y\",\n" +
                "                        \"type\": \"Text\"\n" +
                "                    },\n" +
                "                    \"forceExtLimitEnabled\": \"N\",\n" +
                "                    \"forceExtLimit\": 0,\n" +
                "                    \"dateSelection\": [\n" +
                "                        {\n" +
                "                            \"description\": \"Current Date\",\n" +
                "                            \"isEnabled\": \"Y\",\n" +
                "                            \"value\": {\n" +
                "                                \"qualStartDt\": \"2025-12-23 10:40:39\",\n" +
                "                                \"qualEndDt\": \"2026-12-31 10:40:39\",\n" +
                "                                \"extensionStartDt\": null,\n" +
                "                                \"reinstateStartDt\": null,\n" +
                "                                \"otherDate\": null\n" +
                "                            },\n" +
                "                            \"visible\": \"Y\",\n" +
                "                            \"type\": \"Radio\"\n" +
                "                        }\n" +
                "                    ]" +
                "                }";
//        String flattenedJson = flattenJsonString(jsonString);
        Map<String, Object> objectMap = extractFieldFromJsonString(jsonString);
        printMapToJsonString(objectMap);
    }

    private static void printMapToJsonString(Map<String, Object> objectMap) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        try {
            String jsonString = mapper.writeValueAsString(objectMap);
            System.out.println(jsonString);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Object> extractFieldFromJsonString(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = mapper.readValue(jsonString, new TypeReference<>() {
        });
        Map<String, Object> toReturn = exractMapToString(map);
        return toReturn;
    }

    private static Map<String, Object> exractMapToString(Map<String, Object> map) {
        Map<String, Object> cleanedMap = new HashMap<>();
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof String || value instanceof Integer) {
                cleanedMap.put(key, value);
            } else if (key.equals("dateSelection")) {
                extractDateSelection(cleanedMap, (List<Map<String, Object>>) value);
            } else {
                cleanedMap.put(key, extractRequiredValue((Map<String, Object>) value));
            }
        }
        return cleanedMap;
    }

    private static void extractDateSelection(Map<String, Object> cleanedMap, List<Map<String, Object>> value) {
        Map<String, Object> innerValue = (Map<String, Object>) (value.get(0)).get("value");
        for (String innerKey : innerValue.keySet()) {
            cleanedMap.put(innerKey, innerValue.get(innerKey));
            cleanedMap.put("dateDescription", value.get(0).get("description"));
        }
    }

    private static String extractRequiredValue(Map<String, Object> value) {
        String isEnabled = (String) value.get("isEnabled");
        String isVisible = (String) value.get("isVisible");
        if (isEnabled != null && isEnabled.equals("Y") && value.get("value") != null && isVisible != null && isVisible.equals("Y")) {
            return value.get("value").toString();
        }
        return (String) value.get("value");
    }

    private static String flattenJsonString(String jsonString) {
        return jsonString.replaceAll("\\s+", "");
    }
}
