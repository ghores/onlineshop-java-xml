package com.example.onlineshop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.adapters.ProductAdapter;
import com.example.onlineshop.adapters.ProductCategoryAdapter;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductCategory;
import com.example.onlineshop.models.base.ServiceResponse;
import com.example.onlineshop.service.ProductCategoryService;
import com.example.onlineshop.service.ProductService;
import com.google.android.material.chip.Chip;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragment extends Fragment {

    private final Activity activity;
    private RecyclerView categoryRecyclerView, filteredRecyclerView;
    private Chip cheapChip, newChip, popularChip, expensiveChip;
    private TextView filterText;

    public ProductsFragment(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frame_product, container, false);
        init(rootView);
        return rootView;
    }

    private void init(ViewGroup root) {
        bindViews(root);
        fillProductCategoryData();
        GridLayoutManager newProductsLayoutManager = new GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false);
        filteredRecyclerView.setLayoutManager(newProductsLayoutManager);
        fillNewProductsData();
        fillPopularProductsData();

        newChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fillNewProductsData();
                }
            }
        });

        popularChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    fillPopularProductsData();
                }
            }
        });

        cheapChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//
                }
            }
        });

        expensiveChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                }
            }
        });
    }

    private void bindViews(ViewGroup root) {
        categoryRecyclerView = root.findViewById(R.id.category_list);
        filteredRecyclerView = root.findViewById(R.id.filtered_product_list);
        cheapChip = root.findViewById(R.id.cheap_chip);
        expensiveChip = root.findViewById(R.id.expensive_chip);
        popularChip = root.findViewById(R.id.popular_chip);
        newChip = root.findViewById(R.id.new_chip);
        filterText = root.findViewById(R.id.filtered_text);
    }

    private void fillProductCategoryData() {
        ProductCategoryService.getAll(new Callback<ServiceResponse<ProductCategory>>() {
            @Override
            public void onResponse(Call<ServiceResponse<ProductCategory>> call, Response<ServiceResponse<ProductCategory>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isHasError()) {
                    List<ProductCategory> dataList = response.body().getDataList();
                    categoryRecyclerView.setAdapter(new ProductCategoryAdapter(dataList, activity));
                    LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
                    categoryRecyclerView.setLayoutManager(layoutManager);
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<ProductCategory>> call, Throwable t) {
                Toast.makeText(activity, "Error on getting product categories data from server", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fillNewProductsData() {
        ProductService.getNew(new Callback<ServiceResponse<Product>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Product>> call, Response<ServiceResponse<Product>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isHasError()) {
                    List<Product> dataList = response.body().getDataList();
                    filteredRecyclerView.setAdapter(new ProductAdapter(activity, dataList));
                    filterText.setText(getResources().getText(R.string.new_products));
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Product>> call, Throwable t) {
                Toast.makeText(activity, "Error on getting products data from server", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void fillPopularProductsData() {
        ProductService.getPopular(new Callback<ServiceResponse<Product>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Product>> call, Response<ServiceResponse<Product>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isHasError()) {
                    List<Product> dataList = response.body().getDataList();
                    filteredRecyclerView.setAdapter(new ProductAdapter(activity, dataList));
                    filterText.setText(getResources().getText(R.string.popular_products));
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Product>> call, Throwable t) {
                Toast.makeText(activity, "Error on getting products data from server", Toast.LENGTH_LONG).show();
            }
        });
    }
}