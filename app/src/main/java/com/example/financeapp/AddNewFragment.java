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

public class AddNewFragment extends Fragment {

    Calendar date;
    Button changeDateButton;
    TextView cost;
    CategoriesView categories;

    PurchaseRecord dataToAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_fragment, container, false);

        // Установка обработчика нажатия кнопке
        Button button = view.findViewById(R.id.button_create);
        button.setOnClickListener(v -> onAddRecord());

        cost=view.findViewById(R.id.amount);

        changeDateButton = view.findViewById(R.id.button_change_date);
        changeDateButton.setOnClickListener(v -> showCalendar());
        clearDate();

        categories = view.findViewById(R.id.categories_view);

        dataToAdd=new PurchaseRecord();
        categories.bindPurchase(dataToAdd);

        return view;
    }

    public void showCalendar() {
        new DatePickerDialog(this.getContext(), onChangeDate,
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    DatePickerDialog.OnDateSetListener onChangeDate = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, monthOfYear);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            changeDateButton.setText(date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR));
        }
    };

    public void onAddRecord() {
        // Получение введённых данных
        int amount;
        try {
            amount = Integer.parseInt(cost.getText().toString());
        } catch (Exception ex) {
            return;
        }

        // Проверка данных
        if (!(amount == 0) && !dataToAdd.getCategories().isEmpty()) {

            // Добавление записи
            dataToAdd.setCost(amount);
            dataToAdd.setDate(date);
            Model.getInstance().addItem(dataToAdd);

            // Очистка ввода
            cost.setText("");
            dataToAdd=new PurchaseRecord();
            categories.bindPurchase(dataToAdd);
            clearDate();
        }
    }

    private void clearDate() {
        date = Calendar.getInstance();
        onChangeDate.onDateSet(null, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
    }
}
