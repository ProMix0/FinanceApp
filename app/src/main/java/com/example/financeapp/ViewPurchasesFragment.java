package com.example.financeapp;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewPurchasesFragment extends Fragment implements View.OnClickListener, FragmentLifecycle {
    public PurchaseAdapter purchaseAdapter;

    public void addItem(PurchaseRecord record) {
        MainActivity.sWaitList.add(record);
        Log.d("DEBUUG", (purchaseAdapter == null) + "");
        if (purchaseAdapter != null) {
            purchaseAdapter.add(record);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_purchases_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.showStat).setOnClickListener(this);
        purchaseAdapter = new PurchaseAdapter(MainActivity.sWaitList);
        RecyclerView recyclerView = view.findViewById(R.id.purchases);
        recyclerView.setAdapter(purchaseAdapter);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(
                v.getContext(),
                "Item: " + MainActivity.sWaitList.size() + "; Show: " + purchaseAdapter.getItemCount(),
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {
        purchaseAdapter.replaceAll(MainActivity.sWaitList);
        purchaseAdapter.notifyDataSetChanged();
    }
}
