package com.example.dishdiscovery.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserWeeklyMeals {
    @PrimaryKey
    private String userId;
    private Meal _saturday;
    private Meal _sunday;
    private Meal _monday;
    private Meal _tuesday;
    private Meal _wednesday;
    private Meal _thursday;
    private Meal _friday;


    public UserWeeklyMeals(String userId) {
        this.userId = userId;

        _saturday = new Meal();
        _sunday = new Meal();
        _monday = new Meal();
        _tuesday = new Meal();
        _wednesday = new Meal();
        _thursday = new Meal();
        _friday = new Meal();

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Meal get_saturday() {
        return _saturday;
    }

    public void set_saturday(Meal _saturday) {
        this._saturday = _saturday;
    }

    public Meal get_sunday() {
        return _sunday;
    }

    public void set_sunday(Meal _sunday) {
        this._sunday = _sunday;
    }

    public Meal get_monday() {
        return _monday;
    }

    public void set_monday(Meal _monday) {
        this._monday = _monday;
    }

    public Meal get_tuesday() {
        return _tuesday;
    }

    public void set_tuesday(Meal _tuesday) {
        this._tuesday = _tuesday;
    }

    public Meal get_wednesday() {
        return _wednesday;
    }

    public void set_wednesday(Meal _wednesday) {
        this._wednesday = _wednesday;
    }

    public Meal get_thursday() {
        return _thursday;
    }

    public void set_thursday(Meal _thursday) {
        this._thursday = _thursday;
    }

    public Meal get_friday() {
        return _friday;
    }

    public void set_friday(Meal _friday) {
        this._friday = _friday;
    }
}
