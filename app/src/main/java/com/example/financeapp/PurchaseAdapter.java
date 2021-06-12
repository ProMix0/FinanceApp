package com.example.financeapp;

import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financeapp.db.PurchaseRecord;

import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {

    private List<PurchaseRecord> data;

    public void setData(List<PurchaseRecord> list) {
        data = list;
    }

    public PurchaseAdapter() {
        ViewModel.getInstance().setDataAdapter(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        PurchaseView purchase = new PurchaseView(viewGroup.getContext());

        purchase.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));

        return new ViewHolder(purchase);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bindView(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private PurchaseView purchase;

        public ViewHolder(PurchaseView purchase) {
            super(purchase);
            this.purchase = purchase;
        }

        public void bindView(PurchaseRecord record) {
            purchase.bindPurchase(record);
        }
    }
}
