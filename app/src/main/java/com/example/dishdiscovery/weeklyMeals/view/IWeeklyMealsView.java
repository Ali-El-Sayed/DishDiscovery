package com.example.dishdiscovery.weeklyMeals.view;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;

import java.util.List;

public interface IWeeklyMealsView {
    void showWeeklyMeals(List<LocalWeeklyMeal> userWeeklyMeals);

    void showError(String error);

    void onSavedSuccess();

}
