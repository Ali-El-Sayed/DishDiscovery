package com.example.dishdiscovery.login.presenter;

import androidx.annotation.NonNull;

import com.example.dishdiscovery.login.view.ILogin;
import com.example.dishdiscovery.repository.IMealsRepo;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginPresenter implements ILoginPresenter, OnLoginComplete {
    ILogin view;
    private IMealsRepo repo;

    public LoginPresenter(ILogin view, IMealsRepo repo) {
        this.view = view;
        this.repo = repo;
    }


    public void loginWithEmail(String email, String password) {
        repo.logInEmailPassword(this, email, password);
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) view.onLoginSuccess(task);
        else view.onLoginFailure(task.getException());
    }
}
