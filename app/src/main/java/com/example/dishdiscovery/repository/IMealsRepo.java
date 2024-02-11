package com.example.dishdiscovery.repository;

import com.example.dishdiscovery.login.presenter.OnLoginComplete;
import com.example.dishdiscovery.register.presenter.OnRegisterComplete;

public interface IMealsRepo {
    void logInEmailPassword(OnLoginComplete onLoginComplete, String email, String password);
    void registerEmailPassword(OnRegisterComplete onRegisterComplete, String email, String password);
}
