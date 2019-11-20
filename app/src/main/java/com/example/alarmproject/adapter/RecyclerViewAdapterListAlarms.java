package com.example.alarmproject.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alarmproject.R;
import com.example.alarmproject.model.Alarm;
import com.example.alarmproject.utils.Util;
import com.example.alarmproject.view_holder.RecyclerViewHolderListAlarms;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapterListAlarms extends RecyclerView.Adapter<RecyclerViewHolderListAlarms>{
    private static final String LOGTAG = RecyclerViewAdapterListAlarms.class.getName();
    private List<Alarm> alarms;
    private LayoutInflater inflate;

    public RecyclerViewAdapterListAlarms(List<Alarm> strings, LayoutInflater layoutInflater){
        super();
        alarms = strings;
        inflate = layoutInflater;
    }

    @Override
    public RecyclerViewHolderListAlarms onCreateViewHolder(ViewGroup parent, int viewType) {


        View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_alarm, parent, false);
        return new RecyclerViewHolderListAlarms (cellView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderListAlarms holder, int position) {
        try {
            // Instance alarm
            Alarm alarm = alarms.get(position);

            // Set all data of actual item of the listView
            holder.setSwitchIsActive(alarm.getActive());
            holder.setTextViewTime(Util.convertDateToString(alarm.getHour()));
            holder.setTextViewDescrAlarm(alarm.getName()); // TODO Add days( split ??)

        }catch (Exception e){
            Log.e(LOGTAG + "| onBindViewHolder()", e.toString());
        }

    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }
}