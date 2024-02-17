package com.example.dishdiscovery.weeklyMeals.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.dishdiscovery.R;
import com.example.dishdiscovery.databinding.FragmentWeeklyMealsBinding;
import com.example.dishdiscovery.model.UserWeeklyMeals;
import com.example.dishdiscovery.util.CONSTANTS;

public class WeeklyMealsFragment extends Fragment implements OnWeeklyMealClickListener, IWeeklyMealsView {

    FragmentWeeklyMealsBinding _binding;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        _binding = FragmentWeeklyMealsBinding.inflate(inflater, container, false);
        return _binding.getRoot();
    }

    @Override
    public void onWeeklyMealClick(String mealId) {
        Bundle bundle = new Bundle();
        bundle.putString(CONSTANTS.MEAL_ID, mealId);
        Navigation.findNavController(_binding.getRoot()).navigate(R.id.action_weeklyMealsFragment_to_mealDetailsFragment, bundle);

    }


    @Override
    public void showWeeklyMeals(UserWeeklyMeals userWeeklyMeals) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}