package com.example.dishdiscovery.repository.LocalRepo;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;
import com.example.dishdiscovery.weeklyMeals.presenter.OnLocalWeeklyMeals;

public interface IMealLocalRepo {
    void loadWeeklyMeals(OnLocalWeeklyMeals callback);

    void saveUserWeeklyMeals(LocalWeeklyMeal localWeeklyMeals);
}
