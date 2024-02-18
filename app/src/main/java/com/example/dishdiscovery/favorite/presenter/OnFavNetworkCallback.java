package com.example.dishdiscovery.favorite.presenter;

import com.example.dishdiscovery.model.UserRemoteFavMeals;

public interface OnFavNetworkCallback {

    void onSuccess(UserRemoteFavMeals userRemoteFavMeals);

    void onError(String message);
}
