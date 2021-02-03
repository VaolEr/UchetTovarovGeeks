package com.uchettovarovgeeks.jsonModel.jsonObjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Category {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String mane) {
        this.name = mane;
    }

    @Override
    public String toString() {
        return "name";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return
                getName().equals(category.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
