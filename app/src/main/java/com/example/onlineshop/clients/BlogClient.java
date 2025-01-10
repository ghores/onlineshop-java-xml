package com.example.onlineshop.clients;


import com.example.onlineshop.models.Blog;
import com.example.onlineshop.models.SliderItem;
import com.example.onlineshop.models.base.ServiceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BlogClient {
    @GET("blog/getAllData")
    Call<ServiceResponse<Blog>> getBlog(
            @Query("pageNumber") int pageNumber,
            @Query("pageSize") int pageSize
    );
}
