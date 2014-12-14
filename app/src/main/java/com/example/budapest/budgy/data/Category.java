package com.example.budapest.budgy.data;

import com.orm.SugarRecord;

/**
 * Created by Budapest on 13/12/2014.
 */
public class Category extends SugarRecord<Category>{

    private String name;
    private double amount;

    public Category() {

    }

    public Category(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
