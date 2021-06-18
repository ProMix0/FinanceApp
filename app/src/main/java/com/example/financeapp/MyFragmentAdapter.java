package com.example.financeapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.financeapp.db.PurchaseRecord;

class MyFragmentAdapter extends FragmentPagerAdapter {
    int totalTabs;

    // Фрагменты для вкладок
    public final EditPurchaseFragment editPurchaseFragment;
    public final ViewPurchasesFragment viewPurchasesFragment;

    private String[] tabTitles = new String[]{"Add new purchase", "View purchases"};

    public MyFragmentAdapter(FragmentManager fm, int totalTabs) {
        super(fm, totalTabs);

        // Инициализация вкладок
        editPurchaseFragment = new EditPurchaseFragment();
        editPurchaseFragment.setOnConfirm(new EditPurchaseFragment.Callback() {
            @Override
            public void callback(PurchaseRecord record) {
                ViewModel.getInstance().addItem(record);
                getFragment().bindRecord(ViewModel.getInstance().getEmptyRecord());
            }
        });
        /*editPurchaseFragment.setOnConfirm(record-> {
            ViewModel.getInstance().addItem(record);
            fragment.bindRecord(ViewModel.getInstance().getEmptyRecord());
        });*/
        viewPurchasesFragment = new ViewPurchasesFragment();

        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return editPurchaseFragment;
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