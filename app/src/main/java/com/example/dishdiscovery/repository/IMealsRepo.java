package com.example.dishdiscovery.repository;

import com.example.dishdiscovery.login.presenter.OnLoginComplete;

public interface IMealsRepo {
    void logInEmailPassword(OnLoginComplete onLoginComplete, String email, String password);
}
