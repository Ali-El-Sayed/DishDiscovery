package com.example.dishdiscovery.mealDetails.view;

import com.example.dishdiscovery.model.Meal;

public interface IMealDetails {
    void showMealDetails(Meal meal);
    void showError(String error);
}
