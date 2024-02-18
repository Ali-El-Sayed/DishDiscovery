package com.example.dishdiscovery.mealDetails.view;

import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserLocalFavMeals;

public interface IMealDetails {
    void showMealDetails(Meal meal);

    void showError(String error);

    void onSaveUserWeeklyMealsSuccess();

    void onSaveUserWeeklyMealsError(String error);
    void isFavorite(Boolean isFavorite);

    void onSavedToFavSuccess();

    void onSavedToFavError(String error);

    void onRemoveFavSuccess();

    void onRemoveFavError(String error);

    void displayMealFromLocal(UserLocalFavMeals meal);
}
