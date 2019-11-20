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
            Log.i("hello", strDate);
        } catch (Exception e) {
            Log.e(LOGTAG + "convertDate", e.toString());
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
            e.printStackTrace();
        }
        cal.setTime(date);

        return cal;
    }
}