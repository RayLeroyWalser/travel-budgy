package com.example.budapest.budgy.dialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budapest.budgy.R;
import com.example.budapest.budgy.TransactionsFragment;
import com.example.budapest.budgy.TripDetailActivity;
import com.example.budapest.budgy.data.Category;
import com.example.budapest.budgy.data.Trip;
import com.example.budapest.budgy.data.TripCategoryLinker;
import com.example.budapest.budgy.data.TripTransaction;

import java.util.List;

/**
 * Created by Budapest on 09/12/2014.
 */
public class CreateTransactionFragment extends DialogFragment {

    public static final String TAG = "New Transaction";
    private Trip trip;
    private EditText etName;
    private EditText etDes;
    private EditText etCost;
    private Spinner spinnerCat;
    private CheckBox paidCheck;
    private TextView tvRecipient;
    private EditText etRecipient;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Transaction");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.create_transaction_dialog, null);

        etName = (EditText) v.findViewById(R.id.transactionName);
        etDes = (EditText) v.findViewById(R.id.transactionDes);
        etCost = (EditText) v.findViewById(R.id.transactionCost);
        spinnerCat = (Spinner) v.findViewById(R.id.spinner);
        paidCheck = (CheckBox) v.findViewById(R.id.cbPaid);
        tvRecipient = (TextView) v.findViewById(R.id.tvRecipient);
        etRecipient = (EditText) v.findViewById(R.id.etRecipient);

        paidCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    tvRecipient.setVisibility(View.INVISIBLE);
                    etRecipient.setVisibility(View.INVISIBLE);
                }
                else {
                    tvRecipient.setVisibility(View.VISIBLE);
                    etRecipient.setVisibility(View.VISIBLE);
                }

            }
        });


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String name = etName.getText().toString();
                String des = etDes.getText().toString();
                String category = spinnerCat.getSelectedItem().toString();
                double cost = Double.parseDouble(etCost.getText().toString());
                String recipient = etRecipient.getText().toString();

                int paid = 1;
                if (!paidCheck.isChecked())
                    paid = 0;

                List<TripCategoryLinker> tcLinkerList = TripCategoryLinker.find(TripCategoryLinker.class,
                        "trip = ?",
                        Long.toString(trip.getId()));

                for(int i = 0; i < tcLinkerList.size(); i++) {
                    if(category.equals(tcLinkerList.get(i).getCategory().getName())) {
                        Category transactionCategory = tcLinkerList.get(i).getCategory();
                        transactionCategory.setAmount(transactionCategory.getAmount() + cost);
                        transactionCategory.save();
                        break;
                    }
                }

            //    Toast.makeText(getActivity(), "idfrag: " + tripID, Toast.LENGTH_LONG).show();

                TripTransaction transaction = new TripTransaction(name, trip.getTripID(), des, cost, category,
                        paid, recipient);
                transaction.save();
                trip.setCurrentCost(trip.getCurrentCost() + cost);
                trip.save();

                TripDetailActivity tDA = (TripDetailActivity) getActivity();
                tDA.updateTabView();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                CreateTransactionFragment.this.getDialog().cancel();
            }
        });


        Spinner spinner = (Spinner) v.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.categories_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        builder.setView(v);

        return builder.create();
    }

    public void setTrip(Trip t) {
        trip = t;
    }


}
