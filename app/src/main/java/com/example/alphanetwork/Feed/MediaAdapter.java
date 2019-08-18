package com.example.alphanetwork.Feed;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.alphanetwork.R;

public class MediaAdapter extends Fragment {


    // widgets
    private ImageView mImage;
    private VideoView mVideo;

    // vars
    private Hat mHat;

    public static MediaAdapter getInstance(Hat hat){
        MediaAdapter fragment = new MediaAdapter();

        if(hat != null){
            Bundle bundle = new Bundle();
            bundle.putParcelable("media", hat);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mHat = getArguments().getParcelable("media");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_feed_media, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mImage = view.findViewById(R.id.imageplayer);
        mVideo = view.findViewById(R.id.videoplayer);
        init();
    }

    private void init(){
        if(mHat != null){
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background);

            Glide.with(getActivity())
                    .setDefaultRequestOptions(requestOptions)
                    .load(mHat.getImage())
                    .into(mImage);


        }
    }



}
