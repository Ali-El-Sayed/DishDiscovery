package com.example.dishdiscovery.search.view;

import com.example.dishdiscovery.model.FilteredMeal;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.network.data.FilterResponse;

import java.util.List;

public interface ISearchView {
    void showSearchResult(List<Meal> meals);
    void showErrorMessage(String error);
}
