package com.example.dishdiscovery.database.room;

import android.content.Context;

import com.example.dishdiscovery.database.sharedPreferences.ISharedPreferences;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserWeeklyMeals;
import com.example.dishdiscovery.weeklyMeals.presenter.OnWeeklyMealsLoaded;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsLocalDatasourceImpl implements IMealDatasource {

    MealsDao mealsDao;
    private static MealsLocalDatasourceImpl _instance = null;
    private UserWeeklyMeals userWeeklyMeals;
    private ISharedPreferences sharedPreferences;

    public static MealsLocalDatasourceImpl getInstance(Context context, ISharedPreferences sharedPreferences) {
        if (_instance == null) _instance = new MealsLocalDatasourceImpl(context, sharedPreferences);
        return _instance;
    }

    public MealsLocalDatasourceImpl(Context context, ISharedPreferences sharedPreferences) {
        mealsDao = MealDatabase.getInstance(context).mealsDao();
        Disposable subscribe = mealsDao.loadUserWeeklyMeals(sharedPreferences.getUserId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(userWeeklyMeals -> {
            this.userWeeklyMeals = userWeeklyMeals;
        });
    }

    @Override
    public void getWeeklyMeals(OnWeeklyMealsLoaded callback) {
        callback.onWeeklyMealsLoaded(userWeeklyMeals);
    }


}
