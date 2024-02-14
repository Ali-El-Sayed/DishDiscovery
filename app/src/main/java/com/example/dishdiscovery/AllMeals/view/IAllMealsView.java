package com.example.dishdiscovery.AllMeals.view;

import com.example.dishdiscovery.model.FilteredMeal;

import java.util.List;

public interface IAllMealsView {
    void displayAllMeals(List<FilteredMeal> allMeals);

    void showError(String message);
}
