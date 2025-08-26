package ComparingMethodContents;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class MethodBodyComparator {
    public static void main(String[] args) {

    }

    public static List<ComparisonResult> compareMethodBodies(Main.MethodInfo method1, Main.MethodInfo method2) {
        // must make sure the parameters and method names are the same before calling this method
        return compareAndFindAllDifferences(method1.getMethodBody(), method2.getMethodBody(), Main.generateUniqueMethodName(method1.getMethodName(),method1.getParameterTypes(),null));
    }

    private static List<ComparisonResult> compareAndFindAllDifferences(String formatted, String raw, String uniqueName) {
        int noOfLinesOfDiff = 0;
        ComparisonResult formattedComparison = new ComparisonResult(formatted,0);
        ComparisonResult rawComparison = new ComparisonResult(raw,0);
        List<ComparisonResult> comparisonResultList = List.of(formattedComparison,rawComparison);
        while(formattedComparison.getLastIndex() != formattedComparison.getContent().length() && rawComparison.getLastIndex() != rawComparison.getContent().length()) {
            boolean differenceFound = isThereDifference(comparisonResultList);
            if(differenceFound) {
                noOfLinesOfDiff++;
            }
        }
        if(noOfLinesOfDiff > 0) {
            System.out.println(String.format("Line Difference found for %s: %d", uniqueName, noOfLinesOfDiff));
//            System.out.println("Formatted Method Body with differences:\n" + formattedComparison.getContent());
//            System.out.println("Raw Method Body with differences:\n" + rawComparison.getContent());
            return comparisonResultList;
        } else {
            System.out.println("No differences found for " + uniqueName);
            return null;
        }
    }

    public static boolean isThereDifference(List<ComparisonResult> comparisonResultList) {
        ComparisonResult formattedComparison = comparisonResultList.get(0);
        ComparisonResult rawComparison = comparisonResultList.get(1);
        // returns the List of comparison result where the differences are found
        String formatted = formattedComparison.getContent();
        String raw = rawComparison.getContent();
        int left = formattedComparison.getLastIndex();
        int right = rawComparison.getLastIndex();
        String diffMsg = " <<<DIFF>>> ";
        boolean differenceFound = false;
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
                differenceFound = true;
                formattedComparison.setLastIndex(left++);
                rawComparison.setLastIndex(right++);
                insertDiffMsgInContent(formattedComparison,diffMsg);
                insertDiffMsgInContent(rawComparison,diffMsg);
                finishLineComparison(formattedComparison, rawComparison);
                return differenceFound;
            }
            right++;
            left++;
        }
        if(left != formatted.length()) { // right has finished
            String remaining = formatted.substring(left).trim();
            if(StringUtils.isNotBlank(remaining)) {
                // difference found
                formattedComparison.setLastIndex(left++);
                rawComparison.setLastIndex(right);
                insertDiffMsgInContent(formattedComparison,diffMsg);
                insertDiffMsgInContent(rawComparison,diffMsg);
                finishLineComparison(formattedComparison,rawComparison);
                differenceFound = true;
            }
        }
        if(right != raw.length()) { // left has finished
            String remaining = raw.substring(right).trim();
            if(StringUtils.isNotBlank(remaining)) {
                formattedComparison.setLastIndex(right++);
                rawComparison.setLastIndex(left);
                insertDiffMsgInContent(formattedComparison,diffMsg);
                insertDiffMsgInContent(rawComparison,diffMsg);
                finishLineComparison(formattedComparison,rawComparison);
                differenceFound = true;
            }
        }
        if(!differenceFound) {
            rawComparison.setLastIndex(rawComparison.getContent().length());
            formattedComparison.setLastIndex(formattedComparison.getContent().length());
        }
        return differenceFound; // return false if no difference found
    }

    private static void insertDiffMsgInContent(ComparisonResult comparisonResult, String diffMsg) {
        String content = comparisonResult.getContent();
        String newContent = content.substring(0,comparisonResult.getLastIndex()) + diffMsg + content.substring(comparisonResult.getLastIndex());
        comparisonResult.setContent(newContent);
        comparisonResult.setLastIndex(comparisonResult.getLastIndex() + diffMsg.length());
    }

    private static void finishLineComparison(ComparisonResult formatted, ComparisonResult raw) {
        String lineBreak = "\n";
        String formattedContentFromLastIndex = formatted.getContent().substring(formatted.getLastIndex());
        String rawContentFromLastIndex = raw.getContent().substring(raw.getLastIndex());
        int nextLineBreakInFormatted = formattedContentFromLastIndex.indexOf(lineBreak, formatted.getLastIndex());
        int nextLineBreakInRaw = rawContentFromLastIndex.indexOf(lineBreak, raw.getLastIndex());
        if(nextLineBreakInFormatted == -1) {
            nextLineBreakInFormatted = formatted.getContent().length() -1;
        }
        if(nextLineBreakInRaw == -1) {
            nextLineBreakInRaw = raw.getContent().length() -1;
        }
        formatted.setLastIndex(nextLineBreakInFormatted + 1);
        raw.setLastIndex(nextLineBreakInRaw + 1);
    }

    private static String generateListOfParams(List<String> params) {
        StringBuilder sb = new StringBuilder();
        for(String param : params) {
            sb.append(param).append("-");
        }
        return sb.toString();
    }

    private static String removeWhitespace(String input) {
        return input.replaceAll("\\s+", "");
    }

    private static void printOutDifferences(String body1, int index1, String body2, int index2) {
        System.out.println("Difference found:");
        System.out.println("Method 1: " + body1.substring(0, index1) + " <<<DIFF>>> " + body1.substring(index1));
        System.out.println("Method 2: " + body2.substring(0, index2) + " <<<DIFF>>> " + body2.substring(index2));
    }
}
