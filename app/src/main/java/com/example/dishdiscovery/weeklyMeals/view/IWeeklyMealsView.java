package com.example.dishdiscovery.weeklyMeals.view;

import com.example.dishdiscovery.model.UserWeeklyMeals;

import java.util.List;

public interface IWeeklyMealsView {
    void showWeeklyMeals(List<UserWeeklyMeals> userWeeklyMeals);

    void showError(String error);


}
