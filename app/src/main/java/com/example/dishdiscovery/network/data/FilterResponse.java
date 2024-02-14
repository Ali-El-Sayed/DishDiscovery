package com.example.dishdiscovery.network.data;

import com.example.dishdiscovery.model.FilteredMeal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilterResponse {
    @SerializedName("meals")
    @Expose
    private List<FilteredMeal> meals;

    public List<FilteredMeal> getMeals() {
        return meals;
    }

    public void setMeals(List<FilteredMeal> meals) {
        this.meals = meals;
    }
}
