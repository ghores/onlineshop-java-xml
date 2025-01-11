package com.example.onlineshop;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlineshop.enums.ApiAddress;
import com.example.onlineshop.models.Blog;
import com.example.onlineshop.models.base.ServiceResponse;
import com.example.onlineshop.service.BlogService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogDetailActivity extends AppCompatActivity {
    private Blog blog;
    private ImageView blogImage;
    private TextView blogTitle, blogSubtitle, blogDescription, blogPublishDate, blogVisitCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);
        init();
    }

    private void init() {
        bindViews();
        if (getIntent().getExtras() != null) {
            blog = (Blog) getIntent().getExtras().get("blog");
        }
        if (blog != null) {
            blogTitle.setText(blog.getTitle());
            blogSubtitle.setText(blog.getSubtitle());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                blogDescription.setText(Html.fromHtml(blog.getDescription(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                blogDescription.setText(Html.fromHtml(blog.getDescription()));
            }
            Picasso.get().load(ApiAddress.getFileUrl(blog.getImage())).into(blogImage);
            blogPublishDate.setText(blog.getPublishDateStr());
            blogVisitCount.setText(String.valueOf(blog.getVisitCount()));
            fillBlogData();
        }
    }

    private void fillBlogData() {
        BlogService.getIncreaseVisitCount(new Callback<ServiceResponse<Blog>>() {
            @Override
            public void onResponse(Call<ServiceResponse<Blog>> call, Response<ServiceResponse<Blog>> response) {

            }

            @Override
            public void onFailure(Call<ServiceResponse<Blog>> call, Throwable throwable) {

            }
        }, blog.getId());

    }
    private void bindViews() {
        blogImage = findViewById(R.id.blog_image);
        blogTitle = findViewById(R.id.blog_title);
        blogSubtitle = findViewById(R.id.blog_subtitle);
        blogDescription = findViewById(R.id.blog_description);
        blogPublishDate = findViewById(R.id.blog_publish_date);
        blogVisitCount = findViewById(R.id.blog_visit_count);
    }
}