package com.uchettovarovgeeks.jsonModel.jsonObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item extends NewItem {

    @SerializedName("total_qty")
    @Expose
    private int totalQty;

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

}
