package MARMSUI.SpecialisedSqlMappingToVo;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateFormat extends SimpleDateFormat {

    public CustomDateFormat(){
        // blank
    }


    @Override
    public Date parse(String text) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        Date parsedDate = null;
        try {
            parsedDate = format1.parse(text);
        } catch (ParseException e) {
            try {
                parsedDate = format2.parse(text);
            }catch (ParseException e2) {
                throw new RuntimeException("Cannot parse date");
            }
        }
        return parsedDate;
    }
}
