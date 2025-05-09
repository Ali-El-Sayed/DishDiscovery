package com.example.dishdiscovery.database.firebaseRealtime;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.dishdiscovery.database.firebaseRealtime.model.RemoteUserWeeklyMeals;
import com.example.dishdiscovery.favorite.presenter.OnFavNetworkCallback;
import com.example.dishdiscovery.model.Meal;
import com.example.dishdiscovery.model.UserRemoteFavMeals;
import com.example.dishdiscovery.util.CONSTANTS;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.rxjava3.core.Completable;

public class FirebaseRealtimeImpl implements IFirebaseRealtimeDataSource {
    private static final String TAG = "FirebaseRealtimeImpl";
    private static FirebaseRealtimeImpl _instance;

    private final FirebaseDatabase _database;


    private FirebaseRealtimeImpl() {
        _database = FirebaseDatabase.getInstance();
    }

    public static synchronized IFirebaseRealtimeDataSource getInstance() {
        if (_instance == null) _instance = new FirebaseRealtimeImpl();

        return _instance;
    }

    @Override
    public void saveUserFavoriteMeals(String userId, String mealId) {
        _database.getReference(CONSTANTS.USERS_REALTIME_DB).child(userId).child(CONSTANTS.FAVORITES_REALTIME_DB).child(CONSTANTS.FAVMEALS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                    if (dataSnapshot.getValue().equals(mealId)) return;


                _database.getReference(CONSTANTS.USERS_REALTIME_DB).child(CONSTANTS.FAVORITES_REALTIME_DB).child(userId).child(CONSTANTS.FAVMEALS).child(String.valueOf(snapshot.getChildrenCount())).setValue(mealId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled: " + error.getMessage());
            }
        });
    }

    @Override
    public Completable saveUserWeeklyMeals(String userId, Meal meal) {
        Log.i(TAG, "saveUserWeeklyMeals: " + meal);
        meal.userId = userId;
        return Completable.create(emitter -> {
            _database.getReference(CONSTANTS.USERS_REALTIME_DB)
                    .child(userId)
                    .child(CONSTANTS.WEEKLY_MEALS_REALTIME_DB)
                    .child("_" + meal.dayOfTheWeek.toLowerCase()).setValue(meal).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) emitter.onComplete();
                        else emitter.onError(task.getException());
                    });
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
        _database.getReference(CONSTANTS.USERS_REALTIME_DB).child(userId).child(CONSTANTS.FAVORITES_REALTIME_DB).setValue(new UserRemoteFavMeals());
    }

    private void createUserWeeklyMeals(String userId) {
        _database.getReference(CONSTANTS.USERS_REALTIME_DB).child(userId).child(CONSTANTS.WEEKLY_MEALS_REALTIME_DB).setValue(new RemoteUserWeeklyMeals(userId));
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
    public void getUserFavoriteMeals(String userId, OnFavNetworkCallback onFavNetworkCallBack) {
        _database.getReference(CONSTANTS.USERS_REALTIME_DB).child(userId).child(CONSTANTS.FAVORITES_REALTIME_DB).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                onFavNetworkCallBack.onSuccess(snapshot.getValue(UserRemoteFavMeals.class));
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
