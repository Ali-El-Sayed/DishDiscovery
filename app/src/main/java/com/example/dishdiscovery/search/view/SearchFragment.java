package com.example.dishdiscovery.search.view;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.dishdiscovery.AllMeals.view.OnMealClickListener;
import com.example.dishdiscovery.R;
import com.example.dishdiscovery.database.firebaseRealtime.FirebaseRealtimeImpl;
import com.example.dishdiscovery.database.sharedPreferences.SharedPreferencesImpl;
import com.example.dishdiscovery.databinding.FragmentSearchBinding;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.network.Api.MealRemoteDataSourceImpl;
import com.example.dishdiscovery.repository.RemoteRepo.MealsRemoteRepo;
import com.example.dishdiscovery.search.presenter.ISearchPresenter;
import com.example.dishdiscovery.search.presenter.SearchPresenterImpl;
import com.example.dishdiscovery.util.CONSTANTS;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragment extends Fragment implements ISearchView, OnMealClickListener {
    private static final String TAG = "SearchFragment";
    FragmentSearchBinding binding;
    ISearchPresenter searchPresenter;
    private Disposable textChangeDisposable;
    private String categoryName = "";
    private String areaName = "";

    private String ingredientName = "";


    public SearchFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchPresenter = new SearchPresenterImpl(MealsRemoteRepo.getInstance(MealRemoteDataSourceImpl.getInstance(), FirebaseRealtimeImpl.getInstance(), SharedPreferencesImpl.getInstance(getActivity().getApplicationContext())), this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intiUi();
    }

    private void intiUi() {
        binding.rvSearchResults.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        // Create an Observable for text changes
        Observable<CharSequence> textChangeObservable = Observable.create(emitter -> {
            TextWatcher textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // Do nothing before text changes
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (binding.chipArea.isChecked()) {
                        categoryName = ingredientName = "";
                        areaName = s.toString();
                        emitter.onNext("");
                    } else if (binding.chipCategory.isChecked()) {
                        areaName = ingredientName = "";
                        categoryName = s.toString();
                        emitter.onNext("");
                    } else if (binding.chipIngredient.isChecked()) {
                        areaName = categoryName = "";
                        ingredientName = s.toString();
                        emitter.onNext("");
                    } else {
                        areaName = categoryName = ingredientName = "";
                        emitter.onNext(s);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // Do nothing after text changes
                }
            };

            // Attach the TextWatcher to the EditText
            binding.searchView.getEditText().addTextChangedListener(textWatcher);

            // Dispose the TextWatcher when unsubscribed
            emitter.setCancellable(() -> binding.searchView.getEditText().removeTextChangedListener(textWatcher));
        });

        // Subscribe to text changes
        textChangeDisposable = textChangeObservable.debounce(600, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
                .subscribe(updatedText -> {
                    Log.i(TAG, "onViewCreated: " + updatedText);
                    searchPresenter.getSearchResults(updatedText.toString());
                });

    }


    @SuppressLint("CheckResult")
    @Override
    public void showSearchResult(List<Meal> meals) {
        if (meals.isEmpty())
            Toast.makeText(requireContext(), "No meals found", Toast.LENGTH_SHORT).show();

        Observable<List<Meal>> mealsObservable = Observable.just(meals);

        // filer meals by category and area
        mealsObservable.subscribeOn(Schedulers.io())
                .map(
                        mealList -> {
                            if (!categoryName.isEmpty()) {
                                return Meal.filterMealsByCategory(mealList, categoryName);
                            } else if (!areaName.isEmpty()) {
                                return Meal.filterMealsByArea(mealList, areaName);
                            } else if (!ingredientName.isEmpty()) {
                                return Meal.filterMealsByIngredient(mealList, ingredientName);
                            }
                            return mealList;
                        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealList -> {
                    MealSearchAdapter adapter = new MealSearchAdapter(mealList, this);
                    binding.rvSearchResults.setAdapter(adapter);
                });


    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(requireContext(), "Not Found", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealClick(String mealId) {
        Bundle bundle = new Bundle();
        bundle.putString(CONSTANTS.MEAL_ID, mealId);
        Navigation.findNavController(requireView()).navigate(R.id.action_searchFragment_to_mealDetailsFragment, bundle);
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.searchView.getEditText().clearFocus();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (textChangeDisposable != null && !textChangeDisposable.isDisposed()) {
            textChangeDisposable.dispose();
        }
    }
}