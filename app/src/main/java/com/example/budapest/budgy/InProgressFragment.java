package com.example.budapest.budgy;

import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.budapest.budgy.adapters.TransactionAdapter;
import com.example.budapest.budgy.data.Trip;
import com.example.budapest.budgy.data.TripTransaction;

import java.util.List;

/**
 * Created by Budapest on 09/12/2014.
 */
public class InProgressFragment extends ListFragment {

    public List<TripTransaction> transactions;
    private TextView tvTotalCost;
    private Trip trip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = View.inflate(getActivity(), R.layout.fragment_inprogress, null);

        transactions = TripTransaction.find(TripTransaction.class, "trip_Ref = ? and paid = ?",
                Integer.toString(trip.getTripID()), "0");

        TransactionAdapter adapter = new TransactionAdapter(getActivity(), transactions);
        setListAdapter(adapter);

        return root;
    }

    public void setTrip(Trip t) {
        trip = t;
    }

}
