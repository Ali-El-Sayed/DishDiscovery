package com.example.dishdiscovery.database.firebaseRealtime;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.dishdiscovery.favorite.presenter.OnFavNetworkCallBack;
import com.example.dishdiscovery.mealDetails.presenter.onSaveUserWeeklyMealsCallBack;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserFavMeals;
import com.example.dishdiscovery.model.UserWeeklyMeals;
import com.example.dishdiscovery.util.CONSTANTS;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseRealtimeImpl implements IFirebaseRealtime {
    private static final String TAG = "FirebaseRealtimeImpl";
    private static FirebaseRealtimeImpl _instance;

    private final FirebaseDatabase _database;


    private FirebaseRealtimeImpl() {
        _database = FirebaseDatabase.getInstance();
    }

    public static synchronized IFirebaseRealtime getInstance() {
        if (_instance == null) _instance = new FirebaseRealtimeImpl();

        return _instance;
    }

    @Override
    public void saveUserWeeklyMeals(String userId, String dayOfWeek, Meal meal, onSaveUserWeeklyMealsCallBack callBack) {
        _database.getReference(CONSTANTS.USERS_REALTIME_DB)
                .child(userId)
                .child(CONSTANTS.WEEKLY_MEALS_REALTIME_DB)
                .child("_" + dayOfWeek.toLowerCase())
                .setValue(meal).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) callBack.onSaveUserWeeklyMealsSuccess();
                    else callBack.onSaveUserWeeklyMealsError(task.getException().getMessage());
                });
    }

    @Override
    public void getUserWeeklyMeals(String userId) {

    }


    @Override
    public void deleteUserWeeklyMeal(String userId, String dayOfWeek) {

    }

    @Override
    public void verifyUserFavoriteMealsCreated(String userId) {
        _database.getReference(CONSTANTS.USERS_REALTIME_DB).child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(userId))
                    Log.d(TAG, "onDataChange: Child Exist " + snapshot.hasChild(userId));
                else {
                    createUserFavoriteMeals(userId);
                    createUserWeeklyMeals(userId);
                    Log.d(TAG, "onDataChange: Child Created " + snapshot.hasChild(userId));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled: " + error.getMessage());
            }
        });
    }

    private void createUserFavoriteMeals(String userId) {
        _database.getReference(CONSTANTS.USERS_REALTIME_DB).child(userId).child(CONSTANTS.FAVORITES_REALTIME_DB).setValue(new UserFavMeals(userId, new ArrayList<>()));

        _database.getReference("users").child("favorites").child(userId).setValue(new UserFavMeals());
    }

    private void createUserWeeklyMeals(String userId) {
        _database.getReference(CONSTANTS.USERS_REALTIME_DB).child(userId).child(CONSTANTS.WEEKLY_MEALS_REALTIME_DB).setValue(new UserWeeklyMeals(userId));

        _database.getReference("users").child("weeklyMeals").child(userId).setValue(new UserFavMeals());
    }


    @Override
    public void saveUserFavoriteMeals(String userId, String mealId) {
        _database.getReference(CONSTANTS.USERS_REALTIME_DB).child(userId).child(CONSTANTS.FAVORITES_REALTIME_DB).child(CONSTANTS.FAVMEALS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                    if (dataSnapshot.getValue().equals(mealId)) return;


                _database.getReference(CONSTANTS.USERS_REALTIME_DB)
                        .child(CONSTANTS.FAVORITES_REALTIME_DB)
                        .child(userId).child(CONSTANTS.FAVMEALS).child(snapshot.getChildrenCount() + "").setValue(mealId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled: " + error.getMessage());
            }
        });
    }

    @Override
    public void deleteUserFavoriteMeals(String userId, String mealId) {
        _database.getReference(CONSTANTS.USERS_REALTIME_DB).child(userId).child(CONSTANTS.FAVORITES_REALTIME_DB).child(CONSTANTS.FAVMEALS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                    if (dataSnapshot.getValue().equals(mealId)) dataSnapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled: " + error.getMessage());
            }
        });
    }

    @Override
    public void getUserFavoriteMeals(String userId, OnFavNetworkCallBack onFavNetworkCallBack) {
        _database.getReference(CONSTANTS.USERS_REALTIME_DB).child(userId).child(CONSTANTS.FAVORITES_REALTIME_DB).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onFavNetworkCallBack.onSuccess(snapshot.getValue(UserFavMeals.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                onFavNetworkCallBack.onError(error.getMessage());
            }
        });
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        _instance = null;
    }
}
