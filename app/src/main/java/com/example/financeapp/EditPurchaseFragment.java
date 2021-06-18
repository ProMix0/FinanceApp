package com.example.financeapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.financeapp.db.Category;
import com.example.financeapp.db.PurchaseRecord;
import com.google.android.material.chip.ChipGroup;

import java.util.Calendar;

public class EditPurchaseFragment extends DialogFragment {

    TextView amountTextView;
    Button changeDateButton;
    TextView newCategoryTextView;
    Button addCategoryButton;
    ChipGroup chipGroup;

    PurchaseRecord record;

    ICallback onConfirmCallback;
    private Button button;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("dataToAdd", record);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (record == null)
            if (savedInstanceState != null) {
                record = (PurchaseRecord) savedInstanceState.getSerializable("dataToAdd");
            } else {
                record = ViewModel.getInstance().getEmptyRecord();
            }
    }

    @Override
    public void onResume() {
        super.onResume();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_purchase_fragment, container, false);

        // Установка обработчика нажатия кнопке
        button = view.findViewById(R.id.button_confirm);
        button.setOnClickListener(v -> onConfirmClick());

        amountTextView = view.findViewById(R.id.amount);

        changeDateButton = view.findViewById(R.id.button_change_date);
        changeDateButton.setOnClickListener(v -> showCalendar());

        chipGroup = view.findViewById(R.id.chip_group_closeable);

        newCategoryTextView = view.findViewById(R.id.new_category);

        addCategoryButton = view.findViewById(R.id.add_category);
        addCategoryButton.setOnClickListener(clickView -> {
            String text = newCategoryTextView.getText().toString();
            if (text.compareTo("") == 0) return;
            newCategoryTextView.setText("");
            addNewCategory(text);
        });

        bindRecord(record);

        return view;
    }

    private void addNewCategory(String text) {
        Category newCategory = new Category(text);
        record.getCategories().add(newCategory);
        addChip(newCategory);
    }

    private void addChip(Category category) {
        MyChip temp = new MyChip(getContext(), true);
        temp.setCategory(category);
        temp.setOnCloseIconClickListener(view -> {
            MyChip chip = (MyChip) view;
            record.getCategories().remove(chip.getCategory());
            record.getCategoriesToDelete().add(chip.getCategory());
            chipGroup.removeView(chip);
        });
        chipGroup.addView(temp);
    }

    public void bindRecord(PurchaseRecord record) {
        this.record = record;

        amountTextView.setText(record.getCost() + "");

        chipGroup.removeAllViews();
        for (Category category : record.getCategories()) {
            addChip(category);
        }

        Calendar date = record.getDate();
        onChangeDate.onDateSet(null, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
    }

    public void lateBind(PurchaseRecord record) {
        this.record = record;
    }

    public void showCalendar() {
        Calendar date = record.getDate();
        new DatePickerDialog(this.getContext(), onChangeDate,
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    DatePickerDialog.OnDateSetListener onChangeDate = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Calendar date = record.getDate();
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, monthOfYear);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            changeDateButton.setText(date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR));
        }
    };

    public void onConfirmClick() {
        // Получение введённых данных
        int amount;
        try {
            amount = Integer.parseInt(amountTextView.getText().toString());
        } catch (Exception ex) {
            amount = 0;
        }
        record.setCost(amount);

        if (onConfirmCallback != null) onConfirmCallback.callback(record);
    }

    public void setOnConfirm(ICallback callback) {
        onConfirmCallback = callback;
    }
}
