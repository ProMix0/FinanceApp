package com.example.financeapp.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

@Entity(tableName = "purchases")
public class Purchase implements Serializable {
    @PrimaryKey(autoGenerate = true)
    long id;
    Calendar date = Calendar.getInstance();
    int cost;

    public long getId() {
        return id;
    }

    @Override
    public Purchase clone() {
        Purchase clone = new Purchase();
        clone.setId(getId());
        clone.setCost(getCost());
        clone.setDate((Calendar) getDate().clone());
        return clone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
