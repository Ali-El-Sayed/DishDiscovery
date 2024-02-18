package com.example.dishdiscovery.search.view;

import com.example.dishdiscovery.model.Meal;

import java.util.List;

public interface ISearchView {
    void showSearchResult(List<Meal> meals);

    void showErrorMessage(String error);
}
