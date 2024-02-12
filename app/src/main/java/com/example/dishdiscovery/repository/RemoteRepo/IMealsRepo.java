package com.example.dishdiscovery.repository.RemoteRepo;

import com.example.dishdiscovery.home.presenter.ICategoriesNetworkCall;
import com.example.dishdiscovery.home.presenter.IMealNetworkCall;

public interface IMealsRepo {
    void getCategories(ICategoriesNetworkCall categoriesNetworkCall);
    void getRandomMeal(IMealNetworkCall mealNetworkCall);

    void getMealById(String mealId, IMealNetworkCall mealNetworkCall);

}
