package com.example.dishdiscovery.favorite.view;

import com.example.dishdiscovery.model.UserLocalFavMeals;

import java.util.List;

public interface IFavoriteView {
    void displayLocalFavMeals(List<UserLocalFavMeals> userLocalFavMeals);

    void displayLocalMealsError(String error);
}
