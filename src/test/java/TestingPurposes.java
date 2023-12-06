import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.lang.management.MemoryNotificationInfo;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class TestingPurposes {
    public static void main(String[] args) {
       try {
          String date = "2007-Sep-14";
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd");
          Date d = simpleDateFormat.parse(date);
           System.out.println(d);
       } catch (Exception e) {
           System.out.println(e);
       }
    }

    private static void generateSqlConditional(String[] arr) {
        String toPrint = "";
        for(String s : arr) {
            toPrint += String.format("%s is not null or ", s);
        }
        System.out.println(toPrint);
    }

    private static String[] generateColumnArr(String s) {
        String[] preCleansed = s.split(",");
        for(int i=0; i<preCleansed.length; i++) {
            preCleansed[i] = preCleansed[i].trim();
        }
        return preCleansed;
    }

    private static String cleanField(String fields) {
        String stringToReturn = "";
        String[] fieldSplit = fields.split("\n");
        for(String line : fieldSplit) {
            if(line.trim().startsWith("@Json") || line.trim().equals("")) {
                continue;
            }
            String[] lineSplit = line.trim().split(" ");
            stringToReturn += generateModelString(lineSplit);
        }
        return stringToReturn;
    }

    private static String generateModelString(String[] lineSplit) {
        String var = lineSplit[2];
        var = var.substring(0,var.indexOf(";"));
        String type = lineSplit[1];
        return String.format("\t\"%s\": \"%s\",\n",var, type);
    }


}