package com.example.dishdiscovery.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDiff {
    public static boolean isDifferenceGreaterThan7Days(String dateString1, String dateString2) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        try {
            Date date1 = sdf.parse(dateString1);
            Date date2 = sdf.parse(dateString2);

            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(date1);
            cal2.setTime(date2);

            long differenceInMillis = cal2.getTimeInMillis() - cal1.getTimeInMillis();
            long differenceInDays = differenceInMillis / (1000 * 60 * 60 * 24);

            return differenceInDays > 7;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
