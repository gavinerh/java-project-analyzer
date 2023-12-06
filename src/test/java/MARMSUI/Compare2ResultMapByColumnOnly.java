package MARMSUI;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Compare2ResultMapByColumnOnly {
    public static void main(String[] args) {
        // clean the string by removing the \n, and ensure each column name is closed on same line
        String comparatorString = "<result column=\"SEQ_NO\" jdbcType=\"NUMERIC\" property=\"seqNo\"/>\n" +
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
                "        <result column=\"PRG_CD\" jdbcType=\"VARCHAR\" property=\"programCd\"/>\n" +
                "        <result column=\"TIER_TYPE_IND\" jdbcType=\"VARCHAR\" property=\"tierTypeInd\"/>\n" +
                "        <result column=\"QUAL_PROC_IND\" jdbcType=\"VARCHAR\" property=\"qualProcID\"/>\n" +
                "        <result column=\"TIER_STATUS_VAL_OFFSET\" jdbcType=\"NUMERIC\" property=\"valOffset\"/>\n" +
                "        <result column=\"TIER_STATUS_ACCUM_VAL_OFFSET\" jdbcType=\"NUMERIC\" property=\"accValOffset\"/>\n" +
                "        <result column=\"QUAL_SCHEME\" jdbcType=\"VARCHAR\" property=\"interim\"/>";
        String baseComparator = "<result column=\"PRG_CD\" jdbcType=\"VARCHAR\" property=\"programCd\"/>\n" +
                "        <result column=\"TIER_TYPE_IND\" jdbcType=\"VARCHAR\" property=\"tierTypeInd\"/>\n" +
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
                "        <result column=\"TIER_QUAL_PRD\" jdbcType=\"NUMERIC\" property=\"tierQuadPrd\"/>\n" +
                "        <result column=\"TIER_STATUS_VAL_REQ\" jdbcType=\"NUMERIC\" property=\"valReq\"/>\n" +
                "        <result column=\"TIER_STATUS_VAL_OFFSET\" jdbcType=\"NUMERIC\" property=\"valOffset\"/>\n" +
                "        <result column=\"TIER_STATUS_ACCUM_VAL_REQ\" jdbcType=\"NUMERIC\" property=\"accValReq\"/>\n" +
                "        <result column=\"TIER_STATUS_ACCUM_VAL_OFFSET\" jdbcType=\"NUMERIC\" property=\"accValOffset\"/>\n" +
                "        <result column=\"RULE_IND\" jdbcType=\"VARCHAR\" property=\"ruleInd\"/>\n" +
                "        <result column=\"START_DT\" jdbcType=\"TIMESTAMP\" property=\"startDate\"/>\n" +
                "        <result column=\"END_DT \" jdbcType=\"TIMESTAMP\" property=\"endDate\"/>\n" +
                "        <result column=\"QUAL_SCHEME\" jdbcType=\"VARCHAR\" property=\"qualInterim\"/>\n" +
                "        <result column=\"RULE_ACTIVE_IND\" jdbcType=\"VARCHAR\" property=\"active\"/>\n" +
                "        <result column=\"APPLICABLE_QLFY_IND\" jdbcType=\"VARCHAR\" property=\"applQualInd\"/>\n" +
                "        <result column=\"END_DT\" jdbcType=\"TIMESTAMP\" property=\"endDate\"/>\n" +
                "        <result column=\"YRS_IN_TIER\" jdbcType=\"NUMERIC\" property=\"yrsInTier\"/>\n" +
                "        <result column=\"PER_EM_PPS \" jdbcType=\"NUMERIC\" property=\"eliteOrPPSpercentage\"/>";

        List<String> comparatorColList = generateColList(comparatorString);
        List<String> baseColList = generateColList(baseComparator);
        Set<String> setOfBaseCol = generateSetFromList(baseColList);
        Set<String> setOfComparatorCol = generateSetFromList(comparatorColList);

        // find all the columns that are present in base and not in comparator
        System.out.println("List of columns that are in base but not in comparator:  --- ");
        printColumnsPresentListNotInSet(baseColList, setOfComparatorCol);

        // find all if any that are in comparator but not in base
        System.out.println("List of columns that are in comparator but not in base:  --- ");
        printColumnsPresentListNotInSet(comparatorColList, setOfBaseCol);
    }

    private static void printColumnsPresentListNotInSet(List<String> list, Set<String> set) {
        for(String s : list) {
            if(!set.contains(s)) {
                System.out.println(s);
            }
        }
    }

    private static Set<String> generateSetFromList(List<String> colList) {
        Set<String> set = new HashSet<>();
        for(String col : colList) {
            if(set.contains(col)) {
                throw new RuntimeException("Column " + col + " already exist");
            }
            set.add(col);
        }
        return set;
    }

    private static List<String> generateColList(String col) {
        String[] colArr = col.split("\n");
        List<String> list = new ArrayList<>();
        for(String s : colArr) {
            int startIndex = s.indexOf("\"") + 1;
            int endIndex = s.indexOf("\"", startIndex);
            list.add(s.substring(startIndex,endIndex));
        }
        return list;
    }
}
