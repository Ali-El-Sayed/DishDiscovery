package com.example.dishdiscovery.home.presenter;

import com.example.dishdiscovery.model.Meal;

public interface IMealNetworkCall {
    void onSuccess(Meal meal);

    void onError(String error);
}
