package com.example.alphanetwork.Profile;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.alphanetwork.Feed.Adapter;
import com.example.alphanetwork.Model.ModelFeed;
import com.example.alphanetwork.Model.ModelHomeWall;
import com.example.alphanetwork.Model.ModelViewProfile;
import com.example.alphanetwork.Model.ViewProfile;
import com.example.alphanetwork.R;
import com.example.alphanetwork.Retrofit.Api;
import com.example.alphanetwork.Retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Utils.BaseBackPressedListener;
import Utils.ExpandableHeightGridView;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";


//    public interface OnGridImageSelectedListener{
//        void onGridImageSelected(Photo photo, int activityNumber);
//    }
//    OnGridImageSelectedListener mOnGridImageSelectedListener;

    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMNS = 3;




    //widgets
    private TextView mPosts, mFollowers, mFollowing, mFollowersCount, mFollowingCount, mDisplayName, mHighlights, mHighlightsVote, mHighlight1, mCommitments, mCommitmentsVote, mCommitments1, mPassions, mPassion1, mPassion2;
    private ProgressBar mProgressBar;
    private CircleImageView mProfilePhoto;
    private ExpandableHeightGridView gridView;
    private Toolbar toolbar;
    private ImageView profileMenu, back;


    private Context mContext;

    private List<ModelFeed> feed = new ArrayList<>();
    private ViewProfile vp;

    //vars
//    private int mFollowersCount = 0;
//    private int mFollowingCount = 0;
//    private int mPostsCount = 0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);



        ((ProfileActivity)getActivity()).setOnBackPressedListener(new BaseBackPressedListener(getActivity()));



        mDisplayName = (TextView) view.findViewById(R.id.display_name);
//        mUsername = (TextView) view.findViewById(R.id.username);

        mProfilePhoto = (CircleImageView) view.findViewById(R.id.profile_photo);
        mPosts = (TextView) view.findViewById(R.id.tvPosts);
        mFollowers = (TextView) view.findViewById(R.id.tvFollowers);
        mFollowing = (TextView) view.findViewById(R.id.tvFollowing);
        mFollowersCount = (TextView) view.findViewById(R.id.tvFollowersCount);
        mFollowingCount = (TextView) view.findViewById(R.id.tvFollowingCount);

        mProgressBar = (ProgressBar) view.findViewById(R.id.profileProgressBar);
        back = view.findViewById(R.id.profileback);

        gridView = (ExpandableHeightGridView) view.findViewById(R.id.gridView);
        gridView.setExpanded(true);


        toolbar = (Toolbar) view.findViewById(R.id.profileToolBar);
        profileMenu = (ImageView) view.findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to account settings.");
                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });



        mHighlights = (TextView) view.findViewById(R.id.tv_highlights);
        mHighlightsVote = view.findViewById(R.id.tv_highlightsVoteTotal);
        mHighlight1 = (TextView) view.findViewById(R.id.tv_highlight1);
        mCommitments = (TextView) view.findViewById(R.id.tv_commitments);
        mCommitmentsVote = view.findViewById(R.id.tv_commitmentsVoteTotal);
        mCommitments1 = (TextView) view.findViewById(R.id.tv_commitment1);
        mPassions = (TextView) view.findViewById(R.id.tv_passions);
        mPassion1 = (TextView) view.findViewById(R.id.tv_passion1);
        mPassion2 = (TextView) view.findViewById(R.id.tv_passion2);







        mContext = getActivity();

        Log.d(TAG, "onCreateView: stared.");



//        setupToolbar();
        setProfileWidgets();
        setupGridView();

//        getFollowersCount();
//        getFollowingCount();
//        getPostsCount();

//        TextView editProfile = (TextView) view.findViewById(R.id.textEditProfile);
//        editProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
//                Intent intent = new Intent(getActivity(), AccountSettingsActivity.class);
//                intent.putExtra(getString(R.string.calling_activity), getString(R.string.profile_activity));
//                startActivity(intent);
////                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//            }
//        });

//  FOLLOWERS

         mFollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(getActivity(), FollowersActivity.class);
                intent.putExtra(getString(R.string.calling_activity), getString(R.string.profile_activity));
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
        mFollowersCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(getActivity(), FollowersActivity.class);
                intent.putExtra(getString(R.string.calling_activity), getString(R.string.profile_activity));
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });



//   FOLLOWING
        mFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(getActivity(), FollowingActivity.class);
                intent.putExtra(getString(R.string.calling_activity), getString(R.string.profile_activity));
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        mFollowingCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to " + mContext.getString(R.string.edit_profile_fragment));
                Intent intent = new Intent(getActivity(), FollowingActivity.class);
                intent.putExtra(getString(R.string.calling_activity), getString(R.string.profile_activity));
                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });




        mHighlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewProfileFragment fragment = new ViewProfileFragment();
                FragmentManager fragmentManager = getFragmentManager();
                Bundle args = new Bundle();
                args.putString("onClick", "Highlights");
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(String.valueOf(R.string.profile_fragment));
                fragmentTransaction.commit();
            }
        });

        mCommitments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewProfileFragment fragment = new ViewProfileFragment();
                FragmentManager fragmentManager = getFragmentManager();
                Bundle args = new Bundle();
                args.putString("onClick", "Commitments");
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(String.valueOf(R.string.profile_fragment));
                fragmentTransaction.commit();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to 'Home activity'");
//                getFragmentManager().popBackStack();
                getActivity().finish();

            }
        });







        return view;
    }

//    @Override
//    public void onAttach(Context context) {
//        try{
//            mOnGridImageSelectedListener = (OnGridImageSelectedListener) getActivity();
//        }catch (ClassCastException e){
//            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage() );
//        }
//        super.onAttach(context);
//    }

    private void setupGridView(){
        Log.d(TAG, "setupGridView: Setting up image grid.");

                //setup our image grid
        Api api = RetrofitClient.getInstance().getApi();
        Call<ModelHomeWall> call;
        call = api.feed();
        call.enqueue(new Callback<ModelHomeWall>() {
            @Override
            public void onResponse(Call<ModelHomeWall> call, Response<ModelHomeWall> response) {
                if(response.isSuccessful() && response.body().getStatus()!=null){

                    feed = response.body().getPosts();
                    System.out.println(feed);
//                    adapter = new Adapter(feed, getActivity(), getActivity().getSupportFragmentManager());
//                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();



                    int gridWidth = getResources().getDisplayMetrics().widthPixels;
                    int imageWidth = gridWidth/NUM_GRID_COLUMNS;
                    gridView.setColumnWidth(imageWidth);

                    ArrayList<String> imgUrls = new ArrayList<String>();

                    for(int i = 0; i < feed.size(); i++){
                        ModelFeed modelFeed = feed.get(i);
                        List<String> urls = modelFeed.getMedia();

                        if(urls.size() != 0)
                        {
                            imgUrls.add(modelFeed.getMedia().get(0));
                        }

                    }


                    System.out.println(imgUrls);


                    GridImageAdapter adapter = new GridImageAdapter(getActivity(),R.layout.layout_grid_imageview,
                            "", imgUrls);
                    gridView.setAdapter(adapter);




                } else {

                    Toast.makeText(getActivity(), "No Response", Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<ModelHomeWall> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure is triggered", Toast.LENGTH_LONG).show();
            }

        });







//                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        mOnGridImageSelectedListener.onGridImageSelected(photos.get(position), ACTIVITY_NUM);
//                    }
//                });
            }



    private void setProfileWidgets() {

        Log.d(TAG, "setupProfileWidgets: Setting up profile widgets.");

        //setup our image grid
        Api api = RetrofitClient.getInstance().getApi();
        Call<ModelViewProfile> call;
        call = api.getProfile();
        call.enqueue(new Callback<ModelViewProfile>() {
            @Override
            public void onResponse(Call<ModelViewProfile> call, Response<ModelViewProfile> response) {
                if(response.isSuccessful() ){
                    Log.d(TAG, "setupProfileWidgets: got response");
                    if (response.body() != null) {
                        vp = response.body().getProfile();
                        mDisplayName.setText(vp.getUsername());
                        Glide.with(getActivity())
                                .load(vp.getPhoto())
                                .into(mProfilePhoto);
//        mFollowingCount.setText(viewProfile.getFollowing());
                        mProgressBar.setVisibility(View.GONE);

                    }
                    System.out.println(response.body());
                    System.out.println(vp);
                } else {
                    Log.d(TAG, "setupProfileWidgets: No damn response");
                    Toast.makeText(getActivity(), "No Response", Toast.LENGTH_LONG).show();

                }


            }

            @Override
            public void onFailure(Call<ModelViewProfile> call, Throwable t) {
                Log.d(TAG, "setupProfileWidgets: On failure");
                Toast.makeText(getActivity(), "onFailure is triggered", Toast.LENGTH_LONG).show();
            }

        });





    }




}