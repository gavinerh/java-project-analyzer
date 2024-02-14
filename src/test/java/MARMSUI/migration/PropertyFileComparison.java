package MARMSUI.migration;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PropertyFileComparison {
    public static void main(String[] args) {
        String oldFile = "/Users/macuser/Documents/lsl-marmsui-profile/ocp_templates/configmap/sit/app.properties";
        String newFile = "/Users/macuser/Documents/lsl-marmsui-profile/src/main/resources/properties/app.properties";
        Set<String> keysPresentInNew = new HashSet<>();
        Set<String> keysPresentInOld = new HashSet<>();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(oldFile);
            extractKeysFromPropertiesFile(keysPresentInOld,fileInputStream);
            fileInputStream.close();
            fileInputStream = new FileInputStream(newFile);
            extractKeysFromPropertiesFile(keysPresentInNew,fileInputStream);
            compareOldAndNewKeys(keysPresentInOld,keysPresentInNew);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void compareOldAndNewKeys(Set<String> old, Set<String> keysNew){
        System.out.println("Missing keys: ");
        for(String key : keysNew) {
            if(!old.contains(key)) {
                System.out.println(key);
            }
        }
    }

    private static void extractKeysFromPropertiesFile(Set<String> keysStorage, FileInputStream fileInputStream) {
        Scanner scanner = new Scanner(fileInputStream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.trim().startsWith("#") || line.trim().equals("")) {
                continue;
            }
            int endInd = line.indexOf("=");
            String key = line.substring(0,endInd);
            keysStorage.add(key.trim());
        }
    }
}
