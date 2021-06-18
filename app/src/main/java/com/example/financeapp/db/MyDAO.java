package com.example.financeapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.Calendar;
import java.util.List;

import static androidx.room.OnConflictStrategy.ABORT;
import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MyDAO {

    @Insert(onConflict = REPLACE)
    long insert(Category category);

    @Insert(onConflict = REPLACE)
    long insert(Purchase purchase);

    //@Insert(onConflict = IGNORE)
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
    @Query("SELECT id,date,cost FROM purchases ORDER BY date DESC")
    List<com.example.financeapp.db.PurchaseRecord> getAllPurchases();

    @Transaction
    @Query("SELECT id,date,cost FROM purchases WHERE date LIKE :date")
    List<com.example.financeapp.db.PurchaseRecord> getWithDate(Calendar date);

}
