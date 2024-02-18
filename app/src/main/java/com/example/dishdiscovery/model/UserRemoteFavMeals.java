package com.example.dishdiscovery.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRemoteFavMeals {
    public List<HashMap<String, Meal>> favMealsMap;
    private final String userId;

    public UserRemoteFavMeals() {
        userId = "";
        favMealsMap = new ArrayList<>();

    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder favMeals = new StringBuilder();
        for (HashMap<String, Meal> meal : favMealsMap)
            favMeals.append(meal.toString()).append("\n");

        return "UserFavMeals{" + "userId='" + userId + '\'' + ", favMealsMap=" + favMeals + '}';
    }
}
