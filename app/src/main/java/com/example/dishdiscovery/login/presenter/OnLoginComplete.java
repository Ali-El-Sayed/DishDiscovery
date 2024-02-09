package com.example.dishdiscovery.login.presenter;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public interface OnLoginComplete {
    void onComplete(@NonNull Task<AuthResult> task);

}
