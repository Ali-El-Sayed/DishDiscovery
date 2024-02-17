package com.example.dishdiscovery.mealDetails.presenter;

import com.example.dishdiscovery.model.Meal;

public interface IMealDetailsPresenter {
    void getMealById(String mealId);

    void saveUserWeeklyMeals(String dayOfTheWeek, Meal meal);

}
