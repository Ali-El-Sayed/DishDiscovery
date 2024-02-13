package com.example.dishdiscovery.authDataSource.authEmailPassword;

import com.example.dishdiscovery.login.presenter.OnLoginComplete;
import com.example.dishdiscovery.register.presenter.OnRegisterComplete;

public interface IAuthEmailPassword {

    void loginWithEmail(OnLoginComplete onLoginComplete, String email, String password);
    void registerWithEmail(OnRegisterComplete onRegisterComplete, String email, String password);



}
