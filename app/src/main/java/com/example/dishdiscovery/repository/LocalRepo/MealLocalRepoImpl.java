package com.example.dishdiscovery.repository.LocalRepo;

import com.example.dishdiscovery.database.db.IMealLocalDatasource;
import com.example.dishdiscovery.favorite.presenter.OnFavLocalCallback;
import com.example.dishdiscovery.mealDetails.presenter.OnFavouriteCheckCallback;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserLocalFavMeals;
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
    public void saveUserWeeklyMeals(Meal meal) {
        _mealsLocalDatasource.saveUserWeeklyMeals(meal);
    }

    @Override
    public void loadUserFavoriteMeals(OnFavLocalCallback callback) {
        _mealsLocalDatasource.getUserFavoriteMeals(callback);
    }

    @Override
    public void isFavorite(String mealId, OnFavouriteCheckCallback callback) {
        _mealsLocalDatasource.isFavorite(mealId, callback);
    }

    @Override
    public void saveUserFavoriteMeal(Meal meal, OnFavouriteCheckCallback callback) {
        _mealsLocalDatasource.saveUserFavoriteMeal(new UserLocalFavMeals(meal), callback);
    }

    @Override
    public void deleteUserFavoriteMeal(String mealId, OnFavouriteCheckCallback callback) {
        _mealsLocalDatasource.deleteUserFavoriteMeal(mealId, callback);
    }
}
