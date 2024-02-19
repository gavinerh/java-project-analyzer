package MARMSUI.SpecialisedSqlMappingToVo;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class T {
    static List<String> fieldDeclarations = new ArrayList<>();
    static Set<String> requiredFields = new HashSet<>();
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/Users/macuser/Documents/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/vo/dynamicStatements/DynamicStatementTransactions.java";
        String s = "<result column=\"TRANSACTION_TYPE\" jdbcType=\"VARCHAR\" property=\"transactionType\"/>\n" +
                "        <result column=\"TRANS_CD\" jdbcType=\"VARCHAR\" property=\"transCd\"/>\n" +
                "        <result column=\"BATCH_DATE\" jdbcType=\"TIMESTAMP\" property=\"batchDate\"/>\n" +
                "        <result column=\"PTS_AWDED\" jdbcType=\"NUMERIC\" property=\"ptsAwded\"/>\n" +
                "        <result column=\"TRANS_XREF_ID\" jdbcType=\"VARCHAR\" property=\"transXrefId\"/>\n" +
                "        <result column=\"PRT_CD\" jdbcType=\"VARCHAR\" property=\"prtCd\"/>\n" +
                "        <result column=\"AWD_DESC\" jdbcType=\"VARCHAR\" property=\"awdDesc\"/>\n" +
                "        <result column=\"TRANS_DATE\" jdbcType=\"TIMESTAMP\" property=\"transDate\"/>\n" +
                "        <result column=\"REMARKS\" jdbcType=\"VARCHAR\" property=\"remarks\"/>\n" +
                "        <result column=\"ELITE_PTS\" jdbcType=\"NUMERIC\" property=\"elitePts\"/>\n" +
                "        <result column=\"PPS_PTS\" jdbcType=\"NUMERIC\" property=\"ppsPts\"/>\n" +
                "        <result column=\"SECT_CNT\" jdbcType=\"DOUBLE\" property=\"sectCnt\"/>\n" +
                "        <result column=\"PPS_VAL\" jdbcType=\"NUMERIC\" property=\"ppsVal\"/>\n" +
                "        <result column=\"BATCH_ID\" jdbcType=\"VARCHAR\" property=\"batchId\"/>\n" +
                "        <result column=\"SUPERVISOR_ID\" jdbcType=\"VARCHAR\" property=\"supervisorId\"/>\n" +
                "        <result column=\"MERGE_ACCUM_NAT_PTS\" jdbcType=\"NUMERIC\" property=\"mergeAccumNatPts\"/>\n" +
                "        <result column=\"MERGE_CUR_ELITE_PTS\" jdbcType=\"NUMERIC\" property=\"mergeCurElitePts\"/>\n" +
                "        <result column=\"MERGE_CUR_PPS_PTS\" jdbcType=\"NUMERIC\" property=\"mergeCurPpsPts\"/>\n" +
                "        <result column=\"MERGE_CUR_PPS_SECT_CNT\" jdbcType=\"NUMERIC\" property=\"mergeCurPpsSectCnt\"/>\n" +
                "        <result column=\"MERGE_ACCUM_ELITE_PTS\" jdbcType=\"NUMERIC\" property=\"mergeAccumElitePts\"/>\n" +
                "        <result column=\"MERGE_ACCUM_PPS_PTS\" jdbcType=\"NUMERIC\" property=\"mergeAccumPpsPts\"/>\n" +
                "        <result column=\"MERGE_ACCUM_PPS_SECT_CNT\" jdbcType=\"DOUBLE\" property=\"mergeAccumPpsSectCnt\"/>\n" +
                "        <result column=\"MERGE_ELITE_QUAL_DT\" jdbcType=\"TIMESTAMP\" property=\"mergeEliteQualDt\"/>\n" +
                "        <result column=\"MERGE_ELITE_EXP_DT\" jdbcType=\"TIMESTAMP\" property=\"mergeEliteExpDt\"/>\n" +
                "        <result column=\"MERGE_PPS_QUAL_DT\" jdbcType=\"TIMESTAMP\" property=\"mergePpsQualDt\"/>\n" +
                "        <result column=\"MERGE_PPS_EXP_DT\" jdbcType=\"TIMESTAMP\" property=\"mergePpsExpDt\"/>\n" +
                "        <result column=\"UNMERGE_ACCUM_NAT_PTS\" jdbcType=\"NUMERIC\" property=\"unmergeAccumNatPts\"/>\n" +
                "        <result column=\"UNMERGE_CUR_ELITE_PTS\" jdbcType=\"NUMERIC\" property=\"unmergeCurElitePts\"/>\n" +
                "        <result column=\"UNMERGE_CUR_PPS_PTS\" jdbcType=\"NUMERIC\" property=\"unmergeCurPpsPts\"/>\n" +
                "        <result column=\"UNMERGE_CUR_PPS_SECT_CNT\" jdbcType=\"NUMERIC\" property=\"unmergeCurPpsSectCnt\"/>\n" +
                "        <result column=\"UNMERGE_ACCUM_ELITE_PTS\" jdbcType=\"NUMERIC\" property=\"unmergeAccumElitePts\"/>\n" +
                "        <result column=\"UNMERGE_ACCUM_PPS_PTS\" jdbcType=\"NUMERIC\" property=\"unmergeAccumPpsPts\"/>\n" +
                "        <result column=\"UNMERGE_ACCUM_PPS_SECT_CNT\" jdbcType=\"NUMERIC\" property=\"unmergeAccumPpsSectCnt\"/>\n" +
                "        <result column=\"UNMERGE_ELITE_QUAL_DT\" jdbcType=\"TIMESTAMP\" property=\"unmergeEliteQualDt\"/>\n" +
                "        <result column=\"UNMERGE_ELITE_EXP_DT\" jdbcType=\"TIMESTAMP\" property=\"unmergeEliteExpDt\"/>\n" +
                "        <result column=\"UNMERGE_PPS_QUAL_DT\" jdbcType=\"TIMESTAMP\" property=\"unmergePpsQualDt\"/>\n" +
                "        <result column=\"UNMERGE_PPS_EXP_DT\" jdbcType=\"TIMESTAMP\" property=\"unmergePpsExpDt\"/>\n" +
                "        <result column=\"MERGE_CUR_PPS_VAL\" jdbcType=\"NUMERIC\" property=\"mergeCurPpsVal\"/>\n" +
                "        <result column=\"MERGE_CUM_PPS_VAL\" jdbcType=\"NUMERIC\" property=\"mergeCumPpsVal\"/>\n" +
                "        <result column=\"MERGE_CUM_LIFETIME_PPS_VAL\" jdbcType=\"NUMERIC\" property=\"mergeCumLifetimePpsVal\"/>\n" +
                "        <result column=\"UNMERGE_CUR_PPS_VAL\" jdbcType=\"NUMERIC\" property=\"unmergeCurPpsVal\"/>\n" +
                "        <result column=\"UNMERGE_CUM_PPS_VAL\" jdbcType=\"NUMERIC\" property=\"unmergeCumPpsVal\"/>\n" +
                "        <result column=\"UNMERGE_CUM_LIFETIME_PPS_VAL\" jdbcType=\"NUMERIC\" property=\"unmergeCumLifetimePpsVal\"/>";
        extractPropertyField(s);
        printingFieldDeclaration(fileName);
    }

    private static void printingFieldDeclaration(String filename) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        CompilationUnit cu = StaticJavaParser.parse(fileInputStream);

        // Visit and print the methods names
        new FieldVisitor().visit(cu, null);
    }

    private static void extractPropertyField(String s) {
        String[] arr = s.split("\n");
        String key = "property=\"";
        for(String line : arr) {
            int start = line.indexOf(key) + key.length();
            int end = line.indexOf("\"", start);
            requiredFields.add(line.substring(start,end));
        }
    }

    private static class FieldVisitor extends VoidVisitorAdapter<Void> {
        @Override
        public void visit(FieldDeclaration fd, Void arg) {
            super.visit(fd, arg);
            if(requiredFields.contains(fd.getVariable(0).getName().asString())){
                System.out.println(String.format("private %s %s;",fd.getElementType(),fd.getVariable(0).getName()));
            }
        }
    }
}