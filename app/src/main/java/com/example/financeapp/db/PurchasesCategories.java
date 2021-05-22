package com.example.financeapp.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "purchases_categories", foreignKeys = {
        @ForeignKey(entity = Purchase.class, childColumns = "purchaseId", parentColumns = "id",
                onDelete = CASCADE),
        @ForeignKey(entity = Category.class, childColumns = "categoryId", parentColumns = "id",
                onDelete = CASCADE)},
        primaryKeys = {"purchaseId", "categoryId"})
public class PurchasesCategories {
    @ColumnInfo(index = true)
    long purchaseId;
    @ColumnInfo(index = true)
    long categoryId;
}
