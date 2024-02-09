package com.example.dishdiscovery;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dishdiscovery.databinding.ActivityLoginRegisterBinding;
import com.example.dishdiscovery.login.view.Login;
import com.example.dishdiscovery.register.view.Register;

public class MainActivity extends AppCompatActivity {

    ActivityLoginRegisterBinding binding;
    NavHostFragment navHostFragment;
    NavController navController;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, Login.class));
        });
        binding.btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, Register.class));
        });
    }
}