package com.example.onlineshop.service;

import com.example.onlineshop.clients.BlogClient;
import com.example.onlineshop.handlers.ClientHandler;
import com.example.onlineshop.models.Blog;
import com.example.onlineshop.models.base.ServiceResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class BlogService {
    public static void getBlog(Callback<ServiceResponse<Blog>> callback, int pageNumber, int pageSize) {
        ClientHandler clientHandler = new ClientHandler();
        BlogClient blogClient = clientHandler.getRetrofit().create(BlogClient.class);
        Call<ServiceResponse<Blog>> blogResponse = blogClient.getBlog(pageNumber, pageSize);
        blogResponse.enqueue(callback);
    }
}
