package com.example.onlineshop;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.adapters.ProductAdapter;
import com.example.onlineshop.enums.ApiAddress;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductCategory;
import com.example.onlineshop.models.base.ServiceResponse;
import com.example.onlineshop.service.ProductService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductActivity extends AppCompatActivity {

    private ProductCategory category;
    private TextView name;
    private ImageView image;
    private RecyclerView productsRecyclerView;
    private NestedScrollView mainScrollView;
    private ProgressBar progressBar;
    private List<Product> dataList;
    private int pageNumber = 0, pageSize = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        init();
    }

    private void init() {
        bindViews();
        if (getIntent().getExtras()!=null) {
            category = (ProductCategory)getIntent().getExtras().get("category");
        }
        if (category != null) {
            name.setText(category.getTitle());
            Picasso.get().load(ApiAddress.getFileUrl(category.getImage())).into(image);
        }
        dataList = new ArrayList<>();
        fillProductsDataList();

        mainScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    progressBar.setVisibility(View.VISIBLE);
                    pageNumber++;
                    fillProductsDataList();
                }
            }
        });
    }

    private void fillProductsDataList() {
        ProductService.getByCategory(new Callback<ServiceResponse<Product>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Product>> call, Response<ServiceResponse<Product>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isHasError()) {
                    List<Product> newDataList = response.body().getDataList();
                    dataList.addAll(newDataList);
                    productsRecyclerView.setAdapter(new ProductAdapter(ProductActivity.this, dataList));
                    productsRecyclerView.setLayoutManager(new GridLayoutManager(ProductActivity.this, 2, RecyclerView.VERTICAL, false));
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Product>> call, Throwable t) {
                Toast.makeText(ProductActivity.this, "Error on getting product categories data from server", Toast.LENGTH_SHORT).show();
            }
        }, category.getId(), pageNumber, pageSize);
    }

    private void bindViews() {
        name = findViewById(R.id.category_name);
        image = findViewById(R.id.category_image);
        productsRecyclerView = findViewById(R.id.products_recycler);
        mainScrollView = findViewById(R.id.product_scrollview);
        progressBar = findViewById(R.id.product_progressbar);
    }
}