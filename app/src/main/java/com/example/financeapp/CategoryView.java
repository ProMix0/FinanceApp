package com.example.financeapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.financeapp.db.Category;

public class CategoryView extends Fragment {

    Category category;
    EditText editText;
    CategoriesFlexbox parent;

    public CategoryView(CategoriesFlexbox parent, Category category) {
        super();
        this.parent = parent;
        this.category = category;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);

        editText = view.findViewById(R.id.edit_text);
        editText.setText(category.getName());
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                category.setName(s.toString());
            }
        });

        Button addButton = view.findViewById(R.id.delete);
        addButton.setOnClickListener(v -> parent.deleteCategory(this));

        return view;
    }
}
