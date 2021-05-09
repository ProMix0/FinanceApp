package com.example.financeapp;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class MyFragmentAdapter extends FragmentPagerAdapter {
    int totalTabs;

    // Фрагменты для вкладок
    public final AddNewFragment addNewFragment;
    public final ViewPurchasesFragment viewPurchasesFragment;

    private String[] tabTitles = new String[]{"Add new purchase", "View purchases"};

    public MyFragmentAdapter(FragmentManager fm, int totalTabs) {
        super(fm, totalTabs);

        // Инициализация вкладок
        addNewFragment = new AddNewFragment();
        viewPurchasesFragment = new ViewPurchasesFragment();

        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return addNewFragment;
            case 1:
                return viewPurchasesFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}