package com.example.dishdiscovery.database.firebaseRealtime.model;

import androidx.room.PrimaryKey;

import com.example.dishdiscovery.model.Meal;

public class RemoteUserWeeklyMeals {
    @PrimaryKey
    public String userId;
    public Meal _saturday;
    public Meal _sunday;
    public Meal _monday;
    public Meal _tuesday;
    public Meal _wednesday;
    public Meal _thursday;
    public Meal _friday;


    public RemoteUserWeeklyMeals(String userId) {
        this.userId = userId;

        _saturday = new Meal();
        _sunday = new Meal();
        _monday = new Meal();
        _tuesday = new Meal();
        _wednesday = new Meal();
        _thursday = new Meal();
        _friday = new Meal();

    }
}
