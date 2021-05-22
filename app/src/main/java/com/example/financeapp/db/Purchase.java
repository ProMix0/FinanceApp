package com.example.financeapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = "purchases")
public class Purchase {
    @PrimaryKey(autoGenerate = true)
    long id;
    Calendar date;
    int cost;
}
