package com.example.dishdiscovery.repository.authRepo;

import androidx.activity.result.ActivityResult;

import com.example.dishdiscovery.login.presenter.OnLoginComplete;
import com.example.dishdiscovery.register.presenter.OnRegisterComplete;

public interface IAuthRepo {
    void logInEmailPassword(OnLoginComplete onLoginComplete, String email, String password);

    void registerEmailPassword(OnRegisterComplete onRegisterComplete, String email, String password);

    void logInGoogle(OnLoginComplete onLoginComplete, ActivityResult result);

    void logOut();

    String getUserId();

    void saveUserId(String userId);

}
