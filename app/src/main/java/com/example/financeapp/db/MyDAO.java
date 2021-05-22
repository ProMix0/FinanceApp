package com.example.financeapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.Calendar;
import java.util.List;

@Dao
public interface MyDAO {

    @Insert
    void insert(Category category);

    @Insert
    void insert(Purchase purchase);

    @Insert
    void insert(PurchasesCategories purchasesCategories);

    @Delete
    void delete(Category category);

    @Delete
    void delete(Purchase purchase);

    @Delete
    void delete(PurchasesCategories purchasesCategories);

    @Update
    void update(Category category);

    @Update
    void update(Purchase purchase);

    @Update
    void update(PurchasesCategories purchasesCategories);

    @Transaction
    @Query("SELECT id,date,cost from purchases")
    List<com.example.financeapp.db.PurchaseRecord> getAllPurchases();

    @Transaction
    @Query("SELECT id,date,cost FROM purchases WHERE date LIKE :date")
    List<com.example.financeapp.db.PurchaseRecord> getWithDate(Calendar date);

}
