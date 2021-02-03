package com.uchettovarovgeeks.jsonModel.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ResponseItemsList {

    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    @SerializedName("data")
    @Expose
    private ResponseData responseData;

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

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }
}
