package com.example.dishdiscovery.network.Api;

import com.example.dishdiscovery.network.data.CategoryResponse;
import com.example.dishdiscovery.network.data.FilterResponse;
import com.example.dishdiscovery.network.data.MealResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsService {

    @GET(END_POINTS.CATEGORIES)
    Call<CategoryResponse> getCategories();

    @GET(END_POINTS.RANDOM)
    Call<MealResponse> getRandomMeal();

    @GET(END_POINTS.LOOKUP)
    Call<MealResponse> getMealById(@Query("i") String mealId);

    @GET(END_POINTS.FILTER)
    Observable<FilterResponse> getMealsByCategoryName(@Query("c") String categoryName);
}
