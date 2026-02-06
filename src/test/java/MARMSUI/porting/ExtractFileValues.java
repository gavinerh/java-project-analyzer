package MARMSUI.porting;

import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExtractFileValues {
    public static List<String> extractValues(String filePath) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        List<String> fileContent = new ArrayList<>();
        Scanner scanner = new Scanner(fileInputStream);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(StringUtils.isNotBlank(line)) {
                fileContent.add(line.trim());
            }
        }
        return fileContent;
    }
}
