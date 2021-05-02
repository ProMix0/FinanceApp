package com.example.financeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    ArrayList<PurchaseRecord> purchaseRecords = new ArrayList<>();
    ListView purchases;
    PurchaseAdapter adapter;

    public static final List<PurchaseRecord> purchasesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        final MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(this, getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        purchases = findViewById(R.id.purchases);
        adapter = new PurchaseAdapter(this, R.layout.purchase_view, purchaseRecords);
        purchases.setAdapter(adapter);

        // Получаем ViewPager и устанавливаем в него адаптер
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(
                new SampleFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));

        // Передаём ViewPager в TabLayout
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }
    public void add(View view){
        int amount = Integer.parseInt(((EditText) findViewById(R.id.amount)).getText().toString());
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
}