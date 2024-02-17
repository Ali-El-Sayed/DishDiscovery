package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserWeeklyMeals;

import java.util.List;

public interface OnWeeklyMealsLoaded {
    void onWeeklyMealsLoaded(List<UserWeeklyMeals> meals);
    void onWeeklyMealsLoadFailed(String message);
}
