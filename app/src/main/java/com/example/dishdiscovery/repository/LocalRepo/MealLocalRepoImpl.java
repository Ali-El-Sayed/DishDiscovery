package com.example.dishdiscovery.repository.LocalRepo;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;
import com.example.dishdiscovery.database.room.IMealLocalDatasource;
import com.example.dishdiscovery.weeklyMeals.presenter.OnLocalWeeklyMeals;

public class MealLocalRepoImpl implements IMealLocalRepo {

    private static MealLocalRepoImpl _instance = null;
    private final IMealLocalDatasource _mealsLocalDatasource;

    private MealLocalRepoImpl(IMealLocalDatasource mealsLocalDatasource) {
        _mealsLocalDatasource = mealsLocalDatasource;
    }

    public static MealLocalRepoImpl getInstance(IMealLocalDatasource mealsLocalDatasource) {
        if (_instance == null)
            _instance = new MealLocalRepoImpl(mealsLocalDatasource);
        return _instance;
    }


    @Override
    public void loadWeeklyMeals(OnLocalWeeklyMeals callback) {
        _mealsLocalDatasource.getLocalWeeklyMeals(callback);
    }

    @Override
    public void saveUserWeeklyMeals(LocalWeeklyMeal localWeeklyMeals) {
        _mealsLocalDatasource.saveUserWeeklyMeals(localWeeklyMeals);
    }
}
