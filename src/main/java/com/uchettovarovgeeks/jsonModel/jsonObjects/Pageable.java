package com.uchettovarovgeeks.jsonModel.jsonObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pageable {
    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("size")
    @Expose
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
