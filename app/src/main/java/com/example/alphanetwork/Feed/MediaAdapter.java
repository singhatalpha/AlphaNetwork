package com.example.alphanetwork.Feed;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.alphanetwork.R;

public class MediaAdapter extends Fragment {


    //
    public ImageView mImage;
    public VideoView mVideo;
    public TextView mTest;
    // vars
    public String link;
    public static MediaController mediacontroller;
    public Uri uri;
    public ProgressBar bar;

    public static MediaAdapter getInstance(String media) {
        MediaAdapter fragment = new MediaAdapter();

//        if(media != null){
//            Bundle bundle = new Bundle();
//            bundle.putParcelable("media", media);
//            fragment.setArguments(bundle);
//        }
//        return fragment;

        Bundle args = new Bundle();
        args.putString("media", media);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            link = getArguments().getString("media");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_feed_media, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        System.out.println("thiis reached onview created");
        mImage = view.findViewById(R.id.imageplayer);
        mVideo = view.findViewById(R.id.videoplayer);
        mTest = view.findViewById(R.id.test);
        bar = view.findViewById(R.id.progress);
        init();
    }

    private void init() {
//        if(link != null){
//            mImage.setVisibility(View.VISIBLE);
//            RequestOptions requestOptions = new RequestOptions()
//                    .placeholder(R.drawable.ic_launcher_background);
//
//            Glide.with(getActivity())
//                    .setDefaultRequestOptions(requestOptions)
//                    .load(link)
//                    .into(mImage);
//        }
//        System.out.println("thiis reached media inititations " + link);
//        if (link != null) {
//            mTest.setText("Its working for tv");
//            if (link.endsWith(".mp4")) {
//                mVideo.setVisibility(View.VISIBLE);
//                mVideo.setVideoPath(link);
//                mVideo.start();
//
//            }
//            else {
//                mImage.setVisibility(View.VISIBLE);
//                RequestOptions requestOptions = new RequestOptions()
//                        .placeholder(R.drawable.ic_launcher_background);
//                System.out.println("loading image");
//                Glide.with(getActivity())
//                        .setDefaultRequestOptions(requestOptions)
//                        .load(link)
//                        .dontAnimate()
//                        .into(mImage);
//            }
//
//
//        }
        if (link != null) {
            if (link.endsWith(".mp4")) {
//                bar = (ProgressBar) view.findViewById(R.id.progrss);
//            bar=new ProgressDialog(getActivity());
//            bar.setTitle("Connecting server");
//            bar.setMessage("Please Wait... ");
//            bar.setCancelable(false);
//            bar.show();
//                vv = (VideoView) view.findViewById(R.id.videoplayer);
                mediacontroller = new MediaController(getActivity());
                mediacontroller.setAnchorView(mVideo);
                mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    // Close the progress bar and play the video
                    public void onPrepared(MediaPlayer mp) {
                        bar.setVisibility(View.GONE);
                    }
                });
//                mImage = (ImageView) view.findViewById(R.id.imageshow);
                mImage.setVisibility(View.GONE);
            } else {
                //put the glide code for the image
//            RequestOptions
//                iv = (ImageView) view.findViewById(R.id.imageshow);
//                vv = (VideoView) view.findViewById(R.id.videoplayer);
                mVideo.setVisibility(View.GONE);
//            iv.setImageURI();
//.setBackgroundResource(R.drawable.ic_launcher_background);

            }
        }
    }
}