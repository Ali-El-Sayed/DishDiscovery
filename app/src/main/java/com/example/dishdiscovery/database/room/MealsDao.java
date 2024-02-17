package com.example.dishdiscovery.database.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserWeeklyMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealsDao {
    @Query("SELECT * FROM UserWeeklyMeals WHERE userId = :userId")
    Flowable<List<UserWeeklyMeals>> loadUserWeeklyMeals(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserWeeklyMeals(UserWeeklyMeals userWeeklyMeals);

    @Query("UPDATE UserWeeklyMeals SET _saturday = :meal WHERE userId = :userId")
    void updateUserWeeklyMealsSaturday(String userId, Meal meal);

    @Query("UPDATE UserWeeklyMeals SET _sunday = :meal WHERE userId = :userId")
    void updateUserWeeklyMealsSunday(String userId, Meal meal);

    @Query("UPDATE UserWeeklyMeals SET _monday = :meal WHERE userId = :userId")
    void updateUserWeeklyMealsMonday(String userId, Meal meal);

    @Query("UPDATE UserWeeklyMeals SET _tuesday = :meal WHERE userId = :userId")
    void updateUserWeeklyMealsTuesday(String userId, Meal meal);

    @Query("UPDATE UserWeeklyMeals SET _wednesday = :meal WHERE userId = :userId")
    void updateUserWeeklyMealsWednesday(String userId, Meal meal);


    @Query("UPDATE UserWeeklyMeals SET _thursday = :meal WHERE userId = :userId")
    void updateUserWeeklyMealsThursday(String userId, Meal meal);

    @Query("UPDATE UserWeeklyMeals SET _friday = :meal WHERE userId = :userId")
    void updateUserWeeklyMealsFriday(String userId, Meal meal);

    @Delete
    void deleteUserWeeklyMeals(UserWeeklyMeals userWeeklyMeals);
}
