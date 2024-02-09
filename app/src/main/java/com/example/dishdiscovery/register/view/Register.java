package com.example.dishdiscovery.register.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dishdiscovery.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private static final String TAG = "Register";
    private ActivityRegisterBinding binding;
    TextInputEditText textInputEditTextEmail;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    TextInputEditText textInputEditTextPassword;
    Button btnSignInWithGoogle;
    Button btnRegisterWithEmail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        initUi();


    }

    private void initUi() {
        textInputEditTextEmail = binding.etEmail;
        textInputLayoutEmail = binding.etEmailLayout;
        // email change listener
        textInputEditTextEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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
            public void afterTextChanged(Editable s) {
            }
        });
        textInputEditTextPassword = binding.etPassword;
        textInputLayoutPassword = binding.etPasswordLayout;
        // password change listener
        textInputEditTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
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

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        btnSignInWithGoogle = binding.btnSignInWithGoogle;
        btnRegisterWithEmail = binding.btnRegisterWithEmail;

        // sign in with google button click listener

        btnRegisterWithEmail.setOnClickListener(v -> {
            String email = textInputEditTextEmail.getText().toString(), password = textInputEditTextPassword.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success
                        FirebaseUser user = mAuth.getCurrentUser();
                        Log.i(TAG, "Register OnSuccess User Id  " + user.getUid());
                        Toast.makeText(Register.this, "Registration success.", Toast.LENGTH_SHORT).show();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.e(TAG, "onComplete: " + task.getException().getMessage().toString());
                        Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

}