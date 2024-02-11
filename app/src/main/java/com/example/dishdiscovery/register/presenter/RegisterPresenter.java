package com.example.dishdiscovery.register.presenter;

import androidx.annotation.NonNull;

import com.example.dishdiscovery.register.view.IRegister;
import com.example.dishdiscovery.repository.IMealsRepo;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class RegisterPresenter implements IRegisterPresenter, OnRegisterComplete {

    private final IRegister view;
    private final IMealsRepo repo;

    public RegisterPresenter(IRegister view, IMealsRepo repo) {
        this.view = view;
        this.repo = repo;
    }


    @Override
    public void registerWithEmail(String email, String password) {
        repo.registerEmailPassword(this, email, password);
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
