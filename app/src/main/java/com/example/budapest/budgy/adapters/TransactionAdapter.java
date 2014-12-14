package com.example.budapest.budgy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.budapest.budgy.R;
import com.example.budapest.budgy.data.TripTransaction;

import java.util.List;

/**
 * Created by Budapest on 10/12/2014.
 */
public class TransactionAdapter extends BaseAdapter {

    private Context context;
    private List<TripTransaction> transactions;

    public TransactionAdapter(Context context, List<TripTransaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    public void addTransaction(TripTransaction t) {
        transactions.add(t);
    }

    public void removeTransaction(int index) {
        transactions.remove(index);
    }

    @Override
    public int getCount() {
        return transactions.size();
    }

    @Override
    public Object getItem(int position) {
        return transactions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView tvTransactionTitle;
        TextView tvTransactionCost;
        TextView tvTransactionDes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.row_transaction, null);
            ViewHolder holder = new ViewHolder();
            holder.tvTransactionTitle = (TextView) v.findViewById(R.id.tvTransactionTitle);
            holder.tvTransactionCost = (TextView) v.findViewById(R.id.tvTransactionCost);
            holder.tvTransactionDes = (TextView) v.findViewById(R.id.tvTransactionDes);
            v.setTag(holder);
        }

        TripTransaction t = transactions.get(position);
        if (t != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            holder.tvTransactionCost.setText(Double.toString(t.getCost()));
            holder.tvTransactionTitle.setText(t.getType() +  " - " + t.getName());
            holder.tvTransactionDes.setText(t.getDescription());
        }

        return v;
    }
}
