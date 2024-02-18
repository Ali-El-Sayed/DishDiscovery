package com.example.dishdiscovery.database.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserLocalFavMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealsDao {
    @Query("SELECT * FROM Meal WHERE userId = :userId")
    Flowable<List<Meal>> loadUserWeeklyMeals(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUserWeeklyMeals(Meal localWeeklyMeal);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUserFavMeal(UserLocalFavMeals userLocalFavMeals);

    @Query("SELECT * FROM UserLocalFavMeals WHERE userId = :userId")
    Flowable<List<UserLocalFavMeals>> loadUserFavMeals(String userId);

    @Query("DELETE FROM UserLocalFavMeals WHERE userId = :userId AND idMeal = :mealId")
    Completable deleteUserFavMeals(String userId, String mealId);

    // is favorite meal
    @Query("SELECT * FROM UserLocalFavMeals WHERE userId = :userId AND idMeal = :mealId")
    Flowable<UserLocalFavMeals> isFavoriteMeal(String userId, String mealId);

    @Query("SELECT * FROM Meal WHERE idMeal = :mealId")
    Flowable<UserLocalFavMeals> getMealById(String mealId);
}
