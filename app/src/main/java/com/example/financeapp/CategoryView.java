package com.example.financeapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.financeapp.db.Category;

public class CategoryView extends FrameLayout {

    Category category;
    EditText editText;
    CategoriesList parent;

    public CategoryView(Context context, AttributeSet attr) {
        super(context, attr);
    }

    public CategoryView(Context context, CategoriesList parent, Category category) {
        super(context);

        inflate(context, R.layout.category_view, this);

        editText = findViewById(R.id.edit_text);
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

        ImageButton deleteButton = findViewById(R.id.delete);
        deleteButton.setOnClickListener(v -> parent.deleteCategory(this));

        this.parent = parent;
        this.category = category;
    }
}
