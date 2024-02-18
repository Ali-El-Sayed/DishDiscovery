package com.example.dishdiscovery.mealDetails.presenter;

import android.util.Pair;

import com.example.dishdiscovery.home.presenter.IMealNetworkCall;
import com.example.dishdiscovery.mealDetails.view.IMealDetails;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserLocalFavMeals;
import com.example.dishdiscovery.repository.LocalRepo.IMealLocalRepo;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRemoteRepo;
import com.example.dishdiscovery.util.MealParser;

import java.util.List;

public class MealDetailsImpl implements IMealDetailsPresenter, IMealNetworkCall, OnFavouriteCheckCallback, onSaveUserWeeklyMealsCallBack, OnLoadFavMeal {

    private final IMealDetails _view;
    private final IMealsRemoteRepo _remoteRepo;
    private final IMealLocalRepo _localRepo;

    public MealDetailsImpl(IMealDetails view, IMealsRemoteRepo repo, IMealLocalRepo localRepo) {
        this._view = view;
        this._remoteRepo = repo;
        _localRepo = localRepo;
    }

    @Override
    public void getMealById(String mealId) {
        _remoteRepo.getMealById(mealId, this);
    }

    @Override
    public void saveUserWeeklyMeals(String dayOfTheWeek, Meal meal) {
        meal.dayOfTheWeek = dayOfTheWeek;
        _remoteRepo.saveUserWeeklyMeals(dayOfTheWeek, meal, this);
        _localRepo.saveUserWeeklyMeals(meal);
    }

    @Override
    public void checkIsFavorite(String mealId) {
        _localRepo.isFavorite(mealId, this);
    }

    @Override
    public void addToFavorites(Meal meal) {
        _localRepo.saveUserFavoriteMeal(meal, this);
    }

    @Override
    public void removeFromFavorites(String mealId) {
        _localRepo.deleteUserFavoriteMeal(mealId, this);
    }

    @Override
    public void getMealFromLocal(String mealId) {
        _localRepo.getMealById(mealId,this );
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

    @Override
    public void onSaveUserWeeklyMealsSuccess() {
        _view.onSaveUserWeeklyMealsSuccess();
    }

    @Override
    public void onSaveUserWeeklyMealsError(String error) {
        _view.onSaveUserWeeklyMealsError(error);
    }

    @Override
    public void isFavorite(Boolean isFavorite) {
        _view.isFavorite(isFavorite);

    }

    @Override
    public void onAddToFavSuccess() {
        _view.onSavedToFavSuccess();
    }

    @Override
    public void onAddToFavError(String error) {
        _view.onSavedToFavError(error);
    }

    @Override
    public void onRemoveFavSuccess() {
        _view.onRemoveFavSuccess();
    }

    @Override
    public void onRemoveFavError(String error) {
        _view.onRemoveFavError(error);
    }

    @Override
    public void onLoadFavMealsSuccess(UserLocalFavMeals userLocalFavMeals) {
        _view.displayMealFromLocal(userLocalFavMeals);
    }

    @Override
    public void onLoadFavMealsError(String message) {
        _view.showError(message);
    }
}
