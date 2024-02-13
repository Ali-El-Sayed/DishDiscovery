package com.example.dishdiscovery.authDataSource.authGoogle;

import androidx.activity.result.ActivityResult;

import com.example.dishdiscovery.login.presenter.OnLoginComplete;

public interface IAuthGoogle {
    void loginWithGoogle(OnLoginComplete onLoginComplete, ActivityResult result);
}
