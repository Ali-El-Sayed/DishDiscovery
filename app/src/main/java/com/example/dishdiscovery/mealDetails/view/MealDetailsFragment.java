package com.example.dishdiscovery.mealDetails.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.dishdiscovery.R;
import com.example.dishdiscovery.databinding.FragmentMealDetailsBinding;
import com.example.dishdiscovery.mealDetails.ViewPagerAdapter;
import com.example.dishdiscovery.mealDetails.presenter.IMealDetailsPresenter;
import com.example.dishdiscovery.mealDetails.presenter.MealDetailsImpl;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.network.Api.MealRemoteDataSourceImpl;
import com.example.dishdiscovery.repository.RemoteRepo.MealsRepo;
import com.google.android.material.tabs.TabLayout;

public class MealDetailsFragment extends Fragment implements IMealDetails {

    private FragmentMealDetailsBinding _binding;
    private IMealDetailsPresenter _presenter;
    private ViewPagerAdapter _adapter;
    private Meal meal = new Meal();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _presenter = new MealDetailsImpl(this, MealsRepo.getInstance(MealRemoteDataSourceImpl.getInstance()));
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false);
        return _binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.bottom_nav_bar).setVisibility(View.GONE);
        String mealId = (String) getArguments().get("mealId");
        _presenter.getMealById(mealId);
        _binding.tabLayout.addTab(_binding.tabLayout.newTab().setText("Ingredients"));
        _binding.tabLayout.addTab(_binding.tabLayout.newTab().setText("Instructions"));
        _binding.tabLayout.addTab(_binding.tabLayout.newTab().setText("Video"));
        _binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        _binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                _binding.tabLayout.selectTab(_binding.tabLayout.getTabAt(position));
            }
        });

        _binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                _binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().findViewById(R.id.bottom_nav_bar).setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("mealId", (String) getArguments().get("mealId"));
        outState.putString("mealName", _binding.tvMealDetailsName.getText().toString());
        outState.putString("mealArea", _binding.tvMealDetailsArea.getText().toString());
        outState.putString("mealCategory", _binding.tvMealDetailsCategory.getText().toString());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            _binding.tvMealDetailsName.setText(savedInstanceState.getString("mealName"));
            _binding.tvMealDetailsArea.setText(savedInstanceState.getString("mealArea"));
            _binding.tvMealDetailsCategory.setText(savedInstanceState.getString("mealCategory"));
        }
    }

    @Override
    public void showMealDetails(Meal meal) {
        this.meal = meal;
        _adapter = new ViewPagerAdapter(getChildFragmentManager(), getLifecycle());
        _adapter.setMeal(meal);
        _binding.viewPager.setAdapter(_adapter);
        _binding.tvMealDetailsName.setText("Name : " + meal.getStrMeal());
        _binding.tvMealDetailsArea.setText("Area : " + meal.getStrArea());
        _binding.tvMealDetailsCategory.setText("Category : " + meal.getStrCategory());
        Glide.with(getContext()).load(meal.getStrMealThumb()).into(_binding.ivMealDetails);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

}