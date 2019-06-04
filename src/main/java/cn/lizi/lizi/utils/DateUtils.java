package cn.lizi.lizi.utils;

import org.joda.time.DateTime;

import java.util.Date;

public class DateUtils {

    public static String dateToString(Date date, String format) {
        if (date == null) {
            return "";
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(format);
    }

}
