package com.example.dishdiscovery.weeklyMeals.view;

import com.example.dishdiscovery.model.UserWeeklyMeals;

public interface IWeeklyMealsView {
    void showWeeklyMeals(UserWeeklyMeals userWeeklyMeals);

    void showError(String error);


}
