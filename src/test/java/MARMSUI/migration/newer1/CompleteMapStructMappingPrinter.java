package MARMSUI.migration.newer1;

import MARMSUI.migration.ExtractFieldsFromClass;
import MARMSUI.migration.model.Type;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class CompleteMapStructMappingPrinter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("** Use this when you want to create MapStruct interface from BaseResultMap **");
                execute(scanner);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void execute(Scanner scanner) throws Exception {
        int count = 0;
        String rawSqlQuery = "";
        String resultSetGetColumn = "";
        List<String> sourceFields = null;
        List<String> targetFieldList = null;
        String sourcePath = "";
        String sourceParent = "";
        String targetPath = "";
        String targetParent = "";
        while (true) {
            switch (count) {
                case 0:
                    rawSqlQuery = getUserInput(scanner, "Enter sql query with columns");
                    count++;
                    break;
                case 1:
                    resultSetGetColumn = getUserInput(scanner, "Paste in the resultSet.getString(\"Column name\") if any, only enter when (select *) is used");
                    count++;
                    break;
                case 2: // check the above results
                    if (!rawSqlQuery.isBlank() && !resultSetGetColumn.isBlank()) {
                        System.out.println("Both rawSql and resultSet are entered");
                        count = 0;
                        break;
                    }
                    count++;
                    break;
                case 3: // handle previous inputs
                    List<String> cols = null;
                    if (StringUtils.isNotBlank(rawSqlQuery)) {
                        rawSqlQuery = CompleteSqlQueryConverter.extractSqlQuery(rawSqlQuery);
                        String extractedCol = CompleteSqlQueryConverter.extractColumnsFromSql(rawSqlQuery);
                        cols = CompleteSqlQueryConverter.generateColumnArr(extractedCol);
                    } else if (StringUtils.isNotBlank(resultSetGetColumn)) {
                        cols = extractColsFromResultSetGetter(resultSetGetColumn);
                        if (cols == null) {
                            System.out.println("You have not entered a resultSet with .get identifier");
                            count = 1;
                            break;
                        }
                    }
                    sourceFields = extractSourceFields(cols);
                    count++;
                    break;
                case 4:
                    String targetFields = getUserInput(scanner, "Enter the eg. resultSet.getString( after the rs.next() expression ");
                    String objVariable = getUserInput(scanner, "Enter the \"obj\" variable name calling setter in resultMap eg. obj.setLastChangeDate ");
                    targetFieldList = CompleteSqlQueryConverter.extractFieldsFromSetter(targetFields.split(";"), objVariable);
                    if (targetFieldList.size() != sourceFields.size()) {
                        System.out.println("Target and source does not match");
                        count = 0;
                        break;
                    }
                    count++;
                    break;
                case 5: // get the file paths
                    sourcePath = getUserInput(scanner, "Enter the full path of class of the source (BaseResultMap)");
                    sourceParent = getUserInput(scanner, "Enter any source's parent class file path (BaseResultMap)");
                    targetPath = getUserInput(scanner, "Enter full path of class of the target");
                    targetParent = getUserInput(scanner, "Enter the path of parent target inherited path");
                    isValidFile(sourcePath, false);
                    isValidFile(sourceParent, true);
                    isValidFile(targetPath, false);
                    isValidFile(targetParent, true);
                    count++;
                    break;
                case 6: // extract actual fields of target and source and validate
                    Map<String, Type> source = new HashMap<>();
                    ExtractFieldsFromClass.execute(new FileInputStream(sourcePath), source);
                    if (StringUtils.isNotBlank(sourceParent)) {
                        ExtractFieldsFromClass.execute(new FileInputStream(sourceParent), source);
                    }
                    Map<String, Type> target = new HashMap<>();
                    ExtractFieldsFromClass.execute(new FileInputStream(targetPath), target);
                    if (StringUtils.isNotBlank(targetParent)) {
                        ExtractFieldsFromClass.execute(new FileInputStream(targetParent), target);
                    }
                    if (!validateFields(target, targetFieldList) || !validateFields(source, sourceFields)) {
                        count = 10;
                        return;
                    }
                    count++;
                    break;
                case 7: // print the mapStruct contents
                    String template = "@Mapping(target=\"%s\", source=\"%s\")";
                    for (int i = 0; i < targetFieldList.size(); i++) {
                        System.out.println(String.format(template, targetFieldList.get(i), sourceFields.get(i)));
                    }
                default:
                    targetFieldList.clear();
                    sourceFields.clear();
                    return;
            }
        }
    }

    private static boolean validateFields(Map<String, Type> actual, List<String> fromCode) {
        for (String s : fromCode) {
            if (!actual.containsKey(s.toLowerCase())) {
                System.out.println(s + " is not found in class file");
                return false;
            }
        }
        return true;
    }

    private static List<String> extractSourceFields(List<String> cols) {
        List<String> modified = new ArrayList<>();
        boolean changeNextToUpper = false;
        for (String col : cols) {
            String lowered = col.toLowerCase();
            String val = "";
            for (int i = 0; i < lowered.length(); i++) {
                if (lowered.substring(i, i + 1).equalsIgnoreCase("_")) {
                    changeNextToUpper = true;
                    continue;
                }
                if (changeNextToUpper) {
                    val += lowered.substring(i, i + 1).toUpperCase();
                    changeNextToUpper = false;
                } else {
                    val += lowered.substring(i, i + 1);
                }
            }
            modified.add(val);
        }
        return modified;
    }

    private static boolean isValidFile(String path, boolean isParent) {
        if (StringUtils.isBlank(path) && isParent) {
            return true;
        }
        return !new File(path).exists();
    }

    private static List<String> extractColsFromResultSetGetter(String resultSetGet) {
        String identifier = ".get";
        String[] arr = resultSetGet.split(";");
        List<String> cols = new ArrayList<>();
        for (String row : arr) {
            if (!row.contains(identifier)) {
                return null;
            }
            int start = row.indexOf(identifier) + identifier.length();
            start = row.indexOf("\"", start) + 1;
            int end = row.indexOf("\"", start);
            cols.add(row.substring(start, end));
        }
        return cols;
    }

    public static String getUserInput(Scanner scanner, String printedMsg) {
        System.out.println(printedMsg + " enter (-) to skip:");
        StringBuilder stringBuilder = new StringBuilder();
        String toReturnString = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("-")) {
                toReturnString = stringBuilder.toString();
                return toReturnString;
            } else {
                stringBuilder.append(line);
            }
        }
        return null;
    }
}
