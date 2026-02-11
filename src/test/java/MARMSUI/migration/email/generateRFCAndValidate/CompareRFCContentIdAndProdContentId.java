package MARMSUI.migration.email.generateRFCAndValidate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CompareRFCContentIdAndProdContentId {

    private static String PROD_DTL_PATH = "/Users/macuser/Desktop/holdingForTempFiles/stored-email-requests/dtls_content.txt";
    private static String PROD_MSTR_PATH = "/Users/macuser/Desktop/holdingForTempFiles/stored-email-requests/mstr_content.txt";
    private static String RFC_DTL_PATH = "/Users/macuser/Desktop/holdingForTempFiles/stored-email-requests/dtls_rfc.txt";
    private static String RFC_MSTR_PATH = "/Users/macuser/Desktop/holdingForTempFiles/stored-email-requests/mstr_rfc.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Set<String> prodContentIdsDtls = extractUniqueContentIdsFromFile(PROD_DTL_PATH, 4);
        Set<String> prodContentIdsMstr = extractUniqueContentIdsFromFile(PROD_MSTR_PATH, 0);
        Set<String> rfcContentIdsDtls = extractUniqueContentIdsFromFile(RFC_DTL_PATH, 4);
        Set<String> rfcContentIdsMstr = extractUniqueContentIdsFromFile(RFC_MSTR_PATH, 4);
        List<String> duplicateContentDtls = findDuplicateContentIds(prodContentIdsDtls, rfcContentIdsDtls);
        List<String> duplicateContentMstr = findDuplicateContentIds(prodContentIdsMstr, rfcContentIdsMstr);
        printDuplicateContentIds(duplicateContentDtls, "DTLS");
        printDuplicateContentIds(duplicateContentMstr, "MSTR");
    }

    private static Set<String> extractUniqueContentIdsFromFile(String path, int index) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        Scanner scanner = new Scanner(fileInputStream);
        Set<String> contentIds = new java.util.HashSet<>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] arr = line.split("\t");
            if(arr.length < index + 1) {
                System.out.println("invalid line: " + line);
            }
            contentIds.add(arr[index].trim());
        }
        return contentIds;
    }

    private static List<String> findDuplicateContentIds(Set<String> prodContentIds, Set<String> rfcContentIds) {
        List<String> duplicateContentIds = new java.util.ArrayList<>();
        for(String contentId : rfcContentIds) {
            if(prodContentIds.contains(contentId)) {
                duplicateContentIds.add(contentId);
            }
        }
        return duplicateContentIds;
    }

    private static void printDuplicateContentIds(List<String> duplicateContentIds, String fileType) {
        System.out.println("Duplicate content IDs in " + fileType + ":");
        for(String contentId : duplicateContentIds) {
            System.out.println(contentId);
        }
    }
}
