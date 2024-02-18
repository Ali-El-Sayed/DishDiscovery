package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.model.Meal;

import java.util.List;

public interface OnLocalWeeklyMeals {
    void onLoadingSuccess(List<Meal> meals);

    void onLoadingError(String message);

    void onSaveSuccess();
}
