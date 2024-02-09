package com.example.dishdiscovery.network.auth;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthentication implements IAuth {
    private static FirebaseAuth instance;
    private static final String TAG = "FirebaseAuthentication";
    private Activity activity;

    private FirebaseAuthentication() {
        instance = FirebaseAuth.getInstance();

    }

    public static synchronized FirebaseAuth getInstance(Activity activity) {
        if (instance == null) new FirebaseAuthentication();

        return instance;
    }

    @Override
    public void loginWithEmail(String email, String password) {
        instance.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                // Log.d(TAG, "signInWithEmail:success");
                // FirebaseUser user = instance.getCurrentUser();
                // updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                // Log.w(TAG, "signInWithEmail:failure", task.getException());
                // Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                //         Toast.LENGTH_SHORT).show();
                // updateUI(null);
            }
        });
    }
}
