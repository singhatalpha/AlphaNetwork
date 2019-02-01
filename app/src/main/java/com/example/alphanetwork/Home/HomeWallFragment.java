package com.example.alphanetwork.Home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alphanetwork.R;


public class HomeWallFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "HomeWallFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_wall, container, false);

        return view;
    }
}