package com.example.financeapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.financeapp.db.Category;
import com.google.android.material.chip.Chip;

public class MyChip extends FrameLayout {

    Chip chip;
    Category category;

    public MyChip(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyChip(Context context, boolean closeable) {
        super(context);

        if (closeable) {
            inflate(context, R.layout.category_view_closeable, this);
            chip = findViewById(R.id.category_text_closeable);
        } else {
            inflate(context, R.layout.category_view, this);
            chip = findViewById(R.id.category_text);
        }
    }

    public void setCategory(Category category) {
        this.category = category;
        chip.setText(category.getName());
    }

    public Category getCategory() {
        return category;
    }

    public void setOnCloseIconClickListener(OnClickListener listener) {
        chip.setOnCloseIconClickListener(view -> {
            listener.onClick(this);
        });
    }
}
