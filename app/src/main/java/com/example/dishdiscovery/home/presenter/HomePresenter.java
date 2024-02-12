package com.example.dishdiscovery.home.presenter;

import com.example.dishdiscovery.home.view.IHome;
import com.example.dishdiscovery.model.Category;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRepo;

import java.util.List;

public class HomePresenter implements IHomePresenter, ICategoriesNetworkCall, IMealNetworkCall {
    private final IHome _view;
    private final IMealsRepo _repo;

    public HomePresenter(IHome view, IMealsRepo repo) {
        this._view = view;
        this._repo = repo;
    }

    @Override
    public void getCategories() {
        _repo.getCategories(this);
    }

    @Override
    public void getRandomMeal() {
        _repo.getRandomMeal(this);
    }

    @Override
    public void getMealById(String mealId) {
        _repo.getMealById(mealId, this);
    }

    @Override
    public void onSuccess(List<Category> response) {
        _view.showCategories(response);
    }

    @Override
    public void onSuccess(Meal meal) {
        _view.showMealOfTheDay(meal);
    }

    @Override
    public void onError(String error) {
        _view.showError(error);
    }
}
