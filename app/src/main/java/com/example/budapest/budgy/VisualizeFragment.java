package com.example.budapest.budgy;


import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.budapest.budgy.adapters.TransactionAdapter;
import com.example.budapest.budgy.data.Category;
import com.example.budapest.budgy.data.Trip;
import com.example.budapest.budgy.data.TripCategoryLinker;
import com.example.budapest.budgy.data.TripTransaction;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class VisualizeFragment extends Fragment {

    private static int[] COLORS = new int[]{Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN};
    private static double[] VALUES; //= new double[] { 10, 11, 12, 13 };
    private static String[] NAME_LIST;// = new String[] { "Food", "Housing", "Attractions", "Other" };
    private CategorySeries mSeries = new CategorySeries("");
    private DefaultRenderer mRenderer = new DefaultRenderer();
    private GraphicalView mChartView;
    private List<TripCategoryLinker> categoryLinkerList;


    private View root;
    private Trip trip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = View.inflate(getActivity(), R.layout.fragment_visualize, null);

        categoryLinkerList = TripCategoryLinker.find(TripCategoryLinker.class, "trip = ?",
                Long.toString(trip.getId()));

        NAME_LIST = new String[]{"Food", "Housing", "Attractions", "Other"};


        getPieData();

        LinearLayout layout = (LinearLayout) root.findViewById(R.id.chart);
        mChartView = ChartFactory.getPieChartView(getActivity(), mSeries, mRenderer);
        mRenderer.setClickEnabled(true);
        mRenderer.setSelectableBuffer(10);

        layout.addView(mChartView);

        if (trip.getCurrentCost() != 0)
            makePieChart();

        return root;
    }

    private void getPieData() {
        // NAME_LIST = new String[categoryLinkerList.size()];
        VALUES = new double[categoryLinkerList.size()];

  /*      for (int i = 0; i < categoryLinkerList.size(); i++) {
            NAME_LIST[i] = (categoryLinkerList.get(i)).getCategory().getName();
        }*/
        NAME_LIST = new String[]{"Food", "Housing", "Attractions", "Other"};

        for (int i = 0; i < categoryLinkerList.size(); i++) {
            VALUES[i] = (categoryLinkerList.get(i)).getCategory().getAmount();
        }
    }


    private void makePieChart() {
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setChartTitleTextSize(20);
        mRenderer.setLabelsTextSize(25);
        mRenderer.setLabelsColor(Color.BLACK);
        mRenderer.setLegendTextSize(30);
        mRenderer.setTextTypeface(Typeface.DEFAULT);
        mRenderer.setMargins(new int[]{0, 0, 0, 0});
        mRenderer.setStartAngle(90);

        for (int i = 0; i < VALUES.length; i++) {
            mSeries.add(NAME_LIST[i] + " " + VALUES[i] + "\t", VALUES[i]);
            SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
            renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
            mRenderer.addSeriesRenderer(renderer);
        }

        if (mChartView != null) {
            mChartView.repaint();
        }

    }

    public void setTrip(Trip t) {
        trip = t;
    }

}
