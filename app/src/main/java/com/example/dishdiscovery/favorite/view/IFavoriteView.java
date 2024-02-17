package com.example.dishdiscovery.favorite.view;

import com.example.dishdiscovery.model.UserFavMeals;

public interface IFavoriteView {
    void DisplayFavoriteMeals(UserFavMeals userFavMeals);

    void DisplayError(String message);
}
