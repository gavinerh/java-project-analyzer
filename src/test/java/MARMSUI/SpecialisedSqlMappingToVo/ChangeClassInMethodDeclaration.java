package MARMSUI.SpecialisedSqlMappingToVo;

public class ChangeClassInMethodDeclaration {
    public static void main(String[] args) {
        String toReplace = "CusPpsQual";
        String replacementString = "Tierstat";
        String s = "private static void printSetterMethods(List<CusPpsQual> dataFromDb) throws ParseException, InvocationTargetException, IllegalAccessException {\n" +
                "        List<CusPpsQual> generatedList = new ArrayList<>();\n" +
                "        System.out.println(\"try{\");\n" +
                "        System.out.println(\"List<CusPpsQual> generatedList = new ArrayList<>();\");\n" +
                "        System.out.println(\"CusPpsQual tierstat = null;\");\n" +
                "        System.out.println(\"SimpleDateFormat dateFormat = new SimpleDateFormat(\\\"yyyy-MM-dd\\\");\");\n" +
                "//        System.out.println(\"tierstat = new CusPpsQual();\");\n" +
                "        // print setter code\n" +
                "        for (CusPpsQual tierstat1 : dataFromDb) {\n" +
                "            System.out.println(\"tierstat = new CusPpsQual();\");\n" +
                "            generatedList.add(reflectionOnDataFromDb(tierstat1));\n" +
                "            System.out.println(\"generatedList.add(tierstat);\");\n" +
                "        }\n" +
                "        System.out.println(\"return generatedList;\");\n" +
                "        System.out.println(\"} catch(Exception e) {\");\n" +
                "        System.out.println(\"e.printStackTrace();\\n}\\nreturn null;\");\n" +
                "        System.out.println(generatedList.size());\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "    private static CusPpsQual reflectionOnDataFromDb(CusPpsQual tierstat) throws InvocationTargetException, IllegalAccessException, ParseException {\n" +
                "        initializeMonthMapping();\n" +
                "        Method[] methods = tierstat.getClass().getDeclaredMethods();\n" +
                "        CusPpsQual generated = new CusPpsQual();\n" +
                "        Method[] methodsToInvoke = CusPpsQual.class.getDeclaredMethods();\n" +
                "        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();\n" +
                "        for (Method method : methods) {\n" +
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
                "        for (\n" +
                "                Method method : methodsToInvoke) {\n" +
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
                "                } else {\n" +
                "                    toPrint = String.format(\"tierstat.%s(%s);\", name, val);\n" +
                "                }\n" +
                "                method.invoke(generated, val);\n" +
                "                System.out.println(toPrint);\n" +
                "            }\n" +
                "        }\n" +
                "        return generated;\n" +
                "    }\n" +
                "\n" +
                "    private static String generateSetterName(String getter) {\n" +
                "        return \"set\" + getter.substring(3);\n" +
                "    }\n" +
                "\n" +
                "    private static Map<String, String> monthMapper = new HashMap<>();\n" +
                "\n" +
                "    private static String generateDateString(String dateStr) throws ParseException {\n" +
                "        String[] dateArr = dateStr.split(\" \");\n" +
                "        SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd\");\n" +
                "        String s = String.format(String.format(\"%s-%s-%s\", dateArr[5], monthMapper.get(dateArr[1]), dateArr[2]));\n" +
                "        return s;\n" +
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
        for(int i=0; i<strArr.length; i++) {
            if(strArr[i].contains(toReplace)) {
                strArr[i] = strArr[i].replace(toReplace,replacementString);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(String row : strArr) {
            stringBuilder.append(row + "\n");
        }
        System.out.println(stringBuilder.toString());
    }
}
