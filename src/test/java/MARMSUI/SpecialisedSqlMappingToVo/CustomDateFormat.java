package MARMSUI.SpecialisedSqlMappingToVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CustomDateFormat extends SimpleDateFormat {

    public CustomDateFormat() {
        // blank
    }


    @Override
    public Date parse(String text) {
        if (text == null) {
            return null;
        }
        String datePattern1 = "[\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2}";
        String datePattern2 = "[\\d]{4}-[\\d]{2}-[\\d]{2}";
        String datePattern3 = "[\\d]{2}-[\\d]{2}-[\\d]{4}";
        String datePattern4 = "[\\d]{2}-[\\d]{2}-[\\d]{4} [\\d]{2}:[\\d]{2}:[\\d]{2}";
        Pattern pattern1 = Pattern.compile(datePattern1);
        Pattern pattern2 = Pattern.compile(datePattern2);
        Pattern pattern3 = Pattern.compile(datePattern3);
        Pattern pattern4 = Pattern.compile(datePattern4);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format3 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat format4 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date parsedDate = null;
        try {
            if (pattern1.matcher(text).matches()) {
                parsedDate = format1.parse(text);
            } else if (pattern2.matcher(text).matches()) {
                parsedDate = format2.parse(text);
            } else if (pattern3.matcher(text).matches()) {
                parsedDate = format3.parse(text);
            } else if (pattern4.matcher(text).matches()) {
                parsedDate = format4.parse(text);
            }
        } catch (ParseException e) {
            throw new RuntimeException("Cannot parse date");
        }
        return parsedDate;
    }
}
