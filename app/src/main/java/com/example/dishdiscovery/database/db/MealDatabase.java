package com.example.dishdiscovery.database.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserLocalFavMeals;

@Database(entities = {Meal.class, UserLocalFavMeals.class}, version = 1, exportSchema = false)
public abstract class MealDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "meals_db";
    private static MealDatabase _instance;

    public static synchronized MealDatabase getInstance(Context context) {
        if (_instance == null) {
            _instance = Room.databaseBuilder(context.getApplicationContext(), MealDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }


        return _instance;
    }

    public abstract MealsDao mealsDao();

}
