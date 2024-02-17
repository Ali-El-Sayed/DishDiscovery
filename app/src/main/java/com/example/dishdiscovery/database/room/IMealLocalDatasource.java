package com.example.dishdiscovery.database.room;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;
import com.example.dishdiscovery.weeklyMeals.presenter.OnLocalWeeklyMeals;

public interface IMealLocalDatasource {
    void getLocalWeeklyMeals(OnLocalWeeklyMeals callback);


    void saveUserWeeklyMeals(LocalWeeklyMeal localWeeklyMeals);
}
