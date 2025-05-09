package com.example.dishdiscovery.home.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.dishdiscovery.R;
import com.example.dishdiscovery.database.firebaseRealtime.FirebaseRealtimeImpl;
import com.example.dishdiscovery.database.sharedPreferences.SharedPreferencesImpl;
import com.example.dishdiscovery.databinding.FragmentHomeBinding;
import com.example.dishdiscovery.home.presenter.HomePresenter;
import com.example.dishdiscovery.home.presenter.IHomePresenter;
import com.example.dishdiscovery.model.Category;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.network.Api.MealRemoteDataSourceImpl;
import com.example.dishdiscovery.repository.RemoteRepo.MealsRemoteRepo;
import com.example.dishdiscovery.util.CONSTANTS;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements IHome, OnCardItemClick {

    Meal meal = new Meal();
    List<Category> categories = new ArrayList<>();
    private IHomePresenter presenter;
    private FragmentHomeBinding binding;
    private RvCategoryAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(this,
                MealsRemoteRepo.getInstance(MealRemoteDataSourceImpl.getInstance(),
                        FirebaseRealtimeImpl.getInstance(), SharedPreferencesImpl.getInstance(getActivity().getApplicationContext())));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvHome.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new RvCategoryAdapter(this, meal, categories);
        binding.rvHome.setAdapter(adapter);
    }


    @Override
    public void showCategories(List<Category> categories) {
        this.categories = categories;
        adapter.setCategories(categories);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!isInternetAvailable()) {
            binding.homeScreenLottie.setAnimation(R.raw.no_internet_animation);
            binding.homeScreenLottie.loop(true);
            binding.homeScreenLottie.playAnimation();
            binding.homeScreenLottieContainer.setVisibility(View.VISIBLE);
            binding.homeScreenLottie.setVisibility(View.VISIBLE);
            Toast.makeText(getContext(), R.string.connection_failed, Toast.LENGTH_SHORT).show();
        } else {
            binding.homeScreenLottie.setAnimation(R.raw.loading_animation);
            presenter.getCategories();
            presenter.getRandomMeal();
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMealOfTheDay(Meal meal) {
        this.meal = meal;
        adapter.setMeal(meal);
        binding.homeScreenLottie.cancelAnimation();
        binding.homeScreenLottie.setVisibility(View.GONE);
        binding.homeScreenLottieContainer.setVisibility(View.GONE);
    }

    @Override
    public void onMealOfTheDayClick(String id) {
        Bundle bundle = new Bundle();
        bundle.putString(CONSTANTS.MEAL_ID, id);
        Navigation.findNavController(requireView()).navigate(R.id.action_categoryFragment_to_mealDetailsFragment, bundle);
    }

    @Override
    public void onCategoryClick(String name) {
        Bundle bundle = new Bundle();
        bundle.putString(CONSTANTS.CATEGORY_NAME, name);
        Navigation.findNavController(requireView()).navigate(R.id.action_categoryFragment_to_allMealsFragment, bundle);
    }

    private Boolean isInternetAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}