package MARMSUI.methodcopier;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class SqlQueryComparator {
    public static void main(String[] args) {
        while(true) {
            String formatted = receiveFormattedQuery();
            String raw = receiveRawquery();
             compare(formatted.toLowerCase(),raw.toLowerCase(), true);

        }
    }

    public static int compare(String formatted, String raw, boolean toPrintSuccessMsg) {
        int left = 0;
        int right = 0;
        while(left != formatted.length() && right != raw.length()) {
            char leftChar = formatted.charAt(left);
            char rightChar = raw.charAt(right);
            if(leftChar == ' ') {
                left++;
                continue;
            }
            if(rightChar == ' ') {
                right++;
                continue;
            }
            if(rightChar != leftChar) {
                System.out.println("Difference found");
                System.out.println("Formatted: " + formatted.substring(0,left) + " <<<DIFF>>> " + formatted.substring(left));
                System.out.println("Updated: " + raw.substring(0,right) + " <<<DIFF>>> " + raw.substring(right));
                return -1;
            }
            right++;
            left++;
        }
        if(left < 20 || right < 20) {
            System.out.println("Comparison is not valid, inputs are too short");
            return -1;
        }
        if(left != formatted.length()) {
            String remaining = formatted.substring(left).trim();
            if(StringUtils.isNotBlank(remaining)) {
                System.out.println("Difference found");
                System.out.println("Formatted: " + formatted.substring(0,left) + " <<<DIFF>>> " + formatted.substring(left));
                System.out.println("Raw: " + raw.substring(0,right) + " <<<DIFF>>> " + raw.substring(right));
                return -1;
            }
        }
        if(right != raw.length()) {
            String remaining = raw.substring(right).trim();
            if(StringUtils.isNotBlank(remaining)) {
                System.out.println("Difference found");
                System.out.println("Formatted: " + formatted.substring(0,left) + " <<<DIFF>>> " + formatted.substring(left));
                System.out.println("Raw: " + raw.substring(0,right) + " <<<DIFF>>> " + raw.substring(right));
                return -1;
            }
        }
        System.out.println("Comparison is successful, inputs are similar");
        return 0;
    }

    private static String receiveRawquery() {
        System.out.println("Enter query from current marms source code:");
        String input = receiveUserInput();
        input = input.replaceAll("\n", " ");
        input = input.replaceAll("\t"," ");
        String result = extractValuesFromQuotes(input);
        return result;
    }

    private static String extractValuesFromQuotes(String input) {
        StringBuilder result = new StringBuilder();
        int start = 0;
        int end = 0;
        while(true) {
            start = input.indexOf("\"", end+1) + 1;
            if(start == 0) {
                break;
            }
            end = input.indexOf("\"", start);
            result.append(input.substring(start,end));
        }
        return result.toString();
    }

    private static String receiveFormattedQuery() {
        System.out.println("Enter query from formatted query:");
        String input = receiveUserInput();
        // remove #{} and replace with ?
        input = input.replaceAll("\n", " ");
        input = input.replaceAll("\t"," ");
        input = removeMybatisPattern(input);
        return input;
    }

    private static String removeMybatisPattern(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        int startOfParams = 0;
        for(char c : input.toCharArray()) {
            if(c == '#') {
                startOfParams++;
                continue;
            }
            if(c == '{' && startOfParams == 1) {
                startOfParams++;
                continue;
            }
            if(c == '}' && startOfParams == 2) {
                startOfParams = 0;
                stringBuilder.append("?");
                continue;
            }
            if(startOfParams == 0) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    private static String receiveUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        while (scanner.hasNextLine()) {
            String temp = scanner.nextLine();
            if(temp.equals("c")) {
                break;
            }
            input += temp;
        }
        return input;
    }
}
