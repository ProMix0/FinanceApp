package com.example.financeapp;

import android.os.AsyncTask;

import com.example.financeapp.db.Category;
import com.example.financeapp.db.MyDAO;
import com.example.financeapp.db.MyDatabase;
import com.example.financeapp.db.PurchaseRecord;
import com.example.financeapp.db.PurchasesCategories;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModel {

    // Паттерн Singleton
    private static ViewModel instance;

    public static ViewModel getInstance() {
        if (instance == null) instance = new ViewModel();
        return instance;
    }

    private MyDAO dao;

    private Executor executor = Executors.newSingleThreadExecutor();

    private ViewModel() {
    }

    public void setDb(MyDatabase db) {
        this.dao = db.getMyDao();
        executor.execute(() ->
        {
            data = dao.getAllPurchases();
            Collections.sort(data, (o1, o2) -> o2.getDateAsString().compareTo(o1.getDateAsString()));
        });
    }

    public PurchaseRecord getEmptyRecord() {
        return new PurchaseRecord();
    }

    private List<PurchaseRecord> data;

    public PurchaseRecord getItem(int index) {
        return data.get(index);
    }

    public void addItem(PurchaseRecord record) {
        new ItemInserter(adapter, dao, data, record).execute();
    }


    private static class ItemInserter extends AsyncTask<Void, Void, Void> {

        PurchaseAdapter adapter;
        private MyDAO dao;
        private List<PurchaseRecord> data;
        private PurchaseRecord record;

        public ItemInserter(PurchaseAdapter adapter, MyDAO dao, List<PurchaseRecord> data, PurchaseRecord record) {
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
            Collections.sort(data, (o1, o2) -> o2.getDateAsString().compareTo(o1.getDateAsString()));

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.notifyDataSetChanged();
        }
    }

    public void delete(PurchaseRecord parent, Category category) {

    }

    private static class ItemDeleter extends AsyncTask<Void, Void, Void> {

        private Category category;
        private MyDAO dao;
        private PurchaseRecord record;

        public ItemDeleter(MyDAO dao, PurchaseRecord record, Category category) {
            this.dao = dao;
            this.record = record;
            this.category = category;
        }

        @Override
        protected Void doInBackground(Void... aVoid) {
            dao.delete(new PurchasesCategories(record.getId(), category.getId()));

            return null;
        }
    }

    public int size() {
        return data.size();
    }

    public void saveData() {
        new ItemUpdater(dao, data).execute();
    }

    private static class ItemUpdater extends AsyncTask<Void, Void, Void> {

        private MyDAO dao;
        private List<PurchaseRecord> data;

        public ItemUpdater(MyDAO dao, List<PurchaseRecord> data) {
            this.dao = dao;
            this.data = data;
        }

        @Override
        protected Void doInBackground(Void... aVoid) {
            for (PurchaseRecord record : data) {
                dao.update(record.getPurchase());
                for (Category category : record.getCategories()) {
                    dao.update(category);
                }
            }

            return null;
        }
    }


    private PurchaseAdapter adapter;

    public void setDataAdapter(PurchaseAdapter adapter) {
        this.adapter = adapter;
        adapter.setData(data);
    }
}
