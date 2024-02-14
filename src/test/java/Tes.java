//public class Tes {
//
//    List<String> fieldsWithError = new ArrayList<>();
//
//    @Test
//    public void getFields() {
//        Field[] fields = CustomerSelectiveRequest.class.getDeclaredFields();
//        String key = CommonConstants.UPDATECUSTOMER;
//        try {
//            iterateField(fields, key);
//            System.out.println(fieldsWithError.size());
//            System.out.println("Completed");
//        } catch (Exception e) {
//            throw new RuntimeException("error");
//        }
//    }
//
//    private void iterateField(Field[] fields, String key) throws ClassNotFoundException {
//        Field temp = null;
//
//        for (Field field : fields) {
//            try {
//                temp = field;
//                field.setAccessible(true);
//                String type = field.getType().getTypeName();
//                String fieldName = null;
//
//                if (type.contains("[]") && !type.contains("java.")) {
//                    String className = extractClassName(type);
//                    System.out.println(className);
//                    iterateField(Class.forName(className).getDeclaredFields(), key);
//                } else if (type.contains("List")) {
//                    System.out.println("list found");
//                    String annotated = field.getAnnotatedType().toString();
//                    iterateField(Class.forName(extractInnerTypeForList(annotated)).getDeclaredFields(), key);
//                } else {
//                    fieldName = field.getName();
//                    String fieldType = extractTypeForField(field);
//                    System.out.println(String.format("%s.%s.type=${%s}", key, fieldName, fieldType));
//                }
//            } catch (Exception e) {
//                System.out.println("error");
//                throw new RuntimeException("Error");
//            }
//        }
//    }
//
//    private String extractInnerTypeForList(String type) {
//        int start = type.indexOf("<") + 1;
//        int end = type.indexOf(">");
//        return type.substring(start, end);
//    }
//
//    private String extractTypeForField(Field field) {
//        if (field.getType().getTypeName().contains("String")) {
//            return "STRING";
//        } else if (field.getType().getTypeName().toLowerCase().contains("boolean")) {
//            return "BOOLEAN";
//        } else if (field.getType().getTypeName().toLowerCase().contains("long")) {
//            return "DECIMAL";
//        } else if (field.getType().getTypeName().contains("int") || field.getType().getTypeName().contains("Integer")) {
//            return "DECIMAL";
//        } else if (field.getType().getTypeName().contains("Date")) {
//            return "DATE";
//        } else if (field.getType().getTypeName().toLowerCase().contains("short")) {
//            return "SHORT";
//        } else {
//            fieldsWithError.add(field.getName() + "--" + field.getType().getTypeName());
//            return null;
//        }
//    }
//
//    private String extractClassName(String type) {
//        int start = type.indexOf("[");
//        return type.substring(0, start);
//    }
//}
