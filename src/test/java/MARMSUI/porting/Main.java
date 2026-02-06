package MARMSUI.porting;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // Todo: change the fileDir accordingly and line 46
        String fileDir = "/Users/macuser/Desktop/holdingForTempFiles/force-qual-kf-porting/cus-duplicate-jan/raw_data";
        int counter = 1;
        for (File file : FileOperation.listFilesInDirectory(fileDir)) {
            try {
                if(file.isDirectory() || !file.getName().endsWith(".sql")) {
                    continue;
                }
                String filePath = file.getAbsolutePath();
                execute(filePath, counter,true);
                counter++;
            } catch (Exception e) {
                System.out.println("Error processing file: " + file.getAbsolutePath());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }

    private static void execute(String filePath, int counter, boolean isIntIdString) throws FileNotFoundException {
        try {
            long replacementIntId = 0L;
            long replacementIntId2 = 8987005944L;
            String alternativeAddr = "'17 Changi East Rd'";
            String phoneNo = "87654321";
            String emailAddress = "'saa_devmail@sqdev.com.sg'";
            String givenName = "'Forceq'";
            String familyName = "'Once'";
            String remarks = "'test remarks'";
            String passportId = "'K1234567A'";
            String ipAddress = "null";
            String phoneNumber = "'6587654321'";
            Set<String> listOfTableNamesToIgnore = Set.of("CUS_CREDIT_CARD");

            // todo: change the filePathForExport accordingly
            String filePathForExport = "/Users/macuser/Desktop/holdingForTempFiles/force-qual-kf-porting/cus-duplicate-jan/update-insert-queries";
            // Todo: change the intIdReplacement accordingly
            String intIdReplacement = "replace 8550022728 to 8991483759";

            Map<String, String> intIdMap = createReplacementMap(intIdReplacement);

            List<List<String>> columnList = new ArrayList<>();
            List<List<String>> valueList = new ArrayList<>();
            List<String> tableList = new ArrayList<>();

            List<String> fileContent = ExtractFileValues.extractValues(filePath);
            StepOne.execute(columnList, valueList, tableList, fileContent);
            String intId = "";
            if (isIntIdString) {
                intId = StepOne.removeStartAndEndQuotes(valueList.get(0).get(columnList.get(0).indexOf("INT_ID")));
            } else
                intId = valueList.get(0).get(columnList.get(0).indexOf("INT_ID"));
            replacementIntId = Long.parseLong(intIdMap.get(intId));

            Object[] nextCount = NumberGenerator.identifyNextCount(counter);
            familyName = String.format("'%s'", nextCount[1]);
            // configuring what columns to be replace
            Map<String, String> replacementMap = new HashMap<>();
            replacementMap.put("EMAIL_ADDR", emailAddress);
            replacementMap.put("GIVEN_NAME", givenName);
            replacementMap.put("FAMILY_NAME", familyName);
            replacementMap.put("REMARKS", remarks);
            replacementMap.put("PASSPORT_ID", passportId);
            replacementMap.put("IP_ADDRESS", ipAddress);
            replacementMap.put("DELIVERY_ADDR", alternativeAddr);
            replacementMap.put("ADDR_LINE_1", alternativeAddr);
            replacementMap.put("ADDR_LINE_2", alternativeAddr);
            replacementMap.put("ADDR_LINE_3", alternativeAddr);
            replacementMap.put("ADDR_LINE_4", alternativeAddr);
            replacementMap.put("NOMINEES_INT_ID", String.valueOf(replacementIntId2));
            replacementMap.put("COY_EMAIL_ADDR", emailAddress);
            replacementMap.put("ENRLMT_EMAIL_ID", emailAddress);
            replacementMap.put("INT_ID", String.valueOf(replacementIntId));
            replacementMap.put("PHONE_NO", phoneNo);
            replacementMap.put("PHONE_NUMBER", phoneNumber);

            // modify the values in the original queries
            List<String> modifiedValues = StepTwo.modifyValues(replacementMap, intIdMap, columnList, valueList, tableList, Long.parseLong(intId), listOfTableNamesToIgnore);

            // create directory to store the modified queries
            String directoryPath = filePathForExport + "/" + String.format("%s-%s", intId, replacementIntId);
            FileOperation.createDirectoryIfNotExists(directoryPath);

            // store the modified queries into file
            String fileNameNew = String.format("%s-%s.sql", intId, replacementIntId);
            FileOperation.insertStringsToFile(modifiedValues, directoryPath + "/" + fileNameNew);
            String deleteFileName = String.format("delete-%s.sql", replacementIntId);
            List<String> deleteQueries = StepThree.generateDeleteQueries(tableList, columnList, valueList, replacementIntId);
            FileOperation.insertStringsToFile(deleteQueries, directoryPath + "/" + deleteFileName);
        } catch (IllegalArgumentException e) {
            System.out.println("Error processing file: " + filePath);
            e.printStackTrace();
        }
    }

    private static Map<String, String> createReplacementMap(String intIdReplacement) {
        Map<String, String> intIdMap = new java.util.HashMap<>();
        String[] lines = intIdReplacement.split("\n");
        for (String line : lines) {
            int startIndex = line.indexOf("replace") + "replace".length();
            int endIndex = line.indexOf("to", startIndex);
            String oldIntId = line.substring(startIndex, endIndex).trim();
            String newIntId = line.substring(endIndex + "to".length()).trim();
            intIdMap.put(oldIntId, newIntId);
        }
        return intIdMap;
    }
}
