package com.example.onlineshop.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.ProductActivity;
import com.example.onlineshop.R;
import com.example.onlineshop.enums.ApiAddress;
import com.example.onlineshop.models.ProductCategory;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ViewHolder> {

    private final List<ProductCategory> dataList;
    private final Activity activity;

    public ProductCategoryAdapter(List<ProductCategory> dataList, Activity activity) {
        this.dataList = dataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_category_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductCategory category = dataList.get(position);
        TextView title = holder.title;
        ImageView image = holder.image;
        title.setText(category.getTitle());
        Picasso.get().load(ApiAddress.getFileUrl(category.getImage())).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productIntent = new Intent(activity, ProductActivity.class);
                productIntent.putExtra("category", category);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair(title, "category_name");
                pairs[1] = new Pair(image, "category_image");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, pairs);
                activity.startActivity(productIntent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView image;
        private final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_category_image);
            title = itemView.findViewById(R.id.product_category_title);
        }
    }
}
