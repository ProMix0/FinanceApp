package com.example.financeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.financeapp.db.PurchaseRecord;

public class PurchaseFragment extends Fragment {

    TextView cost, date;
    CategoriesFlexbox categories;
    PurchaseRecord record;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.purchase_fragment, container, false);

        cost = view.findViewById(R.id.amount);
        date = view.findViewById(R.id.date);
        categories = (CategoriesFlexbox) getFragmentManager().findFragmentById(R.id.category);

        return view;
    }

    public void setPurchase(PurchaseRecord record) {
        this.record = record;
        cost.setText(record.getCost() + "");
        date.setText(record.getDateAsString());
        categories.clear();
        categories.addCategories(record.getCategories());
    }
}
