package com.example.budapest.budgy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.budapest.budgy.R;
import com.example.budapest.budgy.data.Trip;

import java.util.List;

/**
 * Created by Budapest on 09/12/2014.
 */
public class TripAdapter extends BaseAdapter {

    private Context context;
    private List<Trip> trips;

    public TripAdapter(Context context, List<Trip> trips) {
        this.context = context;
        this.trips = trips;


    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public void removeTrip(int index) {
        trips.remove(index);
    }

    @Override
    public int getCount() {
        return trips.size();
    }

    @Override
    public Object getItem(int position) {
        return trips.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        TextView tvTripTitle;
        TextView tvTripCost;
        TextView tvTripDes;
        TextView tvTripDates;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.row_trip, null);
            ViewHolder holder = new ViewHolder();
            holder.tvTripCost = (TextView) v.findViewById(R.id.tvTripCost);
            holder.tvTripTitle = (TextView) v.findViewById(R.id.tvTripTitle);
            holder.tvTripDates = (TextView) v.findViewById(R.id.tvTripDates);
            holder.tvTripDes = (TextView) v.findViewById(R.id.tvTripDescription);
            v.setTag(holder);
        }

        Trip trip = trips.get(position);
        if (trip != null) {
            ViewHolder holder = (ViewHolder) v.getTag();
            String proposedCost = Double.toString(trip.getTotalCost());
            String currentCost = Double.toString(trip.getCurrentCost());

            holder.tvTripCost.setText("Status: " + currentCost + " of " + proposedCost);
            holder.tvTripTitle.setText(trip.getName());
            holder.tvTripDes.setText(trip.getDescription());
            holder.tvTripDates.setText(trip.getStartDate() + " to " + trip.getEndDate());
        }

        return v;
    }
}
