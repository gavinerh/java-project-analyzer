package MARMSUI.porting;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StepThree {

    static String templateForIntId = "DELETE FROM %s WHERE %s = %d;";
    static String templateForOthers = "DELETE FROM %s WHERE %s";
    static String conditionTemplate = "%s = %s";
    public static List<String> generateDeleteQueries(List<String> tableList, List<List<String>> columnList, List<List<String>> valueList, long replacementIntId) {
        List<String> deleteQueries = new ArrayList<>();
        Set<String> tableProcessed = new HashSet<>();
        for(int i=0; i<tableList.size(); i++) {
            if(tableProcessed.contains(tableList.get(i))) {
                continue;
            }
            List<String> columnsPerRow = columnList.get(i);
            if(columnsPerRow.contains("INT_ID")) {
                deleteQueries.add(String.format(templateForIntId, tableList.get(i), "INT_ID", replacementIntId));
                tableProcessed.add(tableList.get(i));
            } else {
                String conditions = iterateOverColumnAndPopulateCondition(columnsPerRow,valueList.get(i));
                deleteQueries.add(String.format(templateForOthers, tableList.get(i), conditions));
            }
        }
        return deleteQueries;
    }

    private static String iterateOverColumnAndPopulateCondition(List<String> columnsPerRow, List<String> valuesPerRow) {
        StringBuilder conditions = new StringBuilder();
        for(int j=0; j<columnsPerRow.size(); j++) {
            String condition = String.format(conditionTemplate, columnsPerRow.get(j), valuesPerRow.get(j));
            if(conditions.isEmpty()) {
                conditions.append(condition);
            } else {
                conditions.append(" AND ").append(condition);
            }
        }
        return conditions.toString();
    }
}
