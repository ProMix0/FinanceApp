package com.example.financeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ViewPurchasesFragment extends Fragment {

    public PurchaseAdapter purchaseAdapter;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        purchaseAdapter= new PurchaseAdapter(this,)
    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_new_fragment, container, false);
        return view;
    }
}
