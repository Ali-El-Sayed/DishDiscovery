package com.example.dishdiscovery.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(primaryKeys = {"dayOfTheWeek", "userId"})
public class Meal {

    @NonNull
    public String dayOfTheWeek = "";
    @NonNull
    public String userId = "";
    @SerializedName("idMeal")
    @Expose
    public String idMeal;
    @SerializedName("strMeal")
    @Expose
    public String strMeal;
    @SerializedName("strCategory")
    @Expose
    public String strCategory;
    @SerializedName("strArea")
    @Expose
    public String strArea;
    @SerializedName("strInstructions")
    @Expose


    public String strInstructions;
    @SerializedName("strMealThumb")
    @Expose

    public String strMealThumb;
    @SerializedName("strYoutube")
    @Expose

    public String strYoutube;
    @SerializedName("strIngredient1")
    @Expose
    public String strIngredient1;
    @SerializedName("strIngredient2")
    @Expose

    public String strIngredient2;
    @SerializedName("strIngredient3")
    @Expose

    public String strIngredient3;
    @SerializedName("strIngredient4")
    @Expose

    public String strIngredient4;
    @SerializedName("strIngredient5")
    @Expose

    public String strIngredient5;
    @SerializedName("strIngredient6")
    @Expose

    public String strIngredient6;
    @SerializedName("strIngredient7")
    @Expose

    public String strIngredient7;
    @SerializedName("strIngredient8")
    @Expose

    public String strIngredient8;
    @SerializedName("strIngredient9")
    @Expose

    public String strIngredient9;
    @SerializedName("strIngredient10")
    @Expose

    public String strIngredient10;
    @SerializedName("strIngredient11")
    @Expose

    public String strIngredient11;
    @SerializedName("strIngredient12")
    @Expose

    public String strIngredient12;
    @SerializedName("strIngredient13")
    @Expose

    public String strIngredient13;
    @SerializedName("strIngredient14")
    @Expose


    public String strIngredient14;
    @SerializedName("strIngredient15")
    @Expose


    public String strIngredient15;
    @SerializedName("strIngredient16")
    @Expose


    public String strIngredient16;
    @SerializedName("strIngredient17")
    @Expose


    public String strIngredient17;
    @SerializedName("strIngredient18")
    @Expose


    public String strIngredient18;
    @SerializedName("strIngredient19")
    @Expose


    public String strIngredient19;
    @SerializedName("strIngredient20")
    @Expose


    public String strIngredient20;
    @SerializedName("strMeasure1")
    @Expose


    public String strMeasure1;
    @SerializedName("strMeasure2")
    @Expose


    public String strMeasure2;
    @SerializedName("strMeasure3")
    @Expose


    public String strMeasure3;
    @SerializedName("strMeasure4")
    @Expose


    public String strMeasure4;
    @SerializedName("strMeasure5")
    @Expose


    public String strMeasure5;
    @SerializedName("strMeasure6")
    @Expose


    public String strMeasure6;
    @SerializedName("strMeasure7")
    @Expose


    public String strMeasure7;
    @SerializedName("strMeasure8")
    @Expose


    public String strMeasure8;
    @SerializedName("strMeasure9")
    @Expose


    public String strMeasure9;
    @SerializedName("strMeasure10")
    @Expose


    public String strMeasure10;
    @SerializedName("strMeasure11")
    @Expose


    public String strMeasure11;
    @SerializedName("strMeasure12")
    @Expose


    public String strMeasure12;
    @SerializedName("strMeasure13")
    @Expose


    public String strMeasure13;
    @SerializedName("strMeasure14")
    @Expose


    public String strMeasure14;
    @SerializedName("strMeasure15")
    @Expose


    public String strMeasure15;
    @SerializedName("strMeasure16")
    @Expose


    public String strMeasure16;
    @SerializedName("strMeasure17")
    @Expose

    public String strMeasure17;
    @SerializedName("strMeasure18")
    @Expose

    public String strMeasure18;
    @SerializedName("strMeasure19")
    @Expose


    public String strMeasure19;
    @SerializedName("strMeasure20")
    @Expose


    public String strMeasure20;
    @SerializedName("strSource")
    @Expose


    public String strSource;

    @Ignore

    public List<String> ingredients;

    @Ignore
    public List<String> measurements;

    public Meal() {
        this.idMeal = "";
        this.strMeal = "";
        this.strCategory = "";
        this.strArea = "";
        this.strMealThumb = "";
        this.measurements = new ArrayList<>();
        this.ingredients = new ArrayList<>();
    }

    public Meal(@NonNull String idMeal, String strMeal, String strCategory, String strArea, String strMealThumb) {
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strMealThumb = strMealThumb;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<String> measurements) {
        this.measurements = measurements;
    }

    @Override
    public String toString() {
        return "Meal{" + "idMeal='" + idMeal + '\'' + ", strMeal='" + strMeal + '\'' + ", strCategory='" + strCategory + '\'' + ", strArea='" + strArea + '\'' + ", strMealThumb='" + strMealThumb + '\'' + '}';
    }
}
