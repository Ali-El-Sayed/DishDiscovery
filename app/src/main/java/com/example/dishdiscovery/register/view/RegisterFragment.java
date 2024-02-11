package com.example.dishdiscovery.register.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dishdiscovery.HomeScreenActivity;
import com.example.dishdiscovery.authDataSource.FirebaseAuthentication;
import com.example.dishdiscovery.databinding.FragmentRegisterBinding;
import com.example.dishdiscovery.register.presenter.IRegisterPresenter;
import com.example.dishdiscovery.register.presenter.RegisterPresenter;
import com.example.dishdiscovery.repository.authRepo.AuthRepository;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;

public class RegisterFragment extends Fragment implements IRegister {
    private static final String TAG = "Register";
    private FragmentRegisterBinding binding;
    TextInputEditText textInputEditTextEmail;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    TextInputEditText textInputEditTextPassword;
    Button btnSignInWithGoogle;
    Button btnRegisterWithEmail;
    private IRegisterPresenter registerPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPresenter = new RegisterPresenter(this, AuthRepository.getInstance(FirebaseAuthentication.getInstance(getActivity())));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();
    }

    private void initUi() {
        // UI initialization
        textInputEditTextEmail = binding.etEmail;
        textInputLayoutEmail = binding.etEmailLayout;
        textInputEditTextPassword = binding.etPassword;
        textInputLayoutPassword = binding.etPasswordLayout;
        btnSignInWithGoogle = binding.btnSignInWithGoogle;
        btnRegisterWithEmail = binding.btnRegisterWithEmail;

        // email change listener
        textInputEditTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                emailValidation(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        // password change listener
        textInputEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                passwordValidation(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        // sign in with google button click listener
        btnRegisterWithEmail.setOnClickListener(v -> {
            registerPresenter.registerWithEmail(textInputEditTextEmail.getText().toString(), textInputEditTextPassword.getText().toString());
        });
    }

    private void passwordValidation(CharSequence s) {
        // check if password is valid
        if (s.length() < 6) {
            textInputLayoutPassword.setErrorEnabled(true);
            textInputLayoutPassword.setError("Password must be at least 6 characters");
        } else if (s.length() > 20) {
            textInputLayoutPassword.setErrorEnabled(true);
            textInputLayoutPassword.setError("Password must be at most 20 characters");
        } else if (s.toString().contains(" ")) {
            textInputLayoutPassword.setErrorEnabled(true);
            textInputLayoutPassword.setError("Password must not contain spaces");
        } else if (!s.toString().matches(".*\\d.*")) {
            textInputLayoutPassword.setErrorEnabled(true);
            textInputLayoutPassword.setError("Password must contain at least one digit");
        } else if (!s.toString().matches(".*[a-z].*")) {
            textInputLayoutPassword.setErrorEnabled(true);
            textInputLayoutPassword.setError("Password must contain at least one lowercase letter");
        } else if (!s.toString().matches(".*[A-Z].*")) {
            textInputLayoutPassword.setErrorEnabled(true);
            textInputLayoutPassword.setError("Password must contain at least one uppercase letter");
        } else if (!s.toString().matches(".*[!@#$%^&*].*")) {
            textInputLayoutPassword.setErrorEnabled(true);
            textInputLayoutPassword.setError("Password must contain at least one special character");
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
            textInputLayoutPassword.setError(null);
        }
    }

    private void emailValidation(CharSequence s) {
        // check if email is valid
        if (s.toString().isEmpty()) {
            textInputLayoutEmail.setErrorEnabled(true);
            textInputLayoutEmail.setError("Email is required");
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
            textInputLayoutEmail.setErrorEnabled(true);
            textInputLayoutEmail.setError("Invalid email");
        } else {
            textInputLayoutEmail.setErrorEnabled(false);
            textInputLayoutEmail.setError(null);
        }
    }

    @Override
    public void onRegisterSuccess(Task<AuthResult> task) {
        Toast.makeText(getActivity(), "Registration success.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), HomeScreenActivity.class));
        getActivity().finish();
    }

    @Override
    public void onRegisterFailure(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }
}