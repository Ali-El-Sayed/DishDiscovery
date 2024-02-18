package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.model.Meal;

public interface IWeeklyMealsPresenter {

    void getUserWeeklyMeal(String mealId);

    void saveUserWeeklyMeal(Meal meal);

}
