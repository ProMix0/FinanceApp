package com.example.financeapp.db;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Junction;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PurchaseRecord implements Serializable, Cloneable {
    @Embedded
    Purchase purchase = new Purchase();
    @Relation(
            parentColumn = "id",
            entity = Category.class,
            entityColumn = "id",
            associateBy = @Junction(
                    value = PurchasesCategories.class,
                    parentColumn = "purchaseId",
                    entityColumn = "categoryId")
    )
    List<Category> categories = new ArrayList<>();
    @Ignore
    List<Category> deletedCategories = new ArrayList<>();

    public PurchaseRecord() {
    }

    @Override
    public PurchaseRecord clone() {
        PurchaseRecord clone = new PurchaseRecord();
        clone.setPurchase(purchase.clone());
        List<Category> newCategories = clone.getCategories();
        for (Category category : categories) {
            newCategories.add(category.clone());
        }
        List<Category> newDeletedCategories = clone.getCategoriesToDelete();
        for (Category category : deletedCategories) {
            newDeletedCategories.add(category.clone());
        }
        return clone;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public long getId() {
        return purchase.id;
    }

    public void setId(long id) {
        purchase.id = id;
    }

    public Calendar getDate() {
        return purchase.date;
    }

    public void setDate(Calendar date) {
        purchase.date = date;
    }

    public String getDateAsString() {
        return purchase.date.get(Calendar.DAY_OF_MONTH) + "/" + (purchase.date.get(Calendar.MONTH) + 1) + "/" + purchase.date.get(Calendar.YEAR);
    }

    public int getCost() {
        return purchase.cost;
    }

    public void setCost(int cost) {
        purchase.cost = cost;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getCategoriesToDelete() {
        return deletedCategories;
    }
}
