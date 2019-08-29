package com.example.alphanetwork.Feed;

import android.content.Context;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.alphanetwork.R;

import Utils.Utils;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

public class MediaAdapter extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public ImageView mImage;
    public VideoView mVideo;
    public TextView mTest;
    // vars
    public String link;

    public Uri uri;
    public ProgressBar bar;
    private OnFragmentInteractionListener mListener;

    public static MediaController mediaController;
//    public ProgressBar progressBar;


    public MediaAdapter() {
        // Required empty public constructor
    }

//    public static MediaAdapter getInstance(String media) {
//        MediaAdapter fragment = new MediaAdapter();
//
////        if(media != null){
////            Bundle bundle = new Bundle();
////            bundle.putParcelable("media", media);
////            fragment.setArguments(bundle);
////        }
////        return fragment;
//
//        Bundle args = new Bundle();
//        args.putString("media", media);
//        fragment.setArguments(args);
//        return fragment;
//    }



    public static MediaAdapter newInstance(String media) {
        MediaAdapter fragment = new MediaAdapter();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, media);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            link = getArguments().getString(ARG_PARAM1);
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
//        mImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        mVideo = view.findViewById(R.id.videoplayer);
//        mTest = view.findViewById(R.id.test);
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
        if (link != null) {
            if (link.endsWith(".mp4")) {
                mVideo.setVisibility(View.VISIBLE);

                mediaController = new MediaController(getActivity());
                mediaController.setAnchorView(mVideo);
                mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        bar.setVisibility(getView().GONE);
                    }
                });

//                mVideo.setVideoPath(link);
//                bar.setVisibility(View.GONE);
//                mVideo.start();
                mImage.setVisibility(View.GONE);

            } else {

                mVideo.setVisibility(View.GONE);
                mImage.setVisibility(View.VISIBLE);

                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.ic_launcher_background);
                requestOptions.error(Utils.getRandomDrawbleColor());
                requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);


                System.out.println("loading image");
                bar.setVisibility(View.GONE);
                Glide.with(getActivity())
                        .setDefaultRequestOptions(requestOptions)
                        .load(link)
                        .dontAnimate()

                        .into(mImage);
            }


        }
        else
        {
            mVideo.setVisibility(View.GONE);
            mImage.setVisibility(View.GONE);
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}