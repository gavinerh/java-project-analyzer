package MARMSUI.SpecialisedSqlMappingToVo;

import MARMSUI.SpecialisedSqlMappingToVo.model.Tierstat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class SqlToResultMap {
    public static void main(String[] args) {
        try {
            boolean toSortSqlColumns = true;
            String sql = "SEQ_NO, TIER_STATUS_IND, TIER_STATUS_PTS_REQ, TIER_STATUS_SECT_REQ, TIER_STATUS_MIN_PRD, TIER_HIERARCHY,\n" +
                    "        TIER_STATUS_PTS_OFFSET, TIER_STATUS_SECT_OFFSET, TIER_STATUS_ACCUM_PTS_REQ, TIER_STATUS_ACCUM_SECT_REQ,\n" +
                    "        TIER_STATUS_ACCUM_PTS_OFFSET, TIER_STATUS_ACCUM_SECT_OFFSET, RULE_ACTIVE_IND, APPLICABLE_QLFY_IND,\n" +
                    "        TIER_QUAL_PRD, TIER_STATUS_VAL_REQ, TIER_STATUS_ACCUM_VAL_REQ, RULE_IND, START_DT, END_DT, YRS_IN_TIER,\n" +
                    "        TIER_STATUS_VAL_OFFSET, TIER_STATUS_ACCUM_VAL_OFFSET,PER_EM_PPS ,QUAL_SCHEME,QUAL_PROC_IND";

            String methodString = "ts.setSeqNo(rs.getInt(1));\n" +
                    "        ts.setTierStatus(rs.getString(2));\n" +
                    "        ts.setPtsReq((long) rs.getLong(3));\n" +
                    "        ts.setSectReq((float) rs.getFloat(4));\n" +
                    "        ts.setMinPeriod((long) rs.getLong(5));\n" +
                    "        ts.setHierarchy(rs.getInt(6));\n" +
                    "        ts.setPointOffset((long) rs.getLong(7));\n" +
                    "        ts.setSectorOffset((float) rs.getFloat(8));\n" +
                    "        ts.setAccPtsReq((long) rs.getLong(9));\n" +
                    "        ts.setAccSectReq((float) rs.getFloat(10));\n" +
                    "        ts.setAccPtsOffset((long) rs.getLong(11));\n" +
                    "        ts.setAcctSectOffset((float) rs.getFloat(12));\n" +
                    "            ts.setActive(rs.getString(13));\n" +
                    "        ts.setApplQualInd(rs.getString(14));\n" +
                    "        ts.setTierQuadPrd(rs.getInt(15));\n" +
                    "        ts.setValReq(rs.getLong(16));\n" +
                    "        ts.setAccValReq(rs.getLong(17));\n" +
                    "        ts.setRuleInd(rs.getString(18));\n" +
                    "        ts.setStartDate(rs.getDate(19));\n" +
                    "        ts.setEndDate(rs.getDate(20));\n" +
                    "        ts.setYrsInTier(rs.getInt(21));\n" +
                    "        ts.setValOffset(rs.getLong(22));\n" +
                    "        ts.setAccValOffset(rs.getLong(23));\n" +
                    "        ts.setEliteOrPPSpercentage(rs.getInt(24));\n" +
                    "        ts.setQualInterim(rs.getString(25))\n;" +
                    "        ts.setQualProcID(rs.getString(26));";
            List<String> methodNamesList = generateMethodNamesUsed(methodString);
            // paste in the class file in use
            Tierstat ts = new Tierstat();
            validateMethodNames(methodNamesList, ts.getClass());
            List<String> fieldNameList = generateFieldNames(methodNamesList);
            List<Class> fieldReturnTypes = validateFieldNamesAndGenerateReturnType(fieldNameList, ts.getClass());
            List<String> fieldTypesString = mapClassTypeToTypeInString(fieldReturnTypes);
            List<String> actualColListFromSqlQuery = generateSqlColumnArr(sql);
            List<String> sqlColFromMtd = null;
            List<String> sqlColList = null;
            if(toSortSqlColumns) {
                sqlColFromMtd = generateSqlColumnsFromSetterMethod(methodString);
                if(actualColListFromSqlQuery != null) {
                    sqlColList = generateSqlColumnGivenSortedList(actualColListFromSqlQuery,sqlColFromMtd);
                } else {
                    sqlColList = sqlColFromMtd;
                }
                verifyColFromMtdAreSimilarToSqlQuery(actualColListFromSqlQuery, sqlColList);
            } else {
                sqlColList = actualColListFromSqlQuery;
            }
            generateResultMap(sqlColList, fieldNameList, fieldTypesString);
            System.out.println();
            sqlColList.stream().forEach(x-> {
                System.out.print(String.format("%s,",x));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void verifyColFromMtdAreSimilarToSqlQuery(List<String> actualSqlCols, List<String> listToBeVerified) {
        if(actualSqlCols == null) {
            return;
        }
        if(actualSqlCols.size() != listToBeVerified.size()) {
            throw new RuntimeException("List size for actual and method generated are not equal");
        }
        Set<String> setOfActual = actualSqlCols.stream().collect(Collectors.toSet());
        Set<String> setOfOther = listToBeVerified.stream().collect(Collectors.toSet());
        if(setOfOther.size() != setOfActual.size() || setOfOther.size() != actualSqlCols.size()) {
            throw new RuntimeException("List size for actual and method generated are not equal");
        }
        for(String col : listToBeVerified){
            if(!setOfActual.contains(col)) {
                throw new RuntimeException(String.format("Col %s is not found in actual", col));
            }
        }
    }

    private static List<String> generateSqlColumnGivenSortedList(List<String> sqlFromActualQuery, List<String> sqlColFromMtd) {
        if(sqlFromActualQuery == null) {
            return null;
        }
        List<String> sqlColList = new ArrayList<>();
        for(int i=0; i<sqlColFromMtd.size(); i++) {
            try {
                int colNum = Integer.parseInt(sqlColFromMtd.get(i));
                sqlColList.add(sqlFromActualQuery.get(colNum-1));
            } catch (NumberFormatException e) {
                sqlColList.add(sqlColFromMtd.get(i));
            }
        }
        return sqlColList;
    }

    private static List<String> generateSqlColumnsFromSetterMethod(String methodString) {
        // get the column name from the setter method
        List<String> listOfSqlMethod = new ArrayList<>();
        String[] mtdArr = methodString.split("\n");
        for(String row : mtdArr){
            int startIndex = row.indexOf(".get");
            startIndex = row.indexOf("(", startIndex) + 1;
            int endIndex = row.indexOf(")", startIndex);
            String content = row.substring(startIndex,endIndex).trim();
            if(content.contains("\"")) {
                startIndex = content.indexOf("\"")+1;
                endIndex = content.indexOf("\"", startIndex);
                listOfSqlMethod.add(content.substring(startIndex,endIndex));
            } else {
                // contain numbers
                listOfSqlMethod.add(content.toString());
            }
        }
        return listOfSqlMethod;
    }

    private static List<String> mapClassTypeToTypeInString(List<Class> types) {
        List<String> list = new ArrayList<>();
        for(Class type : types) {
            if(type.getTypeName().equals("java.lang.String")) {
                list.add("VARCHAR");
            } else if (type.getTypeName().equals("long") || type.getTypeName().equals("int") ) {
                list.add("NUMERIC");
            }  else if(type.getTypeName().equals("double") || type.getTypeName().equals("float")) {
                list.add("DOUBLE");
            } else if (type.getTypeName().equals("java.util.Date") || type.getTypeName().equals("java.sql.Timestamp")) {
                list.add("TIMESTAMP");
            } else {
                throw new RuntimeException("Type not mapped: " + type.getTypeName());
            }
        }
        return list;
    }

    private static void generateResultMap(List<String> sqlColList, List<String> fieldNameList, List<String> fieldTypeList) {
        if(sqlColList.size() != fieldNameList.size() || fieldNameList.size() != fieldTypeList.size()) {
            throw new RuntimeException("List size is not the same");
        }
        System.out.println("<resultMap id=\"\" type=\"\">");
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<sqlColList.size(); i++) {
            String toAppend = String.format("<result column=\"%s\" jdbcType=\"%s\" property=\"%s\"/>\n", sqlColList.get(i),fieldTypeList.get(i),fieldNameList.get(i));
            buffer.append(toAppend);
        }
        System.out.print(buffer.toString());
        System.out.println("</resultMap>");
    }

    private static List<Class> validateFieldNamesAndGenerateReturnType(List<String> fieldNameList, Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<Class> fieldTypes = new ArrayList<>();
        Map<String, Field> trueFields = new HashMap<>();
        for(Field field : fields) {
            field.setAccessible(true);
            trueFields.put(field.getName(), field);
        }
        fields = clazz.getSuperclass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            trueFields.put(field.getName(),field);
        }
        for(String field : fieldNameList) {
            if(trueFields.get(field) == null) {
                System.out.println(String.format("Field name is not found: %s", field));
                throw new RuntimeException();
            }
            fieldTypes.add(trueFields.get(field).getType());
        }
        return fieldTypes;
    }

    private static List<String> generateFieldNames(List<String> mtdNameList) {
        List<String> fieldList = new ArrayList<>();
        for (String mtdName : mtdNameList) {
            fieldList.add(generateFieldNameFromMtdName(mtdName));
        }
        return fieldList;
    }

    private static String generateFieldNameFromMtdName(String mtdName) {
        String truncatedName = mtdName.substring(3);
        return truncatedName.substring(0, 1).toLowerCase() + truncatedName.substring(1);
    }

    private static void validateMethodNames(List<String> mtdNamesList, Class clazz) throws NoSuchMethodException {
        Method[] methods = clazz.getDeclaredMethods();
        Set<String> trueMethodNames = new HashSet<>();
        for (Method method : methods) {
            trueMethodNames.add(method.getName());
        }
        methods = clazz.getSuperclass().getDeclaredMethods();
        for(Method method : methods) {
            trueMethodNames.add(method.getName());
        }
        for (String name : mtdNamesList) {
            if (!trueMethodNames.contains(name)) {
                System.out.println(String.format("Method is not found: %s", name));
                throw new NoSuchMethodException("Method name is not found");
            }
        }
    }

    private static List<String> generateMethodNamesUsed(String methodStr) {
        String[] mtdArr = methodStr.split("\n");
        List<String> mtdList = new ArrayList<>();
        for (String row : mtdArr) {
            String trimmed = row.trim();
            if (!trimmed.isEmpty() && !trimmed.startsWith("//")) {
                int startIndex = trimmed.indexOf(".") + 1;
                int endIndex = trimmed.indexOf("(");
                mtdList.add(trimmed.substring(startIndex, endIndex).trim());
            }
        }
        return mtdList;
    }

    private static List<String> generateSqlColumnArr(String sql) {
        if(sql == null || sql.trim().isEmpty()){
            return null;
        }
        List<String> listOfCol = new ArrayList<>();
        boolean insideParams = false;
        String column = "";
        for (int i = 0; i < sql.length(); i++) {
            String letter = sql.substring(i, i + 1);
            if (letter.equals("(")) {
                insideParams = true;
                column += letter;
                continue;
            }
            if (letter.equals(")")) {
                insideParams = false;
                column += letter;
                continue;
            }
            column += letter;
            if (letter.equals(",") && !insideParams || i == sql.length() - 1) {
                String trimmedCol = column.trim();
                if (trimmedCol.substring(trimmedCol.length() - 1, trimmedCol.length()).equals(",")) {
                    trimmedCol = trimmedCol.substring(0, trimmedCol.length() - 1);
                }
                listOfCol.add(trimmedCol.trim());
                column = "";
            }
        }
        return listOfCol;
    }
}


