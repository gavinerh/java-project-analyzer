package MARMSUI.migration;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class CopyOnlyNonDuplicates {
    public static void main(String[] args) {
        String fromFilePath = "/Users/macuser/Documents/marms/MARMS/Source Code/Business Components/Common Classes/com/singaporeair/marms/abacus/business/activity/overdraft/OverdraftConstant.java";
        String toFilePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/constant/OverdraftConstant.java";
        // change this only
        String fileToExamine = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/service/Overdraft.java";
        String patternToIdentify = "OverdraftConstant.";
        String fieldDeclaration = "public static final";

        try {
            FileInputStream fileInputStream = new FileInputStream(fileToExamine);
            Set<String> listOfValuesToCopy = generateValuesToCopy(fileInputStream, patternToIdentify);
            listOfValuesToCopy.add("MAILING_INDICATOR_X");
            // check if the destination file has these chosen values
            fileInputStream = new FileInputStream(toFilePath);
            Map<String,String> destinationValues = populateDestinationCurrVal(fileInputStream,fieldDeclaration);
            fileInputStream.close();
            List<String> listOfMissingFields = generateListOfMissingFields(listOfValuesToCopy,destinationValues.keySet());
            fileInputStream = new FileInputStream(fromFilePath);
            Map<String,String> sourceValues = populateDestinationCurrVal(fileInputStream,fieldDeclaration);
            fileInputStream.close();
            printMissingFieldDeclaration(listOfMissingFields,sourceValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static List<String> printMissingFieldDeclaration(List<String> missingFields, Map<String,String> source) {
        List<String> val = new ArrayList<>();
        for(String field : missingFields) {
            System.out.println(source.get(field));
            val.add(field);
        }
        return val;
    }

    private static List<String> generateListOfMissingFields(Set<String> toCopy, Set<String> destVal) {
        List<String> missingFields = new ArrayList<>();
        Iterator iterator = toCopy.iterator();
        while(iterator.hasNext()) {
            String valToCheck = (String) iterator.next();
            if(!destVal.contains(valToCheck)){
                missingFields.add(valToCheck);
            }
        }
        return missingFields;
    }

    private static Map<String,String> populateDestinationCurrVal(FileInputStream fileInputStream, String fieldDeclaration) {
        Map<String,String> mapOfFields = new HashMap<>();
        Scanner sc = new Scanner(fileInputStream);
        while (sc.hasNextLine()) {
            String l = sc.nextLine();
            if(l.contains(fieldDeclaration)) {
                mapOfFields.put(extractFieldName(l, fieldDeclaration),l.trim());
            }
        }
        return mapOfFields;
    }

    private static String extractFieldName(String line, String fieldDeclaration) {
        int startInd = line.indexOf(fieldDeclaration) + fieldDeclaration.length();
        int endInd = line.indexOf("=");
        String stillContainType = line.substring(startInd,endInd).trim();
        String[] arr = stillContainType.split(" ");
        String valToReturn = "";
        for(int i=1; i< arr.length; i++) {
            valToReturn += arr[i] + " ";
        }
        return valToReturn.trim();
    }

    private static Set<String> generateValuesToCopy(FileInputStream fileInputStream, String pattern) {
        Set<String> list = new HashSet<>();
        Scanner sc = new Scanner(fileInputStream);
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.contains(pattern)) {
                list.add(extractFieldString(line, pattern));
            }
        }
        return list;
    }

    private static String extractFieldString(String line, String pattern) {
        String patternOnlyAlphaNum = "[a-zA-Z0-9_]";
        int startInd = line.indexOf(pattern) + pattern.length();
        String val = "";
        while(startInd < line.length()) {
            try {
                if(line.equals(".setCompanyName((customerParticularsVo.getCompanyNameLine1() == null ? CommonConstants.EMPTY_STRING")){
                    System.out.println("Reached");
                }
                if(!line.substring(startInd, startInd+1).matches(patternOnlyAlphaNum)){
                    break;
                }
            } catch (Exception e) {
                System.out.println(line);
                throw new RuntimeException();
            }
            val += line.substring(startInd,startInd+1);
            startInd++;
        }
        return val;
    }
}
