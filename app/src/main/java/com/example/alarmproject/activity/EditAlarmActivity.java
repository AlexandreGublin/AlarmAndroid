package com.example.alarmproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import com.example.alarmproject.R;
import com.example.alarmproject.model.Alarm;
import com.example.alarmproject.utils.Util;

import androidx.appcompat.app.AppCompatActivity;

public class EditAlarmActivity extends AppCompatActivity {

    private static final String LOGTAG = EditAlarmActivity.class.getName();
    public static final String EDIT_ALARM_CODE = "com.example.alarmproject.activity.EDIT_ALARM";

    TimePicker timePicker;
    EditText nameAlarm;
    Button btnSaveAlarm;
    Button btnCancelAlarm;
    Switch switchIsActive;
    Alarm alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alarm);

        try{
            alarm = (Alarm) getIntent().getSerializableExtra(EDIT_ALARM_CODE);

            if(alarm.getName() != null){
                nameAlarm = findViewById(R.id.edit_text_name_edit_alarm);
                nameAlarm.setText(alarm.getName());
            }

            String date = Util.convertDateToString(alarm.getHour());
            String[] parts = date.split(":");
            timePicker = findViewById(R.id.time_picker_edit_alarm);
            timePicker.setHour(Integer.valueOf(parts[0]));
            timePicker.setMinute(Integer.valueOf(parts[1]));

            switchIsActive = findViewById(R.id.switch_edit_is_active);
            switchIsActive.setChecked(alarm.getActive());

        }catch (Exception e){
            Log.i(LOGTAG + " | setDateInInputs", e.toString());
        }


        // Instance and start a listener on submit
        btnSaveAlarm = findViewById(R.id.submit_add_alarm);
        btnSaveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get all inputs
                timePicker = findViewById(R.id.time_picker_edit_alarm);
                nameAlarm = findViewById(R.id.edit_text_name_edit_alarm);
                switchIsActive = findViewById(R.id.switch_edit_is_active);

                // Get data inputs
                // TODO Check what we get from input
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();

                // Put data inputs in new object Alarm

                alarm.setActive(switchIsActive.isChecked());
                alarm.setName(nameAlarm.getText().toString());
                alarm.setHour(Util.convertStringToDate(hour + ":" + minute));
                notifyUpdateAlarmFinished(true, alarm);
            }
        });

        // Instance and start a listener on cancel
        btnCancelAlarm = findViewById(R.id.cancel_add_alarm);
        btnCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kill view
                notifyUpdateAlarmFinished(false, null);
            }
        });

    }

    private void notifyUpdateAlarmFinished(Boolean isSuccess, Alarm alarm){
        Intent resultIntent = new Intent();
        if(isSuccess){
            resultIntent.putExtra(EDIT_ALARM_CODE, alarm);
            setResult(Activity.RESULT_OK, resultIntent);
        }else {
            setResult(Activity.RESULT_CANCELED, resultIntent);
        }
        finish();
    }
}
