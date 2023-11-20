package MARMSUI.SpecialisedSqlMappingToVo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class SqlToResultMap {
    public static void main(String[] args) {
        try {
            boolean toSortSqlColumns = true;
            String sql = "";

            String methodString = "ts.setSeqNo(rs.getInt(\"SEQ_NO\"));\n" +
                    "            ts.setProgramCd(rs.getString(\"PRG_CD\"));\n" +
                    "            ts.setTierTypeInd(rs.getString(\"TIER_TYPE_IND\"));\n" +
                    "            ts.setTierStatus(rs.getString(\"TIER_STATUS_IND\"));\n" +
                    "            ts.setPtsReq((long) rs.getLong(\"TIER_STATUS_PTS_REQ\"));\n" +
                    "            ts.setSectReq((float) rs.getFloat(\"TIER_STATUS_SECT_REQ\"));\n" +
                    "            ts.setMinPeriod((long) rs.getLong(\"TIER_STATUS_MIN_PRD\"));\n" +
                    "            ts.setHierarchy(rs.getInt(\"TIER_HIERARCHY\"));\n" +
                    "            ts.setPointOffset((long) rs.getLong(\"TIER_STATUS_PTS_OFFSET\"));\n" +
                    "            ts.setSectorOffset((float) rs.getFloat(\"TIER_STATUS_SECT_OFFSET\"));\n" +
                    "            ts.setQualProcID(rs.getString(\"QUAL_PROC_IND\"));\n" +
                    "            ts.setAccPtsReq((long) rs.getLong(\"TIER_STATUS_ACCUM_PTS_REQ\"));\n" +
                    "            ts.setAccSectReq((float) rs.getFloat(\"TIER_STATUS_ACCUM_SECT_REQ\"));\n" +
                    "            ts.setAccPtsOffset((long) rs.getLong(\"TIER_STATUS_ACCUM_PTS_OFFSET\"));\n" +
                    "            ts.setAcctSectOffset((float) rs.getFloat(\"TIER_STATUS_ACCUM_SECT_OFFSET\"));\n" +
                    "            ts.setActive(rs.getString(\"RULE_ACTIVE_IND\"));\n" +
                    "            ts.setApplQualInd(rs.getString(\"APPLICABLE_QLFY_IND\"));\n" +
                    "            ts.setTierQuadPrd(rs.getInt(\"TIER_QUAL_PRD\"));\n" +
                    "            ts.setValReq(rs.getLong(\"TIER_STATUS_VAL_REQ\"));\n" +
                    "            ts.setValOffset(rs.getLong(\"TIER_STATUS_VAL_OFFSET\"));\n" +
                    "            ts.setAccValReq(rs.getLong(\"TIER_STATUS_ACCUM_VAL_REQ\"));\n" +
                    "            ts.setAccValOffset(rs.getLong(\"TIER_STATUS_ACCUM_VAL_OFFSET\"));\n" +
                    "            ts.setRuleInd(rs.getString(\"RULE_IND\"));\n" +
                    "            ts.setStartDate(rs.getDate(\"START_DT\"));\n" +
                    "            ts.setEndDate(rs.getDate(\"END_DT\"));\n" +
                    "            ts.setYrsInTier(rs.getInt(\"YRS_IN_TIER\"));";
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
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<sqlColList.size(); i++) {
            String toAppend = String.format("<result column=\"%s\" jdbcType=\"%s\" property=\"%s\"/>\n", sqlColList.get(i),fieldTypeList.get(i),fieldNameList.get(i));
            buffer.append(toAppend);
        }
        System.out.println(buffer.toString());
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


