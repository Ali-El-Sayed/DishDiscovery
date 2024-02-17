package com.example.dishdiscovery.database.room;

import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.weeklyMeals.presenter.OnWeeklyMealsLoaded;

public interface IMealDatasource {
    void getWeeklyMeals(OnWeeklyMealsLoaded callback);



}
