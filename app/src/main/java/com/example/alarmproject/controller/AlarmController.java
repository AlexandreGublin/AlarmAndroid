package com.example.alarmproject.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.alarmproject.model.Alarm;
import com.example.alarmproject.utils.AlarmReceiver;
import com.example.alarmproject.utils.Util;

import java.util.Calendar;

public class AlarmController {

    private final static String LOGTAG = AlarmController.class.getName();

    public static final String TAG_ID_ALARM = "ID_ALARM";


    public static void activeAlarm(Context context, Alarm alarm){

        try {
            Intent intent = new Intent(context, AlarmReceiver.class);
            intent.putExtra(TAG_ID_ALARM, alarm.getId());
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarm.getId(), intent, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(Util.correctDate(alarm.getHour()).getTimeInMillis(), pendingIntent), pendingIntent);

        } catch (Exception e) {
            Log.d(LOGTAG + " activeAlarm()", e.toString());
        }
    }

    public static void desactiveAlarm(Context context, Alarm alarm){
        try {
            Intent intent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarm.getId(), intent, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }catch (Exception e){
            Log.d(LOGTAG + " activeAlarm()", e.toString());
        }
    }
}
