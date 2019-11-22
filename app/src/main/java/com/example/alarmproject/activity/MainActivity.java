package com.example.alarmproject.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.example.alarmproject.R;
import com.example.alarmproject.adapter.RecyclerViewAdapterListAlarms;
import com.example.alarmproject.controller.AlarmController;
import com.example.alarmproject.model.Alarm;
import com.example.alarmproject.services.SharedPreferencesService;
import com.example.alarmproject.utils.RecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = MainActivity.class.getName();

    FloatingActionButton buttonPlus;
    final static int CODE_INTENT_ADD_ALARM = 007;
    final static int CODE_INTENT_EDIT_ALARM = 214;

    List<Alarm> alarms = new ArrayList<>();
    RecyclerViewAdapterListAlarms adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlus = findViewById(R.id.btn_add_alarm);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), CreateAlarmActivity.class), CODE_INTENT_ADD_ALARM);
            }
        });

        // Get the list of Alarm
        alarms = SharedPreferencesService.getAlarms(this);

        // Init adapter with list Alarms and the layout
        adapter = new RecyclerViewAdapterListAlarms(alarms, this.getLayoutInflater());

        // Listen if user want to see actions of the ticket
        RecyclerView recyclerView = findViewById(R.id.rv_alarm);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (view instanceof AppCompatImageButton) {
                    Alarm alarm = alarms.get(position);
                    if(view.getId() == R.id.img_btn_trash) deleteAlarm(alarm);
                    if(view.getId() == R.id.img_btn_update) startActivityForResult(new Intent(getApplicationContext(), EditAlarmActivity.class).putExtra(EditAlarmActivity.EDIT_ALARM_CODE, alarm), CODE_INTENT_EDIT_ALARM);

                } else if(view instanceof Switch) {
                    Alarm alarm = alarms.get(position);
                    desactiveAlarm(alarm);
                }

            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        }));

        // Set adapter in RV
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    private void deleteAlarm(Alarm alarm){
        if(alarm.getActive()) AlarmController.desactiveAlarm(this, alarm);// Desactive alarm

        // Delete alarm from list
        alarms.remove(alarm);

        // Update sharedPreferences
        SharedPreferencesService.setAlarms(this, alarms);

        // Update view
        adapter.notifyDataSetChanged();
    }

    public void desactiveAlarm(Alarm alarm){
        if(alarm.getActive()){
            alarm.setActive(false);

            // Desactive alarm
            AlarmController.desactiveAlarm(this, alarm);

            // Update sharedPreferences
            SharedPreferencesService.setAlarms(this, alarms);

            // No need to update view, the switch has changer when user had click on it
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // If receive result of intent add alarm and it is success
        if(requestCode == CODE_INTENT_ADD_ALARM && resultCode == Activity.RESULT_OK){

            if (data != null) {
                try {
                    // Retrieve and save the alarm
                    Alarm alarm = (Alarm) data.getSerializableExtra(CreateAlarmActivity.ADD_ALARM_CODE);
                    alarms.add(alarm);
                    SharedPreferencesService.setAlarms(this, alarms);
                    AlarmController.activeAlarm(this, alarm);

                    // Update recycler view
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    Log.e(LOGTAG + " | onActivityResult()", e.toString());
                }
            }
        }

        if(requestCode == CODE_INTENT_EDIT_ALARM && resultCode == Activity.RESULT_OK){

            if (data != null) {
                try {
                    // Retrieve and save the alarm
                    Alarm alarm = (Alarm) data.getSerializableExtra(EditAlarmActivity.EDIT_ALARM_CODE);
                    alarms.add(alarms.indexOf(alarm), alarm);

                    if (alarm.getActive()) {
                        AlarmController.activeAlarm(this, alarm);
                    }
                    SharedPreferencesService.setAlarms(this, alarms);

                    // Update recycler view
                    adapter.notifyDataSetChanged();
                }catch (Exception e){
                    Log.e(LOGTAG + " | onActivityResult()", e.toString());
                }
            }
        }
    }



}
