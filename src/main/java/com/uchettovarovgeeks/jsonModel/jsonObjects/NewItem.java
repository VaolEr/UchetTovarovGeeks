package com.uchettovarovgeeks.jsonModel.jsonObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewItem {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("categories")
    @Expose
    private Category[] categories;

    @SerializedName("supplier")
    @Expose
    private Supplier supplier;

    @SerializedName("unit")
    @Expose
    private Unit unit;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @SerializedName("storehouses_balance")
    @Expose
    private StorehousesBalance[] storehousesBalance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }


    public Category[] getCategories() {
        return categories;
    }

    public void setCategories(Category[] categories) {
        this.categories = categories;
    }

    public Supplier getSuppliers() {
        return supplier;
    }

    public void setSuppliers(Supplier suppliers) {
        this.supplier = suppliers;
    }

    public StorehousesBalance[] getStorehousesBalance() {
        return storehousesBalance;
    }

    public void setStorehousesBalance(StorehousesBalance[] storehousesBalance) {
        this.storehousesBalance = storehousesBalance;
    }

    public NewItem() {
    }
}
