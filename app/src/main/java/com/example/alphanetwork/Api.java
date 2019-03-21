package com.example.alphanetwork;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Belal on 10/2/2017.
 */

public interface Api {


//
//    @GET("marvel")
//    Call<List<Hero>> getHeroes();



    @FormUrlEncoded
    @POST("token/")
    Call<ResponseBody> createUser(
        @Field("username") String username,
//        @Field("email") String email,
        @Field("password") String password1
//        @Field("password2") String password2
    );

    @FormUrlEncoded
    @POST("users/")
    Call<ResponseBody> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

}