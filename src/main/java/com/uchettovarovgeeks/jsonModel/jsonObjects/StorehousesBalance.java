package com.uchettovarovgeeks.jsonModel.jsonObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StorehousesBalance {
    @SerializedName("quantity")
    @Expose
    private int quantity;

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StorehousesBalance() {    }


    public StorehousesBalance(int quantity, String name, int id) {
        this.quantity = quantity;
        this.name = name;
        this.id = id;
    }
}





