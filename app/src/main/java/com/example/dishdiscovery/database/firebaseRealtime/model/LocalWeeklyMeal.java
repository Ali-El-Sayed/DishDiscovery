package com.example.dishdiscovery.database.firebaseRealtime.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class LocalWeeklyMeal {

    public LocalWeeklyMeal() {
        this.dayOfTheWeek = "";
        this.idMeal = "";
        this.strMeal = "";
        this.strCategory = "";
        this.strArea = "";
        this.strMealThumb = "";
        userId = "";
    }

    public LocalWeeklyMeal(String dayOfTheWeek, @NonNull String idMeal, String strMeal, String strCategory, String strArea, String strMealThumb) {
        this.dayOfTheWeek = dayOfTheWeek;
        this.idMeal = idMeal;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strMealThumb = strMealThumb;
        userId = null;
    }

    @PrimaryKey
    @NonNull
    @SerializedName("idMeal")
    @Expose
    public String idMeal;

    @SerializedName("userId")
    @NonNull
    public String userId;

    @SerializedName("dayOfTheWeek")
    @Expose
    public String dayOfTheWeek;
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
}
