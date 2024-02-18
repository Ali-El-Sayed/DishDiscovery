package com.example.dishdiscovery.favorite.presenter;

import com.example.dishdiscovery.favorite.view.IFavoriteView;
import com.example.dishdiscovery.model.UserLocalFavMeals;
import com.example.dishdiscovery.model.UserRemoteFavMeals;
import com.example.dishdiscovery.repository.LocalRepo.IMealLocalRepo;
import com.example.dishdiscovery.repository.RemoteRepo.IMealsRemoteRepo;

import java.util.List;

public class FavoritePresenterImpl implements IFavoritePresenter, OnFavNetworkCallback, OnFavLocalCallback {

    private final IFavoriteView iFavoriteView;
    private final IMealsRemoteRepo mIMealsRemoteRepo;
    private final IMealLocalRepo _iMealLocalRepo;

    public FavoritePresenterImpl(IFavoriteView iFavoriteView, IMealsRemoteRepo iMealsRemoteRepo, IMealLocalRepo mIMealLocalRepo) {
        this.iFavoriteView = iFavoriteView;
        this.mIMealsRemoteRepo = iMealsRemoteRepo;
        this._iMealLocalRepo = mIMealLocalRepo;
    }



    @Override
    public void onSuccess(UserRemoteFavMeals userRemoteFavMeals) {
//        iFavoriteView.displayLocalFavMeals(userRemoteFavMeals);
    }

    @Override
    public void onLoadFavMealsSuccess(List<UserLocalFavMeals> userLocalFavMeals) {
        iFavoriteView.displayLocalFavMeals(userLocalFavMeals);
    }

    @Override
    public void onLoadFavMealsError(String message) {
        iFavoriteView.displayLocalMealsError(message);
    }

    @Override
    public void onError(String message) {
//        iFavoriteView.DisplayError(message);
    }

    @Override
    public void getLocalFavMeals() {
        _iMealLocalRepo.loadUserFavoriteMeals(this);
    }
}
