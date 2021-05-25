package com.example.financeapp.db;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PurchaseRecord {
    @Embedded
    Purchase purchase=new Purchase();
    @Relation(
            parentColumn = "id",
            entity = Category.class,
            entityColumn = "id",
            associateBy = @Junction(
                    value = PurchasesCategories.class,
                    parentColumn = "purchaseId",
                    entityColumn = "categoryId")
    )
    List<Category> categories=new ArrayList<>();

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

    public List<String> getCategoriesNames() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            result.add(categories.get(i).name);
        }
        return result;
    }

    public void setCategoriesNames(List<String> categories) {
        this.categories = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            Category temp = new Category(0, categories.get(i));
            this.categories.add(temp);
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
