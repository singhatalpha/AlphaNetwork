package com.example.alphanetwork.Retrofit;


//import com.example.alphanetwork.Model.CommentFeed;
import com.example.alphanetwork.Model.CommentFeed;
import com.example.alphanetwork.Model.Comments;
import com.example.alphanetwork.Model.ModelHomeWall;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Api {




    @FormUrlEncoded
    @POST("rest-auth/registration/")
    Call<ResponseBody> createUser(
        @Field("username") String username,
//        @Field("email") String email,
        @Field("email") String email,
//        @Field("password2") String password2
        @Field("password1") String password1,
        @Field("password2") String password2
    );

    @FormUrlEncoded
    @POST("rest-auth/login/")
    Call<ResponseBody> userLogin(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );


    @GET("feed/feed_i/")
    Call<ModelHomeWall> feed();

    @GET("feed/comments/1/")
    Call<CommentFeed> comments();


}