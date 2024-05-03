package com.example.onlineshop.clients;


import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.base.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductClient {
    @GET("product/newProducts")
    Call<ServiceResponse<Product>> getNew();

    @GET("product/popularProducts")
    Call<ServiceResponse<Product>> getPopular();
}
