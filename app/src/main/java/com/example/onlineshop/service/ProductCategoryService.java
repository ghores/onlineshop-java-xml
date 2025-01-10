package com.example.onlineshop.service;

import com.example.onlineshop.clients.ProductCategoryClient;
import com.example.onlineshop.handlers.ClientHandler;
import com.example.onlineshop.models.ProductCategory;
import com.example.onlineshop.models.base.ServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductCategoryService {

    public static void getProductCategory(Callback<ServiceResponse<ProductCategory>> callback) {
        ClientHandler clientHandler = new ClientHandler();
        ProductCategoryClient client = clientHandler.getRetrofit().create(ProductCategoryClient.class);
        Call<ServiceResponse<ProductCategory>> responseCall = client.getProductCategory();
        responseCall.enqueue(callback);
    }
}
