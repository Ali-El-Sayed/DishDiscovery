package com.example.dishdiscovery.mealDetails.ingredients.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dishdiscovery.databinding.FragmentIngredientBinding;
import com.example.dishdiscovery.model.Meal;


public class IngredientFragment extends Fragment {
    private FragmentIngredientBinding _binding;

    private Meal _meal;

    public IngredientFragment(Meal meal) {
        this._meal = meal;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _binding = FragmentIngredientBinding.inflate(inflater, container, false);
        return _binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding.tvIngredients.setText(_meal.getStrIngredient1());


    }
}