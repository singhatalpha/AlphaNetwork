package com.example.alphanetwork;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.widget.Toast;
import android.preference.PreferenceManager;

import com.example.alphanetwork.Home.Home;
import com.example.alphanetwork.Retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class user_login extends AppCompatActivity {

    private EditText id,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        id = findViewById(R.id.txtEmail);
        pass = findViewById(R.id.txtPwd);

        Button login = (Button)findViewById(R.id.btnLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = id.getText().toString().trim();
                String password = pass.getText().toString().trim();

                if(validateLogin(user,password)){
                    login(user,password);
                }
            }
        });


        TextView register = (TextView)findViewById(R.id.lnkRegister);
        register.setMovementMethod(LinkMovementMethod.getInstance());
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_login.this, Registration.class);
                startActivity(intent);
            }
        });


    }


    private boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    public void login(final String user, final String password){
//        String user = id.getText().toString().trim();
//        String password = pass.getText().toString().trim();

        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .userLogin(user,user,password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String s = null;
                try {
                    if(response.code() == 200 || response.code() == 201) {
                        System.out.println("entered here");
                        s = response.body().string();
                        SharedPreferences sharedPref = getSharedPreferences("Login" , Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();
                        // login token
                        editor.putString("token" , s);
                        // login boolean to check for login every time app is launched
                        editor.putBoolean("logged",true);
                        editor.apply();
                        String temp = sharedPref.getString("token" , "NULL");

                        Toast.makeText(user_login.this, temp + " " + "sharedpref", Toast.LENGTH_LONG).show();

                        //redirect to home activity if successful
                        Intent intent = new Intent(user_login.this, Home.class);
                        startActivity(intent);

                }
                else
                {
                    s = response.errorBody().string();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(s!= null)
            {
                try {

                    JSONObject jsonObject = new JSONObject(s);
                    Toast.makeText(user_login.this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();


                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }


            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {

            Toast.makeText(user_login.this,t.getMessage(),Toast.LENGTH_LONG).show();

        }
    });

    }
}
//btn_login.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent = new Intent(MainActivity.this, Login.class);
//            startActivity(intent);
//        }
//    });
//logout.setOnClickListener(new View.OnClickListener() {
//
//        @Override
//        public void onClick(View view) {
//            // Launching News Feed Screen
//
//            SharedPreferences preferences =getSharedPreferences("loginPrefs",Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.clear();
//            editor.commit();
//            finish();
//        });
