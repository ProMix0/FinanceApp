package com.example.financeapp;

import com.example.financeapp.db.PurchaseRecord;

public interface ICallback {
    void callback(PurchaseRecord record);
}
