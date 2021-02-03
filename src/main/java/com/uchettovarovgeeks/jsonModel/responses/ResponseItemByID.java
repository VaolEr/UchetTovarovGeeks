package com.uchettovarovgeeks.jsonModel.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Item;

public class ResponseItemByID {
    @SerializedName("responseStatus")
    @Expose
    private String responseStatus;

    @SerializedName("errorMessage")
    @Expose
    private String errorMessage;

    @SerializedName("data")
    @Expose
    private Item item;

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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
