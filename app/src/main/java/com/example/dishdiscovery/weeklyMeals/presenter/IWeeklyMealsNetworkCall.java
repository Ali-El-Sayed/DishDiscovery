package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.model.UserWeeklyMeals;

public interface IWeeklyMealsNetworkCall {
     void onRemoteSuccess(UserWeeklyMeals meal);

     void onRemoteError(String error);

}
