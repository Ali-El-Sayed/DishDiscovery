package com.example.dishdiscovery.search.presenter;

import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRemoteRepo;
import com.example.dishdiscovery.search.view.ISearchView;

import java.util.List;

public class SearchPresenterImpl implements ISearchPresenter, ISearchNetworkCallBack {

    private final IMealsRemoteRepo _mealsRepo;
    private final ISearchView _searchView;

    public SearchPresenterImpl(IMealsRemoteRepo mealsRepo, ISearchView searchView) {
        _mealsRepo = mealsRepo;
        _searchView = searchView;
    }

    @Override
    public void getSearchResults(String searchQuery) {
        _mealsRepo.searchMealByName(searchQuery, this);
    }

    @Override
    public void onSearchResults(List<Meal> meals) {
        _searchView.showSearchResult(meals);
    }

    @Override
    public void onSearchError(String error) {
        _searchView.showErrorMessage(error);
    }
}
