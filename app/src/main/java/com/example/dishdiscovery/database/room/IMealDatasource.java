package com.example.dishdiscovery.database.room;

import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.weeklyMeals.presenter.OnWeeklyMealsLoaded;

public interface IMealDatasource {
    void getWeeklyMeals(OnWeeklyMealsLoaded callback);

    void updateUserWeeklyMealsSaturday(String userId, Meal meal);

    void updateUserWeeklyMealsSunday(String userId, Meal meal);

    void updateUserWeeklyMealsMonday(String userId, Meal meal);

    void updateUserWeeklyMealsTuesday(String userId, Meal meal);

    void updateUserWeeklyMealsWednesday(String userId, Meal meal);


    void updateUserWeeklyMealsThursday(String userId, Meal meal);

    void updateUserWeeklyMealsFriday(String userId, Meal meal);

}
