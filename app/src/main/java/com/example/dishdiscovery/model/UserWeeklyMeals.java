package com.example.dishdiscovery.model;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;

import java.util.ArrayList;
import java.util.List;

public class UserWeeklyMeals {
 
    public String userId;
    public List<LocalWeeklyMeal> weeklyMeals;

    public UserWeeklyMeals() {
        userId = "";
        weeklyMeals = new ArrayList<>();
    }

    public UserWeeklyMeals(@NonNull String userId, List<LocalWeeklyMeal> weeklyMeals) {
        this.userId = userId;
        this.weeklyMeals = weeklyMeals;
    }
}

