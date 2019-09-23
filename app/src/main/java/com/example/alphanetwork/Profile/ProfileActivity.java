package com.example.alphanetwork.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alphanetwork.MainActivity;
import com.example.alphanetwork.R;


/**
 * Created by User on 5/28/2017.
 */


//implements
//        ProfileFragment.OnGridImageSelectedListener ,
//        ViewPostFragment.OnCommentThreadSelectedListener,
//        ViewProfileFragment.OnGridImageSelectedListener

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

//    @Override
//    public void onCommentThreadSelectedListener(Photo photo) {
//        Log.d(TAG, "onCommentThreadSelectedListener:  selected a comment thread");
//
//        ViewCommentsFragment fragment = new ViewCommentsFragment();
//        Bundle args = new Bundle();
//        args.putParcelable(getString(R.string.photo), photo);
//        fragment.setArguments(args);
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container, fragment);
//        transaction.addToBackStack(getString(R.string.view_comments_fragment));
//        transaction.commit();
//    }

//    @Override
//    public void onGridImageSelected(Photo photo, int activityNumber) {
//        Log.d(TAG, "onGridImageSelected: selected an image gridview: " + photo.toString());
//
//        ViewPostFragment fragment = new ViewPostFragment();
//        Bundle args = new Bundle();
//        args.putParcelable(getString(R.string.photo), photo);
//        args.putInt(getString(R.string.activity_number), activityNumber);
//
//        fragment.setArguments(args);
//
//        FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.container, fragment);
//        transaction.addToBackStack(getString(R.string.view_post_fragment));
//        transaction.commit();
//
//    }


    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLUMNS = 3;

    private Context mContext = ProfileActivity.this;

    private ProgressBar mProgressBar;
    private ImageView profileMenu, back;
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started.");
        toolbar = (Toolbar) findViewById(R.id.profileToolBar);
        profileMenu = (ImageView)findViewById(R.id.profileMenu);
        back = findViewById(R.id.profileback);

        init();


    }

    private void init(){
        Log.d(TAG, "init: inflating profile fragment" );

        Intent intent = getIntent();
//        if(intent.hasExtra(getString(R.string.calling_activity))){
//            Log.d(TAG, "init: searching for user object attached as intent extra");
//            if(intent.hasExtra(getString(R.string.intent_user))){
//                User user = intent.getParcelableExtra(getString(R.string.intent_user));
//                if(!user.getUser_id().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
//                    Log.d(TAG, "init: inflating view profile");
//                    ViewProfileFragment fragment = new ViewProfileFragment();
//                    Bundle args = new Bundle();
//                    args.putParcelable(getString(R.string.intent_user),
//                            intent.getParcelableExtra(getString(R.string.intent_user)));
//                    fragment.setArguments(args);
//
//                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.container, fragment);
//                    transaction.addToBackStack(getString(R.string.view_profile_fragment));
//                    transaction.commit();
//                }else{
//                    Log.d(TAG, "init: inflating Profile");
//                    ProfileFragment fragment = new ProfileFragment();
//                    FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.container, fragment);
//                    transaction.addToBackStack(getString(R.string.profile_fragment));
//                    transaction.commit();
//                }
//            }else{
//                Toast.makeText(mContext, "something went wrong", Toast.LENGTH_SHORT).show();
//            }
//    }
//        else{
            Log.d(TAG, "init: inflating Profile");
            ProfileFragment fragment = new ProfileFragment();
            FragmentTransaction transaction = ProfileActivity.this.getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(getString(R.string.profile_fragment));
            transaction.commit();
//        }

    }

//    private void setupToolbar(){
//
//
//        setSupportActionBar(toolbar);
//
//        profileMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: navigating to account settings.");
//                Intent intent = new Intent(mContext, AccountSettingsActivity.class);
//                startActivity(intent);
////                ProfileActivity.this.overridePendingTransition(R.anim., R.anim.fade_out);
//
//            }
//        });
//
//
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: navigating back to 'Home activity'");
//                finish();
//
//            }
//        });
//
//    }

}