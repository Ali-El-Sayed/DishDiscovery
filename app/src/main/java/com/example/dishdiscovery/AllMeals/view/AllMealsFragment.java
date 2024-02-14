package com.example.dishdiscovery.AllMeals.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.dishdiscovery.AllMeals.presenter.AllMealsPresenterImpl;
import com.example.dishdiscovery.AllMeals.presenter.IAllMealsPresenter;
import com.example.dishdiscovery.R;
import com.example.dishdiscovery.databinding.FragmentAllMealsBinding;
import com.example.dishdiscovery.model.FilteredMeal;
import com.example.dishdiscovery.network.Api.MealRemoteDataSourceImpl;
import com.example.dishdiscovery.repository.RemoteRepo.MealsRepo;
import com.example.dishdiscovery.util.CONSTANTS;

import java.util.List;

public class AllMealsFragment extends Fragment implements IAllMealsView, OnMealClickListener {
    FragmentAllMealsBinding binding;
    private IAllMealsPresenter _presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _presenter = new AllMealsPresenterImpl(this, MealsRepo.getInstance(MealRemoteDataSourceImpl.getInstance()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAllMealsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();

        String category = getArguments().getString(CONSTANTS.CATEGORY_NAME);
        _presenter.getAllMealsByCategory(category);
    }


    void initUI() {
        binding.rvAllMeals.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.bottom_nav_bar).setVisibility(View.GONE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CONSTANTS.CATEGORY_NAME, getArguments().getString(CONSTANTS.CATEGORY_NAME));
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            String category = savedInstanceState.getString(CONSTANTS.CATEGORY_NAME);
            _presenter.getAllMealsByCategory(category);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().findViewById(R.id.bottom_nav_bar).setVisibility(View.VISIBLE);
    }

    @Override
    public void displayAllMeals(List<FilteredMeal> allMeals) {
        binding.rvAllMeals.setAdapter(new AllMealsAdapter(allMeals, this));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealClick(String mealId) {
        Bundle bundle = new Bundle();
        bundle.putString(CONSTANTS.MEAL_ID, mealId);
        Navigation.findNavController(binding.getRoot()).navigate(com.example.dishdiscovery.R.id.action_allMealsFragment_to_mealDetailsFragment, bundle);
    }
}