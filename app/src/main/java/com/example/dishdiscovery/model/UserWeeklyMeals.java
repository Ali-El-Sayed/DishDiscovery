package com.example.dishdiscovery.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Meal.class, parentColumns = "idMeal", childColumns = "_saturdayId"),
        @ForeignKey(entity = Meal.class, parentColumns = "idMeal", childColumns = "_sundayId"),
        @ForeignKey(entity = Meal.class, parentColumns = "idMeal", childColumns = "_mondayId"),
        @ForeignKey(entity = Meal.class, parentColumns = "idMeal", childColumns = "_tuesdayId"),
        @ForeignKey(entity = Meal.class, parentColumns = "idMeal", childColumns = "_wednesdayId"),
        @ForeignKey(entity = Meal.class, parentColumns = "idMeal", childColumns = "_thursdayId"),
        @ForeignKey(entity = Meal.class, parentColumns = "idMeal", childColumns = "_fridayId")
})
public class UserWeeklyMeals {
    @PrimaryKey
    @NonNull
    public String userId;
    public String _saturdayId;
    public String _sundayId;
    public String _mondayId;
    public String _tuesdayId;
    public String _wednesdayId;
    public String _thursdayId;
    public String _fridayId;

    public UserWeeklyMeals() {
        userId = "";
    }
}

