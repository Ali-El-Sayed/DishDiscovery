package com.example.dishdiscovery.database.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.dishdiscovery.database.firebaseRealtime.model.LocalWeeklyMeal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealsDao {
    @Query("SELECT * FROM LocalWeeklyMeal WHERE userId = :userId")
    Flowable<List<LocalWeeklyMeal>> loadUserWeeklyMeals(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUserWeeklyMeals(LocalWeeklyMeal localWeeklyMeal);
}
