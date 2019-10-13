package com.example.alphanetwork.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alphanetwork.Feed.CommentListAdapter;
import com.example.alphanetwork.Home.Home;
import com.example.alphanetwork.Model.CommentFeed;
import com.example.alphanetwork.Model.Comments;
import com.example.alphanetwork.R;
import com.example.alphanetwork.Retrofit.Api;
import com.example.alphanetwork.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import Utils.BaseBackPressedListener;
import Utils.BaseBackPressedPopListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.alphanetwork.Home.Home;
import com.example.alphanetwork.Model.CommentFeed;
import com.example.alphanetwork.Model.Comments;
import com.example.alphanetwork.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import com.example.alphanetwork.Feed.Adapter;
import com.example.alphanetwork.Feed.MediaAdapter;
import com.example.alphanetwork.Feed.ViewCommentsFragment;
import com.example.alphanetwork.Model.ModelFeed;
import com.example.alphanetwork.Retrofit.Api;
import com.example.alphanetwork.Model.ModelHomeWall;
import com.example.alphanetwork.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by User on 8/12/2017.
 */

public class ViewProfileFragment extends Fragment {

    private static final String TAG = "ViewCommentsFragment";

//    @Override
//    public void onResume() {
//        super.onResume();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
//    }



    //widgets
    private ImageView mBackArrow;
    private ListView mListView;
    public String value;

    //vars

    private List<Comments> commentfeed = new ArrayList<>();
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_comments, container, false);


        ((ProfileActivity)getActivity()).setOnBackPressedPopListener(new BaseBackPressedPopListener(getActivity()));

        mBackArrow = (ImageView) view.findViewById(R.id.backArrow);
        mListView = (ListView) view.findViewById(R.id.listView);
        mContext = getActivity();


        value = getArguments().getString("onClick");
        setupWidgets();
        LoadJson();


        return view;
    }

    private void setupWidgets() {

        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back");
// TO DO - if coming from home wall, do this, else do something else , in other cases, we wouldnt want to show layout of home!
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });






    }





    public void LoadJson() {

        if(value == "Highlights") {
            Api api = RetrofitClient.getInstance().getApi();
            Call<CommentFeed> call;
            call = api.comments();
            call.enqueue(new Callback<CommentFeed>() {
                @Override
                public void onResponse(Call<CommentFeed> call, Response<CommentFeed> response) {
                    if (response.isSuccessful()) {

                        commentfeed = response.body().getComments();

                        ProfileListAdapter adapter = new ProfileListAdapter(mContext,
                                R.layout.layout_comment, commentfeed);

                        mListView.setAdapter(adapter);

                    } else {

                        Toast.makeText(getActivity(), "No Response", Toast.LENGTH_LONG).show();

                    }

                }

                @Override
                public void onFailure(Call<CommentFeed> call, Throwable t) {
                    Toast.makeText(getActivity(), "onFailure for Comments is triggered", Toast.LENGTH_LONG).show();
                }

            });
        }

        else if(value == "Commitments") {
            Api api = RetrofitClient.getInstance().getApi();
            Call<CommentFeed> call;
            call = api.comments();
            call.enqueue(new Callback<CommentFeed>() {
                @Override
                public void onResponse(Call<CommentFeed> call, Response<CommentFeed> response) {
                    if (response.isSuccessful()) {

                        commentfeed = response.body().getComments();

                        ProfileListAdapter adapter = new ProfileListAdapter(mContext,
                                R.layout.layout_comment, commentfeed);

                        mListView.setAdapter(adapter);

                    } else {

                        Toast.makeText(getActivity(), "No Response", Toast.LENGTH_LONG).show();

                    }

                }

                @Override
                public void onFailure(Call<CommentFeed> call, Throwable t) {
                    Toast.makeText(getActivity(), "onFailure for Comments is triggered", Toast.LENGTH_LONG).show();
                }

            });
        }

    }}