package com.example.financeapp;

import java.util.ArrayList;
import java.util.Date;

public class PurchaseRecord {
    private int amount;
    private String category;
    private String date;

    public PurchaseRecord(int amount,String category, String date){
        this.amount=amount;
        this.category=category;
        this.date=date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
