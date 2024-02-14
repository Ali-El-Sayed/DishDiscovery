package com.example.dishdiscovery.repository.authRepo;

import androidx.activity.result.ActivityResult;

import com.example.dishdiscovery.authDataSource.authEmailPassword.IAuthEmailPassword;
import com.example.dishdiscovery.authDataSource.authGoogle.IAuthGoogle;
import com.example.dishdiscovery.login.presenter.OnLoginComplete;
import com.example.dishdiscovery.register.presenter.OnRegisterComplete;

public class AuthRepository implements IAuthRepo {
    private final IAuthEmailPassword _iAuthEmailPassword;
    private final IAuthGoogle _iAuthGoogle;
    private static AuthRepository _instance;

    private AuthRepository(IAuthEmailPassword iAuthEmailPassword, IAuthGoogle iAuthGoogle) {
        _iAuthEmailPassword = iAuthEmailPassword;
        _iAuthGoogle = iAuthGoogle;
    }

    public static synchronized AuthRepository getInstance(IAuthEmailPassword iAuthEmailPassword, IAuthGoogle iAuthGoogle) {
        if (_instance == null) {
            _instance = new AuthRepository(iAuthEmailPassword, iAuthGoogle);
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
        _iAuthGoogle.loginWithGoogle(onLoginComplete ,result);
    }


}
