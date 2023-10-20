package com.example.onlineshop.models;

public class CardItem {
    private int id;
    private Product product;
    private Color color;
    private Size size;
    private int quantity;

    public static String KEY_ID = "id";
    public static String KEY_PRODUCT = "product_id";
    public static String KEY_COLOR = "color_id";
    public static String KEY_SIZE = "size_id";
    public static String KEY_QUANTITY = "qty";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
