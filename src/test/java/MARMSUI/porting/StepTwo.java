package MARMSUI.porting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StepTwo {
    public static List<String> modifyValues(Map<String,String> replacementMap, Map<String,String> intIdMap, List<List<String>> columnList, List<List<String>> valueList, List<String> tableList, long oldIntId, Set<String> tableNamesToIgnore) {
        List<String> modifiedSqlQueries = new ArrayList<>();
        for(int i=0; i<tableList.size(); i++) {
            String tableName = tableList.get(i);
            if(tableNamesToIgnore.contains(tableName)) {
                continue;
            }
            List<String> columns = columnList.get(i);
            List<String> values = valueList.get(i);
            for(int j=0; j<columns.size(); j++) {
                String columnName = columns.get(j);
                String currentValue = values.get(j);
                // check if the column needs to be replaced
                if(replacementMap.containsKey(columnName)) {
                    String newValue = replacementMap.get(columnName);
                    // handle quotes for string values
                    if (newValue == null) {
                        newValue = currentValue;
                    }
                    values.set(j, newValue);
                }
                if(currentValue.contains(String.valueOf(oldIntId))) {
                    currentValue = currentValue.replace(String.valueOf(oldIntId),intIdMap.get(String.valueOf(oldIntId)));
                    values.set(j, currentValue);
                }
            }
            // reconstruct the SQL insert statement
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("INSERT INTO ").append(tableName).append(" (");
            sqlBuilder.append(String.join(", ", columns));
            sqlBuilder.append(") VALUES (");
            sqlBuilder.append(String.join(", ", values));
            sqlBuilder.append(");");
            modifiedSqlQueries.add(sqlBuilder.toString());
        }
        return modifiedSqlQueries;
    }
}
