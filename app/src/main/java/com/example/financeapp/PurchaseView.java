package com.example.financeapp;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.financeapp.db.Category;
import com.example.financeapp.db.PurchaseRecord;
import com.google.android.material.chip.ChipGroup;

public class PurchaseView extends FrameLayout {

    TextView cost, date;
    ChipGroup categories;
    PurchaseRecord record;

    public PurchaseView(Context context) {
        super(context);

        inflate(context, R.layout.purchase_view, this);

        cost = findViewById(R.id.amount);
        date = findViewById(R.id.date);
        categories = findViewById(R.id.chip_group);
    }

    public void bindPurchase(PurchaseRecord record) {
        this.record = record;
        cost.setText(record.getCost() + "");
        date.setText(record.getDateAsString());
        categories.removeAllViews();
        for (Category category : record.getCategories()) {
            MyChip temp = new MyChip(getContext(), false);
            temp.setCategory(category);
            categories.addView(temp);
        }
    }
}
