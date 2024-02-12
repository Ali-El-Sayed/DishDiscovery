package com.example.dishdiscovery.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.dishdiscovery.databinding.FragmentHomeBinding;
import com.example.dishdiscovery.home.presenter.HomePresenter;
import com.example.dishdiscovery.home.presenter.IHomePresenter;
import com.example.dishdiscovery.model.Category;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.network.Api.MealRemoteDataSourceImpl;
import com.example.dishdiscovery.repository.RemoteRepo.MealsRepo;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements IHome {

    private IHomePresenter presenter;
    private FragmentHomeBinding binding;
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
        binding.rvHome.setLayoutManager(new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL));
        presenter.getCategories();
        presenter.getMealById("52772");
    }


    @Override
    public void showCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMealOfTheDay(Meal meal) {
        this.meal = meal;
        binding.rvHome.setAdapter(new RvCategoryAdapter(meal, categories));
    }


}