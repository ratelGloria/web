package com.two.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtils {

    private static final String STANDARD_FORMAT="yyyy-MM-dd HH:mm:ss";


    public static String dateToStr(Date date,String format){

        DateTime dateTime = new DateTime();

        return dateTime.toString(format);

    }
    public static String dateToStr(Date date){

        DateTime dateTime = new DateTime();

        return dateTime.toString(STANDARD_FORMAT);
    }


    public static Date strToDate(String dateStr){

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateStr);

        return dateTime.toDate();
    }

}
