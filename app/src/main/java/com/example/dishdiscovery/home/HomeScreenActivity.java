package com.example.dishdiscovery.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.dishdiscovery.MainActivity;
import com.example.dishdiscovery.R;
import com.example.dishdiscovery.authDataSource.FirebaseAuthentication;
import com.example.dishdiscovery.database.sharedPreferences.SharedPreferencesImpl;
import com.example.dishdiscovery.databinding.ActivityHomeScreenBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeScreenActivity extends AppCompatActivity {

    ActivityHomeScreenBinding binding;
    AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.fabLogOut.setOnClickListener(v -> {
            FirebaseAuthentication.getInstance(HomeScreenActivity.this).logout();
            SharedPreferencesImpl.getInstance(this).deleteUserId();
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();

        });

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_bar);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }
}