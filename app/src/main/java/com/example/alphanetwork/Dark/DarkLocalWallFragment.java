package com.example.alphanetwork.Dark;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alphanetwork.Feed.Adapter;
import com.example.alphanetwork.Feed.MediaAdapter;
import com.example.alphanetwork.Model.ModelFeed;
import com.example.alphanetwork.Model.ModelHomeWall;
import com.example.alphanetwork.R;
import com.example.alphanetwork.Retrofit.Api;
import com.example.alphanetwork.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DarkLocalWallFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener, MediaAdapter.OnFragmentInteractionListener{




    private static final String TAG = "DarkLocalFragment";
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelFeed> feed = new ArrayList<>();
    private DarkAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout errorLayout;
    private ImageView errorImage;
    private TextView errorTitle, errorMessage;
    private Button btnRetry;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_darkfeed, container, false);





        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        onLoadingSwipeRefresh();

        return view;

    }

    public void LoadJson() {

        swipeRefreshLayout.setRefreshing(true);


        Api api = RetrofitClient.getInstance().getApi();
        Call<ModelHomeWall> call;
        call = api.feed();
        call.enqueue(new Callback<ModelHomeWall>() {
            @Override
            public void onResponse(Call<ModelHomeWall> call, Response<ModelHomeWall> response) {
                if(response.isSuccessful() && response.body().getStatus()!=null){

                    feed = response.body().getPosts();
                    System.out.println(feed);
                    adapter = new DarkAdapter(feed, getActivity(), getActivity().getSupportFragmentManager());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    swipeRefreshLayout.setRefreshing(false);

                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(), "No Response", Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<ModelHomeWall> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure is triggered", Toast.LENGTH_LONG).show();
            }

        });

    }



//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        try{
//            mOnCommentThreadSelectedListener = (OnCommentThreadSelectedListener) getActivity();
//        }catch (ClassCastException e){
//            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
//        }
//    }


    @Override
    public void onRefresh() {
        LoadJson();
    }

    private void onLoadingSwipeRefresh(){

        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        LoadJson();
                    }
                }
        );

    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}

//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_dark_local_wall, container, false);
//
//        return view;
//    }
//}