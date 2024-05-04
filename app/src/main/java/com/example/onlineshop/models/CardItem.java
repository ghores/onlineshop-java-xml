package com.example.onlineshop.models;

import android.database.Cursor;

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
    public static String KEY_PRODUCT_IMAGE = "product_image";
    public static String KEY_PRODUCT_NAME = "product_name";
    public static String KEY_PRODUCT_PRICE = "product_price";
    public static String KEY_COLOR_NAME = "color_name";
    public static String KEY_COLOR_VALUE = "color_value";
    public static String KEY_SIZE_TITLE = "size_title";

    public CardItem() {
    }

    public CardItem(Cursor cursor) {
        this.setId(Integer.parseInt(cursor.getString(0)));
        this.setQuantity(Integer.parseInt(cursor.getString(4)));
        Product product = new Product();
        product.setId(Long.parseLong(cursor.getString(1)));
        product.setTitle(cursor.getString(8));
        product.setImage(cursor.getString(7));
        product.setPrice(Long.parseLong(cursor.getString(9)));
        this.setProduct(product);
        Size size = new Size();
        size.setTitle(cursor.getString(10));
        size.setId(Long.parseLong(cursor.getString(2)));
        this.setSize(size);
        Color color = new Color();
        color.setId(Long.parseLong(cursor.getString(3)));
        color.setName(cursor.getString(5));
        color.setValue(cursor.getString(6));
        this.setColor(color);
    }

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
