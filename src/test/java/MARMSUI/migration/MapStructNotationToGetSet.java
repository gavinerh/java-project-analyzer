package MARMSUI.migration;

import MARMSUI.migration.model.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MapStructNotationToGetSet {
    public static void main(String[] args) {
        String methodName = "convertCusGuardianToCustomerGuardianInfoVO";
        String mapStructNotation = "@Mapping(target = \"rcreUserId\", source = \"rcreUserId\")\n" +
                "\t@Mapping(target = \"rcreDt\", source = \"rcreDt\", qualifiedByName = \"convertDateToString\")\n" +
                "\t@Mapping(target = \"lchgUserId\", source = \"lchgUserId\")\n" +
                "\t@Mapping(target = \"lchgDt\", source = \"lchgDt\", qualifiedByName = \"convertDateToString\")\n" +
                "\t@Mapping(target = \"intId\", source = \"intId\")\n" +
                "\t@Mapping(target = \"crCardTypeCd\", source = \"crCardTypeCd\")\n" +
                "\t@Mapping(target = \"crCardBankCd\", source = \"crCardBankCd\")\n" +
                "\t@Mapping(target = \"crCardBrandCd\", source = \"crCardBrandCd\")\n" +
                "\t@Mapping(target = \"crCardNo\", source = \"crCardNo\")\n" +
                "\t@Mapping(target = \"crCardExpDt\", source = \"crCardExpDt\", qualifiedByName = \"convertDateToString\")\n" +
                "\t@Mapping(target = \"crCardCobrandFlg\", source = \"crCardCobrandFlg\")\n" +
                "\t@Mapping(target = \"crCardBankCtryCd\", source = \"crCardBankCtryCd\")\n" +
                "\t@Mapping(target = \"crCardBankTxt\", source = \"crCardBankTxt\")\n" +
                "\t@Mapping(target = \"crCardBankCtryTxt\", source = \"crCardBankCtryTxt\")\n" +
                "\t@Mapping(target = \"token\", source = \"token\")\n" +
                "\t@Mapping(target = \"paymentcurrency\", source = \"paymentcurrency\")\n" +
                "\t@Mapping(target = \"defaultcardflag\", source = \"defaultcardflag\")\n" +
                "\t@Mapping(target = \"cardholdername\", source = \"cardholdername\")";
        String[] arr = mapStructNotation.split("\n");
        List<String> sourceFields = extractFields(arr, "source");
        List<String> targetFields = extractFields(arr, "target");
        List<String> qualifiedByNameMethods = extractQualifiedMethods(arr);
        // change the class names
        Class target = CustomerCreditCardVo.class;
        Class source = CusCreditCard.class;
        List<String> listOfSimilarFields = extractSimilarFields(target, source);
        Set<String> printedMtds = printSetterMethods(sourceFields, targetFields, qualifiedByNameMethods, target,source, methodName);
        System.out.println("======================");
        printSetterMethodsAdv(listOfSimilarFields, printedMtds, target, source);
    }

    public static void printSetterMethodsAdv(List<String> fieldList, Set<String> printedMtds, Class target, Class source) {
        for(String s : fieldList) {
            String setterName = generateSetterName(s);
            String toPrint = String.format("target.%s(source.%s());", generateSetterName(s),MapStructNotationToGetSet.generateGetterName(s));
            if(!printedMtds.contains(setterName)){
                System.out.println(toPrint);
            }
        }
        System.out.println("return target;\n}\n");
    }

    private static List<String> extractSimilarFields(Class target, Class source) {
        Set<String> targetFieldsSet = new HashSet<>();
        Set<String> sourceFieldSet = new HashSet<>();
        for(Field field : target.getDeclaredFields()) {
            field.setAccessible(true);
            targetFieldsSet.add(field.getName());
        }
        for(Field field : source.getDeclaredFields()) {
            field.setAccessible(true);
            sourceFieldSet.add(field.getName());
        }
        List<String> listOfSimilarFieldName = new ArrayList<>();
        for(String field : targetFieldsSet) {
            if(sourceFieldSet.contains(field)) {
                listOfSimilarFieldName.add(field);
            }
        }
        return listOfSimilarFieldName;
    }

    public static Set<String> printSetterMethods(List<String> source, List<String> target, List<String> qualifiedMtd, Class targetClass, Class sourceClass, String methodName) {
        if (source.size() != target.size() && target.size() != qualifiedMtd.size()) {
            throw new RuntimeException("Size not similar");
        }
        System.out.println(String.format("public static %s %s(%s source) {\n %s target = new %s();", targetClass.getSimpleName(), methodName, sourceClass.getSimpleName(), targetClass.getSimpleName(), targetClass.getSimpleName()));
        Set<String> printedMethods = new HashSet<>();
        for (int i = 0; i < source.size(); i++) {
            String toPrint = "";
            if(qualifiedMtd.get(i).equals("none")){
                toPrint = String.format("target.%s(source.%s());", generateSetterName(target.get(i)),generateGetterName(source.get(i)));
            } else {
                toPrint = String.format("target.%s(%s(source.%s()));", generateSetterName(target.get(i)),qualifiedMtd.get(i),generateGetterName(source.get(i)));
            }
            printedMethods.add(generateSetterName(target.get(i)));
            System.out.println(toPrint);
        }
        return printedMethods;
    }

    public static String generateGetterName(String field) {
        return "get" + field.substring(0,1).toUpperCase() + field.substring(1);
    }

    public static String generateSetterName(String field) {
        return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    private static List<String> extractQualifiedMethods(String[] arr) {
        List<String> list = new ArrayList<>();
        for (String s : arr) {
            if (s.trim().startsWith("//")) continue;
            int startInd = s.indexOf("qualifiedByName");
            if (startInd == -1) {
                list.add("none");
            } else {
                startInd = startInd + "qualifiedByName".length();
                startInd = s.indexOf("\"", startInd) + 1;
                int endInd = s.indexOf("\"", startInd);
                list.add(s.substring(startInd, endInd));
            }
        }
        return list;
    }

    private static List<String> extractFields(String[] arr, String identifier) {
        List<String> list = new ArrayList<>();
        for (String s : arr) {
            if (s.trim().startsWith("//")) continue;
            int startInd = s.indexOf(identifier) + identifier.length();
            startInd = s.indexOf("\"", startInd) + 1;
            int endIndex = s.indexOf("\"", startInd);
            String val = s.substring(startInd, endIndex);
            list.add(val.trim());
        }
        return list;
    }
}
