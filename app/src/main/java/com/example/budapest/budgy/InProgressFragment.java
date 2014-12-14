package com.example.budapest.budgy;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Budapest on 09/12/2014.
 */
public class InProgressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = View.inflate(getActivity(), R.layout.fragment_inprogress, null);


        return root;
    }
}
