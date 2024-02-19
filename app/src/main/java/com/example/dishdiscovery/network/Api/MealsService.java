package com.example.dishdiscovery.network.Api;

import com.example.dishdiscovery.network.data.CategoryResponse;
import com.example.dishdiscovery.network.data.FilterResponse;
import com.example.dishdiscovery.network.data.MealResponse;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsService {

    @GET(END_POINTS.CATEGORIES)
    Flowable<CategoryResponse> getCategories();

    @GET(END_POINTS.RANDOM)
    Flowable<MealResponse> getRandomMeal();

    @GET(END_POINTS.LOOKUP)
    Flowable<MealResponse> getMealById(@Query("i") String mealId);

    @GET(END_POINTS.FILTER)
    Observable<FilterResponse> getMealsByCategoryName(@Query("c") String categoryName);

    @GET(END_POINTS.SEARCH_BY_NAME)
    Observable<MealResponse> searchMealByName(@Query("s") String mealName);


}
