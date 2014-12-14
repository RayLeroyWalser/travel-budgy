package com.example.budapest.budgy.dialogFragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.budapest.budgy.R;
import com.example.budapest.budgy.TripDetailActivity;
import com.example.budapest.budgy.data.Category;
import com.example.budapest.budgy.data.Trip;
import com.example.budapest.budgy.data.TripCategoryLinker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Budapest on 09/12/2014.
 */
public class CreateTripFragment extends DialogFragment  { //}, DatePickerDialog.OnDateSetListener{

    public static final String TAG = "New Trip";
    public static final String TRIP_ID = "Trip ID";

    private final Calendar c = Calendar.getInstance();
    private final DatePickerDialog.OnDateSetListener startPicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(1);
        }
    };
    private final DatePickerDialog.OnDateSetListener endPicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, monthOfYear);
            c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(2);
        }
    };

    private EditText etTitle;
    private EditText etDes;
    private EditText etStart;
    private EditText etEnd;
    private EditText etCost;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final int tripID = getArguments().getInt(TRIP_ID);

        Toast.makeText(getActivity(), "id: " + tripID, Toast.LENGTH_LONG).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Trip");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.create_trip_dialog, null);

        final Context context = getActivity();
        builder.setView(v);

        etTitle = (EditText) v.findViewById(R.id.etTripName);
        etDes = (EditText) v.findViewById(R.id.etDescription);
        etStart = (EditText) v.findViewById(R.id.etStart);
        etEnd = (EditText) v.findViewById(R.id.etEnd);
        etCost = (EditText) v.findViewById(R.id.etProposedCost);

        etStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog startDate = new DatePickerDialog(context, startPicker, c
                        .get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
                startDate.getDatePicker().setCalendarViewShown(false);
                startDate.show();
            }
        });

        etEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog endDate = new DatePickerDialog(context, endPicker, c
                        .get(Calendar.YEAR), c.get(Calendar.MONTH),
                        c.get(Calendar.DAY_OF_MONTH));
                endDate.getDatePicker().setCalendarViewShown(false);
                endDate.show();
            }
        });


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String name = etTitle.getText().toString();
                String des = etDes.getText().toString();
                String cost = etCost.getText().toString();
                String start = etStart.getText().toString();
                String end = etEnd.getText().toString();



                Toast.makeText(getActivity(), "id " + tripID, Toast.LENGTH_LONG).show();

                Trip trip = new Trip(name, tripID, des, Double.parseDouble(cost), 0.00, start, end);
                trip.save();

                addDefaultCategories(trip);

      /*          Toast.makeText(getActivity(), "tripID " + tripID, Toast.LENGTH_LONG).show();

                List<Trip> test = Trip.find(Trip.class,"trip_ID = ?", Integer.toString(tripID));
                List<Trip> test1 = Trip.find(Trip.class,"name = ?", name);
                Toast.makeText(getActivity(), "size " + test.size(), Toast.LENGTH_LONG).show();
                Toast.makeText(getActivity(), "name size " + test1.size(), Toast.LENGTH_LONG).show();*/


                Intent intent = new Intent(context, TripDetailActivity.class);
                intent.putExtra(TripDetailActivity.TRIP_ID, tripID);
                startActivity(intent);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CreateTripFragment.this.getDialog().cancel();
            }
        });

        return builder.create();
    }

    private void updateLabel(int i) {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        if (i==1)
            etStart.setText(sdf.format(c.getTime()));
        if (i==2)
            etEnd.setText(sdf.format(c.getTime()));
    }

    private void addDefaultCategories(Trip t) {
        Category food = new Category("Food", 0);
        Category housing = new Category("Housing", 0);
        Category attractions = new Category("Attractions", 0);
        Category other = new Category("Other", 0);

        food.save();
        housing.save();
        attractions.save();
        other.save();

        TripCategoryLinker linker1 = new TripCategoryLinker(t, food);
        TripCategoryLinker linker2 = new TripCategoryLinker(t, housing);
        TripCategoryLinker linker3 = new TripCategoryLinker(t, attractions);
        TripCategoryLinker linker4 = new TripCategoryLinker(t, other);


        linker1.save();
        linker2.save();
        linker3.save();
        linker4.save();

    }


}
