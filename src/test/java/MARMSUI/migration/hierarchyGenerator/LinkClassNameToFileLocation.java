package MARMSUI.migration.hierarchyGenerator;

import MARMSUI.migration.hierarchyGenerator.util.CheckIfClassOrInterface;
import org.apache.kafka.common.protocol.types.Field;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class LinkClassNameToFileLocation {
    public static void main(String[] args) {
        String basePath = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui";
        Map<String,String> map = new HashMap<>();
    }


    public static void executeModifiedForComplex(String basePath, Map<String,String> map, Map<String,String> interfacesMap) throws FileNotFoundException {
        File[] files = new File(basePath).listFiles();
        for(File file : files) {
            if (file.isDirectory()) {
                executeModifiedForComplex(file.getAbsolutePath(), map, interfacesMap);
            } else {
                if(!file.getAbsolutePath().contains(".java")) continue;
                String type = null;
                try {
                    type = CheckIfClassOrInterface.execute(file.getAbsolutePath());
                } catch (Exception e) {
                    System.out.println(file.getAbsolutePath() + " threw error");
                    e.printStackTrace();
                    throw new RuntimeException("Error in checking type");
                }
                if(type.equals("class")) {
                    if (map.containsKey(file.getAbsolutePath())) {
                        throw new RuntimeException("Duplicate class name found: " + file.getAbsolutePath());
                    }
                    map.put(file.getAbsolutePath(), file.getAbsolutePath());
                } else if (type.equals("interface")) {
                    if (interfacesMap.containsKey(file.getAbsolutePath())) {
                        throw new RuntimeException("Duplicate interface name found: " + file.getAbsolutePath());
                    }
                    interfacesMap.put(file.getAbsolutePath(), file.getAbsolutePath());
                }
            }
        }
    }

    public static void execute(String basePath, Map<String,String> map, Map<String,String> interfacesMap) throws FileNotFoundException {
        File[] files = new File(basePath).listFiles();
        for(File file : files) {
            if (file.isDirectory()) {
                execute(file.getAbsolutePath(), map, interfacesMap);
            } else {
                if(!file.getAbsolutePath().contains(".java")) continue;
                String type = null;
                try {
                    type = CheckIfClassOrInterface.execute(file.getAbsolutePath());
                } catch (Exception e) {
                    System.out.println(file.getAbsolutePath() + " threw error");
                    e.printStackTrace();
                    throw new RuntimeException("Error in checking type");
                }
                if(type.equals("class")) {
                    if (map.containsKey(MappingAllMethodsInClass.extractClassName(file.getAbsolutePath()))) {
                        throw new RuntimeException("Duplicate class name found: " + MappingAllMethodsInClass.extractClassName(file.getAbsolutePath()));
                    }
                    map.put(MappingAllMethodsInClass.extractClassName(file.getAbsolutePath()), file.getAbsolutePath());
                } else if (type.equals("interface")) {
                    if (interfacesMap.containsKey(MappingAllMethodsInClass.extractClassName(file.getAbsolutePath()))) {
                        throw new RuntimeException("Duplicate interface name found: " + MappingAllMethodsInClass.extractClassName(file.getAbsolutePath()));
                    }
                    interfacesMap.put(MappingAllMethodsInClass.extractClassName(file.getAbsolutePath()), file.getAbsolutePath());
                }
            }
        }
    }
}
