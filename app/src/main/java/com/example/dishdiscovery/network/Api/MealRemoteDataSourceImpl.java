package com.example.dishdiscovery.network.Api;

import android.util.Log;

import com.example.dishdiscovery.home.presenter.ICategoriesNetworkCall;
import com.example.dishdiscovery.home.presenter.IMealNetworkCall;
import com.example.dishdiscovery.model.Category;
import com.example.dishdiscovery.network.data.CategoryResponse;
import com.example.dishdiscovery.network.data.MealResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImpl implements IMealRemoteDataSource {
    private static final String TAG = "MealRemoteDataSourceImpl";
    private MealsService mealsService;
    private static MealRemoteDataSourceImpl instance;

    private MealRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(END_POINTS.BASE_URL).build();
        mealsService = retrofit.create(MealsService.class);
    }

    public static synchronized MealRemoteDataSourceImpl getInstance() {
        if (instance == null) instance = new MealRemoteDataSourceImpl();

        return instance;
    }

    @Override
    public void getCategories(ICategoriesNetworkCall categoriesNetworkCall) {
        Log.i(TAG, "getCategories: Called ");
        mealsService.getCategories().enqueue(new retrofit2.Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, retrofit2.Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    List<Category> categories = response.body().getCategories();
                    categoriesNetworkCall.onSuccess(categories);
                } else {
                    categoriesNetworkCall.onError("Error occurred");
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                categoriesNetworkCall.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getRandomMeal(IMealNetworkCall mealNetworkCall) {
        mealsService.getRandomMeal().enqueue(new retrofit2.Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, retrofit2.Response<MealResponse> response) {
                if (response.isSuccessful()) {
                    mealNetworkCall.onSuccess(response.body().getMeals().get(0));
                } else {
                    mealNetworkCall.onError("Error occurred");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                mealNetworkCall.onError(t.getMessage());
            }
        });
    }

    @Override
    public void getMealById(String id, IMealNetworkCall mealNetworkCall) {
        mealsService.getMealById(id).enqueue(new retrofit2.Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, retrofit2.Response<MealResponse> response) {
                if (response.isSuccessful()) {
                    mealNetworkCall.onSuccess(response.body().getMeals().get(0));
                } else {
                    mealNetworkCall.onError("Error occurred");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                mealNetworkCall.onError(t.getMessage());
            }
        });
    }
}
