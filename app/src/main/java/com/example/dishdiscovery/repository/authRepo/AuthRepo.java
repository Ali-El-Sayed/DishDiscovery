package com.example.dishdiscovery.repository.authRepo;

import com.example.dishdiscovery.network.auth.IAuth;

public class AuthRepo {

    private IAuth iAuth;

    private static AuthRepo instance;

    private AuthRepo() {
        this.iAuth = iAuth;
    }

    public static synchronized AuthRepo getInstance(IAuth iAuth) {
        if (instance == null) new AuthRepo();
        return instance;
    }

    public void loginWithEmail(String email, String password) {
        iAuth.loginWithEmail(email, password);
    }


}
