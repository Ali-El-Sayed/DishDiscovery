package com.example.dishdiscovery.home.view;

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
import com.example.dishdiscovery.databinding.FragmentHomeBinding;
import com.example.dishdiscovery.home.presenter.HomePresenter;
import com.example.dishdiscovery.home.presenter.IHomePresenter;
import com.example.dishdiscovery.model.Category;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.network.Api.MealRemoteDataSourceImpl;
import com.example.dishdiscovery.repository.RemoteRepo.MealsRepo;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements IHome, OnCardItemClick {

    private IHomePresenter presenter;
    private FragmentHomeBinding binding;
    private RvCategoryAdapter adapter;
    Meal meal = new Meal();
    List<Category> categories = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new HomePresenter(this, MealsRepo.getInstance(MealRemoteDataSourceImpl.getInstance()));
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
        presenter.getCategories();
        presenter.getRandomMeal();
    }


    @Override
    public void showCategories(List<Category> categories) {
        this.categories = categories;
        adapter.setCategories(categories);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMealOfTheDay(Meal meal) {
        this.meal = meal;
        adapter.setMeal(meal);
    }


    @Override
    public void onMealOfTheDayClick(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("mealId", id);
        Navigation.findNavController(requireView()).navigate(R.id.action_categoryFragment_to_mealDetailsFragment, bundle);
    }

    @Override
    public void onCategoryClick(int id) {

    }
}