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
import java.util.stream.Collectors;

public class GenerateObjectForTesting {
    static List<String> initializationList = new ArrayList<>();

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
//                if (testModel.methodName.equalsIgnoreCase("getClubInfo")) {
//                    System.out.println("");
//                }

                List list = mtd(testModel.responseString, testModel.returnTypeOfDataMethod);
                if (list != null) {
                    printSetterMethods(list, testModel);
                    counter++;
                } else {
                    printForNonList(testModel);
                    counterForNonList++;
                }
                printMockMapperMethodCaller(testModel);
            }
            System.out.println();
            for (String s : initializationList) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printMockMapperMethodCaller(TestModel testModel) {
//        String mapperName = extractMapperName(testModel.mapperName);
//        if(testModel.methodName.equalsIgnoreCase("getTransReserveVal")){
//            System.out.println("");
//        }
        String mapperMethodName = testModel.methodName;
        String paramList = generateParamList(testModel);
        String mockDataMethod = testModel.combinedName;
        String val = String.format("when(%s.%s(%s)).thenReturn(%s());", testModel.mapperName, mapperMethodName, paramList == null ? "" : paramList, mockDataMethod);
        initializationList.add(val);
    }

    private static String generateParamList(TestModel testModel) {
        List<String> paramsList = testModel.paramsList;
        List<String> paramTypeList = testModel.paramsTypeList;
        String toReturn = "";
        for (int i = 0; i < paramTypeList.size(); i++) {
            if (paramTypeList.get(i).contains("Date")) {
                toReturn += String.format("customDateFormat.parse(%s),", paramsList.get(i));
            } else if (paramTypeList.get(i).contains("Long") || paramTypeList.get(i).contains("long")) {
                toReturn += String.format("%sl,", paramsList.get(i));
            } else {
                toReturn += String.format("%s,", paramsList.get(i));
            }
        }
        toReturn = toReturn.length() != 0 ? toReturn.substring(0, toReturn.length() - 1) : null;
        return toReturn;
    }

    private static void printForNonList(TestModel testModel) {
        String paramsDetails = "";
        String typeDetails = "";
        for (int i = 0; i < testModel.paramsTypeList.size(); i++) {
            paramsDetails += i >= testModel.paramsList.size() ? "" : testModel.paramsList.get(i) + ",";
            typeDetails += testModel.paramsTypeList.get(i) + ",";
        }
        if (!paramsDetails.isEmpty() && !typeDetails.isEmpty()) {
            paramsDetails = paramsDetails.substring(0, paramsDetails.length() - 1);
            typeDetails = typeDetails.substring(0, typeDetails.length() - 1);
        } else {
            paramsDetails = "empty";
            typeDetails = "empty";
        }
        testModel.combinedName = testModel.mapperName + testModel.methodName;
        System.out.println(String.format("private %s %s(){\n//params: %s\n//types: %s", testModel.returnTypeOfDataMethod, testModel.mapperName + testModel.methodName, paramsDetails, typeDetails));

        if (testModel.returnTypeOfDataMethod.equalsIgnoreCase("date")) {
            System.out.println("SimpleDateFormat sdf = new SimpleDateFormat(\"dd-MM-yyyy\");");
            System.out.println(String.format("try {\n" +
                    "            return sdf.parse(%s);\n" +
                    "        } catch (ParseException e) {\n" +
                    "            throw new RuntimeException(\"Cannot parse date\");\n" +
                    "        }\n}", testModel.responseString));
        } else {
            System.out.println(String.format("return %s;\n}", testModel.responseString));
        }
    }

    private static String extractMapperName(String line) {
        String key = "mappers.";
        int start = line.indexOf(key) + key.length();
        String name = line.substring(start).trim();
        return name.substring(0, 1).toLowerCase() + name.substring(1);
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
                testModel.mapperName = extractMapperName(line);
            } else if (counter == 1) {
                testModel.returnTypeOfDataMethod = line;
            } else if (counter == 2) {
                testModel.methodName = line;
            } else if (counter == 3) {
                testModel.responseString = line;
                list.add(testModel);
            } else if (counter == 4) {
                if (line.equals("[]")) {
                    testModel.setParamsList("");
                } else {
                    testModel.setParamsList(line);
                }
            } else if (counter == 5) {
                if (line.equals("[]")) {
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


    // ======================================================================================================

    // PASTE FROM THIS LINE ONWARDS

    private static MergeTransactions reflectionOnDataFromDb(MergeTransactions tierstat, int counter) throws InvocationTargetException, IllegalAccessException, ParseException {
        if(counter == 0) {
            System.out.println("MergeTransactions tierstat = null;");
            System.out.println("SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");");
            System.out.println("generatedList = new ArrayList<MergeTransactions>();");
        }
        System.out.println("tierstat = new MergeTransactions();");
        initializeMonthMapping();
        MergeTransactions generated = new MergeTransactions();
        Method[] methodsToInvoke = MergeTransactions.class.getDeclaredMethods();
        List<Method> listOfMethods = Arrays.stream(methodsToInvoke).collect(Collectors.toList());
        listOfMethods.addAll(Arrays.stream(tierstat.getClass().getSuperclass().getDeclaredMethods()).collect(Collectors.toList()));
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : listOfMethods) {

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
        for (Method method : listOfMethods) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString((Date) val);
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
        System.out.println("generatedList.add(tierstat);");
        return generated;
    }

    private static MiscTransactions reflectionOnDataFromDb(MiscTransactions tierstat, int counter) throws InvocationTargetException, IllegalAccessException, ParseException {
        if(counter == 0) {
            System.out.println("MiscTransactions tierstat = null;");
            System.out.println("SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");");
            System.out.println("generatedList = new ArrayList<MiscTransactions>();");
        }
        System.out.println("tierstat = new MiscTransactions();");
        initializeMonthMapping();
        MiscTransactions generated = new MiscTransactions();
        Method[] methodsToInvoke = MiscTransactions.class.getDeclaredMethods();
        List<Method> listOfMethods = Arrays.stream(methodsToInvoke).collect(Collectors.toList());
        listOfMethods.addAll(Arrays.stream(tierstat.getClass().getSuperclass().getDeclaredMethods()).collect(Collectors.toList()));
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : listOfMethods) {

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
        for (Method method : listOfMethods) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString((Date) val);
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
        System.out.println("generatedList.add(tierstat);");
        return generated;
    }

    private static AirRedemptionTransactions reflectionOnDataFromDb(AirRedemptionTransactions tierstat, int counter) throws InvocationTargetException, IllegalAccessException, ParseException {
        if(counter == 0) {
            System.out.println("AirRedemptionTransactions tierstat = null;");
            System.out.println("SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");");
            System.out.println("generatedList = new ArrayList<AirRedemptionTransactions>();");
        }
        System.out.println("tierstat = new AirRedemptionTransactions();");
        initializeMonthMapping();
        AirRedemptionTransactions generated = new AirRedemptionTransactions();
        Method[] methodsToInvoke = AirRedemptionTransactions.class.getDeclaredMethods();
        List<Method> listOfMethods = Arrays.stream(methodsToInvoke).collect(Collectors.toList());
        listOfMethods.addAll(Arrays.stream(tierstat.getClass().getSuperclass().getDeclaredMethods()).collect(Collectors.toList()));
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : listOfMethods) {

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
        for (Method method : listOfMethods) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString((Date) val);
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
        System.out.println("generatedList.add(tierstat);");
        return generated;
    }

    private static NonAirRedemptionTransactions reflectionOnDataFromDb(NonAirRedemptionTransactions tierstat, int counter) throws InvocationTargetException, IllegalAccessException, ParseException {
        if(counter == 0) {
            System.out.println("NonAirRedemptionTransactions tierstat = null;");
            System.out.println("SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");");
            System.out.println("generatedList = new ArrayList<NonAirRedemptionTransactions>();");
        }
        System.out.println("tierstat = new NonAirRedemptionTransactions();");
        initializeMonthMapping();
        NonAirRedemptionTransactions generated = new NonAirRedemptionTransactions();
        Method[] methodsToInvoke = NonAirRedemptionTransactions.class.getDeclaredMethods();
        List<Method> listOfMethods = Arrays.stream(methodsToInvoke).collect(Collectors.toList());
        listOfMethods.addAll(Arrays.stream(tierstat.getClass().getSuperclass().getDeclaredMethods()).collect(Collectors.toList()));
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : listOfMethods) {

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
        for (Method method : listOfMethods) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString((Date) val);
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
        System.out.println("generatedList.add(tierstat);");
        return generated;
    }

    private static AccrualTransactions reflectionOnDataFromDb(AccrualTransactions tierstat, int counter) throws InvocationTargetException, IllegalAccessException, ParseException {
        if(counter == 0) {
            System.out.println("AccrualTransactions tierstat = null;");
            System.out.println("SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");");
            System.out.println("generatedList = new ArrayList<AccrualTransactions>();");
        }
        System.out.println("tierstat = new AccrualTransactions();");
        initializeMonthMapping();
        AccrualTransactions generated = new AccrualTransactions();
        Method[] methodsToInvoke = AccrualTransactions.class.getDeclaredMethods();
        List<Method> listOfMethods = Arrays.stream(methodsToInvoke).collect(Collectors.toList());
        listOfMethods.addAll(Arrays.stream(tierstat.getClass().getSuperclass().getDeclaredMethods()).collect(Collectors.toList()));
        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();
        for (Method method : listOfMethods) {

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
        for (Method method : listOfMethods) {
            String name = method.getName();
            if (name.startsWith("set")) {
                Object val = mapOfFieldNameAndValue.get(name);
                if (val == null) continue;
                String toPrint = "";
                if (val.getClass().getTypeName().equals("java.lang.String")) {
                    toPrint = String.format("tierstat.%s(\"%s\");", name, val);
                } else if (val.getClass().getTypeName().equals("java.util.Date")) {
                    String date = generateDateString((Date) val);
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
        System.out.println("generatedList.add(tierstat);");
        return generated;
    }


    private static void printSetterMethods(List dataFromDb, TestModel testModel) throws ParseException, InvocationTargetException, IllegalAccessException {
        List<Object> generatedList = new ArrayList<>();
        if(dataFromDb.isEmpty()) {
            printMethodDeclaration(testModel);
            System.out.println(String.format("%s generatedList = new ArrayList<>();", testModel.returnTypeOfDataMethod));
            System.out.println("return generatedList;\n}");
        } else {
            for (int i = 0; i < dataFromDb.size(); i++) {
                Object tierstat1 = dataFromDb.get(i);
                if (i == 0) {
                    printMethodDeclaration(testModel);
                    System.out.println("List generatedList = null;");
                    System.out.println("try{");
                }
                generatedList.add(bridgingMethod(tierstat1, i));
//            System.out.println("generatedList.add(tierstat);");
            }
            System.out.println("return generatedList;");
            System.out.println("} catch(Exception e) {");
            System.out.println("e.printStackTrace();\n}\nreturn null;\n}");
            printMockMapperMethodCaller(testModel);
        }
    }

    private static String generateSetterName(String getter) {
        return "set" + getter.substring(3);
    }

    private static Map<String, String> monthMapper = new HashMap<>();

    private static String generateDateString(Date dateStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(dateStr);
    }

    private static String generateTimestamp(Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

    private static void printMethodDeclaration(TestModel testModel) {
        String methodName = testModel.mapperName + testModel.methodName;
        methodName = generateUniqueMethodName(methodName, testModel);
        testModel.combinedName = methodName;
        String declaration = String.format("public static %s %s() {\n", testModel.returnTypeOfDataMethod, methodName);
        System.out.println(declaration);
        String paramsDetails = "";
        String typeDetails = "";
        for (int i = 0; i < testModel.paramsTypeList.size(); i++) {
            paramsDetails += i >= testModel.paramsList.size() ? "" : testModel.paramsList.get(i) + ",";
            typeDetails += testModel.paramsTypeList.get(i) + ",";
        }
        if (!paramsDetails.isEmpty() && !typeDetails.isEmpty()) {
            paramsDetails = paramsDetails.substring(0, paramsDetails.length() - 1);
            typeDetails = typeDetails.substring(0, typeDetails.length() - 1);
        } else {
            paramsDetails = "empty";
            typeDetails = "empty";
        }
        System.out.println(String.format("// params: %s", paramsDetails));
        System.out.println(String.format("// types: %s", typeDetails));
    }

    private static Map<String, TestModel> methodMap = new HashMap<>();

    private static String generateUniqueMethodName(String name, TestModel testModel) {
        int num = 1;
        String uniqueName = name;
        while (true) {
            if (methodMap.containsKey(uniqueName)) {
                uniqueName = name + num;
            } else {
                methodMap.put(uniqueName, testModel);
                break;
            }
            num++;
        }
        return uniqueName;
    }
    private static Object bridgingMethod(Object o,int counter) throws ParseException, InvocationTargetException, IllegalAccessException {
        if(MergeTransactions.class.isInstance(o)){
            return reflectionOnDataFromDb((MergeTransactions) o, counter);
        }
        else if (MiscTransactions.class.isInstance(o)){
            return reflectionOnDataFromDb((MiscTransactions) o, counter);
        }
        else if (AirRedemptionTransactions.class.isInstance(o)){
            return reflectionOnDataFromDb((AirRedemptionTransactions) o, counter);
        }
        else if (NonAirRedemptionTransactions.class.isInstance(o)){
            return reflectionOnDataFromDb((NonAirRedemptionTransactions) o, counter);
        }
        else if (AccrualTransactions.class.isInstance(o)){
            return reflectionOnDataFromDb((AccrualTransactions) o, counter);
        }
        return null;
    }
    private static List mtd(String response, String returnType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new CustomDateFormat());
        if(returnType.contains("List<MergeTransactions>"))
            return mapper.readValue(response, new TypeReference<List<MergeTransactions>>(){});
        else if (returnType.contains("List<MiscTransactions>"))
            return mapper.readValue(response, new TypeReference<List<MiscTransactions>>(){});
        else if (returnType.contains("List<AirRedemptionTransactions>"))
            return mapper.readValue(response, new TypeReference<List<AirRedemptionTransactions>>(){});
        else if (returnType.contains("List<NonAirRedemptionTransactions>"))
            return mapper.readValue(response, new TypeReference<List<NonAirRedemptionTransactions>>(){});
        else if (returnType.contains("List<AccrualTransactions>"))
            return mapper.readValue(response, new TypeReference<List<AccrualTransactions>>(){});
        return null;
    }
}