package com.lahrachtech.restapi;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") Integer postId);

    @POST("posts")
    Call<Post> storePost(@Body HashMap<Object, Object> map);
}
