package com.example.onlineshop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.adapters.ProductCategoryAdapter;
import com.example.onlineshop.models.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private Activity activity;
    private RecyclerView mainRecyclerView;
    private List<ProductCategory> mockDataList;

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
        mainRecyclerView.setAdapter(new ProductCategoryAdapter(mockDataList, activity));
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false));
    }

    private void fillMockDataList() {
        mockDataList = new ArrayList<>();
        mockDataList.add(new ProductCategory("New Balance", R.drawable.shoes));
        mockDataList.add(new ProductCategory("Nike", R.drawable.nike));
        mockDataList.add(new ProductCategory("Adidas", R.drawable.adidas));
        mockDataList.add(new ProductCategory("Sorel", R.drawable.sorel));
        mockDataList.add(new ProductCategory("Skechers", R.drawable.skechers));
        mockDataList.add(new ProductCategory("Underarmour", R.drawable.underarmour));
    }

    private void bindViews(ViewGroup view) {
        mainRecyclerView = view.findViewById(R.id.mainRecyclerView);
    }
}
