package com.example.dishdiscovery.AllMeals.presenter;

import com.example.dishdiscovery.AllMeals.view.IAllMealsView;
import com.example.dishdiscovery.model.FilteredMeal;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.network.data.FilterResponse;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRepo;

import java.util.List;

public class AllMealsPresenterImpl implements IFilterMealsNetworkCallBack, IAllMealsPresenter {
    private final IAllMealsView _view;
    private final IMealsRepo _mealsRepo;

    public AllMealsPresenterImpl(IAllMealsView view, IMealsRepo mealsRepo) {
        _view = view;
        _mealsRepo = mealsRepo;
    }


    @Override
    public void onSuccess(List<FilteredMeal> allMeals) {
        _view.displayAllMeals(allMeals);
    }

    @Override
    public void onFailure(String message) {
        _view.showError(message);
    }

    @Override
    public void getAllMealsByCategory(String category) {
        _mealsRepo.getAllMealsByCategory(category, this);
    }
}
