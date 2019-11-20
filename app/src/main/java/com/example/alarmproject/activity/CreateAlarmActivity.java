package com.example.alarmproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.alarmproject.R;
import com.example.alarmproject.model.Alarm;
import com.example.alarmproject.services.SharedPreferencesService;
import com.example.alarmproject.utils.Util;

public class CreateAlarmActivity extends AppCompatActivity {

    private static final String LOGTAG = CreateAlarmActivity.class.getName();
    public static final String ADD_ALARM_CODE = "com.example.alarmproject.activity.ADD_ALARM";

    TimePicker timePicker;
    EditText nameAlarm;
    Button btnSaveAlarm;
    Button btnCancelAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);

        // Instance and start a listener on submit
        btnSaveAlarm = findViewById(R.id.submit_add_alarm);
        btnSaveAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get all inputs
                timePicker = findViewById(R.id.time_picker_add_alarm);
                nameAlarm = findViewById(R.id.edit_text_name_add_alarm);

                // Get data inputs
                // TODO Check what we get from input
                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String test = nameAlarm.getText().toString();

                // Check if all is ok
//                if()

                // Put data inputs in new object Alarm
                Alarm alarm = new Alarm();
                alarm.setActive(true);
                alarm.setName(nameAlarm.getText().toString());
                alarm.setHour(Util.convertStringToDate(hour + ":" + minute));

                notifyAddAlarmFinished(true, alarm);
            }
        });

        // Instance and start a listener on cancel
        btnCancelAlarm = findViewById(R.id.cancel_add_alarm);
        btnCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kill view
                notifyAddAlarmFinished(false, null);
            }
        });
    }

    private void notifyAddAlarmFinished(Boolean isSuccess, Alarm alarm){
        Intent resultIntent = new Intent();
        if(isSuccess){
            resultIntent.putExtra(ADD_ALARM_CODE, alarm);
            setResult(Activity.RESULT_OK, resultIntent);
        }else {
            setResult(Activity.RESULT_CANCELED, resultIntent);
        }
        finish();
    }
}
