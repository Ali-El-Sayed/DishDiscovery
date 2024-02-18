package com.example.dishdiscovery.mealDetails.ingredients.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dishdiscovery.databinding.FragmentIngredientBinding;
import com.example.dishdiscovery.mealDetails.ingredients.presenter.IIngredientsPresenter;
import com.example.dishdiscovery.mealDetails.ingredients.presenter.IngredientsPresenterImpl;
import com.example.dishdiscovery.model.Meal;

import java.util.List;


public class IngredientFragment extends Fragment implements IIngredientView {
    IIngredientsPresenter _presenter;
    private FragmentIngredientBinding _binding;
    private final Meal _meal;

    public IngredientFragment(Meal meal) {
        this._meal = meal;
        _presenter = new IngredientsPresenterImpl(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _binding = FragmentIngredientBinding.inflate(inflater, container, false);
        return _binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _presenter.parseMeal(_meal);
    }

    @Override
    public void showIngredients(List<String> ingredients, List<String> measurements) {
        IngredientsAdapter adapter = new IngredientsAdapter(ingredients, measurements);
        _binding.rvIngredientRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        _binding.rvIngredientRecyclerView.setAdapter(adapter);
    }
}