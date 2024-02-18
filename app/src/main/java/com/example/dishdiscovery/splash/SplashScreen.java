package com.example.dishdiscovery.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dishdiscovery.MainActivity;
import com.example.dishdiscovery.R;
import com.example.dishdiscovery.database.sharedPreferences.SharedPreferencesImpl;
import com.example.dishdiscovery.home.HomeScreenActivity;

public class SplashScreen extends AppCompatActivity {
    private static final String TAG = "SplashScreen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()) {
        }.postDelayed(() -> {
            String userId = SharedPreferencesImpl.getInstance(this).getUserId();
            Log.i(TAG, "onCreate: " + userId);
            if (userId.isEmpty())
                startActivity(new Intent(this, MainActivity.class));
            else
                startActivity(new Intent(this, HomeScreenActivity.class));
            finish();
        }, 3000);


    }

}