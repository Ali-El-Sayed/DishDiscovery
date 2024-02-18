package com.example.dishdiscovery.database.db;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.dishdiscovery.database.sharedPreferences.ISharedPreferences;
import com.example.dishdiscovery.favorite.presenter.OnFavLocalCallback;
import com.example.dishdiscovery.mealDetails.presenter.OnFavouriteCheckCallback;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserLocalFavMeals;
import com.example.dishdiscovery.weeklyMeals.presenter.OnLocalWeeklyMeals;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsLocalDatasourceImpl implements IMealLocalDatasource {

    private static MealsLocalDatasourceImpl _instance = null;
    private final ISharedPreferences _sharedPreferences;
    private final MealsDao _mealsDao;

    public MealsLocalDatasourceImpl(Context context, ISharedPreferences sharedPreferences) {
        _mealsDao = MealDatabase.getInstance(context).mealsDao();
        _sharedPreferences = sharedPreferences;

    }

    public static MealsLocalDatasourceImpl getInstance(Context context, ISharedPreferences sharedPreferences) {
        if (_instance == null) _instance = new MealsLocalDatasourceImpl(context, sharedPreferences);

        return _instance;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getLocalWeeklyMeals(OnLocalWeeklyMeals callback) {
        _mealsDao.loadUserWeeklyMeals(_sharedPreferences.getUserId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback::onLoadingSuccess);
    }

    @Override
    public void saveUserWeeklyMeals(Meal meal) {
        meal.userId = _sharedPreferences.getUserId();
        _mealsDao.insertUserWeeklyMeals(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteUserFavoriteMeal(String mealId, OnFavouriteCheckCallback callback) {
        String userId = _sharedPreferences.getUserId();
        _mealsDao.deleteUserFavMeals(userId, mealId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback::onRemoveFavSuccess, throwable -> {
            callback.onRemoveFavError(throwable.getMessage());
        });
    }

    @Override
    public void saveUserFavoriteMeal(UserLocalFavMeals userLocalFavMeals, OnFavouriteCheckCallback callback) {
        userLocalFavMeals.userId = _sharedPreferences.getUserId();
        Disposable subscribe = _mealsDao.insertUserFavMeal(userLocalFavMeals).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback::onAddToFavSuccess, throwable -> {
                    callback.onAddToFavError(throwable.getMessage());
                }
        );
    }

    @SuppressLint("CheckResult")
    @Override
    public void getUserFavoriteMeals(OnFavLocalCallback callback) {
        _mealsDao.loadUserFavMeals(_sharedPreferences.getUserId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback::onLoadFavMealsSuccess);
    }

    @SuppressLint("CheckResult")
    @Override
    public void isFavorite(String mealId, OnFavouriteCheckCallback callback) {
        _mealsDao.isFavoriteMeal(_sharedPreferences.getUserId(), mealId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(userLocalFavMeals -> {
            if (userLocalFavMeals != null) callback.isFavorite(true);
            else callback.isFavorite(false);
        }, throwable -> {
            callback.isFavorite(false);
        });
    }
}
