package com.example.dishdiscovery.database.firebaseRealtime.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.example.dishdiscovery.model.Meal;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(primaryKeys = {"dateOfTheDay", "userId"})
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

    public LocalWeeklyMeal(String dayOfTheWeek, Meal meal) {
        this.dayOfTheWeek = dayOfTheWeek;
        this.idMeal = meal.idMeal;
        this.strMeal = meal.strMeal;
        this.strCategory = meal.strCategory;
        this.strArea = meal.strArea;
        this.strMealThumb = meal.strMealThumb;
    }

    public LocalWeeklyMeal(String idMeal, String dateOfTheDay, String userId, String dayOfTheWeek, String strMeal, String strCategory, String strArea, String strMealThumb) {
        this.idMeal = idMeal;
        this.dateOfTheDay = dateOfTheDay;
        this.userId = userId;
        this.dayOfTheWeek = dayOfTheWeek;
        this.strMeal = strMeal;
        this.strCategory = strCategory;
        this.strArea = strArea;
        this.strMealThumb = strMealThumb;
    }


    @NonNull
    @SerializedName("idMeal")
    @Expose
    public String idMeal;
    @SerializedName("dateOfTheDay")
    @Expose
    @NonNull
    public String dateOfTheDay;

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
    @SerializedName("strMealThumb")
    @Expose
    public String strMealThumb;

    @Override
    public String toString() {
        return "LocalWeeklyMeal{" +
                "idMeal='" + idMeal + '\'' +
                ", dateOfTheDay='" + dateOfTheDay + '\'' +
                ", userId='" + userId + '\'' +
                ", dayOfTheWeek='" + dayOfTheWeek + '\'' +
                ", strMeal='" + strMeal + '\'' +
                ", strCategory='" + strCategory + '\'' +
                ", strArea='" + strArea + '\'' +
                ", strMealThumb='" + strMealThumb + '\'' +
                '}';
    }
}
