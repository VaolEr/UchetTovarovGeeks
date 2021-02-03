package com.uchettovarovgeeks.jsonModel.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Supplier;

public class ResponseSuppliers {
    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    @SerializedName("data")
    @Expose
    private Supplier[] suppliers;

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

    public Supplier[] getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Supplier[] suppliers) {
        this.suppliers = suppliers;
    }
}
