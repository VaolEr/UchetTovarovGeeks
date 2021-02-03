package com.uchettovarovgeeks.jsonModel.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Category;

public class ResponseCategories {

    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    @SerializedName("data")
    @Expose
    private Category[] categories;

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

    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }
}
