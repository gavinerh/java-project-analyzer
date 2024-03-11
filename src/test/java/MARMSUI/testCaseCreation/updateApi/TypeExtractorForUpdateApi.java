package MARMSUI.testCaseCreation.updateApi;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class TypeExtractorForUpdateApi {
    public static void main(String[] args) {
        try {
            String filename = "/Users/macuser/Desktop/response_content";
            Set<String> types = new HashSet<>();
            Set<String> finalSet = new HashSet<>();
            String[] unwantedTypes = new String[]{"void", "String", "short", "Short", "int", "Integer", "long", "Long", "java.sql.Date", "java.util.Date", "boolean", "Boolean"};
            FileInputStream fileInputStream = new FileInputStream(filename);
            Scanner sc = new Scanner(fileInputStream);
            String toPrint = "{";
            int count = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty()) {
                    count = 0;
                    continue;
                }
                if (count == 1) {
                    if (!line.contains("List<")) {
                        types.add(line);
                    } else {
                        types.add(removeListString(line.trim()));
                    }
                }
                if (count == 5) {
                    if (line.contains("[]")) {
                        continue;
                    }
                    if (line.contains(",")) {
                        String[] typeArr = line.split(",");
                        for (String type : typeArr) {
                            if (!type.contains("List<")) {
                                types.add(type);
                            } else {
                                types.add(removeListString(type));
                            }
                        }
                    } else {
                        types.add(line);

                    }
                }
                count++;
            }
            removeUnwantedType(unwantedTypes, types);
            Iterator<String> s = types.iterator();
            while (s.hasNext()) {
                toPrint += String.format("\"%s\",", s.next());
            }
            toPrint = toPrint.substring(0, toPrint.length() - 1);
            toPrint += "}";
            System.out.println(toPrint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void removeUnwantedType(String[] unwantedTypes, Set<String> set) {
        for (String s : unwantedTypes) {
            if (set.contains(s)) {
                set.remove(s);
            }
        }
    }

    private static String removeListString(String s) {
        return s.substring(s.indexOf("<") + 1, s.indexOf(">"));
    }
}
