package com.example.onlineshop.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.enums.ApiAddress;
import com.example.onlineshop.models.CardItem;
import com.squareup.picasso.Picasso;

import java.util.List;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private final List<CardItem> dataList;
    private final Activity activity;

    public CardAdapter(Activity activity, List<CardItem> dataList) {
        this.dataList = dataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_item_layout, parent, false);
        return new CardViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardItem cardItem = dataList.get(position);
        TextView name = holder.name;
        TextView price = holder.price;
        TextView quantity = holder.quantity;

        String productTitle = cardItem.getProduct().getTitle();
        if (cardItem.getSize() != null) {
            productTitle += " - " + cardItem.getSize().getTitle();
        }
        if (cardItem.getColor() != null) {
            productTitle += " ( " + cardItem.getColor().getName() + " )";
        }
        name.setText(productTitle);
        Picasso.get().load(ApiAddress.getFileUrl(cardItem.getProduct().getImage())).into(holder.image);
        price.setText(cardItem.getProduct().getPrice() + "$");
        quantity.setText(String.valueOf(cardItem.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView name, price, quantity;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.product_image);
            name = itemView.findViewById(R.id.product_name);
            price = itemView.findViewById(R.id.product_price);
            quantity = itemView.findViewById(R.id.product_quantity);
        }
    }
}
