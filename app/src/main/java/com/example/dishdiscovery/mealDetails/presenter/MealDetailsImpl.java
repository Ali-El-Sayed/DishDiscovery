package com.example.dishdiscovery.mealDetails.presenter;

import android.util.Pair;

import com.example.dishdiscovery.home.presenter.IMealNetworkCall;
import com.example.dishdiscovery.mealDetails.view.IMealDetails;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRepo;
import com.example.dishdiscovery.util.MealParser;

import java.util.List;

public class MealDetailsImpl implements IMealDetailsPresenter, IMealNetworkCall {

    private final IMealDetails _view;
    private final IMealsRepo _repo;

    public MealDetailsImpl(IMealDetails view, IMealsRepo repo) {
        this._view = view;
        this._repo = repo;
    }

    @Override
    public void getMealById(String mealId) {
        _repo.getMealById(mealId, this);
    }

    @Override
    public void onSuccess(Meal meal) {
        Pair<List<String>, List<String>> ingredientsAndMeasurements = MealParser.parseMeal(meal);
        meal.setIngredients(ingredientsAndMeasurements.first);
        meal.setMeasurements(ingredientsAndMeasurements.second);
        _view.showMealDetails(meal);
    }

    @Override
    public void onError(String error) {
        _view.showError(error);
    }
}
