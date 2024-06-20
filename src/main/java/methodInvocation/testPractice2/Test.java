package methodInvocation.testPractice2;



import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        int falseCount = 0;
//        Method[] methods = Reflection1.class.getDeclaredMethods();
//        Object instance = Reflection1.class.getDeclaredConstructor().newInstance();
//        for(Method method : methods) {
//            if(method.getName().toLowerCase().contains("test")){
//                if(method.getReturnType().equals(boolean.class)){
//                    Boolean returnVal = (Boolean) method.invoke(instance);
//                    if(returnVal == false) {
//                        falseCount++;
//                    }
//                }
//            }
//        }
//        System.out.println(String.format("False count : %d", falseCount));
        try {
            FileWriter writer = new FileWriter("/Users/macuser/Documents/files/Log/original.csv", true);
            writer.append("\nhello");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
