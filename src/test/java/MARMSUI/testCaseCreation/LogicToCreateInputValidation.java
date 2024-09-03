package MARMSUI.testCaseCreation;

import java.lang.reflect.Field;

public class LogicToCreateInputValidation {
    public static void main(String[] args) throws ClassNotFoundException {
        int month = 2;

        switch (month) {
            case 1 -> System.out.println("1");
            case 2 -> System.out.println("2");
            case 3 -> System.out.println("3");
            case 4 -> System.out.println("4");
        }
    }

    private static void generateValidation(String apiName, String nameOfClass) throws ClassNotFoundException {
        if(nameOfClass.endsWith("[]")) {
            nameOfClass = nameOfClass.substring(0,nameOfClass.length()-2);
        }
        Field[] fields = Class.forName(nameOfClass).getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            String typename = field.getType().getTypeName();
            if(!typename.startsWith("com.sg.sq")) {
                switch (typename) {
                    case "java.lang.String":
                        System.out.println(String.format("%s.%s.type=${STRING}",apiName,field.getName()));
                        break;
                    case "int", "java.lang.Integer", "long", "java.lang.Long":
                        System.out.println(String.format("%s.%s.type=${NUMBER}",apiName,field.getName()));
                        break;
                    case "boolean", "java.lang.Boolean":
                        System.out.println(String.format("%s.%s.type=${BOOLEAN}",apiName,field.getName()));
                        break;
                    case "java.util.Date", "java.sql.Timestamp":
                        System.out.println(String.format("%s.%s.type=${FORMATTED_DATE}",apiName,field.getName()));
                        break;
                    default:
                        throw new RuntimeException("Cannot map type");
                }
            } else {
                generateValidation(apiName, typename);
            }
        }
    }
}
