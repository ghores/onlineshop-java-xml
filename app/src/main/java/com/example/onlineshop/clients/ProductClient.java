package com.example.onlineshop.clients;


import com.example.onlineshop.models.Product;
import com.example.onlineshop.models.base.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductClient {
    @GET("product/newProducts")
    Call<ServiceResponse<Product>> getNew();

    @GET("product/popularProducts")
    Call<ServiceResponse<Product>> getPopular();

    @GET("product/getAll/{cid}")
    Call<ServiceResponse<Product>> getByCategory(
            @Path("cid") long cid,
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );
}
