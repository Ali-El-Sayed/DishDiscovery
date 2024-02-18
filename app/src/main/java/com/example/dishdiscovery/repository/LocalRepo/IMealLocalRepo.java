package com.example.dishdiscovery.repository.LocalRepo;

import com.example.dishdiscovery.favorite.presenter.OnFavLocalCallback;
import com.example.dishdiscovery.mealDetails.presenter.OnFavouriteCheckCallback;
import com.example.dishdiscovery.mealDetails.presenter.OnLoadFavMeal;
import com.example.dishdiscovery.mealDetails.presenter.onSaveUserWeeklyMealsCallBack;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.weeklyMeals.presenter.OnLocalWeeklyMeals;

public interface IMealLocalRepo {
    void loadWeeklyMeals(OnLocalWeeklyMeals callback);

    void saveUserWeeklyMeals(Meal meal, onSaveUserWeeklyMealsCallBack callback);

    void loadUserFavoriteMeals(OnFavLocalCallback callback);

    void isFavorite(String mealId, OnFavouriteCheckCallback callback);

    void saveUserFavoriteMeal(Meal meal, OnFavouriteCheckCallback callback);

    void deleteUserFavoriteMeal(String mealId, OnFavouriteCheckCallback callback);

    void getMealById(String mealId, OnLoadFavMeal callback);
}
