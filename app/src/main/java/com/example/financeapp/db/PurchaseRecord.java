package com.example.financeapp.db;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class PurchaseRecord {
    @Embedded
    Purchase purchase;
    @Relation(
            parentColumn = "id",
            entity = Category.class,
            entityColumn = "id",
            associateBy = @Junction(
                    value = PurchasesCategories.class,
                    parentColumn = "purchaseId",
                    entityColumn = "categoryId"),
            projection = {"name"}
    )
    List<String> categories;
}
