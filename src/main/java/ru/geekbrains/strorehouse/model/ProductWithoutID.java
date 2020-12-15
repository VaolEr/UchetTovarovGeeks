package ru.geekbrains.strorehouse.model;

public class ProductWithoutID {
    private String article;
    private String name;
    private int typeID;
    private int supplierID;
    private int warehouseID;

    public ProductWithoutID() {
    }

    public ProductWithoutID(String article, String name, int typeID, int supplierID, int warehouseID) {
        this.article = article;
        this.name = name;
        this.typeID = typeID;
        this.supplierID = supplierID;
        this.warehouseID = warehouseID;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public int getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(int warehouseID) {
        this.warehouseID = warehouseID;
    }
}
