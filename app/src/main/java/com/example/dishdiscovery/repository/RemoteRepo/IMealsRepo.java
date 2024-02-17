package com.example.dishdiscovery.repository.RemoteRepo;

import com.example.dishdiscovery.AllMeals.presenter.IFilterMealsNetworkCallBack;
import com.example.dishdiscovery.favorite.presenter.OnFavNetworkCallBack;
import com.example.dishdiscovery.home.presenter.ICategoriesNetworkCall;
import com.example.dishdiscovery.home.presenter.IMealNetworkCall;
import com.example.dishdiscovery.mealDetails.presenter.onSaveUserWeeklyMealsCallBack;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.search.presenter.ISearchNetworkCallBack;
import com.example.dishdiscovery.weeklyMeals.presenter.IWeeklyMealsNetworkCall;

public interface IMealsRepo {
    void getCategories(ICategoriesNetworkCall categoriesNetworkCall);

    void getRandomMeal(IMealNetworkCall mealNetworkCall);

    void getMealById(String mealId, IMealNetworkCall mealNetworkCall);

    void searchMealByName(String mealName, ISearchNetworkCallBack searchNetworkCallBack);

    void getAllMealsByCategory(String category, IFilterMealsNetworkCallBack allMealsNetworkCallBack);


    void getFavoriteMeals(OnFavNetworkCallBack onFavNetworkCallBack);

    void saveUserWeeklyMeals(String dayOfTheWeek, Meal meal, onSaveUserWeeklyMealsCallBack callBack);


}
