package com.example.financeapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Purchase.class, Category.class, PurchasesCategories.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class MyDatabase extends RoomDatabase {
    public abstract MyDAO getMyDao();
}