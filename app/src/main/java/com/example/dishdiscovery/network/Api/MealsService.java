package com.example.dishdiscovery.network.Api;

import com.example.dishdiscovery.network.data.CategoryResponse;
import com.example.dishdiscovery.network.data.FilterResponse;
import com.example.dishdiscovery.network.data.MealResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsService {

    @GET(END_POINTS.CATEGORIES)
    Single<CategoryResponse> getCategories();

    @GET(END_POINTS.RANDOM)
    Single<MealResponse> getRandomMeal();

    @GET(END_POINTS.LOOKUP)
    Single<MealResponse> getMealById(@Query("i") String mealId);

    @GET(END_POINTS.FILTER)
    Single<FilterResponse> getMealsByCategoryName(@Query("c") String categoryName);

    @GET(END_POINTS.SEARCH_BY_NAME)
    Single<MealResponse> searchMealByName(@Query("s") String mealName);


}
