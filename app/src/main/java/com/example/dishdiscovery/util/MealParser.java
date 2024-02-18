package com.example.dishdiscovery.util;

import android.util.Pair;

import com.example.dishdiscovery.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealParser {
    public static Pair<List<String>, List<String>> parseMeal(Meal meal) {
        List<String> ingredients = new ArrayList<>();
        List<String> measurements = new ArrayList<>();

        addIngredientAndMeasurement(meal.strIngredient1, meal.strMeasure1, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient2, meal.strMeasure2, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient3, meal.strMeasure3, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient4, meal.strMeasure4, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient5, meal.strMeasure5, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient6, meal.strMeasure6, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient7, meal.strMeasure7, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient8, meal.strMeasure8, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient9, meal.strMeasure9, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient10, meal.strMeasure10, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient11, meal.strMeasure11, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient12, meal.strMeasure12, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient13, meal.strMeasure13, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient14, meal.strMeasure14, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient15, meal.strMeasure15, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient16, meal.strMeasure16, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient17, meal.strMeasure17, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient18, meal.strMeasure18, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient19, meal.strMeasure19, ingredients, measurements);
        addIngredientAndMeasurement(meal.strIngredient20, meal.strMeasure20, ingredients, measurements);

        return new Pair<>(ingredients, measurements);
    }

    private static void addIngredientAndMeasurement(String ingredient, String measurement, List<String> ingredients, List<String> measurements) {
        // Add ingredient and measurement only if both are non-empty
        if (ingredient != null && !ingredient.trim().isEmpty() && measurement != null && !measurement.trim().isEmpty()) {
            ingredients.add(ingredient);
            measurements.add(measurement);
        }
    }
}
