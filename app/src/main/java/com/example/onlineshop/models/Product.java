package com.example.onlineshop.models;

import java.io.Serializable;

public class Product implements Serializable {

    private String name;
    private int image;
    private long price;

    public Product() {
    }

    public Product(String name, int image, long price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
