package com.example.dishdiscovery.search.presenter;

import com.example.dishdiscovery.model.Meal;

import java.util.List;

public interface ISearchNetworkCallBack {
    void onSearchResults(List<Meal> meals);
    void onSearchError(String error);
}
