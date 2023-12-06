package MARMSUI.SpecialisedSqlMappingToVo;

import MARMSUI.SpecialisedSqlMappingToVo.model.Tierstat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenerateObjectForTesting {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ParseException {

        List<Tierstat> dataFromDb = new ArrayList<>();
        Tierstat tierstat = new Tierstat();
        tierstat.setActive("Y");
        tierstat.setAccPtsReq(100);
        tierstat.setPtsReq(355);
        tierstat.setSectReq(3.3f);
        tierstat.setEndDate(new Date());
        dataFromDb.add(tierstat);
        tierstat = new Tierstat();
        tierstat.setActive("N");
        tierstat.setAccPtsReq(200);
        tierstat.setPtsReq(677);
        tierstat.setEndDate(new Date());
        dataFromDb.add(tierstat);
        List<Tierstat> generatedList = new ArrayList<>();
        System.out.println("try{");
        System.out.println("List<Tierstat> generatedList = new ArrayList<>();");
        System.out.println("Tierstat tierstat = null;");
        System.out.println("SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd\");");
//        System.out.println("tierstat = new Tierstat();");
        // print setter code
        printSetterMethods(dataFromDb);
    }

    private static void printSetterMethods(List<Tierstat> dataFromDb) throws ParseException, InvocationTargetException, IllegalAccessException {
        List<Tierstat> generatedList = new ArrayList<>();
        System.out.println("try{");
        System.out.println("List<Tierstat> generatedList = new ArrayList<>();");
        System.out.println("Tierstat tierstat = null;");
        System.out.println("SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd\");");
//        System.out.println("tierstat = new Tierstat();");
        // print setter code
        for (Tierstat tierstat1 : dataFromDb) {
            System.out.println("tierstat = new Tierstat();");
            generatedList.add(reflectionOnDataFromDb(tierstat1));
            System.out.println("generatedList.add(tierstat);");
        }
        System.out.println("return generatedList;");
        System.out.println("} catch(Exception e) {");
        System.out.println("e.printStackTrace();\n}\nreturn null;");
        System.out.println(generatedList.size());
    }


    private static Tierstat reflectionOnDataFromDb(Tierstat tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        Tierstat generated = new Tierstat();
        Method[] methodsToInvoke = Tierstat.class.getDeclaredMethods();
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                Object val = method.invoke(tierstat);
                String type = method.getReturnType().getTypeName();
                try {
                    if (type.equals("java.util.Date")) {
                        if (val == null) continue;
                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
                        if (val == null) continue;
                    } else if (type.equals("double")) {
                        if ((double) val == 0.0) continue;
                    } else if (type.equals("float")) {
                        if ((float) val == 0.0f) continue;
                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
                        if ((int) val == 0) continue;
                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
                        if ((long) val == 0) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (
                Method method : methodsToInvoke) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString(val.toString());
                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
                    toPrint = String.format("tierstat.%s(%sf);", name, val);
                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
                    toPrint = String.format("tierstat.%s(%sl);", name, val);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static String generateSetterName(String getter) {
        return "set" + getter.substring(3);
    }

    private static Map<String, String> monthMapper = new HashMap<>();

    private static String generateDateString(String dateStr) throws ParseException {
        String[] dateArr = dateStr.split(" ");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String s = String.format(String.format("%s-%s-%s", dateArr[5], monthMapper.get(dateArr[1]), dateArr[2]));
        return s;
    }


    private static void initializeMonthMapping() {
        monthMapper.put("Jan", "01");
        monthMapper.put("Feb", "02");
        monthMapper.put("Mar", "03");
        monthMapper.put("Apr", "04");
        monthMapper.put("May", "05");
        monthMapper.put("Jun", "06");
        monthMapper.put("Jul", "07");
        monthMapper.put("Aug", "08");
        monthMapper.put("Sep", "09");
        monthMapper.put("Oct", "10");
        monthMapper.put("Nov", "11");
        monthMapper.put("Dec", "12");
    }
}
