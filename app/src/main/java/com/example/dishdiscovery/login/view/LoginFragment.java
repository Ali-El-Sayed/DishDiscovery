package com.example.dishdiscovery.login.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dishdiscovery.R;
import com.example.dishdiscovery.authDataSource.FirebaseAuthentication;
import com.example.dishdiscovery.login.presenter.ILoginPresenter;
import com.example.dishdiscovery.login.presenter.LoginPresenter;
import com.example.dishdiscovery.repository.Repository;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment implements ILogin {
    private static final String TAG = "LoginFragment";
    TextInputEditText textInputEditTextEmail;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;
    TextInputEditText textInputEditTextPassword;
    Button btnSignInWithGoogle;
    Button btnLoginWithEmail;

    ILoginPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this, Repository.getInstance(FirebaseAuthentication.getInstance(getActivity())));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        initUi();
        presenter = new LoginPresenter(this, Repository.getInstance(FirebaseAuthentication.getInstance(getActivity())));

    }

//    private void initUi() {
//        textInputEditTextEmail = binding.etEmail;
//        textInputLayoutEmail = binding.etEmailLayout;
//        // email change listener
//        textInputEditTextEmail.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.toString().isEmpty()) {
//                    textInputLayoutEmail.setErrorEnabled(true);
//                    textInputLayoutEmail.setError("Email is required");
//                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
//                    textInputLayoutEmail.setErrorEnabled(true);
//                    textInputLayoutEmail.setError("Invalid email");
//                } else {
//                    textInputLayoutEmail.setErrorEnabled(false);
//                    textInputLayoutEmail.setError(null);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//        textInputEditTextPassword = binding.etPassword;
//        textInputLayoutPassword = binding.etPasswordLayout;
//        // password change listener
//        textInputEditTextPassword.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                passwordValidation(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//        btnSignInWithGoogle = binding.btnSignInWithGoogle;
//        btnLoginWithEmail = binding.btnLoginWithEmail;
//
//        // sign in with google button click listener
//        btnLoginWithEmail.setOnClickListener(v -> {
//            String email = textInputEditTextEmail.getText().toString(), password = textInputEditTextPassword.getText().toString();
//            presenter.loginWithEmail(email, password);
//        });
//    }

    private void passwordValidation(CharSequence s) {
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
    public void onLoginSuccess(Task<AuthResult> task) {
        FirebaseUser user = task.getResult().getUser();
        Log.i(TAG, "Login onComplete: user id: " + user.getUid());
        Toast.makeText(getContext(), "Authentication success.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginFailure(Exception e) {
        Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
    }

}