package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;

import java.util.List;

public interface OnLocalWeeklyMeals {
    void onLoadingSuccess(List<LocalWeeklyMeal> meals);

    void onLoadingError(String message);

    void onSaveSuccess();
}
