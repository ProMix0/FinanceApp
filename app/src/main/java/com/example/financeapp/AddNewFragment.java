package com.example.financeapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class AddNewFragment extends Fragment {

    Calendar date;
    Button changeDateButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_fragment, container, false);

        // Установка обработчика нажатия кнопке
        Button button = view.findViewById(R.id.button_create);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddRecord();
            }
        });

        changeDateButton = view.findViewById(R.id.button_change_date);
        changeDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar();
            }
        });
        clearDate();

        return view;
    }

    private View findViewById(int category) {
        return getView().findViewById(category);
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
        int amount = Integer.parseInt(((EditText) findViewById(R.id.amount)).getText().toString());
        String category = ((EditText) findViewById(R.id.category)).getText().toString();

        // Проверка данных
        if (!(amount == 0) && !category.isEmpty() && date != null) {

            // Добавление записи
            Model.getInstance().addItem(new PurchaseRecord(amount, category, date));

            // Очистка ввода
            ((EditText) findViewById(R.id.amount)).setText("");
            ((EditText) findViewById(R.id.category)).setText("");
            clearDate();
        }
    }

    private void clearDate() {
        date = Calendar.getInstance();
        onChangeDate.onDateSet(null, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
    }
}
