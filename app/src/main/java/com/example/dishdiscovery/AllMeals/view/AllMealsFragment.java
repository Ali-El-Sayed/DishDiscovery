package com.example.dishdiscovery.AllMeals.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dishdiscovery.AllMeals.presenter.AllMealsPresenterImpl;
import com.example.dishdiscovery.AllMeals.presenter.IAllMealsPresenter;
import com.example.dishdiscovery.databinding.FragmentAllMealsBinding;
import com.example.dishdiscovery.model.FilteredMeal;
import com.example.dishdiscovery.network.Api.MealRemoteDataSourceImpl;
import com.example.dishdiscovery.repository.RemoteRepo.MealsRepo;
import com.example.dishdiscovery.util.CONSTANTS;

import java.util.List;

public class AllMealsFragment extends Fragment implements IAllMealsView {
    FragmentAllMealsBinding binding;
    private IAllMealsPresenter _presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _presenter = new AllMealsPresenterImpl(this, MealsRepo.getInstance(MealRemoteDataSourceImpl.getInstance()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAllMealsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        _presenter.getAllMealsByCategory(getArguments().getString(CONSTANTS.CATEGORY_NAME));
    }

    void initUI() {
        binding.rvAllMeals.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void displayAllMeals(List<FilteredMeal> allMeals) {
        binding.rvAllMeals.setAdapter(new AllMealsAdapter(allMeals));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}