package com.example.budapest.budgy;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budapest.budgy.adapters.TransactionAdapter;
import com.example.budapest.budgy.adapters.TripAdapter;
import com.example.budapest.budgy.data.Trip;
import com.example.budapest.budgy.data.TripTransaction;

import java.util.List;

/**
 * Created by Budapest on 09/12/2014.
 */
public class TransactionsFragment extends ListFragment {

    public List<TripTransaction> transactions;
    //private View root;
    private TextView tvTotalCost;
    private Trip trip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = View.inflate(getActivity(), R.layout.fragment_transactions, null);

        transactions = TripTransaction.find(TripTransaction.class, "trip_Ref = ?",
                Integer.toString(trip.getTripID()));

        TransactionAdapter adapter = new TransactionAdapter(getActivity(), transactions);
        setListAdapter(adapter);

        tvTotalCost = (TextView) root.findViewById(R.id.tvTransactionsTotal);

        double currCost = trip.getCurrentCost();
        double totalCost = trip.getTotalCost();

        tvTotalCost.setText("Total: " + currCost + " of " + totalCost);


        return root;
    }

    public void setTrip(Trip t) {
        trip = t;
    }
}
