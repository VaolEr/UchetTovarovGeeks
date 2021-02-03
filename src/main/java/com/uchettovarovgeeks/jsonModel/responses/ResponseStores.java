package com.uchettovarovgeeks.jsonModel.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Storehouse;

public class ResponseStores {
    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    @SerializedName("data")
    @Expose
    private Storehouse[] storehouse;

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Storehouse[] getStorehouse() {
        return storehouse;
    }

    public void setStorehouse(Storehouse[] storehouse) {
        this.storehouse = storehouse;
    }
}
