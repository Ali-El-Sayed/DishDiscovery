package com.example.dishdiscovery.home.view;

import com.example.dishdiscovery.model.Category;
import com.example.dishdiscovery.model.Meal;

import java.util.List;

public interface IHome {
    void showCategories(List<Category> categories);

    void showError(String error);

    void showMealOfTheDay(Meal meal);
}
