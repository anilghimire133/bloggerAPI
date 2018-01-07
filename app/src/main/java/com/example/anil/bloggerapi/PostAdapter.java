package com.example.anil.bloggerapi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anil.bloggerapi.Pojo.Item;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by anil on 12/29/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Item> items;

    public PostAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);

    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {

        final Item item = items.get(position);
        holder.postTitle.setText(item.getTitle());

        Document document = Jsoup.parse(item.getContent());
        holder.postDescription.setText(document.text());
        Elements elements = document.select("img");

        Glide.with(context).load(elements.get(0).attr("src")).into(holder.postImage);
//        Log.d("CODE", "Image - " + elements.get(0).attr("src"));
//        Log.d("TEXT" , document.text());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent( context ,DetailActivity.class);
                intent.putExtra("url" ,item.getUrl());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();

    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView postImage;
        TextView postTitle;
        TextView postDescription;

        public PostViewHolder(View itemView) {

            super(itemView);
            postImage = itemView.findViewById(R.id.postimage);
            postTitle = itemView.findViewById(R.id.postTitle);
            postDescription = itemView.findViewById(R.id.postDescription);
        }
    }


}
