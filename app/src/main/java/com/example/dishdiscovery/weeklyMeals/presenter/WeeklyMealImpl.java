package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserWeeklyMeals;
import com.example.dishdiscovery.repository.LocalRepo.IMealLocalRepo;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRemoteRepo;
import com.example.dishdiscovery.weeklyMeals.view.IWeeklyMealsView;

import java.util.List;

public class WeeklyMealImpl implements IWeeklyMealsPresenter, IWeeklyMealsNetworkCall, OnLocalWeeklyMeals {
    private final IWeeklyMealsView _view;
    private final IMealsRemoteRepo _remoteRepo;
    private final IMealLocalRepo _localRepo;

    public WeeklyMealImpl(IWeeklyMealsView view, IMealsRemoteRepo repo, IMealLocalRepo localRepo) {
        this._view = view;
        this._remoteRepo = repo;
        _localRepo = localRepo;
    }

    @Override
    public void getUserWeeklyMeal(String mealId) {
//        _repo.getWeeklyMealById(mealId, this);
    }

    @Override
    public void saveUserWeeklyMeal(Meal localWeeklyMeal) {
        _localRepo.saveUserWeeklyMeals(localWeeklyMeal);
    }

    @Override
    public void onRemoteSuccess(UserWeeklyMeals meal) {
//        _view.showWeeklyMeals(meal);
    }

    @Override
    public void onRemoteError(String error) {
        _view.showError(error);
    }


    @Override
    public void onLoadingSuccess(List<Meal> meals) {
        _view.showWeeklyMeals(meals);
    }

    @Override
    public void onLoadingError(String message) {
        _view.showError(message);
    }

    @Override
    public void onSaveSuccess() {
        _view.onSavedSuccess();
    }
}
