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

    private List<PurchaseRecord> localDataSet;

    public PurchaseAdapter(List<PurchaseRecord> dataSet) {
        localDataSet = new ArrayList<>(dataSet);
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.purchase_view, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bindView(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public void add(PurchaseRecord record) {
        localDataSet.add(record);
        notifyDataSetChanged();
    }

    public void replaceAll(List<PurchaseRecord> list) {
        localDataSet.clear();
        localDataSet.addAll(list);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private EditText amount;
        private EditText category;
        private EditText date;

        private View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            // Define click listener for the ViewHolder's View

            amount = view.findViewById(R.id.amount);
            category = view.findViewById(R.id.category);
            date = view.findViewById(R.id.date);
        }

        public View bindView(PurchaseRecord record) {
            amount.setText(record.getAmount());
            category.setText(record.getCategory());
            date.setText(record.getDate());
            return view;
        }
    }
}
