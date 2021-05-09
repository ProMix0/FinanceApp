package com.example.financeapp;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {

    public PurchaseAdapter() {
        Model.getInstance().setDataAdapter(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.purchase_view, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bindView(Model.getInstance().getItem(position));
    }

    @Override
    public int getItemCount() {
        return Model.getInstance().size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private EditText amount;
        private EditText category;
        private EditText date;

        public ViewHolder(View view) {
            super(view);

            amount = view.findViewById(R.id.amount);
            category = view.findViewById(R.id.category);
            date = view.findViewById(R.id.date);
        }

        public void bindView(PurchaseRecord record) {
            amount.setText(record.getAmount());
            category.setText(record.getCategory());
            date.setText(record.getDate());
        }
    }
}
