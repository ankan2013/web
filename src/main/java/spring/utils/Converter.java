package spring.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Converter {

    public static Date strToDate(String original){
        Date date=Date.valueOf(original);
        return date;
    }

    public static String dateToStr(Date original){
        String str = original.toString();
        return str;
    }
}