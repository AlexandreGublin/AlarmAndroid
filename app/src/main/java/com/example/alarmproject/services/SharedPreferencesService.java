package com.example.alarmproject.services;

import android.content.Context;

import com.example.alarmproject.model.Alarm;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesService {

    private static final String LOGTAG = SharedPreferencesService.class.getName();
    private static final String ID_PREF = "mathias";

    // Get all the list of alarms
    public static List<Alarm> getAlarms(Context context ){

        List<Alarm> alarms = new ArrayList<>();

        return alarms;

    }

    // Save all the list alarm
    public static void setAlarms(List<Alarm> alarms) {
    }

    // Add just 1 alarm to the list in shared preferences
    public static void setAlarm(Alarm alarm) {

    }
}
