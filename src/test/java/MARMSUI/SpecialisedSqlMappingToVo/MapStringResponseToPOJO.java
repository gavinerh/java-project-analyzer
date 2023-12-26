package MARMSUI.SpecialisedSqlMappingToVo;

import MARMSUI.SpecialisedSqlMappingToVo.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.List;

public class MapStringResponseToPOJO {
    public static void main(String[] args) {
        String[] types = {"Tierstat","TierMileageSummary","HisCusEliteQual","CusPpsQual","CustomerTier","TierQual","Long","AccountStatusFunc","GeneralSqlObject","QualDetailsMonthly","CusClubQual","TransReserveVal"};
        generateMethod(types);
    }

    private static void generateMethod(String[] types) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("private static Object mtd(String response, String returnType) throws JsonProcessingException {\n" +
                "        ObjectMapper mapper = new ObjectMapper();\n" +
                "        mapper.setDateFormat(new SimpleDateFormat(\"dd-MM-yyyy\"));\n");
        for(int i=0; i<types.length; i++) {
            if(i==0) {
                stringBuilder.append(String.format("if(returnType.contains(\"%s\"))\n", types[i]));
            } else {
                stringBuilder.append(String.format("else if (returnType.contains(\"%s\"))\n", types[i]));
            }
            stringBuilder.append(String.format("return mapper.readValue(response, new TypeReference<List<%s>>(){});\n", types[i]));
        }
        stringBuilder.append("return null;\n}\n");
        System.out.println(stringBuilder.toString());
    }


    private static Object mtd(String response, String returnType) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        if(returnType.contains("Tierstat"))
            return mapper.readValue(response, new TypeReference<List<Tierstat>>(){});
        else if (returnType.contains("TierMileageSummary"))
            return mapper.readValue(response, new TypeReference<List<TierMileageSummary>>(){});
        else if (returnType.contains("HisCusEliteQual"))
            return mapper.readValue(response, new TypeReference<List<HisCusEliteQual>>(){});
        else if (returnType.contains("CusPpsQual"))
            return mapper.readValue(response, new TypeReference<List<CusPpsQual>>(){});
        else if (returnType.contains("CustomerTier"))
            return mapper.readValue(response, new TypeReference<List<CustomerTier>>(){});
        else if (returnType.contains("TierQual"))
            return mapper.readValue(response, new TypeReference<List<TierQual>>(){});
        else if (returnType.contains("Long"))
            return mapper.readValue(response, new TypeReference<List<Long>>(){});
        else if (returnType.contains("AccountStatusFunc"))
            return mapper.readValue(response, new TypeReference<List<AccountStatusFunc>>(){});
        else if (returnType.contains("GeneralSqlObject"))
            return mapper.readValue(response, new TypeReference<List<GeneralSqlObject>>(){});
        else if (returnType.contains("QualDetailsMonthly"))
            return mapper.readValue(response, new TypeReference<List<QualDetailsMonthly>>(){});
        else if (returnType.contains("CusClubQual"))
            return mapper.readValue(response, new TypeReference<List<CusClubQual>>(){});
        else if (returnType.contains("TransReserveVal"))
            return mapper.readValue(response, new TypeReference<List<TransReserveVal>>(){});
        return null;
    }


}
