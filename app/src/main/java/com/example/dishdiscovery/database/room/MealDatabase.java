package com.example.dishdiscovery.database.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserWeeklyMeals;

@Database(entities = { LocalWeeklyMeal.class}, version = 1, exportSchema = false)
public abstract class MealDatabase extends RoomDatabase {
    public abstract MealsDao mealsDao();

    private static MealDatabase _instance;
    public static final String DATABASE_NAME = "meals_db";


    public static synchronized MealDatabase getInstance(Context context) {
        if (_instance == null) {
            _instance = Room.databaseBuilder(context.getApplicationContext(), MealDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return _instance;
    }

}
