package com.example.alarmproject.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

    private static final String LOGTAG = Util.class.getName();

    public static String convertDateToString(Calendar calendar){

        String strDate = "";

        try {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            strDate = dateFormat.format(calendar.getTime());
        } catch (Exception e) {
            Log.e(LOGTAG + " | convertDate", e.toString());
        }

        return strDate;
    }

    public static Calendar convertStringToDate(String time){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            Log.e(LOGTAG + " | convertStringToDate", e.toString());
        }
        cal.setTime(date);

        return cal;
    }

    public static Calendar correctDate(Calendar dateAlarm){

        Calendar calendar = Calendar.getInstance();


        dateAlarm.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        dateAlarm.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        dateAlarm.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));

        // Check if calendar day < actual day
//        if(dateAlarm.get(Calendar.DAY_OF_WEEK) < calendar.get(Calendar.DAY_OF_WEEK))
//            dateAlarm.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK));

        // Check if calendar hour < actual hour
        if(dateAlarm.get(Calendar.HOUR_OF_DAY) < calendar.get(Calendar.HOUR_OF_DAY))
            dateAlarm.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);

        return dateAlarm;
    }
}