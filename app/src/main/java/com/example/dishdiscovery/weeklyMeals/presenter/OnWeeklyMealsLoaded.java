package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;
import com.example.dishdiscovery.model.UserWeeklyMeals;

import java.util.List;

public interface OnWeeklyMealsLoaded {
    void onWeeklyMealsLoaded(List<LocalWeeklyMeal> meals);

    void onWeeklyMealsLoadFailed(String message);
}
