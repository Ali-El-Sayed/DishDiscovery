package com.example.dishdiscovery.mealDetails.presenter;

public interface OnFavouriteCheckCallback {
    void isFavorite(Boolean isFavorite);

    void onAddToFavSuccess();

    void onAddToFavError(String error);

    void onRemoveFavSuccess();

    void onRemoveFavError(String error);
}
