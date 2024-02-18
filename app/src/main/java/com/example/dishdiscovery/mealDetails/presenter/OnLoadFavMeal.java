package com.example.dishdiscovery.mealDetails.presenter;

import com.example.dishdiscovery.model.UserLocalFavMeals;

public interface OnLoadFavMeal {
    void onLoadFavMealsSuccess(UserLocalFavMeals userLocalFavMeals);

    void onLoadFavMealsError(String message);
}
