package com.example.onlineshop.clients;


import com.example.onlineshop.models.SliderItem;
import com.example.onlineshop.models.base.ServiceResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SliderClient {
    @GET("slider/")
    Call<ServiceResponse<SliderItem>> getSlider();
}
