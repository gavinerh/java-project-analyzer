import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringComparator {

    public static void main(String[] args) {
        List<String> file1 = new ArrayList<>();
        List<String> file2 = new ArrayList<>();
        String filename1 = "/Users/macuser/Downloads/postNonEmptyString";
        String filename2 = "/Users/macuser/Downloads/postEmptyString";

        try{
            FileInputStream inputStream = new FileInputStream(filename1);
            populateList(file1, inputStream);
            inputStream = new FileInputStream(filename2);
            populateList(file2, inputStream);
            compareList(file1, file2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void populateList(List<String> list, FileInputStream inputStream){
        Scanner sc = new Scanner(inputStream);
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            list.add(line);
        }
    }

    private static void compareList(List<String> list1, List<String> list2){
        if(list1.size() != list2.size()){
            System.out.println("size not equal");
            return;
        }
        for(int i=0; i<list1.size(); i++){
            if(!compareString(list1.get(i), list2.get(i))){
                System.out.println("discrepancy occurred at line: " + i);
                System.out.println("File1: " + list1.get(i));
                System.out.println("File2: " + list2.get(i));
            }
        }
    }

    private static boolean compareString(String s1, String s2){
        return s1.equals(s2);
    }
}
