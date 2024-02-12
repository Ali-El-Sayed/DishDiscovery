package com.example.dishdiscovery.network.Api;

import com.example.dishdiscovery.home.presenter.ICategoriesNetworkCall;
import com.example.dishdiscovery.home.presenter.IMealNetworkCall;

public interface IMealRemoteDataSource {
    void getCategories(ICategoriesNetworkCall categoriesNetworkCall);

    void getRandomMeal(IMealNetworkCall mealNetworkCall);

    void getMealById(String id, IMealNetworkCall mealNetworkCall);

}
