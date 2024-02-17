package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.database.firebaseRealtime.model.RemoteUserWeeklyMeals;
import com.example.dishdiscovery.model.UserWeeklyMeals;

public interface IWeeklyMealsNetworkCall {
    public void onSuccess(UserWeeklyMeals meal);

    public void onError(String error);

}
