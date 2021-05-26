package com.example.financeapp;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.financeapp.db.PurchaseRecord;

public class PurchaseView extends FrameLayout {

    TextView cost, date;
    CategoriesView categories;
    PurchaseRecord record;

    public PurchaseView(Context context) {
        super(context);

        inflate(context, R.layout.purchase_view, this);

        cost = findViewById(R.id.amount);
        date = findViewById(R.id.date);
        categories = findViewById(R.id.category);
    }

    public void setPurchase(PurchaseRecord record) {
        this.record = record;
        cost.setText(record.getCost() + "");
        date.setText(record.getDateAsString());
        categories.bindPurchase(record);
    }
}
