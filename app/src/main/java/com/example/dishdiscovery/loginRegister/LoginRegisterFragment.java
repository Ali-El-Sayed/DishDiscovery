package com.example.dishdiscovery.loginRegister;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.dishdiscovery.R;
import com.example.dishdiscovery.databinding.FragmentLoginRegisterBinding;

public class LoginRegisterFragment extends Fragment {
    FragmentLoginRegisterBinding binding;
    NavHostFragment navHostFragment;
    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginRegisterBinding.inflate(inflater, container, false);
        navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnLogin.setOnClickListener(v -> navController.navigate(R.id.action_loginRegisterFragment_to_loginFragment));
        binding.btnRegister.setOnClickListener(v -> navController.navigate(R.id.action_loginRegisterFragment_to_registerFragment));
    }
}