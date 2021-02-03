package com.uchettovarovgeeks.data;

import com.uchettovarovgeeks.jsonModel.auth.Token;

import java.io.IOException;

public class LoginDataSource {

    public Result<AuthData> login(String username, String password, Token token, boolean admin) {
        if (!username.equals("")) {
            AuthData user =
                    new AuthData(
                            username,
                            password,
                            token,
                            admin);

            return new Result.Success<>(user);
        } else {


            return new Result.Error(new IOException("Error logging in"));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}