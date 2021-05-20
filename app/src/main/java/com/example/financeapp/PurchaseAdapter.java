package com.example.financeapp;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        private TextView amount, category, date;
        private SimpleDateFormat formatter;

        public ViewHolder(View view) {
            super(view);

            amount = view.findViewById(R.id.amount);
            category = view.findViewById(R.id.category);
            date = view.findViewById(R.id.date);

            formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            formatter.setLenient(false);
        }

        public void bindView(PurchaseRecord record) {
            amount.setText(String.valueOf(record.getAmount()));
            category.setText(record.getCategory());
            date.setText(formatter.format(record.getDate().getTime()));
        }
    }
}
