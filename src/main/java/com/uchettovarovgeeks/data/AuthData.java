package com.uchettovarovgeeks.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uchettovarovgeeks.jsonModel.auth.Token;

public class AuthData {

    private String email;
    private String password;
    private Token token;
    private Boolean admin;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public AuthData(String email, String password, Token token, Boolean admin) {
        this.email = email;
        this.password = password;
        this.token = token;
        this.admin = admin;
    }
}
