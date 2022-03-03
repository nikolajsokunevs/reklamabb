package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean lessThan5Seconds(long startTime) {
        return (System.currentTimeMillis() - startTime) < 5000;
    }

    public static LocalDate stringToDate(String dateAsString, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateAsString, formatter);
    }

    public static LocalDateTime stringToDateTime(String dateAsString, String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateAsString, formatter);
    }

    public static Integer extractOnlyNumbers(String string){
        String result="";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(string);
        while(matcher.find()) {
            result=result+matcher.group();
        }
        return Integer.valueOf(result);
    }

}
