package com.example.financeapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);

        /*// Получение элементов разметки
        final TabLayout tabLayout = findViewById(R.id.tablayout);
        final ViewPager viewPager = findViewById(R.id.viewpager);

        // Натстройка ViewPager
        MyFragmentAdapter fragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        // Настройка TabLayout
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
        tabLayout.setupWithViewPager(viewPager);*/
    }
}