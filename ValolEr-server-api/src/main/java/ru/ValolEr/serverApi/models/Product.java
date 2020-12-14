package ru.ValolEr.serverApi.models;

public class Product {
    private int id;
    private String article;
    private String name;
    private String type;
    private String supplier;
    private String warehouse;

    public Product(){

    }

    public Product(int id, String article, String name, String type, String supplier, String warehouse) {
        this.id = id;
        this.article = article;
        this.name = name;
        this.type = type;
        this.supplier = supplier;
        this.warehouse = warehouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", article='" + article + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", supplier='" + supplier + '\'' +
                ", warehouse='" + warehouse + '\'' +
                '}';
    }
}
