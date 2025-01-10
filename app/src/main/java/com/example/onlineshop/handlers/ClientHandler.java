package com.example.onlineshop.handlers;

import com.example.onlineshop.config.UnsafeSSLConfig;
import com.example.onlineshop.enums.ApiAddress;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientHandler {
    private final Retrofit.Builder builder;
    private final Retrofit retrofit;

    public ClientHandler() {
        builder = new Retrofit.Builder()
                .baseUrl(ApiAddress.baseDomain + "/api/")
                .client(UnsafeSSLConfig.getUnsafeOkHttpClient().build())
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
    }

    public Retrofit.Builder getBuilder() {
        return builder;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
