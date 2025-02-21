public class CreateMapStructInterfaceFromGetSet {
    public static void main(String[] args) {
        String getSetMethods = "customerVo.setAgentId(request.getAgentId());\n" +
                "customerVo.setCustomerId(request.getCustomerId());\n" +
                "customerVo.setReligiousMealPreferredDesc(request.getReligiousMealPreferredCode());\n" +
                "customerVo.setPrincipalTierCode(request.getTierCode());\n" +
                "customerVo.setYec(request.isYec());\n" +
                "customerVo.setAcvValue(request.getAcvValue());\n" +
                "customerVo.setAcvOverrideFlg(request.getAcvOverrideFlg());\n" +
                "customerVo.setProgramCode(request.getProgramCode());\n" +
                "customerVo.setInternalId(request.getInternalId());\n" +
                "customerVo.setSpokenLanguage(request.getSpokenLanguage());\n" +
                "customerVo.setWrittenLanguage(request.getWrittenLanguage());";

        String mappingTemplate = "@Mapping(target=\"%s\", source=\"%s\")";
        String[] arr = getSetMethods.split("\n");
        for (String s : arr) {
            String targetField = extractField(s,"set");
            String sourceField = extractField(s,"get");
            System.out.println(String.format(mappingTemplate,targetField,sourceField));
        }
    }

    private static String extractField(String row, String identifier) {
        int start = row.indexOf(identifier) + identifier.length();
        int end = row.indexOf("(",start);
        return extractFieldName(row.substring(start,end));
    }

    private static String extractFieldName(String field) {
        return field.substring(0,1).toLowerCase() + field.substring(1);
    }
}
