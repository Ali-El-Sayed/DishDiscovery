package com.example.dishdiscovery.network.Api;

import android.util.Log;

import com.example.dishdiscovery.AllMeals.presenter.IFilterMealsNetworkCallBack;
import com.example.dishdiscovery.home.presenter.ICategoriesNetworkCall;
import com.example.dishdiscovery.home.presenter.IMealNetworkCall;
import com.example.dishdiscovery.model.Category;
import com.example.dishdiscovery.network.data.CategoryResponse;
import com.example.dishdiscovery.network.data.MealResponse;
import com.example.dishdiscovery.search.presenter.ISearchNetworkCallBack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealRemoteDataSourceImpl implements IMealRemoteDataSource {
    private static final String TAG = "MealRemoteDataSourceImpl";
    private MealsService mealsService;
    private static MealRemoteDataSourceImpl instance;

    private MealRemoteDataSourceImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor()) // Add the interceptor here
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(END_POINTS.BASE_URL).build();
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

    @Override
    public void searchMealByName(String mealName, ISearchNetworkCallBack searchNetworkCallBack) {
        Disposable subscribe = mealsService.searchMealByName(mealName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealResponse -> searchNetworkCallBack.onSearchResults(mealResponse.getMeals()),
                        throwable -> searchNetworkCallBack.onSearchError(throwable.getMessage())
                );
    }

    @Override
    public void getMealsByCategoryName(String categoryName, IFilterMealsNetworkCallBack iFilterMealsNetworkCallBack) {
        Disposable subscribe = mealsService.getMealsByCategoryName(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        allMeals -> {
                            if (allMeals.getMeals() == null) {
                                iFilterMealsNetworkCallBack.onSuccess(new ArrayList<>());

                            } else
                                iFilterMealsNetworkCallBack.onSuccess(allMeals.getMeals());
                        },
                        throwable -> iFilterMealsNetworkCallBack.onFailure(throwable.getMessage())
                );
    }

    private class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(chain.request());

            Log.i(TAG, "Response: " + response.body().string());

            String url = request.url().toString();
            // Log the URL here
            Log.i(TAG, "Request URL: " + url);
            return chain.proceed(chain.request());
        }
    }
}
