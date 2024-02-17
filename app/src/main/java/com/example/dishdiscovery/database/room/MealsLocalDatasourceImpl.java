package com.example.dishdiscovery.database.room;

import android.content.Context;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;
import com.example.dishdiscovery.weeklyMeals.presenter.OnWeeklyMealsLoaded;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsLocalDatasourceImpl implements IMealDatasource {

    MealsDao mealsDao;
    private static MealsLocalDatasourceImpl _instance = null;
    private List<LocalWeeklyMeal> _localWeeklyMeals;

    public static MealsLocalDatasourceImpl getInstance(Context context) {
        if (_instance == null) _instance = new MealsLocalDatasourceImpl(context);
        return _instance;
    }

    public MealsLocalDatasourceImpl(Context context) {
        mealsDao = MealDatabase.getInstance(context).mealsDao();
    }

    @Override
    public void getWeeklyMeals(OnWeeklyMealsLoaded callback) {
        callback.onWeeklyMealsLoaded(_localWeeklyMeals);
    }


    @Override
    public void checkIfUserHasWeeklyMeals(String userId) {
        Disposable subscribe = mealsDao.loadUserWeeklyMeals(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(userWeeklyMeals -> {
                    this. _localWeeklyMeals= userWeeklyMeals;
                });
    }


}
