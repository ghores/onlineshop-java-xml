package com.example.onlineshop.adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.BlogDetailActivity;
import com.example.onlineshop.R;
import com.example.onlineshop.enums.ApiAddress;
import com.example.onlineshop.models.Blog;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

    private List<Blog> dataList;
    private Activity activity;

    public BlogAdapter(Activity activity, List<Blog> dataList) {
        this.dataList = dataList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.blog_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Blog blog = dataList.get(position);
        final TextView title = holder.title;
        final ImageView image = holder.image;
        final TextView subtitle = holder.subtitle;

        title.setText(blog.getTitle());
        subtitle.setText(blog.getSubtitle());
        Picasso.get().load(ApiAddress.getFileUrl(blog.getImage()))
                .placeholder(R.drawable.loading)
                .error(R.drawable.brokenimage)
                .into(image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent productIntent = new Intent(activity, BlogDetailActivity.class);
                productIntent.putExtra("blog", blog);
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair(title, "blog_title");
                pairs[1] = new Pair(subtitle, "blog_subtitle");
                pairs[2] = new Pair(image, "blog_image");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, pairs);
                activity.startActivity(productIntent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title;
        public TextView subtitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.blog_image);
            title = itemView.findViewById(R.id.blog_title);
            subtitle = itemView.findViewById(R.id.blog_subtitle);
        }
    }
}
