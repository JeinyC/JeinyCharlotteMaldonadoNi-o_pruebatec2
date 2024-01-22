package com.mycompany.appointmentapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author jeiny
 */
public class DateUtil {

    private static final SimpleDateFormat OLD_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat NEW_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public DateUtil() {
    }

    public static String getFormattedDateFromCalendar(Calendar date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date.getTime());
    }

    public String getFormattedDateFromString(String date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(OLD_FORMAT.parse(date));
            return NEW_FORMAT.format(calendar.getTime());
        } catch (ParseException e) {
            return e.getMessage();
        }
    }
}
