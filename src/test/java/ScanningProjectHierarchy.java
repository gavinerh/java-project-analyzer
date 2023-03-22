import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ScanningProjectHierarchy {
    public static void main(String[] args) {
        String filePath = "/Users/macuser/Documents/ProjectHierarchy.txt";
        checkImplMethodsNotChecked(filePath);
//        String filePath2 = "/Users/macuser/Documents/NewProjectHierarchy.txt";
//        checkImplMethodsNotCheckedNew(filePath2);

//        String filePath3 = "/Users/macuser/Documents/DatabaseModificationMtds.txt";
//        String filePathToSrcFiles = "/Users/macuser/eclipse-workspace/lsl-batch/src/main/resources/com/sg/sq/lsl/batch/database/sql/persistence/mappers";
//        listTheModificationMapperMtds(filePath3, filePathToSrcFiles);
    }

    private static void listTheModificationMapperMtds(String filepath, String filePathToSrcFiles){
        List<String> listOfAllUpdateInsertMappers = new ArrayList<>();
        File file = new File(filePathToSrcFiles);
        FileInputStream fileInputStream = null;
        Scanner sc = null;
        File[] files = file.listFiles();
        for(File f : files){
            try{
                int count = 0;
                fileInputStream = new FileInputStream(f.getPath());
//                if(!f.getName().contains("AcctStatusFuncMapper")) continue;
                sc = new Scanner(fileInputStream, "UTF-8");
                while(sc.hasNextLine()){
                    count++;
                    String line = sc.nextLine();
                    if(count < 130) continue;
                    if (!line.contains("<insert id=") && !line.contains("<update id=")) {
                        continue;
                    } else {
                        if(line.contains("Selective") || line.contains("Example") || line.contains("PrimaryKey") || line.contains("id=\"insert\"")) continue;
                        int indexOfId = line.indexOf("id");
                        int indexOfQuote = line.indexOf("\"");
                        String methodName = line.substring(indexOfQuote + 1);
                        int indexOfEndQuote = methodName.indexOf("\"");
                        methodName = methodName.substring(0, indexOfEndQuote);
                        String finalMethodName = f.getName().replace("xml", "") + methodName;
                        finalMethodName = finalMethodName.substring(0, 1).toLowerCase() + finalMethodName.substring(1);
                        listOfAllUpdateInsertMappers.add(finalMethodName);
                    }
                }


//                for(String s : listOfAllUpdateInsertMappers){
//                    System.out.println(s);
//                }
                saveToFile(filepath, listOfAllUpdateInsertMappers);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void saveToFile(String filepath, List<String> list){
        File file = new File(filepath);
        file.delete();
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(filepath, true);
            for(String nameOfMtd : list){
                fos.write(nameOfMtd.getBytes());
                fos.write("\n".getBytes());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void checkImplMethodsNotCheckedNew(String filepath){
        int count = 0;
        try{
            FileInputStream fileInputStream = new FileInputStream(filepath);
            Scanner sc = new Scanner(fileInputStream);
            HashMap<String, Integer> setOfUncheckedMtd = new HashMap<>();
            while(sc.hasNextLine()){
                count++;
                String line = sc.nextLine();
                if(line.trim().startsWith("[")) continue;
                if(line.trim().equals("")) continue;
                String[] lineSplit = line.trim().split("-");
                String finalMtdName = String.format("%s-%s", lineSplit[0], lineSplit[1]);
                if(!setOfUncheckedMtd.containsKey(finalMtdName)){
                    if(!line.startsWith(" ")) { // means its a mtd declaration
                        setOfUncheckedMtd.put(finalMtdName, 2);
                    }else {
                        setOfUncheckedMtd.put(finalMtdName, 1);
                    }
                } else {
                    int val = setOfUncheckedMtd.get(finalMtdName);
                    if(val == 1){
                        if(line.startsWith(" ")){
                            // dont change the value as it is not a declaration
                        } else {
                            setOfUncheckedMtd.put(finalMtdName, val + 1);
                        }
                    }
                }
            }
            for(String key : setOfUncheckedMtd.keySet()){
                if(setOfUncheckedMtd.get(key) == 1){
                    System.out.println(key);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(count);
            throw new RuntimeException(e);
        } catch (Exception e){
            System.out.println(count);
            throw new RuntimeException();
        }
    }

    public static void checkImplMethodsNotChecked(String filepath){
        try{
            FileInputStream fileInputStream = new FileInputStream(filepath);
            Scanner sc = new Scanner(fileInputStream);
            int lineNum = 0;
            int count = 0;
            boolean isPrevLineImpl = false;
            int numberOfCurrentStartingSpaces = 0;
            int numberOfPrevStartingSpaces = 0;
            List<Integer> listOfIncompleteLines = new ArrayList<>();
            String prevLine = "";
            while (sc.hasNextLine()){
                count++;
                String line = sc.nextLine();
                if(line.trim().equals("")){
                    continue;
                }
                char[] charArrOfLine = line.toCharArray();
                int spacesBeforeLine = 0;
                for(char c : charArrOfLine){
                    if(c == ' '){
                        spacesBeforeLine++;
                    } else {
                        break;
                    }
                }
                numberOfCurrentStartingSpaces = spacesBeforeLine;
                if(isPrevLineImpl){
                    if(numberOfCurrentStartingSpaces <= numberOfPrevStartingSpaces && !prevLine.contains("hierarchy")){
                        System.out.println(prevLine.trim() + String.format("--%d",count-1));
                        listOfIncompleteLines.add(count - 1);
                    }
                    isPrevLineImpl = false;
                }
                if(line.contains("Impl.") || line.contains("Service.") || line.contains("ServiceImpl.")){
                    lineNum = count;
                    isPrevLineImpl = true;
                }
                numberOfPrevStartingSpaces = numberOfCurrentStartingSpaces;
                prevLine = line;
            }
            System.out.println(listOfIncompleteLines);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
