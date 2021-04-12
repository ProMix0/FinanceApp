package com.example.financeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PurchaseAdapter extends ArrayAdapter<PurchaseRecord> {

    private LayoutInflater inflater;
    private int layout;
    private List<PurchaseRecord> purchases;

    public PurchaseAdapter(Context context, int resource, List<PurchaseRecord> purchases) {
        super(context, resource, purchases);
        this.purchases = purchases;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);

        TextView amountView = (TextView) view.findViewById(R.id.amount);
        TextView categoryView = (TextView) view.findViewById(R.id.category);
        TextView dateView = (TextView) view.findViewById(R.id.date);

        PurchaseRecord purchase = purchases.get(position);

        amountView.setText(purchase.getAmount());
        categoryView.setText(purchase.getCategory());
        dateView.setText(purchase.getDate().toString());

        return view;
    }
}