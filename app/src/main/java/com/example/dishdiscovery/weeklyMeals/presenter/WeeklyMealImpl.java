package com.example.dishdiscovery.weeklyMeals.presenter;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;
import com.example.dishdiscovery.model.UserWeeklyMeals;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRemoteRepo;
import com.example.dishdiscovery.weeklyMeals.view.IWeeklyMealsView;

import java.util.List;

public class WeeklyMealImpl implements IWeeklyMealsPresenter, IWeeklyMealsNetworkCall, OnWeeklyMealsLoaded {
    private final IWeeklyMealsView _view;
    private final IMealsRemoteRepo _repo;

    public WeeklyMealImpl(IWeeklyMealsView view, IMealsRemoteRepo repo) {
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
    public void onWeeklyMealsLoaded(List<LocalWeeklyMeal> meals) {
//        _view.showWeeklyMeals(meals);
    }

    @Override
    public void onWeeklyMealsLoadFailed(String message) {

    }
}
