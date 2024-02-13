package com.example.dishdiscovery.authDataSource;

import android.app.Activity;
import android.util.Log;

import androidx.activity.result.ActivityResult;

import com.example.dishdiscovery.R;
import com.example.dishdiscovery.authDataSource.authEmailPassword.IAuthEmailPassword;
import com.example.dishdiscovery.authDataSource.authGoogle.IAuthGoogle;
import com.example.dishdiscovery.login.presenter.OnLoginComplete;
import com.example.dishdiscovery.register.presenter.OnRegisterComplete;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class FirebaseAuthentication implements IAuthEmailPassword, IAuthGoogle {
    private static FirebaseAuthentication instance;
    private FirebaseAuth _firebaseAuth = null;
    private static final String TAG = "FirebaseAuthentication";
    private Activity activity;
    GoogleSignInClient mGoogleSignInClient;
    GoogleSignInOptions gso;


    private FirebaseAuthentication(Activity activity) {
        this.activity = activity;
        _firebaseAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(activity.getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(activity, gso);
        mGoogleSignInClient.revokeAccess();

    }

    public static synchronized FirebaseAuthentication getInstance(Activity activity) {
        if (instance == null) {
            Log.i(TAG, "getInstance: Creating new instance of FirebaseAuthentication");
            instance = new FirebaseAuthentication(activity);
        }
        return instance;
    }

    @Override
    public void loginWithEmail(OnLoginComplete onLoginComplete, String email, String password) {
        _firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, onLoginComplete::onComplete);
    }

    @Override
    public void registerWithEmail(OnRegisterComplete onRegisterComplete, String email, String password) {
        _firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, onRegisterComplete::onComplete);
    }

    @Override
    public void loginWithGoogle(OnLoginComplete onLoginComplete, ActivityResult result) {
        handleLGoogleAuth(GoogleSignIn.getSignedInAccountFromIntent(result.getData())).addOnCompleteListener(activity, onLoginComplete::onComplete);
    }

    private Task<AuthResult> handleLGoogleAuth(Task<GoogleSignInAccount> signInTask) {
        GoogleSignInAccount account = null;
        try {
            account = signInTask.getResult(ApiException.class);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
        AuthCredential googleCredential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        Task<AuthResult> authResultTask = _firebaseAuth.signInWithCredential(googleCredential);
        return authResultTask;

    }
}
