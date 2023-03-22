package arrays;

import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {
        int[] oneDimenArray = new int[]{1,2, 3, 4};
        double[][] twoDimenArray = new double[][]{{1,2,4}, {5,6,8}};
        double[][][] treeDimenArr = new double[][][]{{{1,2}, {3,4}, {5,6}}, {{3,4}, {5,6}, {4,4}}, {{3,4}, {7,7}, {8,8}}};
        inspectArrayValues(treeDimenArr);
    }

    private static void inspectArrayValues(Object array) {
        int arrayLength = Array.getLength(array);
        System.out.print("[");

        for(int i=0; i<arrayLength; i++) {
            Object element = Array.get(array, i);

            if(element.getClass().isArray()) {
                inspectArrayValues(element);
            } else {
                System.out.print(element);
            }



            if(i != arrayLength - 1) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
    }

    public static void inspectArrayObject(Object arrayObject) {
        Class<?> clazz = arrayObject.getClass();

        System.out.println(String.format("Is array: %s", clazz.isArray()));

        Class<?> arrayComponentType = clazz.getComponentType();

        System.out.println(String.format("This is an array of type %s", arrayComponentType.getTypeName()));
    }
}
