package com.example.alphanetwork.Circle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alphanetwork.R;


public class CircleWatchFragment extends android.support.v4.app.Fragment {
    private static final String TAG = "CircleWatchFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circle_watch, container, false);

        return view;
    }
}