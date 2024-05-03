package com.example.onlineshop.clients;

import com.example.onlineshop.models.ProductCategory;
import com.example.onlineshop.models.base.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductCategoryClient {
    @GET("productCategory")
    Call<ServiceResponse<ProductCategory>> get();
}
