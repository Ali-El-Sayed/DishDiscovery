package com.example.dishdiscovery.login.view;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface ILogin {
    void onLoginSuccess(Task<AuthResult> task);

    void onLoginFailure(Exception e);
}
