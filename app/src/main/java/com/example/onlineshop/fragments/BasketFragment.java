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
import com.example.onlineshop.adapters.CardAdapter;
import com.example.onlineshop.adapters.ProductCategoryAdapter;
import com.example.onlineshop.handlers.CardDBHandler;
import com.example.onlineshop.models.CardItem;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment {

    private final Activity activity;
    private RecyclerView basketView;
    private List<CardItem> dataList;

    public BasketFragment(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frame_basket, container, false);
        init(rootView);
        return rootView;
    }

    private void init(ViewGroup rootView) {
        bindViews(rootView);
        fillDataList();
        basketView.setAdapter(new CardAdapter(activity, dataList));
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
        basketView.setLayoutManager(layoutManager);
    }

    private void bindViews(ViewGroup rootView) {
        basketView = rootView.findViewById(R.id.basket_recycler_view);
    }

    private void fillDataList() {
        dataList = new ArrayList<>();
        CardDBHandler dbHandler = new CardDBHandler(activity);
        dataList = dbHandler.getAllData();
    }
}
