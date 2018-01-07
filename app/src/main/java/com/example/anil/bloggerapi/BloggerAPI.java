package com.example.anil.bloggerapi;


import com.example.anil.bloggerapi.Pojo.PostList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by anil on 12/29/2017.
 */

public class BloggerAPI {

    private static final String key = "AIzaSyBLIiVz_e4A9IdGaVU0i-ft9LphVsGI3-U";
    private static final String url = "https://www.googleapis.com/blogger/v3/blogs/8569477381286510135/posts/";

    public static PostService postService = null;

    public static PostService getService() {

        if (postService == null) {
    //Create

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            postService = retrofit.create(PostService.class);
        }
        return postService;
    }


    public interface PostService {
        @GET("?key=" + key)
        Call<PostList> getPostList();

//        @GET("{postId}/?key="+key)
//        Call<Item> getPostId(@Path("postId") String id);
    }
}
