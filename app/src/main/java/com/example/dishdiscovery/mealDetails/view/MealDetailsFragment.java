package com.example.dishdiscovery.mealDetails.view;

import android.os.Bundle;
import android.os.Parcel;
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
import com.example.dishdiscovery.database.firebaseRealtime.FirebaseRealtimeImpl;
import com.example.dishdiscovery.database.sharedPreferences.SharedPreferencesImpl;
import com.example.dishdiscovery.databinding.FragmentMealDetailsBinding;
import com.example.dishdiscovery.mealDetails.presenter.IMealDetailsPresenter;
import com.example.dishdiscovery.mealDetails.presenter.MealDetailsImpl;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.network.Api.MealRemoteDataSourceImpl;
import com.example.dishdiscovery.repository.RemoteRepo.MealsRepo;
import com.example.dishdiscovery.util.CONSTANTS;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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
        _presenter = new MealDetailsImpl(this, MealsRepo.getInstance(MealRemoteDataSourceImpl.getInstance(), FirebaseRealtimeImpl.getInstance(), SharedPreferencesImpl.getInstance(getActivity().getApplicationContext())));
        _binding = FragmentMealDetailsBinding.inflate(inflater, container, false);
        return _binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().findViewById(R.id.bottom_nav_bar).setVisibility(View.GONE);
        String mealId = (String) getArguments().get(CONSTANTS.MEAL_ID);
        _presenter.getMealById(mealId);
        initUI();

    }

    private void initUI() {
        _binding.tabLayout.addTab(_binding.tabLayout.newTab().setText("Ingredients"));
        _binding.tabLayout.addTab(_binding.tabLayout.newTab().setText("Instructions"));
        _binding.tabLayout.addTab(_binding.tabLayout.newTab().setText("Video"));
        _binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        _binding.ivFavorite.setOnClickListener(v -> {


        });
        _binding.ivAddCalender.setOnClickListener(v -> {
            long today = MaterialDatePicker.todayInUtcMilliseconds();

            long start = today;
            long end = today + TimeUnit.DAYS.toMillis(6); // 7 days later

            CalendarConstraints.DateValidator dateValidator = new CalendarConstraints.DateValidator() {
                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(@NonNull Parcel dest, int flags) {

                }

                @Override
                public boolean isValid(long date) {
                    // Only allow dates within the next 7 days
                    return !(date < start || date > end);
                }
            };

            CalendarConstraints constraints = new CalendarConstraints.Builder().setValidator(dateValidator).build();

            MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
            builder.setTitleText("Select a date");
            builder.setCalendarConstraints(constraints);

            final MaterialDatePicker<Long> materialDatePicker = builder.build();

            materialDatePicker.show(getChildFragmentManager(), "DATE_PICKER");

            materialDatePicker.addOnPositiveButtonClickListener(selection -> {
                String selectedDate = materialDatePicker.getHeaderText();
                try {
                    SimpleDateFormat inFormat = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
                    Date date = inFormat.parse(selectedDate);
                    SimpleDateFormat outFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
                    String dayName = outFormat.format(date);

                    _presenter.saveUserWeeklyMeals(dayName, new Meal(meal.getIdMeal(), meal.getStrMeal(), meal.getStrCategory(), meal.getStrArea(), meal.getStrMealThumb()));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            });


        });


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
    public void onResume() {
        super.onResume();
        getActivity().findViewById(R.id.bottom_nav_bar).setVisibility(View.GONE);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().findViewById(R.id.bottom_nav_bar).setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CONSTANTS.MEAL_ID, (String) getArguments().get("mealId"));
        outState.putString(CONSTANTS.CATEGORY_NAME, _binding.tvMealDetailsName.getText().toString());
        outState.putString(CONSTANTS.MEAL_AREA, _binding.tvMealDetailsArea.getText().toString());
        outState.putString(CONSTANTS.MEAL_CATEGORY, _binding.tvMealDetailsCategory.getText().toString());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            _binding.tvMealDetailsName.setText(savedInstanceState.getString(CONSTANTS.CATEGORY_NAME));
            _binding.tvMealDetailsArea.setText(savedInstanceState.getString(CONSTANTS.MEAL_AREA));
            _binding.tvMealDetailsCategory.setText(savedInstanceState.getString(CONSTANTS.MEAL_CATEGORY));
            _presenter.getMealById(savedInstanceState.getString(CONSTANTS.MEAL_ID));
        }
    }

    @Override
    public void showMealDetails(Meal meal) {
        this.meal = meal;
        _adapter = new ViewPagerAdapter(getChildFragmentManager(), getLifecycle());
        _binding.tabLayout.getTabAt(0).setText("Ingredients (" + meal.getIngredients().size() + ")");
        _adapter.setMeal(meal);
        _binding.viewPager.setAdapter(_adapter);
        _binding.tvMealDetailsName.setText(meal.getStrMeal());
        _binding.tvMealDetailsArea.setText("Area : " + meal.getStrArea());
        _binding.tvMealDetailsCategory.setText("Category : " + meal.getStrCategory());
        Glide.with(getContext()).load(meal.getStrMealThumb()).into(_binding.ivMealDetails);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveUserWeeklyMealsSuccess() {
        Toast.makeText(getContext(), "Meal added to your weekly meals", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveUserWeeklyMealsError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }


}