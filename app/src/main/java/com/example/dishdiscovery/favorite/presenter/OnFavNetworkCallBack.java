package com.example.dishdiscovery.favorite.presenter;

import com.example.dishdiscovery.model.UserFavMeals;

public interface OnFavNetworkCallBack {

    void onSuccess(UserFavMeals userFavMeals);

    void onError(String message);
}
