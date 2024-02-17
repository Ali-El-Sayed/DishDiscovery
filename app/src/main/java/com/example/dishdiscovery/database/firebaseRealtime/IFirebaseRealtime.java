package com.example.dishdiscovery.database.firebaseRealtime;

import com.example.dishdiscovery.favorite.presenter.OnFavNetworkCallBack;
import com.example.dishdiscovery.mealDetails.presenter.onSaveUserWeeklyMealsCallBack;
import com.example.dishdiscovery.model.Meal;

public interface IFirebaseRealtime {

    void saveUserWeeklyMeals(String userId, String dayOfWeek, Meal meal, onSaveUserWeeklyMealsCallBack callBack);

    void getUserWeeklyMeals(String userId);


    void deleteUserWeeklyMeal(String userId, String dayOfWeek);


    void saveUserFavoriteMeals(String userId, String mealId);


    void deleteUserFavoriteMeals(String userId, String mealId);

    void getUserFavoriteMeals(String userId, OnFavNetworkCallBack onFavNetworkCallBack);

    void verifyUserFavoriteMealsCreated(String userId);

}
