package com.example.financeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financeapp.db.PurchaseRecord;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder> {

    public PurchaseAdapter() {
        Model.getInstance().setDataAdapter(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.purchase_fragment, viewGroup, false);

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
        private TextView amount, date;
        private SimpleDateFormat formatter;
        private CategoriesFlexbox categories;

        public ViewHolder(View view) {
            super(view);

            amount = view.findViewById(R.id.amount);
            //categories = (CategoriesFlexbox)((MainActivity)view.getContext()).getFragmentManager().findFragmentById(R.id.category);
            date = view.findViewById(R.id.date);

            formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            formatter.setLenient(false);
        }

        public void bindView(PurchaseRecord record) {
            amount.setText(String.valueOf(record.getCost()));
            categories.clear();
            categories.addCategories(record.getCategories());
            date.setText(formatter.format(record.getDate().getTime()));
        }
    }
}
