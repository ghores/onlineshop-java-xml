package com.example.onlineshop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductCategory;
import com.example.onlineshop.models.SliderItem;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private Activity activity;
    private RecyclerView mainRecyclerView;
    private RecyclerView newProductsRecyclerView;
    private RecyclerView popularProductRecyclerView;
    private List<ProductCategory> productCategories;
    private List<Product> products;
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
        fillMockDataList();
        mainRecyclerView.setAdapter(new ProductCategoryAdapter(productCategories, activity));
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));

        newProductsRecyclerView.setAdapter(new ProductAdapter(activity, products));
        newProductsRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false));

        popularProductRecyclerView.setAdapter(new ProductAdapter(activity, products));
        popularProductRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false));

        SliderAdapter sliderAdapter = new SliderAdapter(activity);
        sliderAdapter.addItem(new SliderItem("Shopping 1","https://imgscf.slidemembers.com/docs/1/1/259/shopping_slide_ppt_258349.jpg"));
        sliderAdapter.addItem(new SliderItem("Shopping 2","https://imgscf.slidemembers.com/docs/1/1/259/shopping_slide_ppt_258355.jpg"));
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.startAutoCycle();

    }

    private void bindViews(ViewGroup view) {
        mainRecyclerView = view.findViewById(R.id.mainRecyclerView);
        newProductsRecyclerView = view.findViewById(R.id.newProductsRecyclerView);
        popularProductRecyclerView = view.findViewById(R.id.popularProductsRecyclerView);
        sliderView = view.findViewById(R.id.imageSlider);
    }

    private void fillMockDataList() {
        productCategories = new ArrayList<>();
        productCategories.add(new ProductCategory("New Balance", R.drawable.shoes));
        productCategories.add(new ProductCategory("Nike", R.drawable.nike));
        productCategories.add(new ProductCategory("Adidas", R.drawable.adidas));
        productCategories.add(new ProductCategory("Sorel", R.drawable.sorel));
        productCategories.add(new ProductCategory("Skechers", R.drawable.skechers));
        productCategories.add(new ProductCategory("Underarmour", R.drawable.underarmour));

        products = new ArrayList<>();
        products.add(new Product("New Balance", R.drawable.shoes, 3400));
        products.add(new Product("Nike", R.drawable.nike, 2600));
        products.add(new Product("Adidas", R.drawable.adidas, 1900));
        products.add(new Product("Sorel", R.drawable.sorel, 2800));
        products.add(new Product("Skechers", R.drawable.skechers, 3200));
        products.add(new Product("Underarmour", R.drawable.underarmour, 4400));
    }
}
