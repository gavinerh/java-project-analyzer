package MARMSUI.testCaseCreation;

import java.lang.reflect.Field;
//import java.lang.reflect.InaccessibleObjectException;

public class PrintFieldsGivenClassName {
    private static void printTypeForObjectInitialization(Class clazz, String hierarchy) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String currentHierarchy = addToHierarchy(hierarchy, field.getName());
                // field that is not a basic type
                String type = field.getAnnotatedType().getType().getTypeName();
                System.out.println(String.format("%s(%s)", currentHierarchy, type));
                if (type.contains("[]") && !type.contains("java.lang.String") && !type.contains("java.lang.Long")) {
                    String clazzName = extractTypeName(field.getAnnotatedType().getType().getTypeName(), "0", "[");
                    printTypeForObjectInitialization(Class.forName(clazzName), currentHierarchy);
                } else if (type.equals("java.util.List")) {
                    // logic not validated
                    String clazzName = extractTypeName(field.getAnnotatedType().getType().getTypeName(), "java.util.List<", ">");
                    printTypeForObjectInitialization(Class.forName(clazzName), currentHierarchy);

                } else if (type.startsWith("com.sg.sq.marmsui")) {
                    printTypeForObjectInitialization(Class.forName(type), currentHierarchy);

                } else {
                    // basic types
//                System.out.println(String.format("%s (%s)", c, field.getType()));
                }
            }
//        } catch (InaccessibleObjectException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String extractTypeName(String typeName, String startChar, String endChar) {
        int startInd = 0;
        if (startChar.equals("0")) {
            startInd = 0;
        } else {
            startInd = typeName.indexOf(startChar) + startChar.length();
        }
        int endInd = typeName.indexOf(endChar, startInd);
        return typeName.substring(startInd, endInd).trim();
    }

    private static String addToHierarchy(String parent, String child) {
        return parent + "-" + child;
    }
}
