package com.example.onlineshop.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.onlineshop.R;
import com.example.onlineshop.adapters.ProductAdapter;
import com.example.onlineshop.adapters.ProductCategoryAdapter;
import com.example.onlineshop.mock.MockDataHandler;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.ProductCategory;
import com.google.android.material.chip.Chip;
import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    private final Activity activity;
    private RecyclerView categoryRecyclerView, filteredRecyclerView;
    private Chip cheapChip, newChip, popularChip, expensiveChip;
    private TextView filterText;

    private List<ProductCategory> productCategories;
    private List<Product> products1;
    private List<Product> products2;
    private List<Product> products3;
    private List<Product> products4;

    public ProductFragment(Activity activity) {
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
        fillMockDataList();
        categoryRecyclerView.setAdapter(new ProductCategoryAdapter(productCategories,activity));
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);

        GridLayoutManager newProductsLayoutManager = new GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false);
        filteredRecyclerView.setAdapter(new ProductAdapter(activity, products1));
        filteredRecyclerView.setLayoutManager(newProductsLayoutManager);

        newChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filteredRecyclerView.setAdapter(new ProductAdapter(activity, products1));
                    filterText.setText(getResources().getText(R.string.new_products));
                }
            }
        });

        popularChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filteredRecyclerView.setAdapter(new ProductAdapter(activity, products2));
                    filterText.setText(getResources().getText(R.string.popular_products));
                }
            }
        });

        cheapChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filteredRecyclerView.setAdapter(new ProductAdapter(activity, products3));
                    filterText.setText(getResources().getText(R.string.cheap_products));
                }
            }
        });

        expensiveChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filteredRecyclerView.setAdapter(new ProductAdapter(activity, products4));
                    filterText.setText(getResources().getText(R.string.expensive_products));
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


    private void fillMockDataList() {

        productCategories = MockDataHandler.getProductCategories();

        products1 = new ArrayList<>();
        products2 = new ArrayList<>();
        products3 = new ArrayList<>();
        products4 = new ArrayList<>();
        products1.add(new Product(1,"New Balance", R.drawable.shoes, 3400));
        products1.add(new Product(2,"Nike", R.drawable.nike, 2600));
        products1.add(new Product(3,"Adidas", R.drawable.adidas, 1900));
        products2.add(new Product(4,"Sorel", R.drawable.sorel, 2800));
        products2.add(new Product(5,"Skechers", R.drawable.skechers, 3200));
        products2.add(new Product(6,"Underarmour", R.drawable.underarmour, 4400));
        products3.add(new Product(5,"Skechers", R.drawable.skechers, 3200));
        products3.add(new Product(2,"Nike", R.drawable.nike, 2600));
        products3.add(new Product(6,"Underarmour", R.drawable.underarmour, 4400));
        products4.add(new Product(6,"Underarmour", R.drawable.underarmour, 4400));
    }
}
