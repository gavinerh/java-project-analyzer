package MARMSUI.SpecialisedSqlMappingToVo;

import MARMSUI.SpecialisedSqlMappingToVo.model.Tierstat;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

public class ChangeClassInMethodDeclaration {
    public static void main(String[] args) {
        String toReplace = "UserProfileDetail";
        String[] replacementString = {"Tierstat","TierMileageSummary","HisCusEliteQual","CusPpsQual","CustomerTier","TierQual","Long","AccountStatusFunc","GeneralSqlObject","QualDetailsMonthly","CusClubQual","TransReserveVal"};
        String s = "    private static UserProfileDetail reflectionOnDataFromDb(UserProfileDetail tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {\n" +
                "        initializeMonthMapping();\n" +
                "        Method[] methods = tierstat.getClass().getDeclaredMethods();\n" +
                "        UserProfileDetail generated = new UserProfileDetail();\n" +
                "        Method[] methodsToInvoke = UserProfileDetail.class.getDeclaredMethods();\n" +
                "        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();\n" +
                "        for (Method method : methods) {\n" +
                "\n" +
                "            if (method.getName().startsWith(\"get\")) {\n" +
                "                Object val = method.invoke(tierstat);\n" +
                "                String type = method.getReturnType().getTypeName();\n" +
                "                try {\n" +
                "                    if (type.equals(\"java.util.Date\")) {\n" +
                "                        if (val == null) continue;\n" +
                "                    } else if (type.equals(\"java.lang.String\") || type.equals(\"java.lang.Double\") || type.equals(\"java.lang.Integer\") || type.equals(\"java.lang.Long\")) {\n" +
                "                        if (val == null) continue;\n" +
                "                    } else if (type.equals(\"double\")) {\n" +
                "                        if ((double) val == 0.0) continue;\n" +
                "                    } else if (type.equals(\"float\")) {\n" +
                "                        if ((float) val == 0.0f) continue;\n" +
                "                    } else if (type.equals(\"int\") || type.equals(\"java.lang.Integer\")) {\n" +
                "                        if ((int) val == 0) continue;\n" +
                "                    } else if (type.equals(\"java.lang.Long\") || type.equals(\"long\")) {\n" +
                "                        if ((long) val == 0) continue;\n" +
                "                    } else if (type.equals(\"java.sql.Timestamp\")) {\n" +
                "                        if (val == null) continue;\n" +
                "                    } else if (type.equals(\"java.util.List\")) {\n" +
                "                        continue;\n" +
                "                    }\n" +
                "                    mapOfFieldNameAndValue.put(generateSetterName(method.getName()), val);\n" +
                "                } catch (Exception e) {\n" +
                "                    System.out.println(e);\n" +
                "                }\n" +
                "\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "        for (Method method : methodsToInvoke) {\n" +
                "            String name = method.getName();\n" +
                "            if (name.startsWith(\"set\")) {\n" +
                "                Object val = mapOfFieldNameAndValue.get(name);\n" +
                "                if (val == null) continue;\n" +
                "                String toPrint = \"\";\n" +
                "                if (val.getClass().getTypeName().equals(\"java.lang.String\")) {\n" +
                "                    toPrint = String.format(\"tierstat.%s(\\\"%s\\\");\", name, val);\n" +
                "                } else if (val.getClass().getTypeName().equals(\"java.util.Date\")) {\n" +
                "                    String date = generateDateString(val.toString());\n" +
                "                    toPrint = String.format(\"tierstat.%s(dateFormat.parse(\\\"%s\\\"));\", name, date);\n" +
                "                } else if (val.getClass().getTypeName().equals(\"float\") || val.getClass().getTypeName().equals(\"java.lang.Float\")) {\n" +
                "                    toPrint = String.format(\"tierstat.%s(%sf);\", name, val);\n" +
                "                } else if (val.getClass().getTypeName().equals(\"long\") || val.getClass().getTypeName().equals(\"java.lang.Long\")) {\n" +
                "                    toPrint = String.format(\"tierstat.%s(%sl);\", name, val);\n" +
                "                } else if (val.getClass().getTypeName().equals(\"java.sql.Timestamp\")) {\n" +
                "                    String date = generateTimestamp((Timestamp) val);\n" +
                "                    toPrint = String.format(\"tierstat.%s(new Timestamp(dateFormat.parse(\\\"%s\\\").getTime()));\", name, date);\n" +
                "                } else {\n" +
                "                    toPrint = String.format(\"tierstat.%s(%s);\", name, val);\n" +
                "                }\n" +
                "                method.invoke(generated, val);\n" +
                "                System.out.println(toPrint);\n" +
                "            }\n" +
                "        }\n" +
                "        return generated;\n" +
                "    }\n" +
                "\n";
                String secondStringToAppend ="private static void printSetterMethods(List dataFromDb) throws ParseException, InvocationTargetException, IllegalAccessException {\n" +
                        "        List<Object> generatedList = new ArrayList<>();\n" +
                        "        System.out.println(\"try{\");\n" +
                        "        System.out.println(\"List<Tierstat> generatedList = new ArrayList<>();\");\n" +
                        "        System.out.println(\"Tierstat tierstat = null;\");\n" +
                        "        System.out.println(\"SimpleDateFormat dateFormat = new SimpleDateFormat(\\\"yyyy-MM-dd\\\");\");\n" +
                        "//        System.out.println(\"tierstat = new Tierstat();\");\n" +
                        "        // print setter code\n" +
                        "        for (Object tierstat1 : dataFromDb) {\n" +
                        "            System.out.println(\"tierstat = new Tierstat();\");\n" +
                        "            generatedList.add(bridgingMethod(tierstat1));\n" +
                        "            System.out.println(\"generatedList.add(tierstat);\");\n" +
                        "        }\n" +
                        "        System.out.println(\"return generatedList;\");\n" +
                        "        System.out.println(\"} catch(Exception e) {\");\n" +
                        "        System.out.println(\"e.printStackTrace();\\n}\\nreturn null;\");\n" +
                        "        System.out.println(generatedList.size());\n" +
                        "    }\n " +
                        "   private static String generateSetterName(String getter) {\n" +
                "        return \"set\" + getter.substring(3);\n" +
                "    }\n" +
                "\n" +
                "    private static Map<String, String> monthMapper = new HashMap<>();\n" +
                "\n" +
                "    private static String generateDateString(String dateStr) throws ParseException {\n" +
                "        String[] dateArr = dateStr.split(\" \");\n" +
                "        String s = String.format(String.format(\"%s-%s-%s\", dateArr[5], monthMapper.get(dateArr[1]), dateArr[2]));\n" +
                "        return s;\n" +
                "    }\n" +
                "\n" +
                "    private static String generateTimestamp(Timestamp timestamp) {\n" +
                "        SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd\");\n" +
                "        String dateString = dateFormat.format(timestamp);\n" +
                "        return dateString;\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    private static void initializeMonthMapping() {\n" +
                "        monthMapper.put(\"Jan\", \"01\");\n" +
                "        monthMapper.put(\"Feb\", \"02\");\n" +
                "        monthMapper.put(\"Mar\", \"03\");\n" +
                "        monthMapper.put(\"Apr\", \"04\");\n" +
                "        monthMapper.put(\"May\", \"05\");\n" +
                "        monthMapper.put(\"Jun\", \"06\");\n" +
                "        monthMapper.put(\"Jul\", \"07\");\n" +
                "        monthMapper.put(\"Aug\", \"08\");\n" +
                "        monthMapper.put(\"Sep\", \"09\");\n" +
                "        monthMapper.put(\"Oct\", \"10\");\n" +
                "        monthMapper.put(\"Nov\", \"11\");\n" +
                "        monthMapper.put(\"Dec\", \"12\");\n" +
                "    }";
        String[] strArr = s.split("\n");
        for(String replacement : replacementString) {
            String[] dup = new String[strArr.length];
            for(int i=0; i<strArr.length; i++) {
//                if(strArr[i].contains("printSetterMethods")) {
//                    dup[i] = strArr[i].replace("printSetterMethods", "printSetterMethods1");
//                }
                if(strArr[i].contains(toReplace)) {
                    dup[i] = strArr[i].replace(toReplace,replacement);
                } else {
                    dup[i] = strArr[i];
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for(String row : dup) {
                stringBuilder.append(row + "\n");
            }
            System.out.println(stringBuilder.toString());
        }

        // print bridging method
        String toPrint = toPrint = "private static Object bridgingMethod(Object o) throws ParseException, InvocationTargetException, IllegalAccessException {\n";
        for(int i=0; i<replacementString.length; i++) {
            if(i == 0) {
                toPrint += String.format("if(%s.class.isInstance(o)){\n", replacementString[i]);
            }
            else {
                toPrint += String.format("else if (%s.class.isInstance(o)){\n", replacementString[i]);
            }
            toPrint += String.format("return reflectionOnDataFromDb((%s) o);\n}\n", replacementString[i]);
        }
        toPrint += "return null;\n}";
        System.out.println();
        System.out.println(secondStringToAppend);
        System.out.println("====================");
        System.out.println(toPrint);
    }
}
