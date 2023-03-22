package utilities;

import internal.ImageBuffer;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ReflectiveFactory {
    public static <T> T createObject(Class<T> clazz, Object ...args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> [] parameterTypes = Arrays.stream(args)
                .map(Object::getClass)
                .toArray(Class[]::new);
        Constructor<?> constructor = clazz.getDeclaredConstructor(parameterTypes);
        constructor.setAccessible(true);
        return (T) constructor.newInstance(args);
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Object argument = new byte[700];
        ImageBuffer imageBuffer = createObject(ImageBuffer.class);
    }
}
