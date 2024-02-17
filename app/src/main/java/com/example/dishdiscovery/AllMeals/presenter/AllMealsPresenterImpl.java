package com.example.dishdiscovery.AllMeals.presenter;

import com.example.dishdiscovery.AllMeals.view.IAllMealsView;
import com.example.dishdiscovery.model.FilteredMeal;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRemoteRepo;

import java.util.List;

public class AllMealsPresenterImpl implements IFilterMealsNetworkCallBack, IAllMealsPresenter {
    private final IAllMealsView _view;
    private final IMealsRemoteRepo _mealsRepo;

    public AllMealsPresenterImpl(IAllMealsView view, IMealsRemoteRepo mealsRepo) {
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
