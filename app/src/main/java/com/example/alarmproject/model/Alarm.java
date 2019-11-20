package com.example.alarmproject.model;

import com.example.alarmproject.utils.Day;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Alarm implements Serializable {

    private Integer id;
    private String name;
    private Boolean isActive;
    private Calendar hour;
    private List<Day> days;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Calendar getHour() {
        return hour;
    }

    public void setHour(Calendar hour) {
        this.hour = hour;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }
}


