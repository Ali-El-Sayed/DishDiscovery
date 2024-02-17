package com.example.dishdiscovery.login.presenter;


import androidx.activity.result.ActivityResult;

public interface ILoginPresenter {
    void loginWithEmail(String email, String password);

    void loginWithGoogle(ActivityResult result);

    void saveUserId(String userId);

}
