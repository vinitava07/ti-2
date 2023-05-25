package com.powerchat.gpt.utils;

import java.sql.Timestamp;
import java.util.Calendar;

public class CalendarManager {

    static public Timestamp oneYearFromNow() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(Calendar.YEAR, 1);
        return new Timestamp(calendar.getTimeInMillis());
    }
}
