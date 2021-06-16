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
            sortData();
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
            for (Category category : record.getCategories()) {
                category.setId(dao.insert(category));
                dao.insert(new PurchasesCategories(record.getPurchase().getId(), category.getId()));
            }
            data.add(record);
            ViewModel.getInstance().sortData();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.notifyDataSetChanged();
        }
    }

    public void sortData() {
        Collections.sort(data, (o1, o2) -> (int) (o2.getDate().getTimeInMillis() - o1.getDate().getTimeInMillis()));
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

    public void update(PurchaseRecord record) {
        new ItemUpdater(dao, record).execute();
    }

    private static class ItemUpdater extends AsyncTask<Void, Void, Void> {

        private MyDAO dao;
        private PurchaseRecord record;

        public ItemUpdater(MyDAO dao, PurchaseRecord record) {
            this.dao = dao;
            this.record = record;
        }

        @Override
        protected Void doInBackground(Void... aVoid) {
            dao.update(record.getPurchase());
            for (Category category : record.getCategories()) {
                category.setId(dao.insert(category));
                dao.insert(new PurchasesCategories(record.getPurchase().getId(), category.getId()));
            }
            for (Category category : record.getCategoriesToDelete()) {
                PurchasesCategories toDelete = new PurchasesCategories(record.getPurchase().getId(), category.getId());
                dao.delete(toDelete);
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
