package com.uchettovarovgeeks.jsonModel.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Item;
import com.uchettovarovgeeks.jsonModel.jsonObjects.Pageable;

public class ResponseData {

    @SerializedName("content")
    @Expose
    private Item[] items;

    @SerializedName("total_pages")
    @Expose
    private int totalPages;

    @SerializedName("total_еlements")
    @Expose
    private int totalElements;

    @SerializedName("pageable")
    @Expose
    private Pageable pageable;

    @SerializedName("number")
    @Expose
    private int number;

    @SerializedName("number_of_elements")
    @Expose
    private int numberOfElements;

    @SerializedName("size")
    @Expose
    private int size;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }


}
