package com.example.onlineshop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.onlineshop.adapters.SliderAdapter;
import com.example.onlineshop.clients.SliderClient;
import com.example.onlineshop.handlers.ClientHandler;
import com.example.onlineshop.mock.MockDataHandler;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductCategory;
import com.example.onlineshop.models.SliderItem;
import com.example.onlineshop.models.base.ServiceResponse;
import com.example.onlineshop.service.ProductCategoryService;
import com.example.onlineshop.service.ProductService;
import com.example.onlineshop.service.SliderService;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private final Activity activity;
    private RecyclerView mainRecyclerView;
    private RecyclerView newProductsRecyclerView;
    private RecyclerView popularProductRecyclerView;
    private List<ProductCategory> productCategories;
    private SliderView sliderView;

    public HomeFragment(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frame_home, container, false);
        init(rootView);
        return rootView;
    }

    private void init(ViewGroup view) {
        bindViews(view);
        fillSliderData();
        fillProductCategoryData();
        fillNewProductData();
        fillPopularProductsData();
    }

    private void fillSliderData() {
        SliderAdapter sliderAdapter = new SliderAdapter(activity);
        SliderService.getAll(new Callback<ServiceResponse<SliderItem>>() {
            @Override
            public void onResponse(Call<ServiceResponse<SliderItem>> call, Response<ServiceResponse<SliderItem>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isHasError()) {
                    List<SliderItem> sliderItems = response.body().getDataList();
                    sliderAdapter.setItems(sliderItems);
                    sliderView.setSliderAdapter(sliderAdapter);
                    sliderView.setScrollTimeInSec(3);
                    sliderView.startAutoCycle();
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<SliderItem>> call, Throwable t) {
                Toast.makeText(activity, "Error on getting sliders data from server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillNewProductData() {
        ProductService.getNew(new Callback<ServiceResponse<Product>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Product>> call, Response<ServiceResponse<Product>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isHasError()) {

                    List<Product> productList = response.body().getDataList();
                    newProductsRecyclerView.setAdapter(new ProductAdapter(activity, productList));
                    newProductsRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false));
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Product>> call, Throwable t) {
                Toast.makeText(activity, "Error on getting product categories data from server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillPopularProductsData() {
        ProductService.getPopular(new Callback<ServiceResponse<Product>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Product>> call, Response<ServiceResponse<Product>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isHasError()) {

                    List<Product> popularList = response.body().getDataList();
                    popularProductRecyclerView.setAdapter(new ProductAdapter(activity, popularList));
                    popularProductRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false));
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Product>> call, Throwable t) {
                Toast.makeText(activity, "Error on getting product categories data from server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillProductCategoryData() {
        ProductCategoryService.getAll(new Callback<ServiceResponse<ProductCategory>>() {
            @Override
            public void onResponse(Call<ServiceResponse<ProductCategory>> call, Response<ServiceResponse<ProductCategory>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isHasError()) {

                    List<ProductCategory> productCategoryList = response.body().getDataList();
                    mainRecyclerView.setAdapter(new ProductCategoryAdapter(productCategoryList, activity));
                    mainRecyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<ProductCategory>> call, Throwable t) {
                Toast.makeText(activity, "Error on getting product categories data from server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindViews(ViewGroup view) {
        mainRecyclerView = view.findViewById(R.id.mainRecyclerView);
        newProductsRecyclerView = view.findViewById(R.id.newProductsRecyclerView);
        popularProductRecyclerView = view.findViewById(R.id.popularProductsRecyclerView);
        sliderView = view.findViewById(R.id.imageSlider);
    }
}
