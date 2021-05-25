package com.example.financeapp;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financeapp.db.PurchaseRecord;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {

    public PurchaseAdapter() {
        Model.getInstance().setDataAdapter(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        PurchaseFragment purchase = new PurchaseFragment();

        return new ViewHolder(purchase);
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
        private PurchaseFragment purchase;

        public ViewHolder(PurchaseFragment purchase) {
            super(purchase.getView());
            this.purchase = purchase;
        }

        public void bindView(PurchaseRecord record) {
            purchase.categories.clear();
            purchase.categories.addCategories(record.getCategories());
        }
    }
}
