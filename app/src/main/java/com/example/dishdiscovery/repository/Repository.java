package com.example.dishdiscovery.repository;

import com.example.dishdiscovery.authDataSource.authEmailPassword.IAuthEmailPassword;
import com.example.dishdiscovery.login.presenter.OnLoginComplete;
import com.example.dishdiscovery.register.presenter.OnRegisterComplete;

public class Repository implements IMealsRepo {
    private final IAuthEmailPassword  _iAuthEmailPassword;
    private static Repository _instance;

    private Repository(IAuthEmailPassword iAuthEmailPassword) {
        _iAuthEmailPassword = iAuthEmailPassword;
    }

    public static synchronized Repository getInstance(IAuthEmailPassword iAuthEmailPassword) {
        if (_instance == null) {
            _instance = new Repository(iAuthEmailPassword);
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
