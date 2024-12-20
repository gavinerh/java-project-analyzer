package MARMSUI.testCaseCreation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExtractClassThatNeedsToStringAndEqualMtd {
    public static void main(String[] args) throws IOException {
        // Todo: must change the toStringAlreadyAdded variable reference to either qual or profile app
        String toStringAlreadyAddedForProfile = "CusSeatPrefExample\n" +
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
                "CusStopmailInfo\n" +
                "CusInfltPrefExample\n" +
                "CusOinfltPref\n" +
                "CusInfltPref\n" +
                "CusAddrExample\n" +
                "CusProfileUpdateRequest\n" +
                "DeleteCustomerDetailsVO\n" +
                "CustomerParticularsVo\n" +
                "CusAddr\n" +
                "CountryAddrFormatExample\n" +
                "CustomerCardIssVo\n" +
                "CusTierQualExample\n" +
                "CusSupplInfo\n" +
                "CusNextKinExample\n" +
                "CusRdpnNomineesExample";
        String toStringAlreadyAddedForQual = "ReferenceCdExample\n" +
                "CusSeatPrefExample\nCusOinfltPrefExample\nMileageSumExample\nCusSupplInfoExample\n" +
                "ApiConfigExample\nCusFamilyInfoExample\nCusAccountExample\nCusPersExample\nCusPhoneExample\n" +
                "TierQual\nCusAddrAud\nEventLogVo\nCard\nCusAddnInfoExample\nCustomerTier\nCusCreditCardExample\n" +
                "CusAlternateNameExample\nErrRecExample\nPPSYearQual\nTransReserveVal\nPPSReserveVal\nPromotionAward\n" +
                "QuarterlyBucketPoints\nCusBucketPoints\nMileageSum\nTicketSourceInfo\nTransBucketPoints";
        List<String> classesAlreadyAdded = Arrays.stream(toStringAlreadyAddedForQual.split("\n")).collect(Collectors.toList());
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
