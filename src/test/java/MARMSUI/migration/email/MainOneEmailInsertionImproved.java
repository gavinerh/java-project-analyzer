package MARMSUI.migration.email;

import java.io.FileInputStream;
import java.util.*;

public class MainOneEmailInsertionImproved {
    private static final String sourceMstrFile = "/Users/macuser/Desktop/holdingForTempFiles/stored-email-requests/mstr_content.txt";
    private static final Set<String> contentIds = new HashSet<>();
    private static final String values = "pinackPPS\n" +
            "pwdackPps";

    public static void main(String[] args) {
        String[] arr = values.split("\n");
        contentIds.addAll(Arrays.stream(arr).toList());
        List<String> requiredRows = readMstrFile(contentIds);
        requiredRows.forEach(System.out::println);
        System.out.println("Printing NM content ids:==========================");
        requiredRows.forEach(x -> {
            System.out.println("NM" + x);
        });
    }

    private static List<String> readMstrFile(Set<String> contentIdsToBeMigrated) {
        List<String> retrievedContents = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(sourceMstrFile);
            Scanner scanner = new Scanner(fileInputStream);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // process each line
                String[] arr = line.split("\t");
                if(!contentIdsToBeMigrated.contains(arr[0].trim())) {
                    continue;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for(String row : arr) {
                    stringBuilder.append(row.trim());
                    stringBuilder.append("\t");
                }
                if(arr.length < 12) {
                    stringBuilder.append(" ");
                }
                retrievedContents.add(stringBuilder.substring(0,stringBuilder.length()-1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrievedContents;
    }
}
