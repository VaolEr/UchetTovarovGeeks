package com.uchettovarovgeeks.data;

import com.uchettovarovgeeks.jsonModel.auth.Token;

public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    private AuthData authData;

    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public static LoginRepository getInstance() {
        return instance;
    }

    public boolean isLoggedIn() {
        return authData != null;
    }

    public void logout() {
        authData = null;
        dataSource.logout();
    }

    public Result<AuthData> login(String username, String password, Token token, boolean admin) {

        Result<AuthData> result = dataSource.login(username, password, token, admin);
        if (result instanceof Result.Success) {
            authData = (AuthData) ((Result.Success) result).getData();
        }
        return result;
    }

    public AuthData getAuthData() {
        return authData;
    }

    public void setAuthData(AuthData authData) {
        this.authData = authData;
    }
}