package com.example.dishdiscovery.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(primaryKeys = {"idMeal", "userId"})

public class UserLocalFavMeals {

    public UserLocalFavMeals(Meal meal) {

        this.idMeal = meal.idMeal;
        this.strMeal = meal.strMeal;
        this.strCategory = meal.strCategory;
        this.strArea = meal.strArea;
        this.strMealThumb = meal.strMealThumb;
        this.strInstructions = meal.strInstructions;
        this.strYoutube = meal.strYoutube;
        this.strIngredient1 = meal.strIngredient1;
        this.strIngredient2 = meal.strIngredient2;
        this.strIngredient3 = meal.strIngredient3;
        this.strIngredient4 = meal.strIngredient4;
        this.strIngredient5 = meal.strIngredient5;
        this.strIngredient6 = meal.strIngredient6;
        this.strIngredient7 = meal.strIngredient7;
        this.strIngredient8 = meal.strIngredient8;
        this.strIngredient9 = meal.strIngredient9;
        this.strIngredient10 = meal.strIngredient10;
        this.strIngredient11 = meal.strIngredient11;
        this.strIngredient12 = meal.strIngredient12;
        this.strIngredient13 = meal.strIngredient13;
        this.strIngredient14 = meal.strIngredient14;
        this.strIngredient15 = meal.strIngredient15;
        this.strIngredient16 = meal.strIngredient16;
        this.strIngredient17 = meal.strIngredient17;
        this.strIngredient18 = meal.strIngredient18;
        this.strIngredient19 = meal.strIngredient19;
        this.strIngredient20 = meal.strIngredient20;
        this.strMeasure1 = meal.strMeasure1;
        this.strMeasure2 = meal.strMeasure2;
        this.strMeasure3 = meal.strMeasure3;
        this.strMeasure4 = meal.strMeasure4;
        this.strMeasure5 = meal.strMeasure5;
        this.strMeasure6 = meal.strMeasure6;
        this.strMeasure7 = meal.strMeasure7;
        this.strMeasure8 = meal.strMeasure8;
        this.strMeasure9 = meal.strMeasure9;
        this.strMeasure10 = meal.strMeasure10;
        this.strMeasure11 = meal.strMeasure11;
        this.strMeasure12 = meal.strMeasure12;
        this.strMeasure13 = meal.strMeasure13;
        this.strMeasure14 = meal.strMeasure14;
        this.strMeasure15 = meal.strMeasure15;
        this.strMeasure16 = meal.strMeasure16;
        this.strMeasure17 = meal.strMeasure17;
        this.strMeasure18 = meal.strMeasure18;
        this.strMeasure19 = meal.strMeasure19;
        this.strMeasure20 = meal.strMeasure20;
        this.strSource = meal.strSource;
        this.ingredients = meal.getIngredients();
        this.measurements = meal.getMeasurements();
    }

    @NonNull
    public String userId = "";
    @SerializedName("idMeal")
    @Expose
    @NonNull
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
    @Ignore

    public String strInstructions;
    @SerializedName("strMealThumb")
    @Expose

    public String strMealThumb;
    @SerializedName("strYoutube")
    @Expose

    @Ignore
    public String strYoutube;
    @SerializedName("strIngredient1")
    @Expose
    @Ignore
    public String strIngredient1;
    @SerializedName("strIngredient2")
    @Expose
    @Ignore

    public String strIngredient2;
    @SerializedName("strIngredient3")
    @Expose
    @Ignore

    public String strIngredient3;
    @SerializedName("strIngredient4")
    @Expose
    @Ignore

    public String strIngredient4;
    @SerializedName("strIngredient5")
    @Expose
    @Ignore

    public String strIngredient5;
    @SerializedName("strIngredient6")
    @Expose
    @Ignore

    public String strIngredient6;
    @SerializedName("strIngredient7")
    @Expose
    @Ignore

    public String strIngredient7;
    @SerializedName("strIngredient8")
    @Expose
    @Ignore

    public String strIngredient8;
    @SerializedName("strIngredient9")
    @Expose
    @Ignore

    public String strIngredient9;
    @SerializedName("strIngredient10")
    @Expose
    @Ignore

    public String strIngredient10;
    @SerializedName("strIngredient11")
    @Expose
    @Ignore

    public String strIngredient11;
    @SerializedName("strIngredient12")
    @Expose
    @Ignore

    public String strIngredient12;
    @SerializedName("strIngredient13")
    @Expose
    @Ignore

    public String strIngredient13;
    @SerializedName("strIngredient14")
    @Expose
    @Ignore

    public String strIngredient14;
    @SerializedName("strIngredient15")
    @Expose
    @Ignore

    public String strIngredient15;
    @SerializedName("strIngredient16")
    @Expose
    @Ignore

    public String strIngredient16;
    @SerializedName("strIngredient17")
    @Expose
    @Ignore

    public String strIngredient17;
    @SerializedName("strIngredient18")
    @Expose
    @Ignore

    public String strIngredient18;
    @SerializedName("strIngredient19")
    @Expose
    @Ignore

    public String strIngredient19;
    @SerializedName("strIngredient20")
    @Expose
    @Ignore

    public String strIngredient20;
    @SerializedName("strMeasure1")
    @Expose
    @Ignore

    public String strMeasure1;
    @SerializedName("strMeasure2")
    @Expose
    @Ignore

    public String strMeasure2;
    @SerializedName("strMeasure3")
    @Expose
    @Ignore

    public String strMeasure3;
    @SerializedName("strMeasure4")
    @Expose
    @Ignore

    public String strMeasure4;
    @SerializedName("strMeasure5")
    @Expose
    @Ignore

    public String strMeasure5;
    @SerializedName("strMeasure6")
    @Expose
    @Ignore

    public String strMeasure6;
    @SerializedName("strMeasure7")
    @Expose
    @Ignore

    public String strMeasure7;
    @SerializedName("strMeasure8")
    @Expose
    @Ignore

    public String strMeasure8;
    @SerializedName("strMeasure9")
    @Expose
    @Ignore

    public String strMeasure9;
    @SerializedName("strMeasure10")
    @Expose
    @Ignore

    public String strMeasure10;
    @SerializedName("strMeasure11")
    @Expose
    @Ignore

    public String strMeasure11;
    @SerializedName("strMeasure12")
    @Expose
    @Ignore

    public String strMeasure12;
    @SerializedName("strMeasure13")
    @Expose
    @Ignore

    public String strMeasure13;
    @SerializedName("strMeasure14")
    @Expose
    @Ignore

    public String strMeasure14;
    @SerializedName("strMeasure15")
    @Expose
    @Ignore

    public String strMeasure15;
    @SerializedName("strMeasure16")
    @Expose
    @Ignore

    public String strMeasure16;
    @SerializedName("strMeasure17")
    @Expose
    @Ignore
    public String strMeasure17;
    @SerializedName("strMeasure18")
    @Expose
    @Ignore
    public String strMeasure18;
    @SerializedName("strMeasure19")
    @Expose
    @Ignore

    public String strMeasure19;
    @SerializedName("strMeasure20")
    @Expose
    @Ignore

    public String strMeasure20;
    @SerializedName("strSource")
    @Expose
    @Ignore

    public String strSource;
    @Ignore
    public List<String> ingredients;
    @Ignore
    public List<String> measurements;

    public UserLocalFavMeals() {
        this.idMeal = "";
        this.strMeal = "";
        this.strCategory = "";
        this.strArea = "";
        this.strMealThumb = "";
        this.measurements = new ArrayList<>();
        this.ingredients = new ArrayList<>();
    }

    @Ignore

    public List<String> getIngredients() {
        return ingredients;
    }

    @Ignore

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Ignore
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
