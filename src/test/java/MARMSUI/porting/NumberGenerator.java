package MARMSUI.porting;

public class NumberGenerator {

    static String[] onesString = new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static String[] onesInt = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    static String[] teensString = new String[]{"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    static String[] teensInt = new String[]{"11", "12", "13", "14", "15", "16", "17", "18", "19"};
    static String[] tensString = new String[]{"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    static String[] tensInt = new String[]{"10", "20", "30", "40", "50", "60", "70", "80", "90"};

    public static Object[] identifyNextCount(int currentCount) {

        int nextVal = currentCount + 1;
        return extractStringValueFromIntValue(nextVal);
    }


    private static Object[] extractStringValueFromIntValue(int nextVal) {
        if (nextVal > 10 && nextVal < 20) {
            // check teensString and return
            return new Object[]{nextVal, makeFirstLetterUpper(teensString[identifyTeensIndex(String.valueOf(nextVal), teensInt)])};
        }
        if (nextVal < 10) {
            return new Object[]{nextVal, makeFirstLetterUpper(onesString[identifyOnesIndex(String.valueOf(nextVal), onesInt)])};
        }
        int tensValue = nextVal / 10; // get tens value
        int onesValue = nextVal % 10; // get ones value
        if (onesValue == 0) {
            return new Object[]{nextVal, makeFirstLetterUpper(tensString[identifyTensIndex(String.valueOf(nextVal), tensInt)])};
        }
        return new Object[]{nextVal, makeFirstLetterUpper(tensString[tensValue - 1]) + onesString[onesValue - 1]};
    }

    private static String makeFirstLetterUpper(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private static int identifyOnesIndex(String currentCount, String[] onesString) {
        for (int i = 0; i < onesString.length; i++) {
            if (currentCount.endsWith(onesString[i])) {
                return i; // return 1-based index
            }
        }
        return -1; // not found
    }

    private static int identifyTensIndex(String currentCount, String[] tensString) {
        for (int i = 0; i < tensString.length; i++) {
            if (currentCount.startsWith(tensString[i])) {
                return i; // return 1-based index
            }
        }
        return -1; // not found
    }

    private static int identifyTeensIndex(String currentCount, String[] teensString) {
        for (int i = 0; i < teensString.length; i++) {
            if (currentCount.startsWith(teensString[i])) {
                return i; // return 1-based index starting from 11
            }
        }
        return -1; // not found
    }
}
