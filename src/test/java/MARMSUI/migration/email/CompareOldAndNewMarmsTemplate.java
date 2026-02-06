package MARMSUI.migration.email;

import MARMSUI.porting.FileOperation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CompareOldAndNewMarmsTemplate {

    public static final String OLD_TEMPLATE_PATH = "/Users/macuser/Documents/updated-lsl-app/marms/MARMS/Source Code/Business Components/Email Content";
    public static final String NEW_TEMPLATE_PATH = "/Users/macuser/Documents/updated-lsl-app/lsl-email/src/main/resources/emailtemplate";
    private static final String MASTER_SOURCE_FILE = "/Users/macuser/Desktop/holdingForTempFiles/stored-email-requests/compare-html-source.txt";
    private static final String DESTINATION_PATH = "/Users/macuser/Desktop/holdingForTempFiles/html-template-comparison";
    private static final String RESULT_FILE = "comparison-result.txt";
    private static final String CSV_RESULT_FILE = "simplified-results.csv";
    private static final String CSV_RESULT_FILE_2 = "simplified-results-2.csv";
    private static final String[] negativeIndicators = {
            "not found",
            "mismatch"
    };
    private static final String[] positiveIndicators = {
            "HTML content match",
            "Text content match"
    };
    private static final String[] negativeIndicatorsPrefixes = {
            "Old HTML content",
            "New HTML content",
            "Old Text content",
            "New Text content"
    };

    public static void main(String[] args) throws IOException {
        generateComparisonReport(); // run this first
//        generateSimplifiedReport(RESULT_FILE); // run this next, then use copilot to generate the excel file
//        addUsedByNewMarmsColumn(CSV_RESULT_FILE); // run this last, update the excel file name
    }

    private static void addUsedByNewMarmsColumn(String resultFile) throws FileNotFoundException {
        String values = "\"NMKPTPPREQ\",\n" +
                "    \"NMENROLACK\",\n" +
                "    \"NMpinackKF\",\n" +
                "    \"NMNtfyCNPPS\",\n" +
                "    \"NMstopmobile\",\n" +
                "    \"NMQPPREQUAL\",\n" +
                "    \"NMNtfyCMLoss\",\n" +
                "    \"NMESREQUAL\",\n" +
                "    \"NMHMAMLCNV\",\n" +
                "    \"NMYUCONVPPS\",\n" +
                "    \"NMKPTPPUPG\",\n" +
                "    \"NMNtfyAudRel\",\n" +
                "    \"NMNtfyCNAlrt\",\n" +
                "    \"NMNfAdRlPPS\",\n" +
                "    \"NMNTMLCONVPPS\",\n" +
                "    \"NMmlTrfMinor\",\n" +
                "    \"NMmlTrfGuard\",\n" +
                "    \"NMYUCONVKF\",\n" +
                "    \"NMTCMLCNVPPS\",\n" +
                "    \"NMXOMLCONV\",\n" +
                "    \"NMXOMLCONVPPS\",\n" +
                "    \"NMpwdackKF\",\n" +
                "    \"NMESREQUALST\",\n" +
                "    \"NMOTPPDWG\",\n" +
                "    \"NMCNSTAUTH\",\n" +
                "    \"NMKPQPPREQ\",\n" +
                "    \"NMRUMLCNV\",\n" +
                "    \"NMQRTPPDWG\",\n" +
                "    \"NMNTMLCONV\",\n" +
                "    \"NMHMAMLCNVPPS\",\n" +
                "    \"NMHAACMLCONV\",\n" +
                "    \"NMINTTPPUPG\",\n" +
                "    \"NMQPPUPGRADE\",\n" +
                "    \"NMQPPDWGES\",\n" +
                "    \"NMEGREQUAL\",\n" +
                "    \"NMNTPPDWG1\",\n" +
                "    \"NMNtfyCDPPS\",\n" +
                "    \"NMRUMLCNVPPS\",\n" +
                "    \"NMMLCONVPPS\",\n" +
                "    \"NMESDWG\",\n" +
                "    \"NMNtfyPDPA\",\n" +
                "    \"NMNTPPDWG2\",\n" +
                "    \"NMQPPDWGEG\",\n" +
                "    \"NMNtfyPDPPS\",\n" +
                "    \"NMcorpKFEG\",\n" +
                "    \"NMEGDWG\",\n" +
                "    \"NMMLCONV\",\n" +
                "    \"NMNTPPFDWG\",\n" +
                "    \"NMLMUAPPS\",\n" +
                "    \"NMNtfyCDKF\",\n" +
                "    \"NMKPLPPREQ\",\n" +
                "    \"NMTPPREQUAL\",\n" +
                "    \"NMEGUPGRADE\",\n" +
                "    \"NMGYMLCONV\",\n" +
                "    \"NMTPPUPGRADE\",\n" +
                "    \"NMHACMCNVPPS\",\n" +
                "    \"NMESUPGRADE\",\n" +
                "    \"NMNtfyCMPPS\",\n" +
                "    \"NMTCMLCNV\",\n" +
                "    \"NMRtrOALAck\",\n" +
                "    \"NMTCLNK\",\n" +
                "    \"NMRdpnUpdAck\",\n" +
                "    \"NMTCDLN\",\n" +
                "    \"NMstpMobPPS\",\n" +
                "    \"NMXOLNK\",\n" +
                "    \"NMXODLN\",\n" +
                "    \"NMHMADLN\",\n" +
                "    \"NMLNKMIR\",\n" +
                "    \"NMHMALNK\",\n" +
                "    \"NMLMUAEMAIL\",\n" +
                "    \"NMACCRDOBMPPS\",\n" +
                "    \"NMNotifyCA\",\n" +
                "    \"NMLNKCHILD\",\n" +
                "    \"NM1KFMRPPS\",\n" +
                "    \"NMprofileupd\",\n" +
                "    \"NMACCRITC\",\n" +
                "    \"NMACCRNM\",\n" +
                "    \"NMRdUpAkPPS\",\n" +
                "    \"NM1KFMRRWD\",\n" +
                "    \"NMLNKPARENT\",\n" +
                "    \"NMNTLNK\",\n" +
                "    \"NMprflupPPS\",\n" +
                "    \"NMROALAkPPS\",\n" +
                "    \"NMNTDLN\",\n" +
                "    \"NMXOLNKPPS\",\n" +
                "    \"NMLNKGUD\",\n" +
                "    \"NM1KFMRUSED\",\n" +
                "    \"NMNtfyCAPPS\",\n" +
                "    \"NMACCRDOBMKF\",\n" +
                "    \"NMmlTrmMinor\",\n" +
                "    \"NMENROLAUTH\",\n" +
                "    \"NMGYMLCONVPPS\",\n" +
                "    \"NMKFRtrAcc\",\n" +
                "    \"NMKFRtrHld\",\n" +
                "    \"NMKFRtrRej\",\n" +
                "    \"NMKFRtrAPPS\",\n" +
                "    \"NMKFRtrHPPS\",\n" +
                "    \"NMKFRtrRPPS\",\n" +
                "    \"NMENROLAUTH\",\n" +
                "    \"NMENRLAUTH_F\",\n" +
                "    \"NMAKFPLASTIC\",\n" +
                "    \"NMKFPLASTIC\",\n" +
                "    \"NMMergeReq\",\n" +
                "    \"NM20%KFMRRID\",\n" +
                "    \"NM20%KFMRRWD\",\n" +
                "    \"NMAUELITE\",\n" +
                "    \"NMAULPPS\",\n" +
                "    \"NMAULPPS2\",\n" +
                "    \"NMAULPPS3\",\n" +
                "    \"NMAULPPS4\",\n" +
                "    \"NMAULPPS5\",\n" +
                "    \"NMAUPPS\",\n" +
                "    \"NMAUPPS2\",\n" +
                "    \"NMAUPPS3\",\n" +
                "    \"NMAUPPS4\",\n" +
                "    \"NMAUPPS5\",\n" +
                "    \"NMDMELITE\",\n" +
                "    \"NMDMELITE6\",\n" +
                "    \"NMDMELITE6ID\",\n" +
                "    \"NMDMELITE7\",\n" +
                "    \"NMDMELITE7ID\",\n" +
                "    \"NMDMELITE8\",\n" +
                "    \"NMDMELITE8ID\",\n" +
                "    \"NMDMLPPS\",\n" +
                "    \"NMDMLPPS2\",\n" +
                "    \"NMDMLPPS3\",\n" +
                "    \"NMDMLPPS4\",\n" +
                "    \"NMDMLPPS5\",\n" +
                "    \"NMDMPPS\",\n" +
                "    \"NMDMPPS2\",\n" +
                "    \"NMDMPPS3\",\n" +
                "    \"NMDMPPS4\",\n" +
                "    \"NMDMPPS5\",\n" +
                "    \"NMEMPPS\",\n" +
                "    \"NMKFMRRWD\",\n" +
                "    \"NMKFMRRWDID\",\n" +
                "    \"NMKPEID\",\n" +
                "    \"NMKPELITE\",\n" +
                "    \"NMKSKRISHOP\",\n" +
                "    \"NMKSKRISHPID\",\n" +
                "    \"NMRVELITE\",\n" +
                "    \"NMRVELITE6\",\n" +
                "    \"NMRVELITE6ID\",\n" +
                "    \"NMRVLPPS\",\n" +
                "    \"NMRVLPPS2\",\n" +
                "    \"NMRVLPPS3\",\n" +
                "    \"NMRVLPPS4\",\n" +
                "    \"NMRVLPPS5\",\n" +
                "    \"NMRVPPS\",\n" +
                "    \"NMRVPPS2\",\n" +
                "    \"NMRVPPS3\",\n" +
                "    \"NMRVPPS4\",\n" +
                "    \"NMRVPPS5\",\n" +
                "    \"NMUGELITE\",\n" +
                "    \"NMUGELITE1\",\n" +
                "    \"NMUGLPPS\",\n" +
                "    \"NMUGLPPS1\",\n" +
                "    \"NMUGLPPS2\",\n" +
                "    \"NMUGLPPS3\",\n" +
                "    \"NMUGLPPS4\",\n" +
                "    \"NMUGLPPS5\",\n" +
                "    \"NMUGLPPS6\",\n" +
                "    \"NMUGLPPS7\",\n" +
                "    \"NMUGLPPS8\",\n" +
                "    \"NMUGLPPS9\",\n" +
                "    \"NMUGPPS\",\n" +
                "    \"NMUGPPS1\",\n" +
                "    \"NMUGPPS2\",\n" +
                "    \"NMUGPPS3\",\n" +
                "    \"NMUGPPS4\",\n" +
                "    \"NMUGPPS5\",\n" +
                "    \"NMUGPPS6\",\n" +
                "    \"NMUGPPS7\",\n" +
                "    \"NMUGPPS8\",\n" +
                "    \"NMUGPPS9\",\n" +
                "    \"NMUGVELITEP6\",\n" +
                "    \"NMUGVELITES6\",\n" +
                "    \"NMUGVELTP6ID\",\n" +
                "    \"NMUGVELTS6ID\"";
        Set<String> usedContentIds = Arrays.stream(values.split("\n")).map(x -> {
                    String id = x.replaceAll("\"", "").replaceAll(",", "").trim();
                    return id.substring(id.indexOf("NM") + 2);
                }).
                collect(Collectors.toSet());
        List<String> finalFileContents = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(DESTINATION_PATH + "/" + resultFile);
        Scanner scanner = new Scanner(fileInputStream);
        int count = -1;
        while (scanner.hasNextLine()) {
            count++;
            String line = scanner.nextLine();
            if (line.isBlank()) {
                continue;
            }
            if (count == 0) {
                finalFileContents.add(line + ",Used by New Marms");
                continue;
            }
            if (checkIfContentIdIsUsed(line, usedContentIds)) {
                finalFileContents.add(String.format("%s,Yes", line));
            } else {
                finalFileContents.add(String.format("%s,No", line));
            }
        }
        FileOperation.insertStringsToFile(finalFileContents, DESTINATION_PATH + "/" + CSV_RESULT_FILE_2);
    }

    private static boolean checkIfContentIdIsUsed(String line, Set<String> usedContentIds) {
        String contentId = line.substring(0, line.indexOf(","));
        return usedContentIds.contains(contentId);
    }

    private static void generateSimplifiedReport(String resultFile) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(DESTINATION_PATH + "/" + resultFile);
        Scanner scanner = new Scanner(fileInputStream);
        List<String> orderedContentId = new ArrayList<>();
        List<String> negativeResults = new ArrayList<>();
        String[] issues = {"Issue Found", "No Issue"};
        String currentTemplate = null;
        orderedContentId.add("Content ID,Status,Remarks");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isBlank()) {
                insertResultsForPreviousTemplate(orderedContentId, currentTemplate, issues, negativeResults);
            } else if (!line.startsWith("\t")) {
                currentTemplate = line;
            } else {
                if (line.contains(negativeIndicators[0]) || line.contains(negativeIndicators[1])) {
                    negativeResults.add(line.trim());
                }
            }
        }
        scanner.close();
        try {
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileOperation.insertStringsToFile(orderedContentId, DESTINATION_PATH + "/" + CSV_RESULT_FILE);
    }

    private static void insertResultsForPreviousTemplate(List<String> orderedContentId, String currentTemplate, String[] issues, List<String> negativeResults) {
        if (currentTemplate != null) {
            String separator = " | ";
            StringBuilder sb = new StringBuilder();
            sb.append(currentTemplate);
            sb.append(",");
            if (!negativeResults.isEmpty()) {
                sb.append(issues[0]);
                sb.append(",");
                for (String remark : negativeResults) {
                    sb.append(remark).append(separator);
                }
            } else {
                // no issues
                sb.append(issues[1]);
                sb.append(", ");
            }
            if (!negativeResults.isEmpty()) {
                orderedContentId.add(sb.substring(0, sb.length() - separator.length()));
            } else {
                orderedContentId.add(sb.toString());
            }
            negativeResults.clear();
        }
    }

    private static void generateComparisonReport() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(MASTER_SOURCE_FILE);
        Scanner scanner = new Scanner(fileInputStream);
        List<String> resultOfCopy = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = splitLine(line);
            String filePath = DESTINATION_PATH + "/" + parts[0];
            FileOperations.createDirectoryIfNotExists(filePath);
            resultOfCopy.add(parts[0]);
            copyContentsAndGenerateResults(parts, filePath, resultOfCopy);
        }
        scanner.close();
        fileInputStream.close();
        FileOperation.insertStringsToFile(resultOfCopy, DESTINATION_PATH + "/" + RESULT_FILE);
    }

    private static void copyContentsAndGenerateResults(String[] parts, String destinationPath, List<String> resultOfCopy) {
        String oldContentHtml = null;
        String newContentHtml = null;
        String oldContentTxt = null;
        String newContentTxt = null;
        boolean needsComparisonHtml = parts[3].equals("2") || parts[3].equals("1");
        boolean needsComparisonTxt = parts[3].equals("2") || parts[3].equals("0");
        int textComparisonError = 0;
        int htmlComparisonError = 0;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            if (!parts[1].isBlank() && (needsComparisonHtml)) {
                oldContentHtml = FileOperations.loadContentNoExceptionThrown(OLD_TEMPLATE_PATH, parts[1]);
                newContentHtml = FileOperations.loadContentNoExceptionThrown(NEW_TEMPLATE_PATH, parts[1]);
            }
            if (!parts[2].isBlank() && (needsComparisonTxt)) {
                oldContentTxt = FileOperations.loadContentNoExceptionThrown(OLD_TEMPLATE_PATH, parts[2]);
                newContentTxt = FileOperations.loadContentNoExceptionThrown(NEW_TEMPLATE_PATH, parts[2]);
            }

            if (oldContentHtml == null && needsComparisonHtml) {
                stringBuilder.append(String.format("\t%s %s", negativeIndicatorsPrefixes[0], negativeIndicators[0])).append("\n");
                htmlComparisonError++;
            }
            if (parts[0].trim().equals("MLCONV")) {
                System.out.println("here");
            }
            if (oldContentHtml != null)
                FileOperations.overwriteFile(oldContentHtml, destinationPath, "old-" + parts[1]);

            if (newContentHtml == null && needsComparisonHtml) {
                stringBuilder.append(String.format("\t%s %s", negativeIndicatorsPrefixes[1], negativeIndicators[0])).append("\n");
                htmlComparisonError++;
            }
            if (newContentHtml != null)
                FileOperations.overwriteFile(newContentHtml, destinationPath, "new-" + parts[1]);

            if (oldContentTxt == null && needsComparisonTxt) {
                stringBuilder.append(String.format("\t%s %s", negativeIndicatorsPrefixes[2], negativeIndicators[0])).append("\n");
                textComparisonError++;
            }
            if (oldContentTxt != null)
                FileOperations.overwriteFile(oldContentTxt, destinationPath, "old-" + parts[2]);

            if (newContentTxt == null && needsComparisonTxt) {
                stringBuilder.append(String.format("\t%s %s", negativeIndicatorsPrefixes[3], negativeIndicators[0])).append("\n");
                textComparisonError++;
            }
            if (newContentTxt != null)
                FileOperations.overwriteFile(newContentTxt, destinationPath, "new-" + parts[2]);

            if (htmlComparisonError == 0 && needsComparisonHtml) {
                // compare the old and new html content
                if (oldContentHtml != null && newContentHtml != null) {
                    if (!oldContentHtml.trim().equals(newContentHtml.trim()) && !equalByStringentComparison(oldContentHtml, newContentHtml)) {
                        stringBuilder.append(String.format("\tHTML content %s", negativeIndicators[1])).append("\n");
                    } else
                        stringBuilder.append(String.format("\t%s", positiveIndicators[0])).append("\n");
                } else
                    stringBuilder.append(String.format("\t%s", positiveIndicators[0])).append("\n");
            }
            if (textComparisonError == 0 && needsComparisonTxt) {
                // compare the old and new text content
                if (oldContentTxt != null && newContentTxt != null) {
                    if (!oldContentTxt.trim().equals(newContentTxt.trim()))
                        stringBuilder.append(String.format("\tText content %s", negativeIndicators[1])).append("\n");
                    else
                        stringBuilder.append(String.format("\t%s", positiveIndicators[1])).append("\n");
                }
            }
            resultOfCopy.add(stringBuilder.toString());
        } catch (Exception e) {
            System.out.println("Error copying contents for " + parts[0] + ": " + e.getMessage());
        }
    }

    private static boolean equalByStringentComparison(String oldContent, String newContent) {
        int oldIndex = 0;
        int newIndex = 0;
        char[] oldChars = oldContent.toCharArray();
        char[] newChars = newContent.toCharArray();
        int lastCount = Math.max(oldChars.length, newChars.length);
        while (oldIndex < oldChars.length || newIndex < newChars.length) {
            char oldC = oldChars[oldIndex];
            char newC = newChars[newIndex];
            if (oldC == ' ' || oldC == '\t' || oldC == '\n') {
                oldIndex++;
                continue;
            }
            if (newC == ' ' || newC == '\t' || newC == '\n') {
                newIndex++;
                continue;
            }
            if (newC != oldC) {
                return false;
            }
            newIndex++;
            oldIndex++;
        }
        boolean isEqual = (oldContent.substring(oldIndex).trim().equals(newContent.substring(newIndex).trim()));
        return isEqual;
    }

    private static String[] splitLine(String line) {
        String subString = line;
        String[] arr = new String[4];
        int count = 0;
        while (subString.contains("\t")) {
            int index = subString.indexOf("\t");
            arr[count] = subString.substring(0, index);
            subString = subString.substring(index + 1);
            count++;
        }
        arr[count] = subString;
        return arr;
    }
}
