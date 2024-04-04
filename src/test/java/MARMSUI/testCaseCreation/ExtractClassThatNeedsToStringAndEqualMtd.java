package MARMSUI.testCaseCreation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExtractClassThatNeedsToStringAndEqualMtd {
    public static void main(String[] args) throws IOException {
        String toStringAlreadyAdded = "CusSeatPrefExample\n" +
                "EmailDomainInfoExample\n" +
                "ReferenceCdExample\n" +
                "CusOinfltPrefExample\n" +
                "MileageSumExample\n" +
                "CusSupplInfoExample\n" +
                "CountryExample\n" +
                "String\n" +
                "long\n" +
                "CusFamilyInfoExample\n" +
                "CusCardIssExample\n" +
                "CityExample\n" +
                "CusStopmailInfoExample\n" +
                "CusAccountExample\n" +
                "CusPersExample\n" +
                "CusPhoneExample\n" +
                "CusAddrAud\n" +
                "EventLogVo\n" +
                "java.sql.Date\n" +
                "CusAddnInfoExample\n" +
                "boolean\n" +
                "CusCreditCardExample\n" +
                "Long\n" +
                "short\n" +
                "CusAlternateNameExample\n" +
                "CustomerStopmailInfoVo\n" +
                "CusStopmailInfo";
        List<String> classesAlreadyAdded = Arrays.stream(toStringAlreadyAdded.split("\n")).collect(Collectors.toList());
        FileInputStream fileInputStream = new FileInputStream("/Users/macuser/Desktop/response_content");
        Set<String> set = new HashSet<>();
        extractClassName(set, fileInputStream);
        fileInputStream.close();
        removeClassesAlreadyAdded(classesAlreadyAdded,set);
        printSetContents(set);
    }

    private static void removeClassesAlreadyAdded(List<String> alreadyAdded, Set<String> set) {
        for(String s : alreadyAdded) {
            set.remove(s);
        }
    }

    private static void printSetContents(Set<String> set) {
        Iterator<String> iterator = set.stream().iterator();
        iterator.forEachRemaining(x-> System.out.println(x));
    }

    private static void extractClassName(Set<String> set, FileInputStream fileInputStream) {
        Scanner scanner = new Scanner(fileInputStream);
        int count = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.trim().isEmpty()) {
                count = 0;
                continue;
            } else {
                count++;
            }
            if(count == 6) {
                if(line.contains(",")){
                    String[] arr = line.split(",");
                    Arrays.stream(arr).forEach(x->set.add(x));
                }else {
                    set.add(line);
                }
            }
        }
    }
}
