package com.example.budapest.budgy.data;

import com.orm.SugarRecord;

/**
 * Created by Budapest on 10/12/2014.
 */
public class TripTransaction extends SugarRecord<TripTransaction> {

    private String name;
    private int tripRef;
    private String description;
    private double cost;
    private String type;
    private int paid;
    private String recipient;

    public TripTransaction() {

    }

    public TripTransaction(String name, int tripRef, String des, double cost, String type,
                           int paid, String recipient) {
        this.name = name;
        this.tripRef = tripRef;
        this.description = des;
        this.cost = cost;
        this.type = type;
        this.paid = paid;
        this.recipient = recipient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTripRef() {
        return tripRef;
    }

    public void setTripRef(int tripRef) {
        this.tripRef = tripRef;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
