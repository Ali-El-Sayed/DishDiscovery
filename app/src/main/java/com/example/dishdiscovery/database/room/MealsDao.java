package com.example.dishdiscovery.database.room;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.dishdiscovery.model.UserWeeklyMeals;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealsDao {
    @Query("SELECT * FROM UserWeeklyMeals WHERE userId = :userId")
    Flowable<UserWeeklyMeals> loadUserWeeklyMeals(String userId);

    @Query("UPDATE UserWeeklyMeals SET _saturdayId = :mealId WHERE userId = :userId")
    void updateUserWeeklyMeals(String userId, String mealId);

}
