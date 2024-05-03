package com.example.onlineshop.service;

import com.example.onlineshop.clients.ProductClient;
import com.example.onlineshop.handlers.ClientHandler;
import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.base.ServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class ProductService {

    public static void getNew(Callback<ServiceResponse<Product>> callback) {
        ClientHandler clientHandler = new ClientHandler();
        ProductClient client = clientHandler.getRetrofit().create(ProductClient.class);
        Call<ServiceResponse<Product>> responseCall = client.getNew();
        responseCall.enqueue(callback);
    }

    public static void getPopular(Callback<ServiceResponse<Product>> callback) {
        ClientHandler clientHandler = new ClientHandler();
        ProductClient client = clientHandler.getRetrofit().create(ProductClient.class);
        Call<ServiceResponse<Product>> responseCall = client.getPopular();
        responseCall.enqueue(callback);
    }
}
