package com.example.financeapp;

import android.os.AsyncTask;

import com.example.financeapp.db.Category;
import com.example.financeapp.db.MyDAO;
import com.example.financeapp.db.MyDatabase;
import com.example.financeapp.db.PurchaseRecord;
import com.example.financeapp.db.PurchasesCategories;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Model {

    // Паттерн Singleton
    private static Model instance;

    public static Model getInstance() {
        if (instance == null) instance = new Model();
        return instance;
    }

    private MyDAO dao;
    private ItemInserter inserter;

    private Executor executor = Executors.newSingleThreadExecutor();

    private Model() {
        inserter = new ItemInserter();
    }

    public void setDb(MyDatabase db) {
        this.dao = db.getMyDao();
        executor.execute(() -> data = dao.getAllPurchases());
    }

    private List<PurchaseRecord> data;

    public PurchaseRecord getItem(int index) {
        return data.get(index);
    }

    public void addItem(PurchaseRecord record) {
        inserter.setFields(adapter, dao, data, record);
        inserter.execute();
    }

    private static class ItemInserter extends AsyncTask<Void, Void, Void> {

        PurchaseAdapter adapter;
        private MyDAO dao;
        private List<PurchaseRecord> data;
        private PurchaseRecord record;

        public void setFields(PurchaseAdapter adapter, MyDAO dao, List<PurchaseRecord> data, PurchaseRecord record) {
            this.adapter = adapter;
            this.dao = dao;
            this.data = data;
            this.record = record;
        }

        @Override
        protected Void doInBackground(Void... aVoid) {
            record.getPurchase().setId(dao.insert(record.getPurchase()));
            List<Category> list = record.getCategories();
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setId(dao.insert(list.get(i)));
                dao.insert(new PurchasesCategories(record.getPurchase().getId(), list.get(i).getId()));
            }
            data.add(record);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.notifyDataSetChanged();
        }
    }

    public int size() {
        return data.size();
    }


    private PurchaseAdapter adapter;

    public void setDataAdapter(PurchaseAdapter adapter) {
        this.adapter = adapter;
        adapter.setData(data);
    }
}
