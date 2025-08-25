package MARMSUI.migration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueryConv {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/impl/migration/TierHandlerServiceImpl.java";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fileInputStream);
        List<String> params = new ArrayList<>();
        int lineNo = 0;
        String auditIdString = "";
        boolean printedAuditId = false;
        while (scanner.hasNextLine()) {
            lineNo++;
            String line = scanner.nextLine();
            if(line.trim().isBlank()) {
                continue;
            }
            if(line.contains("new Query()") || line.contains("query.reset()")) {
                params.clear();
                auditIdString = "";
                printedAuditId = false;
            }
            if(line.contains("database.update(")) {
                // use update method
                System.out.println("Line: " + lineNo + "\n");
                String variableName = extractVariableName(".update(",line);
                String paramToInsert = extractUpdateParam(".update(", line);
            } else if (line.contains("database.getQuery(") || line.contains(".getQuery")) {
                System.out.println("Line: " + lineNo);
                printStatement(".getQuery(", line,params);
            } else if (line.contains("database.setQuery(") || line.contains(".setQuery")) {
                System.out.println("Line: " + lineNo);
                if(!printedAuditId) {
                    System.out.println("adminFeesMapper.setAuditId(\"\");");
                }
                printStatement(".setQuery(",line, params);
            } else if (line.contains("query.bind(") || line.contains("qry.bind(") || line.contains(".bind(")) {
                params.add(extractStringWithinParenthesis(".bind(", line));
            } else if ((line.contains("query.setAuditId(") || line.contains("qry.setAuditId(")) && !line.contains("adminFeesMapper.setAuditId(")) {
                auditIdString = extractStringWithinParenthesis(".setAuditId(", line);
                System.out.println("Line " + lineNo);
                System.out.println(String.format("adminFeesMapper.setAuditId(%s);",auditIdString));
                printedAuditId = true;
            }
        }
    }

    private static String extractUpdateParam(String key, String line) {
        int start = key.indexOf(key) + key.length();
        start = line.indexOf(",", start) + 1;
        int end = line.indexOf(");", start);
        return line.substring(start,end).trim();
    }

    private static void printStatement(String key, String line, List<String> params) {
        if(line.contains("Collection daoResult = database.getQuery(ControlParamData.class, \"getCtrlParaRef\", query123)")) {
            System.out.println("Reached here");
        }
        String variableName = extractVariableName(key, line);
        String methodName = extractMethodName(key,line);
        String paramString =  generateParamString(params);
        System.out.println(String.format("%s.%s(%s);", variableName,methodName,paramString));
    }

    private static String extractStringWithinParenthesis(String key, String line) {
        int start = line.indexOf(key) + key.length();
        int end = line.indexOf(");", start);
        return line.substring(start,end);
    }

    private static String generateParamString(List<String> params) {
        if(params.isEmpty()) {
            return "";
        }
        String str = "";
        for(String s : params) {
            str += s + ",";
        }
        return str.substring(0,str.length()-1);
    }

    private static String extractMethodName(String key, String line) {
        try {
            int start = line.indexOf(key) + key.length();
            start = line.indexOf("\"", start) + 1;
            int end = line.indexOf("\"", start);
            return line.substring(start,end);
        } catch (Exception e) {
            System.out.println("Error here");
        }
        return null;
    }

    private static String extractVariableName(String key, String line) {
        int start = line.indexOf(key) + key.length();
        int end = line.indexOf(".class", start);
        String className = line.substring(start,end);
        return convertToVariableName(className);
    }

    private static String convertToVariableName(String className) {
        return className.substring(0,1).toLowerCase() + className.substring(1);
    }
}
