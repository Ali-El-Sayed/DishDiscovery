package com.example.dishdiscovery.register.view;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface IRegister {
    void onRegisterSuccess(Task<AuthResult> task);

    void onRegisterFailure(Exception e);
}
