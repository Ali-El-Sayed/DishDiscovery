package com.example.dishdiscovery.database.db;

import com.example.dishdiscovery.favorite.presenter.OnFavLocalCallback;
import com.example.dishdiscovery.mealDetails.presenter.OnFavouriteCheckCallback;
import com.example.dishdiscovery.mealDetails.presenter.OnLoadFavMeal;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserLocalFavMeals;
import com.example.dishdiscovery.weeklyMeals.presenter.OnLocalWeeklyMeals;

public interface IMealLocalDatasource {
    void getLocalWeeklyMeals(OnLocalWeeklyMeals callback);


    void saveUserWeeklyMeals(Meal meal);


    void deleteUserFavoriteMeal(String mealId,OnFavouriteCheckCallback callback);

    void saveUserFavoriteMeal(UserLocalFavMeals userLocalFavMeals,OnFavouriteCheckCallback callback);

    void getUserFavoriteMeals(OnFavLocalCallback callback);

    void isFavorite(String mealId, OnFavouriteCheckCallback callback);

    void getMealById(String mealId, OnLoadFavMeal callback);
}
