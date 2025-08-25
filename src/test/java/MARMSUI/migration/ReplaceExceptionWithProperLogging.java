package MARMSUI.migration;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReplaceExceptionWithProperLogging {
    private static String template = "Moving from %s --> %s";

    public static void main(String[] args) throws FileNotFoundException {
        String template = "throw commonUtil.generateVerboseMarmsException(clsName + \".%s - \" + %s,ErrorConstants.SYSTEM_ERROR_CODE);";
        String templateForQuotes = "throw commonUtil.generateVerboseMarmsException(clsName + \".%s - \" + \"%s\",ErrorConstants.SYSTEM_ERROR_CODE);";
        String fileName = userEntersFileName("Enter file name:");
        String[] toReplace = new String[]{"new DataException(","new SystemException("};
        FileInputStream fileInputStream = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fileInputStream);
        boolean methodReached = false;
        boolean insideMethod = false;
        String methodName = "";
        int lineNo = 0;
        while (scanner.hasNextLine()) {
            lineNo++;
            String line = scanner.nextLine();
            String pattern = "\\s{0,}\\w{6,7}\\s\\w{3,}\\s\\w{3,}[(\\s,\\w){]*";
            if (line.matches(pattern) && !line.contains("class")) {
                methodReached = true;
                methodName = extractMethodName(line);
                continue;
            }
            for(String replace : toReplace) {
                if (line.contains(replace)) {
                    System.out.println("line no: " + lineNo);
                    String[] val = extractValuesWithinParams(line);
                    if (val[1].equals("Y")) {
                        System.out.println(String.format(templateForQuotes, methodName, val[0]));
                    } else
                        System.out.println(String.format(template, methodName, val[0]));
                }
            }


        }
    }

    private static String[] extractValuesWithinParams(String line) {
        int start = line.indexOf("(");
        int end = line.indexOf(");");
        String[] returnVal = new String[2];
        String extractedString = line.substring(start + 1, end).trim();
        if (extractedString.startsWith("\"") && extractedString.endsWith("\"")) {
            returnVal[0] = extractedString.substring(1, extractedString.length() - 1);
            returnVal[1] = "Y";
        } else {
            returnVal[0] = extractedString;
            returnVal[1] = "N";
        }
        return returnVal;
    }

    public static String extractMethodName(String line) {
        line = line.trim();
        String[] arr = line.split(" ");
        if (StringUtils.isNotBlank(arr[2])) {
            String val = arr[2];
            int end = val.indexOf("(");
            return val.substring(0, end);
        }
        throw new RuntimeException("Method name not found");
    }

    private static String userEntersFileName(String printMsg) {
        System.out.println(printMsg);
        Scanner scanner = new Scanner(System.in);
        String fileName = null;
        while (true) {
            fileName = scanner.nextLine();
            if(StringUtils.isNotBlank(fileName)) {
                break;
            }
            System.out.println("Please enter another value, its invalid : ");
        }
        scanner.close();
        return fileName;
    }
}
