package MARMSUI.SpecialisedSqlMappingToVo;

import MARMSUI.SpecialisedSqlMappingToVo.model.TransReserveVal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class FieldToSetterMethodChecker {
    public static void main(String[] args) {
        TransReserveVal ts = new TransReserveVal();
        Set<String> fieldsFromMtd = generateFieldFromMethods(ts.getClass());
        validateFieldsWithMethods(ts.getClass(), fieldsFromMtd);
    }

    private static void validateFieldsWithMethods(Class clazz, Set<String> fieldsFromMtd) {
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if(!fieldsFromMtd.contains(fieldName)) {
                System.out.println(String.format("Field name: %s not found", fieldName));
            }
        }
    }

    private static Set<String> generateFieldFromMethods(Class clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        Set<String> set = new HashSet<>();
        for(Method method : methods) {
            set.add(generateFieldNameFromMtdName(method.getName()));
        }
        return set;
    }

    private static String generateFieldNameFromMtdName(String mtdName) {
        String truncatedName = mtdName.substring(3);
        return truncatedName.substring(0, 1).toLowerCase() + truncatedName.substring(1);
    }
}
