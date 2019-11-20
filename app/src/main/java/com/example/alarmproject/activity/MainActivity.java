package com.example.alarmproject.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.alarmproject.R;
import com.example.alarmproject.adapter.RecyclerViewAdapterListAlarms;
import com.example.alarmproject.controller.AlarmController;
import com.example.alarmproject.model.Alarm;
import com.example.alarmproject.services.SharedPreferencesService;
import com.example.alarmproject.utils.RecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = MainActivity.class.getName();

    FloatingActionButton buttonPlus;
    final static int CODE_INTENT_ADD_ALARM = 007;
    List<Alarm> alarms;
    RecyclerViewAdapterListAlarms adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlus = findViewById(R.id.btn_add_alarm);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateAlarmActivity.class);
                startActivityForResult(intent, CODE_INTENT_ADD_ALARM);
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
//                alarms.get(position).getId()
            }

            @Override
            public void onItemLongClick(View view, int position) {
//                alarms.get(position).getId()
            }
        }));

        // Set adapter in RV
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
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
    }
}
