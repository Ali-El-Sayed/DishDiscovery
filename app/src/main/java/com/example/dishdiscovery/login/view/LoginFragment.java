package com.example.dishdiscovery.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dishdiscovery.home.HomeScreenActivity;
import com.example.dishdiscovery.R;
import com.example.dishdiscovery.authDataSource.FirebaseAuthentication;
import com.example.dishdiscovery.database.firebaseRealtime.FirebaseRealtimeImpl;
import com.example.dishdiscovery.database.sharedPreferences.SharedPreferencesImpl;
import com.example.dishdiscovery.databinding.FragmentLoginBinding;
import com.example.dishdiscovery.login.presenter.ILoginPresenter;
import com.example.dishdiscovery.login.presenter.LoginPresenter;
import com.example.dishdiscovery.repository.authRepo.AuthRepository;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment implements ILogin {
    private static final String TAG = "LoginFragment";
    ILoginPresenter presenter;
    ActivityResultLauncher<Intent> signInLauncher;
    GoogleSignInClient mGoogleSignInClient;
    private FragmentLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this, AuthRepository.getInstance(FirebaseAuthentication.getInstance(getActivity()), FirebaseAuthentication.getInstance(getActivity()), SharedPreferencesImpl.getInstance(getActivity())));
        signInLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> presenter.loginWithGoogle(result));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi();

    }

    private void initUi() {
        binding.etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    binding.etEmailLayout.setErrorEnabled(true);
                    binding.etEmailLayout.setError("Email is required");
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    binding.etEmailLayout.setErrorEnabled(true);
                    binding.etEmailLayout.setError("Invalid email");
                } else {
                    binding.etEmailLayout.setErrorEnabled(false);
                    binding.etEmailLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        binding.etPassword.addTextChangedListener(new TextWatcher() {
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
        binding.btnLoginWithEmail.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString(), password = binding.etPassword.getText().toString();
            presenter.loginWithEmail(email, password);
        });

        binding.btnSignInWithGoogle.setOnClickListener(v -> {
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
            mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
            mGoogleSignInClient.revokeAccess();
            Intent intent = mGoogleSignInClient.getSignInIntent();
            signInLauncher.launch(intent);
        });
    }

    private void passwordValidation(CharSequence s) {
        if (s.length() < 6) {
            binding.etPasswordLayout.setErrorEnabled(true);
            binding.etPasswordLayout.setError("Password must be at least 6 characters");
        } else if (s.length() > 20) {
            binding.etPasswordLayout.setErrorEnabled(true);
            binding.etPasswordLayout.setError("Password must be at most 20 characters");
        } else if (s.toString().contains(" ")) {
            binding.etPasswordLayout.setErrorEnabled(true);
            binding.etPasswordLayout.setError("Password must not contain spaces");
        } else if (!s.toString().matches(".*\\d.*")) {
            binding.etPasswordLayout.setErrorEnabled(true);
            binding.etPasswordLayout.setError("Password must contain at least one digit");
        } else if (!s.toString().matches(".*[a-z].*")) {
            binding.etPasswordLayout.setErrorEnabled(true);
            binding.etPasswordLayout.setError("Password must contain at least one lowercase letter");
        } else if (!s.toString().matches(".*[A-Z].*")) {
            binding.etPasswordLayout.setErrorEnabled(true);
            binding.etPasswordLayout.setError("Password must contain at least one uppercase letter");
        } else if (!s.toString().matches(".*[!@#$%^&*].*")) {
            binding.etPasswordLayout.setErrorEnabled(true);
            binding.etPasswordLayout.setError("Password must contain at least one special character");
        } else {
            binding.etPasswordLayout.setErrorEnabled(false);
            binding.etPasswordLayout.setError(null);
        }
    }

    @Override
    public void onLoginSuccess(Task<AuthResult> task) {
        FirebaseUser user = task.getResult().getUser();

        FirebaseRealtimeImpl.getInstance().verifyUserFavoriteMealsCreated(user.getUid());

        presenter.saveUserId(user.getUid());


        Log.i(TAG, "Login onComplete: user id: " + user.getUid());
        Toast.makeText(getContext(), "Authentication success.", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), HomeScreenActivity.class));
        getActivity().finish();
    }

    @Override
    public void onLoginFailure(Exception e) {
        Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "onLoginFailure:  " + e.getMessage());
    }

}