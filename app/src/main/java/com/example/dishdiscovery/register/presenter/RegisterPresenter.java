package com.example.dishdiscovery.register.presenter;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;

import com.example.dishdiscovery.login.presenter.OnLoginComplete;
import com.example.dishdiscovery.register.view.IRegister;
import com.example.dishdiscovery.repository.authRepo.IAuthRepo;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class RegisterPresenter implements IRegisterPresenter, OnRegisterComplete, OnLoginComplete {

    private final IRegister view;
    private final IAuthRepo repo;

    public RegisterPresenter(IRegister view, IAuthRepo repo) {
        this.view = view;
        this.repo = repo;
    }


    @Override
    public void registerWithEmail(String email, String password) {
        repo.registerEmailPassword(this, email, password);
    }

    @Override
    public void loginWithGoogle(ActivityResult result) {
        repo.logInGoogle(this, result);
    }

    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if (task.isSuccessful()) {
            view.onRegisterSuccess(task);
        } else {
            view.onRegisterFailure(task.getException());
        }
    }
}
