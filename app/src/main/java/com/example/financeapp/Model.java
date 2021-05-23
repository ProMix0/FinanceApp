package com.example.financeapp;

import com.example.financeapp.db.Category;
import com.example.financeapp.db.MyDAO;
import com.example.financeapp.db.MyDatabase;
import com.example.financeapp.db.PurchaseRecord;
import com.example.financeapp.db.PurchasesCategories;

import java.util.ArrayList;
import java.util.List;

public class Model {

    // Паттерн Singleton
    private static Model instance;

    public static Model getInstance() {
        if (instance == null) instance = new Model();
        return instance;
    }

    private MyDAO dao;

    private Model() {
    }

    public void setDb(MyDatabase db) {
        this.dao = db.getMyDao();
    }

    private List<PurchaseRecord> data = new ArrayList<>();

    public PurchaseRecord getItem(int index) {
        return data.get(index);
    }

    public void addItem(PurchaseRecord record) {
        dao.insert(record.getPurchase());
        List<Category> list = record.getCategories();
        for (int i = 0; i < list.size(); i++) {
            dao.insert(list.get(i));
            dao.insert(new PurchasesCategories(
                    record.getPurchase().getId(),
                    list.get(i).getId()));
        }
    }

    public int size() {
        return data.size();
    }


    private PurchaseAdapter adapter;

    public void setDataAdapter(PurchaseAdapter adapter) {
        this.adapter = adapter;
    }
}
