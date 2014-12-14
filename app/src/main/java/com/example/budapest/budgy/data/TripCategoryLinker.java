package com.example.budapest.budgy.data;

import com.orm.SugarRecord;

/**
 * Created by Budapest on 14/12/2014.
 *
 * Necessary since sugarorm doesn't support many to many relationships
 *
 */
public class TripCategoryLinker extends SugarRecord<TripCategoryLinker> {

    public Trip trip;
    public Category category;

    public TripCategoryLinker() {

    }

    public TripCategoryLinker(Trip trip, Category category) {
        this.trip = trip;
        this.category = category;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
