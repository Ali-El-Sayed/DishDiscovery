package com.example.dishdiscovery.register.presenter;

import androidx.activity.result.ActivityResult;

public interface IRegisterPresenter {


    void registerWithEmail(String email, String password);

    void loginWithGoogle(ActivityResult result);

    void saveUserId(String userId);
}
