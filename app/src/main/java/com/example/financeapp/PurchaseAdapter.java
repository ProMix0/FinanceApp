package com.example.financeapp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financeapp.db.PurchaseRecord;

import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {

    private List<PurchaseRecord> data;

    private FragmentManager manager;

    public void setData(List<PurchaseRecord> list) {
        data = list;
    }

    public PurchaseAdapter(FragmentManager manager) {
        this.manager = manager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        PurchaseView purchase = new PurchaseView(viewGroup.getContext());

        purchase.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));

        return new ViewHolder(purchase, manager);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bindView(data.get(position), record -> {
            data.set(position, record);
            notifyItemChanged(position);
            ViewModel.getInstance().sortData();
            notifyItemMoved(position, data.indexOf(record));
            ViewModel.getInstance().update(record);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private PurchaseView purchase;
        private Callback onChange;
        private FragmentManager manager;

        public ViewHolder(PurchaseView view, FragmentManager manager) {
            super(view);
            this.purchase = view;
            this.manager = manager;

            view.setOnClickListener(this);
        }

        public void bindView(PurchaseRecord record, Callback onChange) {
            purchase.bindPurchase(record);
            this.onChange = onChange;
        }

        @Override
        public void onClick(View v) {
            EditPurchaseFragment dialog = new EditPurchaseFragment();
            dialog.setOnConfirm(new EditPurchaseFragment.Callback() {
                @Override
                public void callback(PurchaseRecord record) {
                    onChange.callback(record);
                    getFragment().dismiss();
                }
            });
            dialog.lateBind(purchase.record);
            dialog.showNow(manager, "editDialog");
        }

        public interface Callback {
            void callback(PurchaseRecord record);
        }
    }
}
