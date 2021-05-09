package com.example.financeapp;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static Model instance;

    public static Model getInstance() {
        if (instance == null) instance = new Model();
        return instance;
    }

    private Model() {
    }

    private List<PurchaseRecord> data = new ArrayList<>();

    public PurchaseRecord getItem(int index) {
        return data.get(index);
    }

    public void addItem(PurchaseRecord record) {
        data.add(record);
        adapter.notifyDataSetChanged();
    }

    public int size() {
        return data.size();
    }

    private PurchaseAdapter adapter;

    public void setDataAdapter(PurchaseAdapter adapter) {
        this.adapter = adapter;
    }
}
