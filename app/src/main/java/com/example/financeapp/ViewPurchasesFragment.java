package com.example.financeapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class ViewPurchasesFragment extends Fragment {
    public PurchaseAdapter purchaseAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_purchases_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Установка адаптера
        purchaseAdapter = new PurchaseAdapter(getFragmentManager());
        RecyclerView recyclerView = view.findViewById(R.id.purchases);
        recyclerView.setAdapter(purchaseAdapter);
        ViewModel.getInstance().setDataAdapter(purchaseAdapter);
    }
}
