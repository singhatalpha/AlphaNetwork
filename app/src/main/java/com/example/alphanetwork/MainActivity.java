package com.example.alphanetwork;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import com.example.alphanetwork.Home.Home;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPref = getSharedPreferences("Login" , Context.MODE_PRIVATE);
        if(sharedPref.getBoolean("logged",false)){
            goToMainActivity();
        }
    }

    public void goToMainActivity(){
        Intent i = new Intent(this, Home.class);
        startActivity(i);
    }
}



