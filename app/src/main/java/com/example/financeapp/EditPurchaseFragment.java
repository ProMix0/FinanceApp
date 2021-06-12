package com.example.financeapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.financeapp.db.PurchaseRecord;

import java.util.Calendar;

public class EditPurchaseFragment extends Fragment {

    Button changeDateButton;
    TextView cost;
    CategoriesList categories;

    PurchaseRecord record;

    ICallback onConfirmCallback;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("dataToAdd", record);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            record = (PurchaseRecord) savedInstanceState.getSerializable("dataToAdd");
        } else {
            record = ViewModel.getInstance().getEmptyRecord();
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_fragment, container, false);

        // Установка обработчика нажатия кнопке
        Button button = view.findViewById(R.id.button_create);
        button.setOnClickListener(v -> onConfirmClick());

        cost = view.findViewById(R.id.amount);

        changeDateButton = view.findViewById(R.id.button_change_date);
        changeDateButton.setOnClickListener(v -> showCalendar());

        categories = view.findViewById(R.id.categories_view);

        return view;
    }

    public void bindRecord(PurchaseRecord record) {
        this.record = record;

        categories.bindCategories(this.record.getCategories(), this.record.getDeletedCategories());

        Calendar date = record.getDate();
        onChangeDate.onDateSet(null, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
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
            amount = Integer.parseInt(cost.getText().toString());
        } catch (Exception ex) {
            amount = 0;
        }
        record.setCost(amount);

        if (onConfirmCallback != null) onConfirmCallback.callback(record);
    }

    public void setOnConfirm(ICallback callback) {
        onConfirmCallback = callback;
    }

    public interface ICallback {
        void callback(PurchaseRecord record);
    }
}
