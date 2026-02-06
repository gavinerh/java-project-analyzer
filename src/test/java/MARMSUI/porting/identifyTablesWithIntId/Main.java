package MARMSUI.porting.identifyTablesWithIntId;

import MARMSUI.porting.FileOperation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/Users/macuser/Desktop/holdingForTempFiles/force-qual-kf-porting/ported-kf-dec/fetching-table-details/oracle_columns_20251226_113553.csv";
        String keyColumnToIdentify = "\"INT_ID\"";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fileInputStream);
        Set<String> tablesWithIntId = new HashSet<>();
        // key: table name, value: list of columns with int_id
        Map<String, List<String>> tableToColumnsMap = new HashMap<>();
        int count = 0;
        while(scanner.hasNextLine()) {
            if(count == 0) {
                //skip header
                scanner.nextLine();
                count++;
                continue;
            }
            String line = scanner.nextLine();
            String[] splits = line.split(",");
            String columnName = splits[2];
            String tableName = splits[1];
            List<String> columnsList = tableToColumnsMap.get(tableName);
            if(!tableToColumnsMap.containsKey(tableName)) {
                columnsList = new ArrayList<>();
                tableToColumnsMap.put(tableName,columnsList);
            }
            columnsList.add(columnName);
            if(columnName.equals(keyColumnToIdentify)) {
                tablesWithIntId.add(tableName);
            }
        }

        // remove the tables without int_id from the map
        tableToColumnsMap.keySet().retainAll(tablesWithIntId);

        String fileContainingTableNamesWithIntId = "/Users/macuser/Desktop/holdingForTempFiles/force-qual-kf-porting/ported-kf-dec/fetching-table-details/tables-with-int-id.txt";
        List<String> listToSave = new ArrayList<>();
        for(String key : tableToColumnsMap.keySet()) {
            listToSave.add(String.format("%s-%s",key,tableToColumnsMap.get(key).toString()));
        }
        FileOperation.insertStringsToFile(listToSave,fileContainingTableNamesWithIntId);
    }
}
