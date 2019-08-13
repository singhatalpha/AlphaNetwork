package com.example.alphanetwork.Retrofit;

import java.io.IOException;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alphanetwork.MainActivity;
import com.example.alphanetwork.MyApp;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class RequestInterceptor implements Interceptor {

//    .addHeader("Authenticator", prefs.getToken())

    private SharedPreferences sharedpref;
    private Context context = MyApp.getContext();


//    public RequestInterceptor(Context context) {
//        this.context = context;
//    }

    @Override
    public Response intercept(Chain chain)
            throws IOException {

        sharedpref = context.getSharedPreferences("Login",Context.MODE_PRIVATE);
        String token = sharedpref.getString("token" , "NULL");

        System.out.println("Entered in Interceptor,token is :"+token);


        Request request = chain.request();
        if(token!=null ){

            request = request.newBuilder()
                    .addHeader("token", token)
                    .build();

        }

        Response response = chain.proceed(request);
        return response;
    }
}

