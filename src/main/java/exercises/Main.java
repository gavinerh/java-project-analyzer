package exercises;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException{
        Class<?> stringClass = String.class;

        Map<String, Integer> map = new HashMap<>();

        Class<?> hashMapClass = map.getClass();

        Class<?> squareClass = Class.forName("exercises.Square") ;

//        printClassInfo(hashMapClass, stringClass, squareClass);
        Object circleObject = new Drawable() {
            @Override
            public int getNumberOfCorners() {
                return 0;
            }
        };
        printClassInfo(Collection.class, int[][].class, circleObject.getClass());
    }
    private static void printClassInfo(Class<?> ...classes){
        for(Class<?> clazz : classes) {
            System.out.println(String.format("class name: %s, class package name: %s", clazz.getSimpleName(), clazz.getPackage()));
            Class<?> [] implementedInterfaces = clazz.getInterfaces();
            for(Class<?> i : implementedInterfaces) {
                System.out.println(String.format("class %s implements %s interface", clazz.getSimpleName(), i.getSimpleName()));
                System.out.println(String.format("Is array: %s", clazz.isArray()));
                System.out.println(String.format("Is primitive: %s", clazz.isPrimitive()));
                System.out.println(String.format("Is anonymous: %s", clazz.isAnonymousClass()));
            }

            System.out.println();
            System.out.println();
        }


    }
}
