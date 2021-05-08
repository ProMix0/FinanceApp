package com.example.financeapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

public class AddNewFragment extends Fragment implements View.OnClickListener {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.add_new_fragment, container, false);

        Button button = view.findViewById(R.id.button_create);
        button.setOnClickListener(this);
        return view;
    }

    private Object findViewById(int category) {
       return getView().findViewById(category);
    }

    @Override
    public void onClick(View v) {
        int amount = Integer.parseInt(((EditText) findViewById(R.id.amount)).getText().toString());
        String category = ((EditText) findViewById(R.id.category)).getText().toString();
        String date = ((EditText) findViewById(R.id.date)).getText().toString();
        if (!(amount ==0) && !category.isEmpty() && !date.isEmpty()) {
            ((MainActivity)getActivity()).fragmentAdapter.viewPurchasesFragment.addItem(new PurchaseRecord(amount,category,date));
            ((EditText) findViewById(R.id.amount)).setText("");
            ((EditText) findViewById(R.id.category)).setText("");
            ((EditText) findViewById(R.id.date)).setText("");
        }
    }
}
