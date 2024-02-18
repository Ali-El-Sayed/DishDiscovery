package com.example.dishdiscovery.favorite.view;

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
import com.example.dishdiscovery.database.db.MealsLocalDatasourceImpl;
import com.example.dishdiscovery.database.firebaseRealtime.FirebaseRealtimeImpl;
import com.example.dishdiscovery.database.sharedPreferences.SharedPreferencesImpl;
import com.example.dishdiscovery.databinding.FragmentFavoriteBinding;
import com.example.dishdiscovery.favorite.presenter.FavoritePresenterImpl;
import com.example.dishdiscovery.favorite.presenter.IFavoritePresenter;
import com.example.dishdiscovery.model.UserLocalFavMeals;
import com.example.dishdiscovery.network.Api.MealRemoteDataSourceImpl;
import com.example.dishdiscovery.repository.LocalRepo.MealLocalRepoImpl;
import com.example.dishdiscovery.repository.RemoteRepo.MealsRemoteRepo;
import com.example.dishdiscovery.util.CONSTANTS;

import java.util.List;


public class FavoriteFragment extends Fragment implements IFavoriteView, OnFavMealClickListener {
    FragmentFavoriteBinding binding;
    IFavoritePresenter _presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = new FavoritePresenterImpl(this, MealsRemoteRepo.getInstance(MealRemoteDataSourceImpl.getInstance(), FirebaseRealtimeImpl.getInstance(), SharedPreferencesImpl.getInstance(getActivity())), MealLocalRepoImpl.getInstance(MealsLocalDatasourceImpl.getInstance(getActivity(), SharedPreferencesImpl.getInstance(getActivity()))));
        _presenter.getLocalFavMeals();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        binding.homeScreenLottie.setVisibility(View.VISIBLE);
        _presenter.getLocalFavMeals();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvFavoriteMeals.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void displayLocalFavMeals(List<UserLocalFavMeals> userLocalFavMeals) {
        binding.rvFavoriteMeals.setAdapter(new RvFavoriteAdapter(userLocalFavMeals, this));
        binding.homeScreenLottieContainer.setVisibility(View.GONE);
    }

    @Override
    public void displayLocalMealsError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFavMealClick(String mealId) {
        Bundle bundle = new Bundle();
        bundle.putString(CONSTANTS.MEAL_ID, mealId);
        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_favoriteFragment_to_mealDetailsFragment, bundle);
    }
}