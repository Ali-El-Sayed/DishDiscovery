package com.example.dishdiscovery.repository.authRepo;

import com.example.dishdiscovery.authDataSource.authEmailPassword.IAuthEmailPassword;
import com.example.dishdiscovery.login.presenter.OnLoginComplete;
import com.example.dishdiscovery.register.presenter.OnRegisterComplete;

public class AuthRepository implements IAuthRepo {
    private final IAuthEmailPassword  _iAuthEmailPassword;
    private static AuthRepository _instance;

    private AuthRepository(IAuthEmailPassword iAuthEmailPassword) {
        _iAuthEmailPassword = iAuthEmailPassword;
    }

    public static synchronized AuthRepository getInstance(IAuthEmailPassword iAuthEmailPassword) {
        if (_instance == null) {
            _instance = new AuthRepository(iAuthEmailPassword);
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


}
