package ru.geekbrains.strorehouse.model;

public class Product {
    private long id;
    private String article;
    private String name;
    private int typeID;
    private int supplierID;
    private int warehouseID;

    public Product(long id, String article, String name, int typeID, int supplierID, int warehouseID) {
        this.id = id;
        this.article = article;
        this.name = name;
        this.typeID = typeID;
        this.supplierID = supplierID;
        this.warehouseID = warehouseID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
