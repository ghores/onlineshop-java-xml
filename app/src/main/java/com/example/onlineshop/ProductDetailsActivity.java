package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.onlineshop.models.Product;

public class ProductDetailsActivity extends AppCompatActivity {

    private Product product;
    private TextView price, name;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        init();
    }

    private void init() {
        bindViews();
        product = (Product) getIntent().getExtras().get("product");
        price.setText(product.getPrice() + "$");
        name.setText(product.getName());
        image.setImageDrawable(getResources().getDrawable(product.getImage(), null));
    }

    private void bindViews() {
        price = findViewById(R.id.product_price);
        name = findViewById(R.id.product_name);
        image = findViewById(R.id.product_image);
    }
}