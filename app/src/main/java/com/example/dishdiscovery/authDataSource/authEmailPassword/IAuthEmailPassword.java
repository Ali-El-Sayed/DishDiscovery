package com.example.dishdiscovery.authDataSource.authEmailPassword;

import com.example.dishdiscovery.login.presenter.OnLoginComplete;

public interface IAuthEmailPassword {

    void loginWithEmail(OnLoginComplete onLoginComplete, String email, String password);

}
