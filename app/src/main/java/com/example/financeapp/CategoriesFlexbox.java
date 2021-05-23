package com.example.financeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.financeapp.db.Category;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFlexbox extends Fragment {

    FlexboxLayout flexbox;
    List<CategoryView> items = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.categories_fragment, container, false);

        flexbox = view.findViewById(R.id.flexbox);

        Button addButton = view.findViewById(R.id.add);
        addButton.setOnClickListener(v -> {
            addCategory(new Category(0, "enter name"));
        });

        return view;
    }

    public void deleteCategory(CategoryView category) {
        items.remove(category);
        flexbox.removeView(category.getView());
    }

    public void addCategory(Category category) {
        CategoryView temp = new CategoryView(this, category);
        items.add(0, temp);
        flexbox.addView(temp.getView(), 0);
    }

    public void addCategories(List<Category> list) {
        for (int i = 0; i < list.size(); i++) {
            addCategory(list.get(i));
        }
    }

    public List<Category> getCategories() {
        List<Category> result = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            result.add(new Category(0, items.get(i).category.getName()));
        }
        return result;
    }

    public void clear() {
        while (items.size() > 0) {
            deleteCategory(items.get(0));
        }
    }
}
