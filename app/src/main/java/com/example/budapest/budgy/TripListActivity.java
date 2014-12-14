package com.example.budapest.budgy;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budapest.budgy.adapters.TripAdapter;
import com.example.budapest.budgy.data.Trip;
import com.example.budapest.budgy.dialogFragments.CreateTripFragment;

import java.util.List;

/**
 * Created by Budapest on 09/12/2014.
 */
public class TripListActivity extends ListActivity {

    private List<Trip> trips;
    private int tripID;
    private TextView noTrips;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_list);

        noTrips = (TextView) findViewById(R.id.tvNoTrips);

        View v = getListView();

        updateList();

        registerForContextMenu(v);

        Toast.makeText(this, "id: " + tripID, Toast.LENGTH_LONG).show();

    }

    private void updateList() {
        trips = Trip.findWithQuery(Trip.class, "Select * from Trip");
        if (trips.size()!=0) {
            noTrips.setVisibility(View.INVISIBLE);
            TripAdapter adapter = new TripAdapter(getApplicationContext(), trips);
            setListAdapter(adapter);
        }
        else {
            noTrips.setVisibility(View.VISIBLE);
            Toast.makeText(this, "no trips!", Toast.LENGTH_LONG).show();
        }
        tripID = trips.size();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateList();
    }

   @Override
    protected void onResume() {
       super.onResume();
        updateList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.create_new) {
            CreateTripFragment dialog = new CreateTripFragment();

            Bundle b = new Bundle();
            b.putInt(CreateTripFragment.TRIP_ID, tripID);
            dialog.setArguments(b);
            Toast.makeText(this, "id: " + tripID, Toast.LENGTH_LONG).show();
            dialog.show(getFragmentManager(), CreateTripFragment.TAG);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    //    super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(this, TripDetailActivity.class);
        intent.putExtra(TripDetailActivity.TRIP_ID, position);
        startActivity(intent);
    }

    public void onCreateContextMenu(ContextMenu menu, ListView v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_delete, menu);
    }

}
