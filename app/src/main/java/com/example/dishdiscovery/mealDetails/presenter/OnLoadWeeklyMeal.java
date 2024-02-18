package com.example.dishdiscovery.mealDetails.presenter;

import com.example.dishdiscovery.model.Meal;

public interface OnLoadWeeklyMeal {

    void onLoadFavMealsSuccess(Meal userLocalFavMeals);

    void onLoadFavMealsError(String message);

}
