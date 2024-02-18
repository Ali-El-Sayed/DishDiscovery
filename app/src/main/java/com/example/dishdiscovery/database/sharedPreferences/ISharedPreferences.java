package com.example.dishdiscovery.database.sharedPreferences;

public interface ISharedPreferences {

    void SaveMealId(String mealId);

    String GetMealId();

    void saveUserId(String userId);

    Boolean isUserLoggedIn();

    String getUserId();

    void deleteUserId();
}
