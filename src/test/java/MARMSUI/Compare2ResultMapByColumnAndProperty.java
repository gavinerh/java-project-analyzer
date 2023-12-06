package MARMSUI;

import java.util.HashMap;
import java.util.Map;

public class Compare2ResultMapByColumnAndProperty {
    public static void main(String[] args) {
        String firstResultMap = "<result column=\"TIER_TYPE_IND\" jdbcType=\"VARCHAR\" property=\"tierTypeInd\"/>\n" +
                "        <result column=\"QUAL_PROC_IND\" jdbcType=\"VARCHAR\" property=\"qualProcID\"/>\n" +
                "        <result column=\"SEQ_NO\" jdbcType=\"NUMERIC\" property=\"seqNo\"/>\n" +
                "        <result column=\"TIER_STATUS_IND\" jdbcType=\"VARCHAR\" property=\"tierStatus\"/>\n" +
                "        <result column=\"TIER_STATUS_PTS_REQ\" jdbcType=\"NUMERIC\" property=\"ptsReq\"/>\n" +
                "        <result column=\"TIER_STATUS_SECT_REQ\" jdbcType=\"DOUBLE\" property=\"sectReq\"/>\n" +
                "        <result column=\"TIER_STATUS_MIN_PRD\" jdbcType=\"NUMERIC\" property=\"minPeriod\"/>\n" +
                "        <result column=\"TIER_HIERARCHY\" jdbcType=\"NUMERIC\" property=\"hierarchy\"/>\n" +
                "        <result column=\"TIER_STATUS_PTS_OFFSET\" jdbcType=\"NUMERIC\" property=\"pointOffset\"/>\n" +
                "        <result column=\"TIER_STATUS_SECT_OFFSET\" jdbcType=\"DOUBLE\" property=\"sectorOffset\"/>\n" +
                "        <result column=\"TIER_STATUS_ACCUM_PTS_REQ\" jdbcType=\"NUMERIC\" property=\"accPtsReq\"/>\n" +
                "        <result column=\"TIER_STATUS_ACCUM_SECT_REQ\" jdbcType=\"DOUBLE\" property=\"accSectReq\"/>\n" +
                "        <result column=\"TIER_STATUS_ACCUM_PTS_OFFSET\" jdbcType=\"NUMERIC\" property=\"accPtsOffset\"/>\n" +
                "        <result column=\"TIER_STATUS_ACCUM_SECT_OFFSET\" jdbcType=\"DOUBLE\" property=\"acctSectOffset\"/>\n" +
                "        <result column=\"RULE_ACTIVE_IND\" jdbcType=\"VARCHAR\" property=\"active\"/>\n" +
                "        <result column=\"APPLICABLE_QLFY_IND\" jdbcType=\"VARCHAR\" property=\"applQualInd\"/>\n" +
                "        <result column=\"TIER_QUAL_PRD\" jdbcType=\"NUMERIC\" property=\"tierQuadPrd\"/>\n" +
                "        <result column=\"TIER_STATUS_VAL_REQ\" jdbcType=\"NUMERIC\" property=\"valReq\"/>\n" +
                "        <result column=\"TIER_STATUS_ACCUM_VAL_REQ\" jdbcType=\"NUMERIC\" property=\"accValReq\"/>\n" +
                "        <result column=\"RULE_IND\" jdbcType=\"VARCHAR\" property=\"ruleInd\"/>\n" +
                "        <result column=\"START_DT\" jdbcType=\"TIMESTAMP\" property=\"startDate\"/>\n" +
                "        <result column=\"END_DT\" jdbcType=\"TIMESTAMP\" property=\"endDate\"/>\n" +
                "        <result column=\"YRS_IN_TIER\" jdbcType=\"NUMERIC\" property=\"yrsInTier\"/>\n" +
                "        <result column=\"TIER_STATUS_VAL_OFFSET\" jdbcType=\"NUMERIC\" property=\"valOffset\"/>\n" +
                "        <result column=\"TIER_STATUS_ACCUM_VAL_OFFSET\" jdbcType=\"NUMERIC\" property=\"accValOffset\"/>\n" +
                "        <result column=\"QUAL_SCHEME\" jdbcType=\"VARCHAR\" property=\"interim\"/>\n" +
                "        <result column=\"PRG_CD\" jdbcType=\"VARCHAR\" property=\"programCd\"/>";
        Map<String,String> mapOfColToProperty = generateKeyColValProp(firstResultMap);
        // baseResultMap should be more complete
        String baseResultMap = "<result column=\"SEQ_NO\" jdbcType=\"NUMERIC\" property=\"seqNo\"/>\n" +
                "<result column=\"TIER_STATUS_IND\" jdbcType=\"VARCHAR\" property=\"tierStatus\"/>\n" +
                "<result column=\"TIER_STATUS_PTS_REQ\" jdbcType=\"NUMERIC\" property=\"ptsReq\"/>\n" +
                "<result column=\"TIER_STATUS_SECT_REQ\" jdbcType=\"DOUBLE\" property=\"sectReq\"/>\n" +
                "<result column=\"TIER_STATUS_MIN_PRD\" jdbcType=\"NUMERIC\" property=\"minPeriod\"/>\n" +
                "<result column=\"TIER_HIERARCHY\" jdbcType=\"NUMERIC\" property=\"hierarchy\"/>\n" +
                "<result column=\"TIER_STATUS_PTS_OFFSET\" jdbcType=\"NUMERIC\" property=\"pointOffset\"/>\n" +
                "<result column=\"TIER_STATUS_SECT_OFFSET\" jdbcType=\"DOUBLE\" property=\"sectorOffset\"/>\n" +
                "<result column=\"TIER_STATUS_ACCUM_PTS_REQ\" jdbcType=\"NUMERIC\" property=\"accPtsReq\"/>\n" +
                "<result column=\"TIER_STATUS_ACCUM_SECT_REQ\" jdbcType=\"DOUBLE\" property=\"accSectReq\"/>\n" +
                "<result column=\"TIER_STATUS_ACCUM_PTS_OFFSET\" jdbcType=\"NUMERIC\" property=\"accPtsOffset\"/>\n" +
                "<result column=\"TIER_STATUS_ACCUM_SECT_OFFSET\" jdbcType=\"DOUBLE\" property=\"acctSectOffset\"/>\n" +
                "<result column=\"RULE_ACTIVE_IND\" jdbcType=\"VARCHAR\" property=\"active\"/>\n" +
                "<result column=\"APPLICABLE_QLFY_IND\" jdbcType=\"VARCHAR\" property=\"applQualInd\"/>\n" +
                "<result column=\"TIER_QUAL_PRD\" jdbcType=\"NUMERIC\" property=\"tierQuadPrd\"/>\n" +
                "<result column=\"TIER_STATUS_VAL_REQ\" jdbcType=\"NUMERIC\" property=\"valReq\"/>\n" +
                "<result column=\"TIER_STATUS_ACCUM_VAL_REQ\" jdbcType=\"NUMERIC\" property=\"accValReq\"/>\n" +
                "<result column=\"RULE_IND\" jdbcType=\"VARCHAR\" property=\"ruleInd\"/>\n" +
                "<result column=\"START_DT\" jdbcType=\"TIMESTAMP\" property=\"startDate\"/>\n" +
                "<result column=\"END_DT\" jdbcType=\"TIMESTAMP\" property=\"endDate\"/>\n" +
                "<result column=\"YRS_IN_TIER\" jdbcType=\"NUMERIC\" property=\"yrsInTier\"/>\n" +
                "<result column=\"PRG_CD\" jdbcType=\"VARCHAR\" property=\"programCd\"/>\n" +
                "<result column=\"TIER_TYPE_IND\" jdbcType=\"VARCHAR\" property=\"tierTypeInd\"/>\n" +
                "<result column=\"QUAL_PROC_IND\" jdbcType=\"VARCHAR\" property=\"qualProcID\"/>\n" +
                "<result column=\"TIER_STATUS_VAL_OFFSET\" jdbcType=\"NUMERIC\" property=\"valOffset\"/>\n" +
                "<result column=\"TIER_STATUS_ACCUM_VAL_OFFSET\" jdbcType=\"NUMERIC\" property=\"accValOffset\"/>\n" +
                "<result column=\"QUAL_SCHEME\" jdbcType=\"VARCHAR\" property=\"interim\"/>";
        Map<String,String> mapOfBase = generateKeyColValProp(baseResultMap);

        // fieldNamesOfComparator must be same as the firstResultMap string
        String fieldNamesOfComparator = "private String transactionType;\n" +
                "    private Date date;\n" +
                "    private String prtCd;\n" +
                "    private String description;\n" +
                "    private String reversedFlg;\n" +
                "    private long ptsAwarded;\n" +
                "    private String ppsVal;\n" +
                "    private long eliteMiles;\n" +
                "    private String givenName;\n" +
                "    private String familyName;\n" +
                "    private String nameMismatchFlg;";
        Map<String,String> mapOfFieldNameToFieldDeclaration = generateMapForFieldname(fieldNamesOfComparator);
        compareMaps(mapOfColToProperty, mapOfBase, mapOfFieldNameToFieldDeclaration);
    }

    private static Map<String,String> generateMapForFieldname(String fieldNameDeclaration) {
        String[] fieldDeclarationArr = fieldNameDeclaration.split("\n");
        Map<String,String> mapOfFieldDeclaration = new HashMap<>();
        for(String fieldDeclaration : fieldDeclarationArr) {
            // trim and get the name as key
            String key = fieldDeclaration.trim().split(" ")[2];
            key = key.substring(0,key.length()-1);
            String trimmedFieldDeclaration = fieldDeclaration.trim();
            mapOfFieldDeclaration.put(key, trimmedFieldDeclaration);
        }
        return mapOfFieldDeclaration;
    }

    private static void compareMaps(Map<String,String> mapOfComparator, Map<String,String> map, Map<String,String> mapOfFieldNames) {
        for(String key : mapOfComparator.keySet()) {
            String valOfValueToGetTo = map.get(key);
            String valOfCurrent = mapOfComparator.get(key);
            String fieldDeclaration = mapOfFieldNames.get(valOfCurrent);
            fieldDeclaration += " // " + valOfValueToGetTo;
            System.out.println(fieldDeclaration);
//            System.out.println(String.format("Field %s should have field name of %s", mapOfComparator.get(key), map.get(key)));
        }
    }

    private static Map<String,String> generateKeyColValProp(String resultMap) {
        Map<String,String> map = new HashMap<>();
        String[] resultMapArr = resultMap.split("\n");
        for(String row : resultMapArr) {
            generateKeyValueAndPopMap(map,row);
        }
        return map;
    }

    private static void generateKeyValueAndPopMap(Map<String,String> map, String row) {
        int startIndex = row.indexOf("column");
        startIndex = row.indexOf("\"", startIndex) + 1;
        int endIndex = row.indexOf("\"", startIndex);
        String key = row.substring(startIndex,endIndex);

        startIndex = row.indexOf("property");
        startIndex = row.indexOf("\"", startIndex) + 1;
        endIndex = row.indexOf("\"", startIndex);
        String val = row.substring(startIndex,endIndex);
        map.put(key, val);
    }
}
