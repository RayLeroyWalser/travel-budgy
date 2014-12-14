package com.example.budapest.budgy.data;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Budapest on 09/12/2014.
 */
public class Trip extends SugarRecord<Trip> {

    private String name;
    private double totalCost;
    private String description;
    private int tripID;
    private double currentCost;
    private String startDate;
    private String endDate;


    public Trip() {

    }

    public Trip(String name, int ID, String description, double totalCost, double currentCost,
                String startDate, String endDate) {
        this.name = name;
        this.tripID = ID;
        this.description = description;
        this.totalCost = totalCost;
        this.currentCost = currentCost;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int ID) {
        this.tripID = ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public double getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(double currentCost) {
        this.currentCost = currentCost;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
