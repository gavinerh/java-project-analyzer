import org.mapstruct.factory.Mappers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestingPurposes {


    private static MapperClone mapperClone = Mappers.getMapper(MapperClone.class);

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        String basedir = "com.sg.sq.marmsui.vo.";
        String dirToIterate = "/Users/macuser/Documents/updated-lsl-app/lsl-marmsui-qual/src/main/java/com/sg/sq/marmsui/vo";
        File file = new File(dirToIterate);
        List<String> fileNames = new ArrayList<>();
        iterateFiles(file, basedir, "", fileNames);
        for(String name : fileNames) {
            System.out.print("\"" + name + "\",");
        }
    }



    private static void iterateFiles(File file, String basedir, String packageName, List<String> fileNames) throws IOException, CloneNotSupportedException {
        for (File innerFile : file.listFiles()) {
            if (innerFile.isDirectory()) {
                iterateFiles(innerFile, basedir, packageName + innerFile.getName() + ".", fileNames);
            } else {
                if (innerFile.getName().endsWith(".java")) {
                    String className = innerFile.getName().replace(".java", "");
                    String fullClassName = basedir + packageName + className;
                    fileNames.add(fullClassName);
                }
            }
        }


    }
}