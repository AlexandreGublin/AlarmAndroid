package com.example.alarmproject.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.alarmproject.R;
import com.example.alarmproject.activity.AlarmActivity;
import com.example.alarmproject.activity.MainActivity;
import com.example.alarmproject.controller.AlarmController;
import com.example.alarmproject.model.Alarm;

public class AlarmReceiver extends BroadcastReceiver {
    private final static String LOGTAG = AlarmReceiver.class.getName();

    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("AlarmReceiver", "test");


        try {
            // Get idAlarm
            if(intent.getData() != null){
//                Alarm alarm = new Alarm();
//                alarm.setId(intent.getIntExtra(AlarmController.TAG_ID_ALARM, 0));

                // Get List<Alarm> from shared preferences

                // Find alarm by id in list

                // put isActive = false to alarm

                // Save list<Alarm> in sharedPreferences
            }

            // Show page
            Intent intent2 = new Intent(context, AlarmActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);

        }catch (Exception e){
            Log.e(LOGTAG + " onReceive()", e.toString());
        }
    }

}