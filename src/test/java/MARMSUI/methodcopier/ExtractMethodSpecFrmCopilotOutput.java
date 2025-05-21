package MARMSUI.methodcopier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExtractMethodSpecFrmCopilotOutput {
    public static void main(String[] args) throws IOException {
        String fileName = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/codeupdate/forceQualify_and_called_methods.java";
        List<MethodSpec> methodSpecList = new ArrayList<>();
        execute(fileName, methodSpecList);
        System.out.println(methodSpecList.size());
    }

    public static List<MethodSpec> execute(String specFile, List<MethodSpec> methodSpecList) throws IOException {
        FileInputStream fileInputStream = null;
        Scanner scanner = null;
//        List<MethodSpec> methodSpecList = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(specFile);
            scanner = new Scanner(fileInputStream);
            int count = 0;
            MethodSpec methodSpec = null;
            String methodName = "";
            List<String> arguments = null;
            while(scanner.hasNextLine()) {
                String val = scanner.nextLine();
                if(!val.isBlank()) {
                    if(count == 0) {
                        // get method name
                        methodName = val.trim();
                    } else if (count == 1) {
                        // get arguments
                        String[] arr = val.trim().split(",");
                        trimValues(arr);
                        // save to list
                        arguments = Arrays.stream(arr).toList();
                        methodSpec = new MethodSpec(methodName,arguments);
                        methodSpecList.add(methodSpec);
                    }
                    count++;
                } else {
                    count = 0;
                }
            }
        } catch (Exception e) {
            if(fileInputStream != null) {
                fileInputStream.close();
                scanner.close();
            }
        }
        return methodSpecList;
    }

    private static void trimValues(String[] arr) {
        for(int i=0; i<arr.length; i++) {
            arr[i] = arr[i].trim();
        }
    }
}
