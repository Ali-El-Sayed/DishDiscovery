package com.example.dishdiscovery.util;

import android.util.Pair;

import com.example.dishdiscovery.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealParser {
    public static Pair<List<String>, List<String>> parseMeal(Meal meal) {
        List<String> ingredients = new ArrayList<>();
        List<String> measurements = new ArrayList<>();

        addIngredientAndMeasurement(meal.getStrIngredient1(), meal.getStrMeasure1(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient2(), meal.getStrMeasure2(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient3(), meal.getStrMeasure3(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient4(), meal.getStrMeasure4(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient5(), meal.getStrMeasure5(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient6(), meal.getStrMeasure6(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient7(), meal.getStrMeasure7(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient8(), meal.getStrMeasure8(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient9(), meal.getStrMeasure9(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient10(), meal.getStrMeasure10(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient11(), meal.getStrMeasure11(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient12(), meal.getStrMeasure12(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient13(), meal.getStrMeasure13(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient14(), meal.getStrMeasure14(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient15(), meal.getStrMeasure15(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient16(), meal.getStrMeasure16(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient17(), meal.getStrMeasure17(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient18(), meal.getStrMeasure18(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient19(), meal.getStrMeasure19(), ingredients, measurements);
        addIngredientAndMeasurement(meal.getStrIngredient20(), meal.getStrMeasure20(), ingredients, measurements);

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
