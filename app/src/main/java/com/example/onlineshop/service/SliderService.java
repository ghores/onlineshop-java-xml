package com.example.onlineshop.service;

import com.example.onlineshop.clients.SliderClient;
import com.example.onlineshop.handlers.ClientHandler;
import com.example.onlineshop.models.SliderItem;
import com.example.onlineshop.models.base.ServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class SliderService {

    public static void getAll(Callback<ServiceResponse<SliderItem>> callback) {
        ClientHandler clientHandler = new ClientHandler();
        SliderClient sliderClient = clientHandler.getRetrofit().create(SliderClient.class);
        Call<ServiceResponse<SliderItem>> responseCall = sliderClient.get();
        responseCall.enqueue(callback);
    }
}
