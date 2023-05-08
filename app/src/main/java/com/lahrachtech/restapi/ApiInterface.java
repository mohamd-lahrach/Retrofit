package com.lahrachtech.restapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") Integer postId);
}
