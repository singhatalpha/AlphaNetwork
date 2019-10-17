package com.example.alphanetwork.Circle;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.alphanetwork.Login.user_login;
import com.example.alphanetwork.R;

import Utils.MyApp;


public class CircleEventsFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "CircleEventsFragment";
    private Button logout;
    private SharedPreferences sharedpref;
    private Context context = MyApp.getContext();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_events, container, false);
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedpref = context.getSharedPreferences("Login",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpref.edit();
                editor.clear();
                editor.commit();
                Intent mainIntent = new Intent(getActivity(), user_login.class);
                startActivity(mainIntent);
            }
        });
        return view;
    }
}