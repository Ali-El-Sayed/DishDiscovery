package com.example.dishdiscovery.weeklyMeals.view;

import com.example.dishdiscovery.model.Meal;

import java.util.List;

public interface IWeeklyMealsView {
    void showWeeklyMeals(List<Meal> userWeeklyMeals);

    void showError(String error);

    void onSavedSuccess();

}
