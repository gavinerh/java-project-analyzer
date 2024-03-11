package MARMSUI;

import java.io.FileInputStream;
import java.util.*;

public class TypeExtractorForSpringAOPTest {
    public static void main(String[] args) {
        try{
            String filename = "/Users/macuser/Desktop/response_content";
            Set<String> types = new HashSet<>();
            FileInputStream fileInputStream = new FileInputStream(filename);
            Scanner sc = new Scanner(fileInputStream);
            String toPrint = "{";
            int count = 0;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.trim().isEmpty()) {
                    count = 0;
                    continue;
                }
                if(count == 1) {
                    if(!line.contains("List<")) continue;
                    types.add(removeListString(line.trim()));
                }
                count++;
            }
            Iterator<String> s = types.iterator();
            while(s.hasNext()){
                toPrint += String.format("\"%s\",", s.next());
            }
            toPrint = toPrint.substring(0,toPrint.length()-1);
            toPrint += "}";
            System.out.println(toPrint);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String removeListString(String s) {
        return s.substring(s.indexOf("<") + 1,s.indexOf(">"));
    }
}
