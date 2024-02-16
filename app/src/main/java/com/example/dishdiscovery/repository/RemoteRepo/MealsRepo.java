package com.example.dishdiscovery.repository.RemoteRepo;

import com.example.dishdiscovery.AllMeals.presenter.IFilterMealsNetworkCallBack;
import com.example.dishdiscovery.home.presenter.ICategoriesNetworkCall;
import com.example.dishdiscovery.home.presenter.IMealNetworkCall;
import com.example.dishdiscovery.network.Api.IMealRemoteDataSource;
import com.example.dishdiscovery.search.presenter.ISearchNetworkCallBack;

public class MealsRepo implements IMealsRepo {

    private static IMealsRepo _instance;
    IMealRemoteDataSource _remoteDataSource;

    MealsRepo(IMealRemoteDataSource remoteDataSource) {
        _remoteDataSource = remoteDataSource;
    }

    public static synchronized IMealsRepo getInstance(IMealRemoteDataSource remoteDataSource) {
        if (_instance == null)
            _instance = new MealsRepo(remoteDataSource);

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
}
