package com.example.onlineshop.fragments;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.adapters.BlogAdapter;
import com.example.onlineshop.models.Blog;
import com.example.onlineshop.models.base.ServiceResponse;
import com.example.onlineshop.service.BlogService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogFragment extends Fragment {

    private Activity activity;
    private RecyclerView blogRecycler;
    private List<Blog> dataList;
    private ProgressBar progressBar;
    private NestedScrollView nestedScrollView;
    private int pageNumber = 0, pageSize = 2;

    public BlogFragment(Activity activity) {
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.frame_blog, container, false);
        init(rootView);
        return rootView;
    }

    private void init(ViewGroup rootView) {
        bindViews(rootView);
        dataList = new ArrayList<>();
        fillBlogDataList();
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(@NonNull NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    progressBar.setVisibility(VISIBLE);
                    pageNumber++;
                    fillBlogDataList();
                }
            }
        });
    }

    private void fillBlogDataList() {
        BlogService.getBlog(new Callback<ServiceResponse<Blog>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Blog>> call, Response<ServiceResponse<Blog>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isHasError()) {
                    List<Blog> newDataList = response.body().getDataList();
                    dataList.addAll(newDataList);
                    blogRecycler.setAdapter(new BlogAdapter(activity, dataList));
                    blogRecycler.setLayoutManager(new LinearLayoutManager(activity, RecyclerView.VERTICAL, false));
                    progressBar.setVisibility(INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ServiceResponse<Blog>> call, Throwable throwable) {
                Toast.makeText(activity, "Error on getting data", Toast.LENGTH_SHORT).show();
            }
        }, pageNumber, pageSize);
    }

    private void bindViews(ViewGroup rootView) {
        blogRecycler = rootView.findViewById(R.id.blog_recycler);
        progressBar = rootView.findViewById(R.id.blog_progressbar);
        nestedScrollView = rootView.findViewById(R.id.blog_scrollview);
    }
}
