package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.model.UserWeeklyMeals;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRepo;
import com.example.dishdiscovery.weeklyMeals.view.IWeeklyMealsView;

public class WeeklyMealImpl implements IWeeklyMealsPresenter, IWeeklyMealsNetworkCall, OnWeeklyMealsLoaded {
    private final IWeeklyMealsView _view;
    private final IMealsRepo _repo;

    public WeeklyMealImpl(IWeeklyMealsView view, IMealsRepo repo) {
        this._view = view;
        this._repo = repo;
    }

    @Override
    public void getUserWeeklyMeal(String mealId) {
//        _repo.getWeeklyMealById(mealId, this);
    }

    @Override
    public void onSuccess(UserWeeklyMeals meal) {
        _view.showWeeklyMeals(meal);
    }

    @Override
    public void onError(String error) {
        _view.showError(error);
    }

    @Override
    public void onWeeklyMealsLoaded(UserWeeklyMeals meals) {
        _view.showWeeklyMeals(meals);
    }

    @Override
    public void onWeeklyMealsLoadFailed(String message) {

    }
}
