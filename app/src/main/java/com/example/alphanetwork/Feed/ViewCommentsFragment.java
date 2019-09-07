package com.example.alphanetwork.Feed;

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

public class ViewCommentsFragment extends Fragment {

    private static final String TAG = "ViewCommentsFragment";



    //widgets
    private ImageView mBackArrow, mCheckMark;
    private EditText mComment;
    private ListView mListView;

    //vars

    private List<Comments> commentfeed = new ArrayList<>();
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_comments, container, false);
        mBackArrow = (ImageView) view.findViewById(R.id.backArrow);
        mCheckMark = (ImageView) view.findViewById(R.id.ivPostComment);
        mComment = (EditText) view.findViewById(R.id.comment);
        mListView = (ListView) view.findViewById(R.id.listView);
        mContext = getActivity();


        String value = getArguments().getString("YourKey");
        setupWidgets();
        LoadJson();


        return view;
    }

    private void setupWidgets() {




        mCheckMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mComment.getText().toString().equals("")) {
                    Log.d(TAG, "onClick: attempting to submit new comment.");
                    addNewComment(mComment.getText().toString());

                    mComment.setText("");
                    closeKeyboard();
                } else {
                    Toast.makeText(getActivity(), "you can't post a blank comment", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back");
// TO DO - if coming from home wall, do this, else do something else , in other cases, we wouldnt want to show layout of home!
                getActivity().getSupportFragmentManager().popBackStack();
                ((Home)getActivity()).showLayout();


            }
        });



//        if(mPhoto.getComments().size() == 0){
//            mComments.clear();
//            Comment firstComment = new Comment();
//            firstComment.setComment(mPhoto.getCaption());
//            firstComment.setUser_id(mPhoto.getUser_id());
//            firstComment.setDate_created(mPhoto.getDate_created());
//            mComments.add(firstComment);
//            mPhoto.setComments(mComments);
//            setupWidgets();
//        }


    }

    private void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private void addNewComment(String newComment) {
        Log.d(TAG, "addNewComment: adding new comment: " + newComment);

//        String commentID = myRef.push().getKey();

//        Comment comment = new Comment();
//        comment.setComment(newComment);
//        comment.setDate_created(getTimestamp());
//        comment.setUser_id(FirebaseAuth.getInstance().getCurrentUser().getUid());



    }

//    private String getTimestamp() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.CANADA);
//        sdf.setTimeZone(TimeZone.getTimeZone("Canada/Pacific"));
//        return sdf.format(new Date());
//    }



    public void LoadJson() {


        Api api = RetrofitClient.getInstance().getApi();
        Call<CommentFeed> call;
        call = api.comments();
        call.enqueue(new Callback<CommentFeed>() {
            @Override
            public void onResponse(Call<CommentFeed> call, Response<CommentFeed> response) {
                if(response.isSuccessful() ){

                    commentfeed = response.body().getComments();

                    CommentListAdapter adapter = new CommentListAdapter(mContext,
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




    }

