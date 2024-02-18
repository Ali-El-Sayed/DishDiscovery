package com.example.dishdiscovery.database.firebaseRealtime;

import com.example.dishdiscovery.favorite.presenter.OnFavNetworkCallback;
import com.example.dishdiscovery.model.Meal;

import io.reactivex.rxjava3.core.Completable;

public interface IFirebaseRealtimeDataSource {

    Completable saveUserWeeklyMeals(String userId, Meal meal);

    void getUserWeeklyMeals(String userId);


    void deleteUserWeeklyMeal(String userId, String dayOfWeek);


    void saveUserFavoriteMeals(String userId, String mealId);


    void deleteUserFavoriteMeals(String userId, String mealId);

    void getUserFavoriteMeals(String userId, OnFavNetworkCallback onFavNetworkCallBack);

    void verifyUserFavoriteMealsCreated(String userId);

}
