package com.example.dishdiscovery.repository.RemoteRepo;

import com.example.dishdiscovery.AllMeals.presenter.IFilterMealsNetworkCallBack;
import com.example.dishdiscovery.database.firebaseRealtime.IFirebaseRealtime;
import com.example.dishdiscovery.database.sharedPreferences.ISharedPreferences;
import com.example.dishdiscovery.favorite.presenter.OnFavNetworkCallBack;
import com.example.dishdiscovery.home.presenter.ICategoriesNetworkCall;
import com.example.dishdiscovery.home.presenter.IMealNetworkCall;
import com.example.dishdiscovery.mealDetails.presenter.onSaveUserWeeklyMealsCallBack;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.network.Api.IMealRemoteDataSource;
import com.example.dishdiscovery.search.presenter.ISearchNetworkCallBack;

public class MealsRepo implements IMealsRepo {

    private static IMealsRepo _instance;
    private final IMealRemoteDataSource _remoteDataSource;
    private final IFirebaseRealtime _firebaseRealtime;
    private final ISharedPreferences _sharedPreferences;

    MealsRepo(IMealRemoteDataSource remoteDataSource, IFirebaseRealtime firebaseRealtime, ISharedPreferences sharedPreferences) {
        _remoteDataSource = remoteDataSource;
        _firebaseRealtime = firebaseRealtime;
        _sharedPreferences = sharedPreferences;
    }

    public static synchronized IMealsRepo getInstance(IMealRemoteDataSource remoteDataSource, IFirebaseRealtime firebaseRealtime, ISharedPreferences sharedPreferences) {
        if (_instance == null)
            _instance = new MealsRepo(remoteDataSource, firebaseRealtime, sharedPreferences);

        return _instance;
    }

    @Override
    public void getCategories(ICategoriesNetworkCall categoriesNetworkCall) {
        _remoteDataSource.getCategories(categoriesNetworkCall);
    }

    @Override
    public void getRandomMeal(IMealNetworkCall mealNetworkCall) {
        _remoteDataSource.getRandomMeal(mealNetworkCall);
    }

    @Override
    public void getMealById(String mealId, IMealNetworkCall mealNetworkCall) {
        _remoteDataSource.getMealById(mealId, mealNetworkCall);
    }

    @Override
    public void searchMealByName(String mealName, ISearchNetworkCallBack searchNetworkCallBack) {
        _remoteDataSource.searchMealByName(mealName, searchNetworkCallBack);
    }

    @Override
    public void getAllMealsByCategory(String category, IFilterMealsNetworkCallBack allMealsNetworkCallBack) {
        _remoteDataSource.getMealsByCategoryName(category, allMealsNetworkCallBack);
    }

    @Override
    public void getFavoriteMeals(OnFavNetworkCallBack onFavNetworkCallBack) {
        _firebaseRealtime.getUserFavoriteMeals(_sharedPreferences.getUserId(), onFavNetworkCallBack);
    }

    @Override
    public void saveUserWeeklyMeals(String dayOfTheWeek, Meal meal, onSaveUserWeeklyMealsCallBack callBack) {
        _firebaseRealtime.saveUserWeeklyMeals(_sharedPreferences.getUserId(), dayOfTheWeek, meal, callBack);
    }
}
