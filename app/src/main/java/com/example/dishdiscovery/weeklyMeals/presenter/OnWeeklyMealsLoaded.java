package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.model.UserWeeklyMeals;

public interface OnWeeklyMealsLoaded {
    void onWeeklyMealsLoaded(UserWeeklyMeals meals);

    void onWeeklyMealsLoadFailed(String message);
}
