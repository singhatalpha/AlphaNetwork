package com.example.alphanetwork.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://10.7.8.35:8000/";

    private static RetrofitClient mInstance;
    private static Retrofit retrofit;


    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new RequestInterceptor()) // This is used to add ApplicationInterceptor.
            .addNetworkInterceptor(new RequestInterceptor()) //This is used to add NetworkInterceptor.
            .build();


    private RetrofitClient()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance()
    {
        if(mInstance == null)
        {
            mInstance = new RetrofitClient();
        }
        return  mInstance;
    }


    public Api getApi()
    {
        return retrofit.create(Api.class);
    }
}



