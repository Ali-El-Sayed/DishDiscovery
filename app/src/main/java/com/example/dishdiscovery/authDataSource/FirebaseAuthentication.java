package com.example.dishdiscovery.authDataSource;

import android.app.Activity;

import com.example.dishdiscovery.authDataSource.authEmailPassword.IAuthEmailPassword;
import com.example.dishdiscovery.login.presenter.OnLoginComplete;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthentication implements IAuthEmailPassword {
    private static FirebaseAuthentication instance;
    private FirebaseAuth _firebaseAuth = null;
    private static final String TAG = "FirebaseAuthentication";
    private Activity activity;

    private FirebaseAuthentication(Activity activity) {
        this.activity = activity;
        _firebaseAuth = FirebaseAuth.getInstance();
    }

    public static synchronized FirebaseAuthentication getInstance(Activity activity) {
        if (instance == null) instance = new FirebaseAuthentication(activity);
        return instance;
    }

    @Override
    public void loginWithEmail(OnLoginComplete onLoginComplete, String email, String password) {
        _firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, onLoginComplete::onComplete);
    }
}
