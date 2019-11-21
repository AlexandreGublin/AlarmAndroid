package com.example.alarmproject.view_holder;

import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alarmproject.R;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolderListAlarms extends RecyclerView.ViewHolder{
    public RecyclerViewHolderListAlarms (View itemView) {
        super(itemView);
    }

    // Time cell
    private TextView getTextViewTime() { return itemView.findViewById(R.id.text_time); }
    public void setTextViewTime(String time) { getTextViewTime().setText(time); }

    // Descr cell
    private TextView getTextViewDescrAlarm() { return itemView.findViewById(R.id.desc_alarm); }
    public void setTextViewDescrAlarm(String descr) { getTextViewDescrAlarm().setText(descr); }

    // Active cell
    private Switch getSwitchIsActive(){ return itemView.findViewById(R.id.switch_is_active); }
    public void setSwitchIsActive(Boolean isActive) {
        if(isActive){
            getSwitchIsActive().setChecked(true);
        }else{
            getSwitchIsActive().setChecked(false);
        }
    }
}
