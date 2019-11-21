package com.example.alarmproject.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.alarmproject.model.Alarm;
import com.example.alarmproject.utils.AlarmReceiver;

import java.util.Calendar;

public class AlarmController {

    private final static String LOGTAG = AlarmController.class.getName();


    public static void activeAlarm(Context context, Alarm alarm){

        try {
            Intent intent = new Intent(context, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, alarm.getId(), intent, 0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            // TODO The hour give something like 58800000, so not the actual date
//            Calendar calendar = alarm.getHour();
//            calendar.set();
            Log.i("testTime", String.valueOf(alarm.getHour().getTimeInMillis()));
            

            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(alarm.getHour().getTimeInMillis(), pendingIntent), pendingIntent);

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
