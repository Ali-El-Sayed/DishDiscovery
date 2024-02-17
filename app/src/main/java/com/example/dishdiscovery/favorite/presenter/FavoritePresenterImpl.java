package com.example.dishdiscovery.favorite.presenter;

import com.example.dishdiscovery.favorite.view.IFavoriteView;
import com.example.dishdiscovery.model.UserFavMeals;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRepo;

public class FavoritePresenterImpl implements IFavoritePresenter, OnFavNetworkCallBack {

    private final IFavoriteView iFavoriteView;
    private final IMealsRepo iMealsRepo;

    public FavoritePresenterImpl(IFavoriteView iFavoriteView, IMealsRepo iMealsRepo) {
        this.iFavoriteView = iFavoriteView;
        this.iMealsRepo = iMealsRepo;
    }

    @Override
    public void getFavoriteMeals() {
        iMealsRepo.getFavoriteMeals(this);
    }

    @Override
    public void onSuccess(UserFavMeals userFavMeals) {
        iFavoriteView.DisplayFavoriteMeals(userFavMeals);
    }

    @Override
    public void onError(String message) {
        iFavoriteView.DisplayError(message);
    }
}
