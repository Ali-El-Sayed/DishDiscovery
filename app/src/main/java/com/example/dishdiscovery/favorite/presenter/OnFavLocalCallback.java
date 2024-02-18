package com.example.dishdiscovery.favorite.presenter;

import com.example.dishdiscovery.model.UserLocalFavMeals;

import java.util.List;

public interface OnFavLocalCallback {

    void onLoadFavMealsSuccess(List<UserLocalFavMeals> userLocalFavMeals);

    void onLoadFavMealsError(String message);


}
