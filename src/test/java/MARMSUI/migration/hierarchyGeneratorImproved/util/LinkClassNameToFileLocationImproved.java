package MARMSUI.migration.hierarchyGeneratorImproved.util;

import MARMSUI.migration.hierarchyGenerator.MappingAllMethodsInClass;
import MARMSUI.migration.hierarchyGenerator.util.CheckIfClassOrInterface;
import com.github.javaparser.ParseProblemException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class LinkClassNameToFileLocationImproved {


    public static void execute(Set<String> fileNames, Map<String,String> map, Map<String,String> interfacesMap, String[] classesToSkip) throws IOException {

        for(String fileName : fileNames) {
            File file = new File(fileName);
            if (file.isDirectory()) {
                throw new RuntimeException("Should not contain directory");
            } else {
                if(!file.getAbsolutePath().contains(".java")) continue;
                if(Arrays.stream(classesToSkip).anyMatch(file.getAbsolutePath()::contains)) {
                    continue;
                }
                String type = null;
                try {
                    type = CheckIfClassOrInterface.execute(file.getAbsolutePath());
                }catch(Exception e) {
                    System.out.println(file.getAbsolutePath() + " threw error");
                    e.printStackTrace();
                    throw new RuntimeException("Error in checking type");
                }
                if(type.equals("class")) {
                    if (map.containsKey(MappingAllMethodsInClass.extractClassName(file.getAbsolutePath())) && !file.getAbsolutePath().contains("Vo") && !file.getAbsolutePath().contains("Key")) {
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
