package com.example.dishdiscovery.AllMeals.presenter;

import com.example.dishdiscovery.model.FilteredMeal;

import java.util.List;

public interface IFilterMealsNetworkCallBack {
    void onSuccess(List<FilteredMeal> allMeals);

    void onFailure(String message);
}
