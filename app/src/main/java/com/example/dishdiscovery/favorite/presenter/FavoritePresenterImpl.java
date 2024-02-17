package com.example.dishdiscovery.favorite.presenter;

import com.example.dishdiscovery.favorite.view.IFavoriteView;
import com.example.dishdiscovery.model.UserFavMeals;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRemoteRepo;

public class FavoritePresenterImpl implements IFavoritePresenter, OnFavNetworkCallBack {

    private final IFavoriteView iFavoriteView;
    private final IMealsRemoteRepo mIMealsRemoteRepo;

    public FavoritePresenterImpl(IFavoriteView iFavoriteView, IMealsRemoteRepo iMealsRemoteRepo) {
        this.iFavoriteView = iFavoriteView;
        this.mIMealsRemoteRepo = iMealsRemoteRepo;
    }

    @Override
    public void getFavoriteMeals() {
        mIMealsRemoteRepo.getFavoriteMeals(this);
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
