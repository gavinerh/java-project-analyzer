package methodReflection.test;

import methodReflection.api.ClothingProduct;
import methodReflection.api.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ProductTest {
    public static void main(String[] args) {
        testGetters(ClothingProduct.class);
        testSetters(ClothingProduct.class);
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        if(clazz == null || clazz.equals(Object.class)) {
            return Collections.emptyList();
        }

        Field[] currentClassFields = clazz.getDeclaredFields();
        List<Field> inheritedFields = getAllFields(clazz.getSuperclass());
        List<Field> allFields = new ArrayList<>();
        allFields.addAll(Arrays.asList(currentClassFields));
        allFields.addAll(inheritedFields);

        return allFields;
    }

    public static void testSetters(Class<?> dataClass) {
        List<Field> fields = getAllFields(dataClass);

        for (Field field : fields) {
            String setterName = "set" + capitalizeFirstLetterSetter(field.getName());
//            Map<String, Method> mapOfAllMethods = mapMethodNameToMethod(dataClass);

            Method setterMethod = null;
            try {
                setterMethod = dataClass.getMethod(setterName, field.getType());
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException(String.format("No such method exist: %s", setterName));
            }

            // if the method is contained in all methods
//            if(!mapOfAllMethods.containsKey(setterName)) {
//                throw new IllegalStateException(String.format("Field %s does not contain the corresponding setter method", field.getName()));
//            }

//            Method setterMethod = mapOfAllMethods.get(setterName);
            // check return type
            if(!setterMethod.getReturnType().equals(void.class)){
                throw new IllegalStateException(String.format("Setter %s has a return type %s but expected %s", setterName, setterMethod.getReturnType(), "void"));
            }

            // check parameter type
            if(!setterMethod.getParameterTypes()[0].equals(field.getType())){
                throw new IllegalStateException(String.format("Setter %s should have a parameter type of %s", setterName, field.getType()));
            }

            // check parameter values
            if(setterMethod.getParameterCount() != 1) {
                throw new IllegalStateException(String.format("%s should have only one parameter", setterName));
            }
        }

    }

    private static String capitalizeFirstLetterSetter(String setterName) {
        return setterName.substring(0,1).toUpperCase().concat(setterName.substring(1));
    }

    public static void testGetters(Class<?> dataClass) {
        List<Field> fields = getAllFields(dataClass);

        Map<String, Method> methodNameToMethod = mapMethodNameToMethod(dataClass);

        for (Field field : fields) {
            String getterName = "get" + capitalizeFirstLetter(field.getName());
            if (!methodNameToMethod.containsKey(getterName)) {
                throw new IllegalStateException(String.format("Field %s does not have a getter method", field.getName()));
            }

            // check the return type is expected
            Method getter = methodNameToMethod.get(getterName);
            if (!getter.getReturnType().equals(field.getType())) {
                throw new IllegalStateException(String.format(
                        "Getter method %s() has a return type of %s but expected %s",
                        getterName, getter.getReturnType().getTypeName(), field.getType()
                ));
            }

            // check if getter method takes in any parameters
            if (getter.getParameterCount() > 0) {
                throw new IllegalStateException(String.format("Getter method %s should have 0 parameters", getterName));
            }
        }
    }

    private static String capitalizeFirstLetter(String getterName) {
        String firstLetter = getterName.substring(0, 1);
        String capitalizedFirstLetter = firstLetter.toUpperCase();
        StringBuilder builder = new StringBuilder();
        builder.append(capitalizedFirstLetter);
        builder.append(getterName.substring(1));
        return builder.toString();
    }

    private static Map<String, Method> mapMethodNameToMethod(Class<?> dataClass) {
        Method[] allMethods = dataClass.getMethods();
        Map<String, Method> nameToMethod = new HashMap<>();
        for (Method method : allMethods) {
            nameToMethod.put(method.getName(), method);
        }

        return nameToMethod;
    }
}
