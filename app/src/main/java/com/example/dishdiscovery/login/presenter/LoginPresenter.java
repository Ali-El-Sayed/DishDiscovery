package com.example.dishdiscovery.login.presenter;

import androidx.annotation.NonNull;

import com.example.dishdiscovery.login.view.ILogin;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginPresenter implements ILoginPresenter {
    ILogin view;

    public LoginPresenter(ILogin view) {
        this.view = view;
    }


    public void loginWithEmail(String email, String password) {
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) view.onLoginSuccess(task);
        else view.onLoginFailure(task.getException());
    }
}
