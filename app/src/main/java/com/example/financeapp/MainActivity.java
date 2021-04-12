package com.example.financeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<PurchaseRecord> purchaseRecords = new ArrayList<>();
    ListView purchases;
    PurchaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        purchases = findViewById(R.id.purchases);
        adapter = new PurchaseAdapter(this, R.layout.purchase_view, purchaseRecords);
        purchases.setAdapter(adapter);
    }
    public void add(View view){
        Integer tempAmount = Integer.getInteger(((EditText) findViewById(R.id.amount)).getText().toString());
        int amount = tempAmount == null ? -1 : tempAmount;
        String category = ((EditText) findViewById(R.id.category)).getText().toString();
        String date = ((EditText) findViewById(R.id.date)).getText().toString();
        if (!(amount ==0) && !category.isEmpty() && !date.isEmpty()) {
            adapter.add(new PurchaseRecord(amount,category,date));
            ((EditText) findViewById(R.id.amount)).setText("");
            ((EditText) findViewById(R.id.category)).setText("");
            ((EditText) findViewById(R.id.date)).setText("");
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
}