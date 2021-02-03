package com.uchettovarovgeeks.ui.authentefication;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
class LoginResult {
    @Nullable
    private com.uchettovarovgeeks.ui.authentefication.LoggedInUserView success;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable com.uchettovarovgeeks.ui.authentefication.LoggedInUserView success) {
        this.success = success;
    }

    @Nullable
    com.uchettovarovgeeks.ui.authentefication.LoggedInUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}