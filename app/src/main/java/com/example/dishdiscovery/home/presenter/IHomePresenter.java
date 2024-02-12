package com.example.dishdiscovery.home.presenter;

public interface IHomePresenter {
    void getCategories();

    void getRandomMeal();

    void getMealById(String mealId);
}
