package com.example.dishdiscovery.network.Api;

import com.example.dishdiscovery.AllMeals.presenter.IFilterMealsNetworkCallBack;
import com.example.dishdiscovery.home.presenter.ICategoriesNetworkCall;
import com.example.dishdiscovery.home.presenter.IMealNetworkCall;
import com.example.dishdiscovery.search.presenter.ISearchNetworkCallBack;

public interface IMealRemoteDataSource {
    void getCategories(ICategoriesNetworkCall categoriesNetworkCall);

    void getRandomMeal(IMealNetworkCall mealNetworkCall);

    void getMealById(String id, IMealNetworkCall mealNetworkCall);


    void searchMealByName(String mealName, ISearchNetworkCallBack searchNetworkCallBack);

    void getMealsByCategoryName(String categoryId, IFilterMealsNetworkCallBack allMealsNetworkCallBack);

}
