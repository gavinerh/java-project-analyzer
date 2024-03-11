package MARMSUI.testCaseCreation;

public class ChangeClassInMethodDeclaration {
    public static void main(String[] args) {
        String duplicateNum = "";
        String toReplace = "UserProfileDetail";
        String[] replacementString = {"CusAlternateName","EmailDomainInfoExample","ReferenceCdExample","CusOinfltPrefExample","CusSeatPref","CusSupplInfoExample","String","CusFamilyInfoExample","CusFamilyInfo","CityExample","CusStopmailInfoExample","CusAccountExample","CusPersExample","CusOinfltPref","CusCardIss","MileageSum","CusInfltPref","CusOdBucketPts","ReferenceCd","void","CusPrtSubprg","City","EventLogVo","CusPhone","Long","CusAlternateNameExample","CustomerStopmailInfoVo","CusSeatPrefExample","MileageSumExample","CountryExample","CusOffpInfo","long","CusPin","CusStopmailInfo","CusDirmailPref","CusCardIssExample","CusPers","CusLifestyle","CusPhoneExample","CusAddnInfo","CusAddrAud","CusSocialMedia","int","CusAddr","java.sql.Date","CusAddnInfoExample","boolean","CusCreditCard","CusCreditCardExample","CusGuardianInfo","short","CusMobilePref","CusAccount","CusSupplInfo"};
        String s = "    private static UserProfileDetail reflectionOnDataFromDb" + duplicateNum + "(UserProfileDetail tierstat, int counter) throws InvocationTargetException, IllegalAccessException, ParseException {\n" +
                "if(counter == 0) {\n" +
                "System.out.println(\"UserProfileDetail tierstat = null;\");\n" +
                "System.out.println(\"SimpleDateFormat dateFormat = new SimpleDateFormat(\\\"yyyy-MM-dd HH:mm:ss\\\");\");\n" +
                "System.out.println(\"generatedList = new ArrayList<UserProfileDetail>();\");\n" +
                "        } \n" +
                "System.out.println(\"tierstat = new UserProfileDetail();\");\n" +
                "        initializeMonthMapping();\n" +
                "        UserProfileDetail generated = new UserProfileDetail();\n" +
                "        Method[] methodsToInvoke = UserProfileDetail.class.getDeclaredMethods();\n" +
                "       List<Method> listOfMethods = Arrays.stream(methodsToInvoke).collect(Collectors.toList());\n" +
                "        listOfMethods.addAll(Arrays.stream(tierstat.getClass().getSuperclass().getDeclaredMethods()).collect(Collectors.toList()));\n" +
                "        Map<String, Object> mapOfFieldNameAndValue = new HashMap<>();\n" +
                "        for (Method method : listOfMethods) {\n" +
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
                "        for (Method method : listOfMethods) {\n" +
                "            String name = method.getName();\n" +
                "            if (name.startsWith(\"set\")) {\n" +
                "                Object val = mapOfFieldNameAndValue.get(name);\n" +
                "                if (val == null) continue;\n" +
                "                String toPrint = \"\";\n" +
                "                if (val.getClass().getTypeName().equals(\"java.lang.String\")) {\n" +
                "                    toPrint = String.format(\"tierstat.%s(\\\"%s\\\");\", name, val);\n" +
                "                } else if (val.getClass().getTypeName().equals(\"java.util.Date\")) {\n" +
                "                    String date = generateDateString((Date) val);\n" +
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
                "System.out.println(\"generatedList.add(tierstat);\");\n" +
                "        return generated;\n" +
                "    }\n" +
                "\n";
        String secondStringToAppend = "private static void printSetterMethods(List dataFromDb, TestModel testModel) throws ParseException, InvocationTargetException, IllegalAccessException {\n" +
                "        List<Object> generatedList = new ArrayList<>();\n" +
                "        if(dataFromDb.isEmpty()) {\n" +
                "            printMethodDeclaration(testModel);\n" +
                "            System.out.println(String.format(\"%s generatedList = new ArrayList<>();\", testModel.returnTypeOfDataMethod));\n" +
                "            System.out.println(\"return generatedList;\\n}\");\n" +
                "        } else {\n" +
                "            for (int i = 0; i < dataFromDb.size(); i++) {\n" +
                "                Object tierstat1 = dataFromDb.get(i);\n" +
                "                if (i == 0) {\n" +
                "                    printMethodDeclaration(testModel);\n" +
                "                    System.out.println(\"List generatedList = null;\");\n" +
                "                    System.out.println(\"try{\");\n" +
                "                }\n" +
                "                generatedList.add(bridgingMethod(tierstat1, i));\n" +
                "//            System.out.println(\"generatedList.add(tierstat);\");\n" +
                "            }\n" +
                "            System.out.println(\"return generatedList;\");\n" +
                "            System.out.println(\"} catch(Exception e) {\");\n" +
                "            System.out.println(\"e.printStackTrace();\\n}\\nreturn null;\\n}\");\n" +
                "printMockMapperMethodCaller(testModel);\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    private static String generateSetterName(String getter) {\n" +
                "        return \"set\" + getter.substring(3);\n" +
                "    }\n" +
                "\n" +
                "    private static Map<String, String> monthMapper = new HashMap<>();\n" +
                "\n" +
                "    private static String generateDateString(Date dateStr) throws ParseException {\n" +
                "        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n" +
                "        return simpleDateFormat.format(dateStr);\n" +
                "    }\n" +
                "\n" +
                "    private static String generateTimestamp(Timestamp timestamp) {\n" +
                "        SimpleDateFormat dateFormat = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n" +
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
                "    }\n" +
                "\n" +
                "    private static void printMethodDeclaration(TestModel testModel) {\n" +
                "        String methodName = testModel.mapperName + testModel.methodName;\n" +
                "        methodName = generateUniqueMethodName(methodName, testModel);\n" +
                "       testModel.combinedName = methodName;\n" +
                "        String declaration = String.format(\"public static %s %s() {\\n\", testModel.returnTypeOfDataMethod, methodName);\n" +
                "        System.out.println(declaration);\n" +
                "        String paramsDetails = \"\";\n" +
                "        String typeDetails = \"\";\n" +
                "        for (int i = 0; i < testModel.paramsTypeList.size(); i++) {\n" +
                "            paramsDetails += i >= testModel.paramsList.size() ? \"\" : testModel.paramsList.get(i) + \",\";\n" +
                "            typeDetails += testModel.paramsTypeList.get(i) + \",\";\n" +
                "        }\n" +
                "        if (!paramsDetails.isEmpty() && !typeDetails.isEmpty()) {\n" +
                "            paramsDetails = paramsDetails.substring(0, paramsDetails.length() - 1);\n" +
                "            typeDetails = typeDetails.substring(0, typeDetails.length() - 1);\n" +
                "        } else {\n" +
                "            paramsDetails = \"empty\";\n" +
                "            typeDetails = \"empty\";\n" +
                "        }\n" +
                "        System.out.println(String.format(\"// params: %s\", paramsDetails));\n" +
                "        System.out.println(String.format(\"// types: %s\", typeDetails));\n" +
                "    }\n" +
                "\n" +
                "    private static Map<String, TestModel> methodMap = new HashMap<>();\n" +
                "\n" +
                "    private static String generateUniqueMethodName(String name, TestModel testModel) {\n" +
                "        int num = 1;\n" +
                "        String uniqueName = name;\n" +
                "        while (true) {\n" +
                "            if (methodMap.containsKey(uniqueName)) {\n" +
                "                uniqueName = name + num;\n" +
                "            } else {\n" +
                "                methodMap.put(uniqueName, testModel);\n" +
                "                break;\n" +
                "            }\n" +
                "            num++;\n" +
                "        }\n" +
                "        return uniqueName;\n" +
                "    }";
        String[] strArr = s.split("\n");
        for (String replacement : replacementString) {
            String[] dup = new String[strArr.length];
            if (replacement.equalsIgnoreCase("long") || replacement.equalsIgnoreCase("string")) {
                // print reflectionOnDataFromDb for basic types
                System.out.println(String.format("private static %s reflectionOnDataFromDb(%s tierstat, int counter) {", replacement, replacement));
                System.out.println(String.format("if(counter == 0) {\n" +
                        "            System.out.println(\"Long tierstat = null;\");\n" +
                        "            System.out.println(\"generatedList = new ArrayList<%s>();\");\n" +
                        "        }", replacement));
                if (replacement.equalsIgnoreCase("long")) {
                    System.out.println("System.out.println(String.format(\"generatedList.add(%sl);\", tierstat));\n" +
                            "        return tierstat;\n}");
                } else if (replacement.equalsIgnoreCase("string")) {
                    System.out.println("System.out.println(String.format(\"generatedList.add(%s);\", tierstat));\n" +
                            "        return tierstat;\n}");
                }

            } else {
                // print reflectionOnDataFromDb for non basic types
                for (int i = 0; i < strArr.length; i++) {
//                if(strArr[i].contains("printSetterMethods")) {
//                    dup[i] = strArr[i].replace("printSetterMethods", "printSetterMethods1");
//                }
                    if (strArr[i].contains(toReplace)) {
                        dup[i] = strArr[i].replace(toReplace, replacement);
                    } else {
                        dup[i] = strArr[i];
                    }
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (String row : dup) {
                    stringBuilder.append(row + "\n");
                }
                System.out.println(stringBuilder.toString());
            }

        }

        // print bridging method
        String toPrint = toPrint = "private static Object bridgingMethod(Object o,int counter) throws ParseException, InvocationTargetException, IllegalAccessException {\n";
        for (int i = 0; i < replacementString.length; i++) {
            if (i == 0) {
                toPrint += String.format("if(%s.class.isInstance(o)){\n", replacementString[i]);
            } else {
                toPrint += String.format("else if (%s.class.isInstance(o)){\n", replacementString[i]);
            }
            toPrint += String.format("return reflectionOnDataFromDb((%s) o, counter);\n}\n", replacementString[i]);
        }
        toPrint += "return null;\n}";
        System.out.println();
        System.out.println(secondStringToAppend);
        System.out.println(toPrint);
        generateMethod(replacementString);
    }

    private static void generateMethod(String[] types) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("private static List mtd(String response, String returnType) throws JsonProcessingException {\n" +
                "        ObjectMapper mapper = new ObjectMapper();\n" +
                "        mapper.setDateFormat(new CustomDateFormat());\n");
        for (int i = 0; i < types.length; i++) {
            if (i == 0) {
                stringBuilder.append(String.format("if(returnType.contains(\"List<%s>\"))\n", types[i]));
            } else {
                stringBuilder.append(String.format("else if (returnType.contains(\"List<%s>\"))\n", types[i]));
            }
            stringBuilder.append(String.format("return mapper.readValue(response, new TypeReference<List<%s>>(){});\n", types[i]));
        }
        stringBuilder.append("return null;\n}\n");
        System.out.println(stringBuilder.toString());
    }
}
