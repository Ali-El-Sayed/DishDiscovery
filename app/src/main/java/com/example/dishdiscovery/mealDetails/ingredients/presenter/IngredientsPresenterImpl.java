package com.example.dishdiscovery.mealDetails.ingredients.presenter;

import android.util.Pair;

import com.example.dishdiscovery.mealDetails.ingredients.view.IIngredientView;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.util.MealParser;

import java.util.List;

public class IngredientsPresenterImpl implements IIngredientsPresenter {

    private final IIngredientView _view;

    public IngredientsPresenterImpl(IIngredientView view) {
        this._view = view;
    }

    @Override
    public void parseMeal(Meal meal) {
        Pair<List<String>, List<String>> ingredientsAndMeasurements = MealParser.parseMeal(meal);
        meal.setIngredients(ingredientsAndMeasurements.first);
        meal.setMeasurements(ingredientsAndMeasurements.second);
        _view.showIngredients(ingredientsAndMeasurements.first, ingredientsAndMeasurements.second);
    }
}
