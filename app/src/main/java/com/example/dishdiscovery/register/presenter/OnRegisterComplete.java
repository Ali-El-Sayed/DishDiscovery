package com.example.dishdiscovery.register.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface OnRegisterComplete {
    void onComplete(@NonNull Task<AuthResult> task);

}
