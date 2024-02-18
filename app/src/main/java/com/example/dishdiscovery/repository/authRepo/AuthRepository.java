package com.example.dishdiscovery.repository.authRepo;

import androidx.activity.result.ActivityResult;

import com.example.dishdiscovery.authDataSource.authEmailPassword.IAuthEmailPassword;
import com.example.dishdiscovery.authDataSource.authGoogle.IAuthGoogle;
import com.example.dishdiscovery.database.sharedPreferences.ISharedPreferences;
import com.example.dishdiscovery.login.presenter.OnLoginComplete;
import com.example.dishdiscovery.register.presenter.OnRegisterComplete;

public class AuthRepository implements IAuthRepo {
    private static AuthRepository _instance;
    private final IAuthEmailPassword _iAuthEmailPassword;
    private final IAuthGoogle _iAuthGoogle;
    private final ISharedPreferences _iSharedPreferences;

    private AuthRepository(IAuthEmailPassword iAuthEmailPassword, IAuthGoogle iAuthGoogle, ISharedPreferences iSharedPreferences) {
        _iAuthEmailPassword = iAuthEmailPassword;
        _iAuthGoogle = iAuthGoogle;
        _iSharedPreferences = iSharedPreferences;
    }

    public static synchronized AuthRepository getInstance(IAuthEmailPassword iAuthEmailPassword, IAuthGoogle iAuthGoogle, ISharedPreferences iSharedPreferences) {
        if (_instance == null) {
            _instance = new AuthRepository(iAuthEmailPassword, iAuthGoogle, iSharedPreferences);
        }
        return _instance;
    }

    @Override
    public void logInEmailPassword(OnLoginComplete onLoginComplete, String email, String password) {
        _iAuthEmailPassword.loginWithEmail(onLoginComplete, email, password);
    }

    @Override
    public void registerEmailPassword(OnRegisterComplete onRegisterComplete, String email, String password) {
        _iAuthEmailPassword.registerWithEmail(onRegisterComplete, email, password);
    }

    @Override
    public void logInGoogle(OnLoginComplete onLoginComplete, ActivityResult result) {
        _iAuthGoogle.loginWithGoogle(onLoginComplete, result);
    }

    @Override
    public void logOut() {
        _iSharedPreferences.deleteUserId();
    }

    @Override
    public String getUserId() {
        return _iSharedPreferences.getUserId();
    }

    @Override
    public void saveUserId(String userId) {
        _iSharedPreferences.saveUserId(userId);
    }


}
