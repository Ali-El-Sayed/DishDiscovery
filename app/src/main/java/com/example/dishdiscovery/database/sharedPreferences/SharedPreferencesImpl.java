package com.example.dishdiscovery.database.sharedPreferences;

import static com.example.dishdiscovery.util.CONSTANTS.MEAL_OF_THE_DAY;
import static com.example.dishdiscovery.util.CONSTANTS.SHARED_PREFS;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesImpl implements ISharedPreferences {

    private static ISharedPreferences _instance;
    private static SharedPreferences _sharedPreferences;

    private SharedPreferencesImpl(Context context) {
        _sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
    }

    public static synchronized ISharedPreferences getInstance(Context context) {
        if (_instance == null) _instance = new SharedPreferencesImpl(context);

        return _instance;
    }

    @Override
    public void SaveMealId(String mealId) {
        SharedPreferences.Editor editor = _sharedPreferences.edit();
        editor.putString(MEAL_OF_THE_DAY, mealId);
        editor.apply();
    }

    @Override
    public String GetMealId() {
        return _sharedPreferences.getString(MEAL_OF_THE_DAY, "");
    }
}