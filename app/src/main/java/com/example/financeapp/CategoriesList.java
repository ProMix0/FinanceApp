package com.example.financeapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.financeapp.db.Category;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoriesList extends FrameLayout {

    FlexboxLayout flexbox;
    List<CategoryView> views = new ArrayList<>();
    List<Category> categories;
    List<Category> categoriesToDelete;

    public CategoriesList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.categories_list, this);

        flexbox = findViewById(R.id.flexbox);

        Button addButton = findViewById(R.id.add);
        addButton.setOnClickListener(v -> addCategory(new Category(0, "enter name")));
    }

    public void bindCategories(List<Category> categories, List<Category> categoriesToDelete) {
        clear();
        this.categories = categories;
        this.categoriesToDelete = categoriesToDelete;
        addViews(categories);
    }

    public void deleteCategory(CategoryView categoryView) {
        categories.remove(categoryView.category);
        categoriesToDelete.add(categoryView.category);
        views.remove(categoryView);
        flexbox.removeView(categoryView);
    }

    public void addCategory(Category category) {
        categories.add(category);
        addView(category);
    }

    public void addView(Category category) {
        CategoryView temp = new CategoryView(getContext(), this, category);
        views.add(temp);
        flexbox.addView(temp, flexbox.getChildCount() - 1);
    }

    public void addViews(List<Category> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            addView(list.get(i));
        }
    }

    public void clear() {
        views.clear();
        flexbox.removeAllViews();
    }

    private void deleteView(CategoryView categoryView) {
        views.remove(categoryView);
        flexbox.removeView(categoryView);
    }
}
