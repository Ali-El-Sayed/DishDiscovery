package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;

public interface IWeeklyMealsPresenter {

    void getUserWeeklyMeal(String mealId);

    void saveUserWeeklyMeal(LocalWeeklyMeal localWeeklyMeal);

}
