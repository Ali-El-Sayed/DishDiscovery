package com.example.dishdiscovery.mealDetails.instructions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dishdiscovery.databinding.FragmentInstructionsBinding;
import com.example.dishdiscovery.model.Meal;

public class InstructionsFragment extends Fragment {

    private FragmentInstructionsBinding _binding;
    private final Meal _meal;

    public InstructionsFragment(Meal meal) {
        this._meal = meal;
    }


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false);
        return _binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding.tvInstructions.setText(_meal.getStrInstructions());

    }
}