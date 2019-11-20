package com.example.alarmproject.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.alarmproject.model.Alarm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesService {

    private static final String LOGTAG = SharedPreferencesService.class.getName();
    private static final String ID_PREF = "mathias";
    private static final String ALARM_TAG = "alarm_tag";




    // Get all the list of alarms
    public static List<Alarm> getAlarms(Context context ){
        List<Alarm> alarms = new ArrayList<>();

        try {
            SharedPreferences mPrefs = context.getSharedPreferences(ID_PREF, context.MODE_PRIVATE);
            String json = mPrefs.getString(ALARM_TAG, null);


            if(json != null){
                Gson gson = new Gson();
                alarms = gson.fromJson(json, new TypeToken<ArrayList<Alarm>>(){}.getType());
            }


        }catch (Exception e){
            Log.e(LOGTAG + " getAlarms()", e.toString());
        }

        return alarms;

    }

    // Save all the list alarm
    public static void setAlarms(Context context, List<Alarm> alarms) {
        try {
            SharedPreferences mPrefs = context.getSharedPreferences(ID_PREF, context.MODE_PRIVATE);
            SharedPreferences.Editor prefsEditor = mPrefs.edit();

            Gson gson = new Gson();
            prefsEditor.putString(ALARM_TAG, gson.toJson(alarms));
            prefsEditor.apply();

        }catch (Exception e){
            Log.e(LOGTAG + " setAlarms()", e.toString());
        }
    }

}
