package com.example.dishdiscovery.home.presenter;

import com.example.dishdiscovery.model.Category;

import java.util.List;

public interface ICategoriesNetworkCall {
    void onSuccess(List<Category> response);

    void onError(String error);

}
