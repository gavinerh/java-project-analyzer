package MARMSUI.SpecialisedSqlMappingToVo;

import MARMSUI.SpecialisedSqlMappingToVo.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GenerateObjectForTesting {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ParseException {
        try {
            String fileName = "/Users/macuser/Desktop/response_content";
            String printerMethodFile = "/Users/macuser/Desktop/printingMethodForTest.txt";
            FileInputStream fileInputStream = new FileInputStream(fileName);
            List<TestModel> testModelList = populateListOfTestModel(fileInputStream);
            // there are currently 48 sql queries
            int counter = 0;
            int counterForNonList = 0;
            for (TestModel testModel : testModelList) {
                List list = mtd(testModel.responseString, testModel.returnTypeOfDataMethod);
                if(list != null) {
                    printSetterMethods(list);
                    counter++;
                } else {
                    printForNonList(testModel);
                    counterForNonList++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printForNonList(TestModel testModel) {

    }


    private static List<TestModel> populateListOfTestModel(FileInputStream fileInputStream) throws IOException {
        Scanner sc = new Scanner(fileInputStream);
        List<TestModel> list = new ArrayList<>();
        int counter = 0;
        TestModel testModel = null;
        while (sc.hasNextLine()) {

            String line = sc.nextLine();
            if (line.trim().isEmpty()) {
                counter = 0;
                testModel = new TestModel();
                continue;
            }
            if (counter == 0) {
                testModel.packageName = line;
            } else if (counter == 1) {
                testModel.returnTypeOfDataMethod = line;
            } else if (counter == 2) {
                testModel.methodName = line;
            } else if (counter == 3) {
                testModel.responseString = line;
                list.add(testModel);
            } else if (counter == 4) {
                if(line.equals("[]")){
                    testModel.setParamsList("");
                }else {
                    testModel.setParamsList(line);
                }
            } else if (counter == 5) {
                if(line.equals("[]")){
                    testModel.setParamsTypeList("");
                } else {
                    testModel.setParamsTypeList(line);
                }

            } else {
                throw new RuntimeException("Should not hit here");
            }
            counter++;
        }
        fileInputStream.close();
        return list;
    }


    // ==============================================================

    // PASTE FROM THIS LINE ONWARDS
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
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
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
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static TierMileageSummary reflectionOnDataFromDb(TierMileageSummary tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        TierMileageSummary generated = new TierMileageSummary();
        Method[] methodsToInvoke = TierMileageSummary.class.getDeclaredMethods();
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
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
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
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static HisCusEliteQual reflectionOnDataFromDb(HisCusEliteQual tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        HisCusEliteQual generated = new HisCusEliteQual();
        Method[] methodsToInvoke = HisCusEliteQual.class.getDeclaredMethods();
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
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
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
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static CusPpsQual reflectionOnDataFromDb(CusPpsQual tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        CusPpsQual generated = new CusPpsQual();
        Method[] methodsToInvoke = CusPpsQual.class.getDeclaredMethods();
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
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
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
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static CustomerTier reflectionOnDataFromDb(CustomerTier tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        CustomerTier generated = new CustomerTier();
        Method[] methodsToInvoke = CustomerTier.class.getDeclaredMethods();
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
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
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
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static TierQual reflectionOnDataFromDb(TierQual tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        TierQual generated = new TierQual();
        Method[] methodsToInvoke = TierQual.class.getDeclaredMethods();
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
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
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
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

//    private static Long reflectionOnDataFromDb(Long tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
//        initializeMonthMapping();
//        Method[] methods = tierstat.getClass().getDeclaredMethods();
//        Long generated = new Long();
//        Method[] methodsToInvoke = Long.class.getDeclaredMethods();
//        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
//        for (Method method : methods) {
//
//            if (method.getName().startsWith("get")) {
//                Object val = method.invoke(tierstat);
//                String type = method.getReturnType().getTypeName();
//                try {
//                    if (type.equals("java.util.Date")) {
//                        if (val == null) continue;
//                    } else if (type.equals("java.lang.String") || type.equals("java.lang.Double") || type.equals("java.lang.Integer") || type.equals("java.lang.Long")) {
//                        if (val == null) continue;
//                    } else if (type.equals("double")) {
//                        if ((double) val == 0.0) continue;
//                    } else if (type.equals("float")) {
//                        if ((float) val == 0.0f) continue;
//                    } else if (type.equals("int") || type.equals("java.lang.Integer")) {
//                        if ((int) val == 0) continue;
//                    } else if (type.equals("java.lang.Long") || type.equals("long")) {
//                        if ((long) val == 0) continue;
//                    } else if (type.equals("java.sql.Timestamp")) {
//                        if (val == null) continue;
//                    } else if (type.equals("java.util.List")) {
//                        continue;
//                    }
//                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
//                } catch (Exception e) {
//                    System.out.println(e);
//                }
//
//            }
//
//        }
//        for (Method method : methodsToInvoke) {
//            String name = method.getName();
//            if (name.startsWith("set")) {
//                Object val = mapOfFieldNameAndValue.get(name);
//                if (val == null) continue;
//                String toPrint = "";
//                if (val.getClass().getTypeName().equals("java.lang.String")) {
//                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
//                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
//                    String date = generateDateString(val.toString());
//                    toPrint = String.format("tierstat.%s(dateFormat.parse(\"%s\"));", name, date);
//                } else if (val.getClass().getTypeName().equals("float") || val.getClass().getTypeName().equals("java.lang.Float")) {
//                    toPrint = String.format("tierstat.%s(%sf);", name, val);
//                } else if (val.getClass().getTypeName().equals("long") || val.getClass().getTypeName().equals("java.lang.Long")) {
//                    toPrint = String.format("tierstat.%s(%sl);", name, val);
//                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
//                    String date = generateTimestamp((Timestamp) val);
//                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
//                } else {
//                    toPrint = String.format("tierstat.%s(%s);", name, val);
//                }
//                method.invoke(generated, val);
//                System.out.println(toPrint);
//            }
//        }
//        return generated;
//    }

    private static AccountStatusFunc reflectionOnDataFromDb(AccountStatusFunc tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        AccountStatusFunc generated = new AccountStatusFunc();
        Method[] methodsToInvoke = AccountStatusFunc.class.getDeclaredMethods();
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
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
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
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static GeneralSqlObject reflectionOnDataFromDb(GeneralSqlObject tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        GeneralSqlObject generated = new GeneralSqlObject();
        Method[] methodsToInvoke = GeneralSqlObject.class.getDeclaredMethods();
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
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
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
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static QualDetailsMonthly reflectionOnDataFromDb(QualDetailsMonthly tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        QualDetailsMonthly generated = new QualDetailsMonthly();
        Method[] methodsToInvoke = QualDetailsMonthly.class.getDeclaredMethods();
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
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
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
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static CusClubQual reflectionOnDataFromDb(CusClubQual tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        CusClubQual generated = new CusClubQual();
        Method[] methodsToInvoke = CusClubQual.class.getDeclaredMethods();
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
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
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
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }

    private static TransReserveVal reflectionOnDataFromDb(TransReserveVal tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {
        initializeMonthMapping();
        Method[] methods = tierstat.getClass().getDeclaredMethods();
        TransReserveVal generated = new TransReserveVal();
        Method[] methodsToInvoke = TransReserveVal.class.getDeclaredMethods();
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
                    } else if (type.equals("java.sql.Timestamp")) {
                        if (val == null) continue;
                    } else if (type.equals("java.util.List")) {
                        continue;
                    }
                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

        }
        for (Method method : methodsToInvoke) {
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
                } else if (val.getClass().getTypeName().equals("java.sql.Timestamp")) {
                    String date = generateTimestamp((Timestamp) val);
                    toPrint = String.format("tierstat.%s(new Timestamp(dateFormat.parse(\"%s\").getTime()));", name, date);
                } else {
                    toPrint = String.format("tierstat.%s(%s);", name, val);
                }
                method.invoke(generated, val);
                System.out.println(toPrint);
            }
        }
        return generated;
    }


    //    modify the printSetterMethods to accept list of generic object (alrdy initialised to specific class)
//    modify the printSetterMethods to print the method declaration and it will call the bridgingMethod
//    check why the TierMileageSummary generates lastChangeDate field, when the sql query runs?
    private static void printSetterMethods(List dataFromDb) throws ParseException, InvocationTargetException, IllegalAccessException {
        List<Object> generatedList = new ArrayList<>();

//        System.out.println("tierstat = new Tierstat();");
        // print setter code
        for (Object tierstat1 : dataFromDb) {
            System.out.println("tierstat = new Tierstat();");
            generatedList.add(bridgingMethod(tierstat1));
            System.out.println("generatedList.add(tierstat);");
        }
        System.out.println("return generatedList;");
        System.out.println("} catch(Exception e) {");
        System.out.println("e.printStackTrace();\n}\nreturn null;");
        System.out.println(generatedList.size());
    }

    private static String generateSetterName(String getter) {
        return "set" + getter.substring(3);
    }

    private static Map<String, String> monthMapper = new HashMap<>();

    private static String generateDateString(String dateStr) throws ParseException {
        String[] dateArr = dateStr.split(" ");
        String s = String.format(String.format("%s-%s-%s", dateArr[5], monthMapper.get(dateArr[1]), dateArr[2]));
        return s;
    }

    private static String generateTimestamp(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(timestamp);
        return dateString;
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

    private static Object bridgingMethod(Object o) throws ParseException, InvocationTargetException, IllegalAccessException {
        if (Tierstat.class.isInstance(o)) {
            return reflectionOnDataFromDb((Tierstat) o);
        } else if (TierMileageSummary.class.isInstance(o)) {
            return reflectionOnDataFromDb((TierMileageSummary) o);
        } else if (HisCusEliteQual.class.isInstance(o)) {
            return reflectionOnDataFromDb((HisCusEliteQual) o);
        } else if (CusPpsQual.class.isInstance(o)) {
            return reflectionOnDataFromDb((CusPpsQual) o);
        } else if (CustomerTier.class.isInstance(o)) {
            return reflectionOnDataFromDb((CustomerTier) o);
        } else if (TierQual.class.isInstance(o)) {
            return reflectionOnDataFromDb((TierQual) o);
        } else if (AccountStatusFunc.class.isInstance(o)) {
            return reflectionOnDataFromDb((AccountStatusFunc) o);
        } else if (GeneralSqlObject.class.isInstance(o)) {
            return reflectionOnDataFromDb((GeneralSqlObject) o);
        } else if (QualDetailsMonthly.class.isInstance(o)) {
            return reflectionOnDataFromDb((QualDetailsMonthly) o);
        } else if (CusClubQual.class.isInstance(o)) {
            return reflectionOnDataFromDb((CusClubQual) o);
        } else if (TransReserveVal.class.isInstance(o)) {
            return reflectionOnDataFromDb((TransReserveVal) o);
        }
        return null;
    }

    private static List mtd(String response, String returnType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        if (returnType.contains("Tierstat"))
            return mapper.readValue(response, new TypeReference<List<Tierstat>>() {
            });
        else if (returnType.contains("TierMileageSummary"))
            return mapper.readValue(response, new TypeReference<List<TierMileageSummary>>() {
            });
        else if (returnType.contains("HisCusEliteQual"))
            return mapper.readValue(response, new TypeReference<List<HisCusEliteQual>>() {
            });
        else if (returnType.contains("CusPpsQual"))
            return mapper.readValue(response, new TypeReference<List<CusPpsQual>>() {
            });
        else if (returnType.contains("CustomerTier"))
            return mapper.readValue(response, new TypeReference<List<CustomerTier>>() {
            });
        else if (returnType.contains("TierQual"))
            return mapper.readValue(response, new TypeReference<List<TierQual>>() {
            });
        else if (returnType.contains("Long"))
            return mapper.readValue(response, new TypeReference<List<Long>>() {
            });
        else if (returnType.contains("AccountStatusFunc"))
            return mapper.readValue(response, new TypeReference<List<AccountStatusFunc>>() {
            });
        else if (returnType.contains("GeneralSqlObject"))
            return mapper.readValue(response, new TypeReference<List<GeneralSqlObject>>() {
            });
        else if (returnType.contains("QualDetailsMonthly"))
            return mapper.readValue(response, new TypeReference<List<QualDetailsMonthly>>() {
            });
        else if (returnType.contains("CusClubQual"))
            return mapper.readValue(response, new TypeReference<List<CusClubQual>>() {
            });
        else if (returnType.contains("TransReserveVal"))
            return mapper.readValue(response, new TypeReference<List<TransReserveVal>>() {
            });
        return null;
    }


}
