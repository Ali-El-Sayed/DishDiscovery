package com.example.dishdiscovery.weeklyMeals.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.dishdiscovery.R;
import com.example.dishdiscovery.database.db.MealsLocalDatasourceImpl;
import com.example.dishdiscovery.database.firebaseRealtime.FirebaseRealtimeImpl;
import com.example.dishdiscovery.database.sharedPreferences.SharedPreferencesImpl;
import com.example.dishdiscovery.databinding.FragmentWeeklyMealsBinding;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.network.Api.MealRemoteDataSourceImpl;
import com.example.dishdiscovery.repository.LocalRepo.MealLocalRepoImpl;
import com.example.dishdiscovery.repository.RemoteRepo.MealsRemoteRepo;
import com.example.dishdiscovery.util.CONSTANTS;
import com.example.dishdiscovery.weeklyMeals.presenter.IWeeklyMealsPresenter;
import com.example.dishdiscovery.weeklyMeals.presenter.WeeklyMealImpl;

import java.util.List;

public class WeeklyMealsFragment extends Fragment implements OnWeeklyMealClickListener, IWeeklyMealsView {

    FragmentWeeklyMealsBinding _binding;

    private IWeeklyMealsPresenter _presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _presenter = new WeeklyMealImpl(this, MealsRemoteRepo.getInstance(MealRemoteDataSourceImpl.getInstance(), FirebaseRealtimeImpl.getInstance(), SharedPreferencesImpl.getInstance(getActivity())), MealLocalRepoImpl.getInstance(MealsLocalDatasourceImpl.getInstance(getActivity(), SharedPreferencesImpl.getInstance(getActivity()))));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
    public void showWeeklyMeals(List<Meal> userWeeklyMeals) {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSavedSuccess() {
        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
    }
}