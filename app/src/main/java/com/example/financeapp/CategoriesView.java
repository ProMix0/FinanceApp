package com.example.financeapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.financeapp.db.Category;
import com.example.financeapp.db.PurchaseRecord;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoriesView extends FrameLayout {

    FlexboxLayout flexbox;
    List<CategoryView> items = new ArrayList<>();
    List<Category> categories;

    public CategoriesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.categories_view, this);

        flexbox = findViewById(R.id.flexbox);

        Button addButton = findViewById(R.id.add);
        addButton.setOnClickListener(v -> addNewCategory(new Category(0, "enter name")));
    }

    public void bindPurchase(PurchaseRecord purchase) {
        clear();
        categories = purchase.getCategories();
        addCategories(categories);
    }

    public void deleteCategory(CategoryView category) {
        categories.remove(category.category);
        items.remove(category);
        flexbox.removeView(category);
    }

    public void addNewCategory(Category category) {
        CategoryView temp = new CategoryView(getContext(), this, category);
        categories.add(category);
        items.add(0, temp);
        flexbox.addView(temp, 0);
    }

    public void addCategory(Category category) {
        CategoryView temp = new CategoryView(getContext(), this, category);
        items.add(0, temp);
        flexbox.addView(temp, 0);
    }

    public void addCategories(List<Category> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            addCategory(list.get(i));
        }
    }

    public void clear() {
        while (items.size() > 0) {
            deleteView(items.get(0));
        }
    }

    private void deleteView(CategoryView categoryView) {
        items.remove(categoryView);
        flexbox.removeView(categoryView);
    }
}
