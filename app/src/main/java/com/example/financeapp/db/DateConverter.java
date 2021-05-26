package com.example.financeapp.db;

import androidx.room.TypeConverter;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateConverter {

    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);

    @TypeConverter
    public static Calendar stringToDate(String string) {
        Calendar temp = Calendar.getInstance();
        temp.setTime(formatter.parse(string, new ParsePosition(0)));
        return temp;
    }

    @TypeConverter
    public static String dateToString(Calendar calendar) {
        return formatter.format(calendar.getTime());
    }
}
