package com.example.alphanetwork.Retrofit;


//import com.example.alphanetwork.Model.CommentFeed;
import com.example.alphanetwork.Model.CommentFeed;
import com.example.alphanetwork.Model.ModelHomeWall;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;


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


    @Multipart
    @POST("feed/feed_m/")
    Call<ResponseBody> addPost(
            @Part("title") RequestBody title,
//            @Part("description") RequestBody description,
            @Part List<MultipartBody.Part> file
    );

    @Multipart
    @PATCH("feed/profile/")
    Call<ResponseBody> updateProfile(
        @Part MultipartBody photo,
        @Part ("username") RequestBody username

    );


//    @GET("feed/profile2/")
//    Call<ModelViewProfile> getProfile();


    @GET("feed/feed_i/")
    Call<ModelHomeWall> feed();

    @GET("feed/comments/1/")
    Call<CommentFeed> comments();


}