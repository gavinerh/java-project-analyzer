package MARMSUI.migration.propertymigration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ComparisonOfPropertiesFile {

    // run CleanUatFile class first then change the file path for uiFile for different projects
    // results saved in cleanuiResults

    public static void main(String[] args) throws IOException {
        String uatFile = "/Users/macuser/Desktop/holdingForTempFiles/cleaned-uat";
        String prodFile = "/Users/macuser/Desktop/holdingForTempFiles/config.txt";
        String uiFile = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-profile/ocp_templates/configmap/uat/app.properties";
        String cleanuiResults = "/Users/macuser/Desktop/holdingForTempFiles/cleaned-ui";

        System.out.println("Generating ui map first");
        Map<String,String> uiMap = mapKeysToValue(uiFile);
        System.out.println(uiMap.size());

        Map<String,String> uatMap = mapKeysToValue(uatFile);
        List<String> listOfCorrectUiToUatKey = new ArrayList<>();
        Map<String,String> missingKeys = generateMissingKeyMap(uiMap,uatMap, listOfCorrectUiToUatKey);
        Map<String,String> uatValueToKey = mapValueToKey(uatFile);
        Map<String,String> mapOfUiKeyToUatKey = new HashMap<>();
        Set<String> missingKeysRemaining = null;
        try {
            missingKeysRemaining = compare(missingKeys,uatValueToKey, mapOfUiKeyToUatKey);
        } catch (Exception e) {
            System.out.println();
        }
        System.out.println(mapOfUiKeyToUatKey);
        insertListKeyToMap(mapOfUiKeyToUatKey,listOfCorrectUiToUatKey);
        System.out.println(mapOfUiKeyToUatKey.size());
        // match key of ui to prod value
        Map<String,String> mapOfProdKeyToVal = mapKeysToValue(prodFile);
        Map<String,String> map = matchUiKeyToProdVal(mapOfUiKeyToUatKey,mapOfProdKeyToVal);
        List<String> list = generateListOfUiProp(uiFile,map);
        System.out.println(list);
        CleanUatFile.saveTofile(cleanuiResults,list);
    }

    private static List<String> generateListOfUiProp(String uifile, Map<String,String> uiToProd) throws IOException {
        String content = extractStringFromFile(uifile);
        List<String> list = new ArrayList<>();
        String[] arr = content.split("\n");
        for(String s : arr) {
            if(s.startsWith("#")) {
                continue;
            }
            String[] splitEquals = CleanUatFile.splitOnEquals(s);
            String key = splitEquals[0];
            String value = splitEquals[1];
            String prodValue = uiToProd.get(key);
            String valuetoInsert = "# not found in prod";
            String valueToInsertToList = "";
            if(prodValue == null) {
                valueToInsertToList = String.format("%s=%s%s",key,value,valuetoInsert);
            } else {
                valueToInsertToList = String.format("%s=%s",key,prodValue);
            }
            list.add(valueToInsertToList);
        }
        return list;
    }

    private static Map<String,String> matchUiKeyToProdVal(Map<String,String> uiToUat, Map<String,String> prodKeyVal) {
        Map<String,String> map = new HashMap<>();
        for(String uiKey : uiToUat.keySet()) {
            String uatKey = uiToUat.get(uiKey);
            String prodVal = prodKeyVal.get(uatKey);
            map.put(uiKey,prodVal);
        }
        return map;
    }

    private static void insertListKeyToMap(Map<String,String> map, List<String> list) {
        for(String s : list) {
            map.put(s,s);
        }
    }

    private static Set<String> compare(Map<String,String> missingKeys, Map<String,String> uatValuesToKey, Map<String,String> mapOfUiKeyToUatKey) {
        Set<String> keysMissing = new HashSet<>();
        for(String key : missingKeys.keySet()) {
            try {
                String missingValue = missingKeys.get(key);
                if(uatValuesToKey.containsKey(missingValue)) {
                    System.out.println("removed " + key + " from missing list");
//                    missing.remove(key);
                    // capture the key of missing to correct uat value
                    mapOfUiKeyToUatKey.put(key,uatValuesToKey.get(missingValue));
                } else {
                    keysMissing.add(key);
                }
            } catch (Exception e) {
                System.out.println();
            }
        }
        return keysMissing;
    }

    private static Map<String,String> generateMissingKeyMap(Map<String,String> toCompare, Map<String,String> source, List<String> listOfCorrectUiToUatKey) {
        Map<String,String> missingKeys = new HashMap<>();
        for(String key : toCompare.keySet()) {
            if(!source.containsKey(key)) {
                missingKeys.put(key,toCompare.get(key));
                System.out.println("Missing " + key);
            } else {
                // check if the values match
                if(toCompare.get(key).equals(source.get(key))) {
                    listOfCorrectUiToUatKey.add(key);
                }
            }
        }
        return missingKeys;
    }

    private static Map<String,String> generateCleanedMap(Collection<String> values, Map<String,String> source) {
        Map<String,String> map = new HashMap<>();
        System.out.println("Generating key value: ======");
        for(String val : values) {
            map.put(val,source.get(val));
            System.out.println(String.format("%s=%s",val,source.get(val)));
        }
        return map;
    }
    private static void repopulateKeyToValueMap(Map<String,String> toUpdate, Map<String,String> source) {
        System.out.println("===================================================");
        for(String key : toUpdate.keySet()) {
            if(!source.containsKey(key)) {
                System.out.println(String.format("%s is not found", key));
            } else {
                toUpdate.put(key,source.get(key));
            }
        }
        System.out.println("===============================================");
    }

    private static Map<String,String> mapValueToKey(String file) throws IOException {
        String value = extractStringFromFile(file);
        Map<String,String> map = new HashMap<>();
        String[] valArr = value.split("\n");
        for(String val : valArr) {
            if(!val.trim().isBlank() && !val.trim().startsWith("#")) {
                populateMap(val, map,true);
            }
        }
        return map;
    }

    private static Map<String,String> mapKeysToValue(String file) throws IOException {
        String value = extractStringFromFile(file);
        Map<String,String> map = new HashMap<>();
        String[] valArr = value.split("\n");
        for(String val : valArr) {
            if(!val.trim().isBlank() && !val.trim().startsWith("#")) {
                try {
                    populateMap(val, map, false);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(val);
                } catch (Exception e) {
                    System.out.println(val);
                }
            }
        }
        return map;
    }


    private static void populateMap(String val, Map<String,String> map, boolean valueAsKey) {
        String[] arr = val.split("=");
        String key = arr[0].trim();
        if(arr.length == 1) {
            System.out.println(String.format("%s key has empty value",key));
            return;
        }
        if(key.contains("pntconv.fliggy.url")) {
            System.out.println("reav");
        }
        String value = arr[1].trim();
        if(!valueAsKey) {
            if(map.containsKey(key.trim()) && !map.get(key).equals(value)) {
                System.out.println("Already contain " + arr[0] + " key");
//                throw new RuntimeException("Already contain " + arr[0] + " key");
            }
            map.put(key.trim(),value.trim());
        } else {
            if(map.containsKey(value) && !map.get(value).equals(key)) {
                System.out.println("Already contain " + arr[0] + " key");
            }
            map.put(arr[1].trim(),arr[0].trim());
        }
    }

    public static String extractStringFromFile(String filePath) throws IOException {
        // Read all the content from the file as a string
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
