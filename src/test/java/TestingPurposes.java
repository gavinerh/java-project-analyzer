import java.util.ArrayList;
import java.util.List;

public class TestingPurposes {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        try {
            String s = "\"bbb_bbb\", \"bbb_bbb\", \"password33\", \"N\", \"N\", \"bbb@singaporeair.com.sg\", \"HO\", \"SIA\", \"NA\", \"SIA\", \"B12345\", \"SUPER\", \"firstName\", \"lastName\", \"99999999\", \"bbb_bbb\"";
            String[] arr = s.split(",");
//            int start = 0;
            for (String str : arr) {
                int start = str.indexOf("\"") + 1;

                int end = str.indexOf("\"", start);
                list.add(str.substring(start, end).trim());
            }
            int size = list.size();
            StringBuilder builder = new StringBuilder("generateUserProfile(");
            for (int i = 0; i < size; i++) {
                builder.append("\"");
                builder.append(i);
                builder.append("\"");
                builder.append(",");
            }
            String toPrint = builder.toString();
            toPrint = toPrint.substring(0, toPrint.length() - 1);
            toPrint += ")";
//            System.out.println(toPrint);
            // generate the empty null cases
            for (int i = 0; i < list.size(); i++) {
                // for each param, loop 2 times
                for (int j = 0; j < 2; j++) {
                    String printingMtd = null;
                    // replace the integer in the toPrint string with the error param
                    if (j == 0) {
                        printingMtd = toPrint.replace("\"" + String.valueOf(i) + "\"", "null");
//                        System.out.println(printingMtd);
                    } else {
                        printingMtd = toPrint.replace("\"" + String.valueOf(i) + "\"", "\"\"");
//                        System.out.println(printingMtd);
                    }
                    // loop and populate the method string
                    for (int k = 0; k < list.size(); k++) {
                        if (k == i) {
                            continue;
                        }
                        printingMtd = printingMtd.replace("\"" + String.valueOf(k) + "\"", "\"" + list.get(k) + "\"");
                    }
                    System.out.println(printingMtd + ";");
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateSqlConditional(String[] arr) {
        String toPrint = "";
        for (String s : arr) {
            toPrint += String.format("%s is not null or ", s);
        }
        System.out.println(toPrint);
    }

    private static String[] generateColumnArr(String s) {
        String[] preCleansed = s.split(",");
        for (int i = 0; i < preCleansed.length; i++) {
            preCleansed[i] = preCleansed[i].trim();
        }
        return preCleansed;
    }

    private static String cleanField(String fields) {
        String stringToReturn = "";
        String[] fieldSplit = fields.split("\n");
        for (String line : fieldSplit) {
            if (line.trim().startsWith("@Json") || line.trim().equals("")) {
                continue;
            }
            String[] lineSplit = line.trim().split(" ");
            stringToReturn += generateModelString(lineSplit);
        }
        return stringToReturn;
    }

    private static String generateModelString(String[] lineSplit) {
        String var = lineSplit[2];
        var = var.substring(0, var.indexOf(";"));
        String type = lineSplit[1];
        return String.format("\t\"%s\": \"%s\",\n", var, type);
    }


}