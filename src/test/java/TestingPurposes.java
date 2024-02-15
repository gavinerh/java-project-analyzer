import MARMSUI.SpecialisedSqlMappingToVo.CustomDateFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;

public class TestingPurposes {


    public static void main(String[] args) throws ParseException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new CustomDateFormat());
        String obj1 = "{\"name\":\"obj1\",\"date\":\"31-12-2099\"}";
        String obj2 = "{\"date\":\"31-12-2099\",\"name\":\"obj1\"}";
        int val = obj1.hashCode();
        int val2 = obj2.hashCode();
        System.out.println(val);
        Mod mod1 = objectMapper.readValue(obj1, new TypeReference<Mod>() {
        });
        Mod mod2 = objectMapper.readValue(obj2, new TypeReference<Mod>() {
        });
        System.out.println(mod2);
    }


}