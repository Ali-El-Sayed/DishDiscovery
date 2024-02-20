package com.example.dishdiscovery.database.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.example.dishdiscovery.database.sharedPreferences.ISharedPreferences;
import com.example.dishdiscovery.favorite.presenter.OnFavLocalCallback;
import com.example.dishdiscovery.mealDetails.presenter.OnFavouriteCheckCallback;
import com.example.dishdiscovery.mealDetails.presenter.OnLoadFavMeal;
import com.example.dishdiscovery.mealDetails.presenter.onSaveUserWeeklyMealsCallBack;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserLocalFavMeals;
import com.example.dishdiscovery.weeklyMeals.presenter.OnLocalWeeklyMeals;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsLocalDatasourceImpl implements IMealLocalDatasource {
    private static final String TAG = "MealsLocalDatasourceImp";
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
        if (_sharedPreferences.getUserId() == null || _sharedPreferences.getUserId().isEmpty()) {
            callback.onLoadingError("PLEASE LOGIN");
        } else {
            _mealsDao.loadUserWeeklyMeals(_sharedPreferences.getUserId())
                    .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(callback::onLoadingSuccess
                            , throwable -> callback.onLoadingError(throwable.getMessage())
                    );
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveUserWeeklyMeals(Meal meal, onSaveUserWeeklyMealsCallBack callback) {
        if (_sharedPreferences.getUserId() == null || _sharedPreferences.getUserId().isEmpty()) {
            callback.onSaveUserWeeklyMealsError("PLEASE LOGIN");
        } else {
            meal.userId = _sharedPreferences.getUserId();
            _mealsDao.insertUserWeeklyMeals(meal).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                    callback::onSaveUserWeeklyMealsSuccess,
                    throwable -> callback.onSaveUserWeeklyMealsError(throwable.getMessage())
            );
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void deleteUserFavoriteMeal(String mealId, OnFavouriteCheckCallback callback) {
        if (_sharedPreferences.getUserId() == null || _sharedPreferences.getUserId().isEmpty()) {
            callback.onRemoveFavError("User not logged in");
        } else {
            String userId = _sharedPreferences.getUserId();
            _mealsDao.deleteUserFavMeals(userId, mealId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback::onRemoveFavSuccess, throwable -> {
                callback.onRemoveFavError(throwable.getMessage());
            });
        }
    }

    @Override
    public void saveUserFavoriteMeal(UserLocalFavMeals userLocalFavMeals, OnFavouriteCheckCallback callback) {
        if (_sharedPreferences.getUserId() == null || _sharedPreferences.getUserId().isEmpty()) {
            callback.onAddToFavError("User not logged in");
        } else {
            userLocalFavMeals.userId = _sharedPreferences.getUserId();
            _mealsDao.insertUserFavMeals(userLocalFavMeals).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback::onAddToFavSuccess, throwable -> {
                callback.onAddToFavError(throwable.getMessage());
            });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void getUserFavoriteMeals(OnFavLocalCallback callback) {
        if (_sharedPreferences.getUserId() == null || _sharedPreferences.getUserId().isEmpty()) {
            callback.onLoadFavMealsError("User not logged in");
        } else {
            _mealsDao.loadUserFavMeals(_sharedPreferences.getUserId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback::onLoadFavMealsSuccess, throwable -> callback.onLoadFavMealsError(throwable.getMessage()));
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void isFavorite(String mealId, OnFavouriteCheckCallback callback) {
        if (_sharedPreferences.getUserId() == null || _sharedPreferences.getUserId().isEmpty()) {
            callback.isFavorite(false);
        } else {
            _mealsDao.isFavoriteMeal(_sharedPreferences.getUserId(), mealId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(userLocalFavMeals -> {
                callback.isFavorite(userLocalFavMeals != null);
            }, throwable -> {
                callback.isFavorite(false);
            });
        }
    }

    @Override
    public void getMealById(String mealId, OnLoadFavMeal callback) {
        if (_sharedPreferences.getUserId() == null || _sharedPreferences.getUserId().isEmpty()) {
            callback.onLoadFavMealsError("User not logged in");
        } else {
            _mealsDao.getMealById(mealId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback::onLoadFavMealsSuccess, throwable -> {
                callback.onLoadFavMealsError(throwable.getMessage());
            });
        }
    }
}
