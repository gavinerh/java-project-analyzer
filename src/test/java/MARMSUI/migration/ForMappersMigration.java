package MARMSUI.migration;

import java.io.FileInputStream;
import java.util.*;

public class ForMappersMigration {
    public static void main(String[] args) {
        String mapperName = "syncDataConsumersMapper";
        String mapperVariable = "";

        String valuesToCopyPath = "/Users/macuser/Documents/lsl-marmsui-profile/src/main/java/com/sg/sq/marmsui/util/CommonUtil.java";



        mapperName = mapperName.substring(0,1).toUpperCase() + mapperName.substring(1);
        String fromFilePathXml = "/Users/macuser/Documents/updated-lsl-app/lsl-customer/src/main/resources/com/sg/sq/lsl/database/sql/persistence/mappers/" + mapperName + ".xml";
        String fromFilePathJava = "/Users/macuser/Documents/updated-lsl-app/lsl-customer/src/main/java/com/sg/sq/lsl/database/sql/persistence/mappers/" + mapperName + ".java";
        String destinationXmlPath = "/Users/macuser/Documents/lsl-marmsui-profile/src/main/resources/com/sg/sq/marmsui/database/sql/persistence/mappers/" + mapperName + ".xml";

        // needs change
        String patternToIdentifyFromCopyFile = null;
        if(mapperVariable.equals("")){
            patternToIdentifyFromCopyFile = mapperName.substring(0,1).toLowerCase() + mapperName.substring(1)+ ".";
        } else {
            patternToIdentifyFromCopyFile = mapperVariable + ".";
        }


        String patternIdentificationAtXml = "id=\"";

        try {
            FileInputStream fileInputStream = new FileInputStream(valuesToCopyPath);
            Set<String> listOfValuesToCopy = generateValuesToCopy(fileInputStream, patternToIdentifyFromCopyFile);
            fileInputStream.close();

            // check if the destination file has these chosen values
            fileInputStream = new FileInputStream(destinationXmlPath);
            Map<String, String> destinationValues = populateDestinationCurrValXml(fileInputStream, patternIdentificationAtXml);
            fileInputStream.close();
            fileInputStream = new FileInputStream(fromFilePathXml);
            Map<String, String> sqlFromSourceXml = populateDestinationCurrValXml(fileInputStream, patternIdentificationAtXml);
            // check the current util file if the methods are already present in the destination xml
            List<String> listOfMethodsMissing = extractMissingMethods(listOfValuesToCopy, destinationValues);
            fileInputStream.close();
            fileInputStream = new FileInputStream(fromFilePathJava);

            Map<String, String> missingJavaMethodDeclarationMap = extractMissingJavaMethodDeclaration(fileInputStream, listOfMethodsMissing);
            fileInputStream.close();
            printJavaMtdAndSql(missingJavaMethodDeclarationMap, sqlFromSourceXml);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void printJavaMtdAndSql(Map<String,String> javaMtds, Map<String,String> sql) {
        for(String mtdName : javaMtds.keySet()) {
            System.out.println(sql.get(mtdName));
            System.out.println();
            System.out.println(javaMtds.get(mtdName));
            System.out.println("=====================================");
            System.out.println();
        }
    }

    public static Map<String, String> extractMissingJavaMethodDeclaration(FileInputStream fileInputStream, List<String> missingMethods) {
        Scanner sc = new Scanner(fileInputStream);
        boolean methodDeclarationStarted = false;
        Map<String, String> missingJavaMethodDeclaration = new HashMap<>();
        String methodDeclaration = "";
        String methodName = null;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (checkForMethodDeclaration(line)) {
                methodName = isThisMethodRequired(missingMethods,line);
                if (methodName != null)
                    methodDeclarationStarted = true;
            }
            if (methodDeclarationStarted) {
                methodDeclaration += line + "\n";
            }
            if (line.contains(";")) {
                methodDeclarationStarted = false;
                if (methodName != null && !methodName.isEmpty()) {
                    missingJavaMethodDeclaration.put(methodName, methodDeclaration);
                    methodDeclaration = "";
                    methodName = "";
                }
            }
        }
        return missingJavaMethodDeclaration;
    }

    public static String isThisMethodRequired(List<String> missingMtds, String line) {
        for (String mtd : missingMtds) {
            if (line.contains(mtd)) {
                String val = furtherChecks(line.trim(), mtd);
                if(val != null && val.equals(mtd))
                    return mtd;
            }
        }
        return null;
    }

    public static String furtherChecks(String line, String name){
        int startInd = line.indexOf(name) + name.length();
        String nextChar = line.substring(startInd,startInd+1);
        if(nextChar.equals(" ") || nextChar.equals("(")) {
            return name;
        }
        return null;
    }

    public static boolean checkForMethodDeclaration(String line) {
        String pattern = "[\\w\\s]*[a-zA-Z<>]{3,}[\\s]*[\\w]*[(]{1}[\\w\\s();<>@\".,*/|-]*";
        if(line.contains("//")){
            int start = line.indexOf("//");
            line = line.substring(0,start);
        }
        return line.matches(pattern);
    }

    public static List<String> extractMissingMethods(Set<String> methodsRequired, Map<String, String> methodsPresentInDestination) {
        List<String> missingMethods = new ArrayList<>();
        Iterator iterator = methodsRequired.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            boolean isPresent = methodsPresentInDestination.containsKey(key);
            String val = methodsPresentInDestination.get(key);
            if (!isPresent) {
                missingMethods.add(key);
            }
        }
        return missingMethods;
    }

    public static Map<String, String> populateDestinationCurrValXml(FileInputStream fileInputStream, String identifier) {
        Map<String, String> mapOfFields = new HashMap<>();
        Scanner sc = new Scanner(fileInputStream);
        String idName = "";
        String sqlQuery = "";
        boolean enteredSql = false;
        String startTagIdentifier = "";
        while (sc.hasNextLine()) {
            String l = sc.nextLine();
            if (l.trim().equals("")) continue;
            if (enteredSql) {
                sqlQuery += "\n" + l;
            }
            if (l.contains(identifier) && enteredSql == false) {
                startTagIdentifier = extractStartTagIdentifier(l);
                enteredSql = true;
                idName = extractFieldName(l, identifier);
                sqlQuery += l;
            }
            if (checkEndOfTags(l, startTagIdentifier)) {
//                sqlQuery += l;
                mapOfFields.put(idName, sqlQuery);
                idName = "";
                sqlQuery = "";
                enteredSql = false;
                startTagIdentifier = "";
            }
        }
        return mapOfFields;
    }

    public static String extractStartTagIdentifier(String line) {
        int startIndex = line.indexOf("<") + 1;
        int endIndex = line.indexOf("id=\"");
        try {
            return line.substring(startIndex, endIndex).trim();
        } catch (Exception e) {
            System.out.println(line);
            throw new RuntimeException();
        }
    }

    public static boolean checkEndOfTags(String line, String startTagIdentifier) {
        boolean containsEnd = line.contains("</");
        boolean endTagIdentified = false;
        if (line.contains(startTagIdentifier)) {
            endTagIdentified = true;
        }
        return containsEnd && endTagIdentified;
    }

    public static String extractFieldName(String line, String identifier) {
        int startInd = line.indexOf(identifier) + identifier.length();
        int endInd = line.indexOf("\"", startInd);
        return line.substring(startInd, endInd).trim();
    }

    public static Set<String> generateValuesToCopy(FileInputStream fileInputStream, String pattern) {
        Set<String> list = new HashSet<>();
        Scanner sc = new Scanner(fileInputStream);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.contains(pattern)) {
                list.add(extractFieldString(line, pattern));
            }
        }
        return list;
    }

    public static String extractFieldString(String line, String pattern) {
        String patternOnlyAlphaNum = "[a-zA-Z0-9_]";
        int startInd = line.indexOf(pattern) + pattern.length();
        String val = "";
        while (true) {
            if (!line.substring(startInd, startInd + 1).matches(patternOnlyAlphaNum)) {
                break;
            }
            val += line.substring(startInd, startInd + 1);
            startInd++;
        }
        return val;
    }
}
