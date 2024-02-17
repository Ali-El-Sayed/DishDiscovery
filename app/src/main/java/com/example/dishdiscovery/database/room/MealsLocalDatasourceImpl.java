package com.example.dishdiscovery.database.room;

import android.content.Context;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;
import com.example.dishdiscovery.database.sharedPreferences.ISharedPreferences;
import com.example.dishdiscovery.weeklyMeals.presenter.OnLocalWeeklyMeals;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsLocalDatasourceImpl implements IMealLocalDatasource {

    private MealsDao _mealsDao;
    private static MealsLocalDatasourceImpl _instance = null;

    private final ISharedPreferences _sharedPreferences;

    public static MealsLocalDatasourceImpl getInstance(Context context, ISharedPreferences sharedPreferences) {
        if (_instance == null) _instance = new MealsLocalDatasourceImpl(context, sharedPreferences);

        return _instance;
    }

    public MealsLocalDatasourceImpl(Context context, ISharedPreferences sharedPreferences) {
        _mealsDao = MealDatabase.getInstance(context).mealsDao();
        _sharedPreferences = sharedPreferences;

    }

    @Override
    public void getLocalWeeklyMeals(OnLocalWeeklyMeals callback) {
        _mealsDao.loadUserWeeklyMeals(_sharedPreferences.getUserId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(localWeeklyMeals -> {
                    callback.onLoadingSuccess(localWeeklyMeals);
                });
    }

    @Override
    public void saveUserWeeklyMeals(LocalWeeklyMeal localWeeklyMeals) {
        localWeeklyMeals.userId = _sharedPreferences.getUserId();
        _mealsDao.insertUserWeeklyMeals(localWeeklyMeals).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }
}
