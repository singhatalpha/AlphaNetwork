package com.example.alphanetwork.addpost;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alphanetwork.Home.Home;
import com.example.alphanetwork.R;
import com.example.alphanetwork.Retrofit.RetrofitClient;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utils.SquareImageView;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class post extends AppCompatActivity {
    private static final String TAG = "post";
    public static List<View> views = new ArrayList<>();
    public static int NoOfSlecteImg;
    public EditText mtitle;
    private Button share,anonymousshare;



    public static List<String>  urls = new ArrayList<>();
    private SharedPreferences sharedPref;
    public String LONG,LAT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sharedPref = getSharedPreferences("Location" , Context.MODE_PRIVATE);











        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post.urls.clear();
                gallery.SelectedImgUrls.clear();
                post.NoOfSlecteImg = 0;
                views.clear();
                startActivity(new Intent(getApplicationContext(), Home.class));
            }
        });

        share = findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    postJSON();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        anonymousshare = findViewById(R.id.shareanonymous);
        anonymousshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    postanonymousJSON();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });







        ImageView mgallery = findViewById(R.id.gallery);
        final ViewPager viewPager= findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setVisibility(View.INVISIBLE);
        mgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gallery.SelectedImgUrls.clear();
                post.NoOfSlecteImg = 0;
                Intent i = new Intent(post.this,gallery.class);
                post.this.startActivity(i);
                views.clear();
                finish();
            }
        });

        if(gallery.SelectedImgUrls!=null){
            viewPager.setVisibility(View.VISIBLE);
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(post.this));

//add views which we want to set as pages
            LayoutInflater inflater = (LayoutInflater) ((Activity) this).getSystemService(Context.LAYOUT_INFLATER_SERVICE);






            for (int i = gallery.SelectedImgUrls.size()-1;i>=0;i--)


            {


                final View mview = inflater.inflate(R.layout.postviewpagerholder, null);
                SquareImageView squareImageView = mview.findViewById(R.id.displayImage);
//                final int mimgcursor = i;



                imageLoader.displayImage(gallery.SelectedImgUrls.get(i), squareImageView, new ImageLoadingListener() {

                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {


                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                        Log.e(TAG, "onLoadingComplete: " + "viewpager one page");

                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                });


                views.add(mview);

//                Button close = mview.findViewById(R.id.displayImageCloseBtn);
//                close.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        viewPager.setAdapter(null);
//                        views.remove(mview);
//
//
//
//                        PagerAdapter pagerAdapter = new Pager(views, getApplicationContext());
//                        viewPager.setAdapter(pagerAdapter);
//                        int x = pagerAdapter.getItemPosition(mview);
//                        System.out.println(x);
////                        gallery.SelectedImgUrls.remove(x);
//                        pagerAdapter.notifyDataSetChanged();
//                        viewPager.setCurrentItem(0);
////                        viewPager.setCurrentItem(0);
//                        post.NoOfSlecteImg--;
//
//
//                    }
//                });


            }


            Pager pagerAdapter = new Pager(views, this);
            viewPager.setAdapter(pagerAdapter);
//            SquareImageView displayimage = findViewById(R.id.displayImage);

//            imageLoader.displayImage(imageCursor, displayimage, new ImageLoadingListener() {
//                @Override
//                public void onLoadingStarted(String imageUri, View view) {
//
//                }
//
//                @Override
//                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//
//                }
//
//                @Override
//                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//
//                    Log.e(TAG, "onLoadingComplete: " + imageUri);
//
//                }
//
//                @Override
//                public void onLoadingCancelled(String imageUri, View view) {
//
//                }
//            });

        }

//        Button Closebtn = findViewById(R.id.displayImageCloseBtn);
//       Closebtn.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//
//           }
//       });

        final ProgressBar progressBar =findViewById(R.id.editTextPrgBar);
        progressBar.setMax(120);

        final EditText editText=findViewById(R.id.postcontent);
        editText.setFilters(new InputFilter[] { new InputFilter.LengthFilter(120) });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                progressBar.setProgress(editText.getText().toString().length());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void postJSON() throws IOException {

        LONG  = sharedPref.getString("LONG" , "NULL");
        LAT   = sharedPref.getString("LAT","NULL");
        RequestBody longitude =
                RequestBody.create(MediaType.parse("multipart/form-data"), LONG);
        RequestBody latitude =
                RequestBody.create(MediaType.parse("multipart/form-data"), LAT);

        if(LONG=="NULL"){
            Toast.makeText(getApplication(), "Please Enable Location, We need location for the feed", Toast.LENGTH_LONG).show();

        }
        else{


            mtitle = findViewById(R.id.postcontent);
            String Title = mtitle.getText().toString();


            List<MultipartBody.Part> parts = new ArrayList<>();

//pass it like this

            RequestBody title =
                    RequestBody.create(MediaType.parse("multipart/form-data"), Title);

            if (urls.size() != 0) {
                for (int index = 0; index < urls.size(); index++) {

                    System.out.println("The urls are :" + urls);

                    File file = new File(urls.get(index));

//                RequestBody requestFile =
//                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    RequestBody requestFile =
                            RequestBody.create(MediaType.parse("multipart/form-data"), new Compressor(this).compressToFile(file));

// MultipartBody.Part is used to send also the actual file name
                    MultipartBody.Part body =
                            MultipartBody.Part.createFormData("media", file.getName(), requestFile);

                    parts.add(body);


                }
            }

            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .addPost(title,longitude,latitude,parts);
            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call,
                                       Response<ResponseBody> response) {
                    String m = response.message();
                    System.out.println(m);
                    if(response.code()==200){
                        Intent i = new Intent(post.this, Home.class);
                        startActivity(i);
                    }

                    Log.v("Upload", "success");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplication(), "Couldn't reach the server", Toast.LENGTH_LONG).show();

                    Log.e("Upload error:", t.getMessage());
                }
            });


        }
        }


    public void postanonymousJSON() throws IOException {

        LONG  = sharedPref.getString("LONG" , "NULL");
        LAT   = sharedPref.getString("LAT","NULL");
        RequestBody longitude =
                RequestBody.create(MediaType.parse("multipart/form-data"), LONG);
        RequestBody latitude =
                RequestBody.create(MediaType.parse("multipart/form-data"), LAT);

        if(LONG=="NULL"){
            Toast.makeText(getApplication(), "Please Enable Location, We need location for the feed", Toast.LENGTH_LONG).show();

        }
        else{


            mtitle = findViewById(R.id.postcontent);
            String Title = mtitle.getText().toString();


            List<MultipartBody.Part> parts = new ArrayList<>();

//pass it like this

            RequestBody title =
                    RequestBody.create(MediaType.parse("multipart/form-data"), Title);

            if (urls.size() != 0) {
                for (int index = 0; index < urls.size(); index++) {

                    System.out.println("The urls are :" + urls);

                    File file = new File(urls.get(index));

//                RequestBody requestFile =
//                        RequestBody.create(MediaType.parse("multipart/form-data"), file);

                    RequestBody requestFile =
                            RequestBody.create(MediaType.parse("multipart/form-data"), new Compressor(this).compressToFile(file));

// MultipartBody.Part is used to send also the actual file name
                    MultipartBody.Part body =
                            MultipartBody.Part.createFormData("media", file.getName(), requestFile);

                    parts.add(body);


                }
            }

            Call<ResponseBody> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .addAnonymousPost(title,longitude,latitude, parts);
            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call,
                                       Response<ResponseBody> response) {
                    String m = response.message();
                    System.out.println(m);
                    if(response.code()==200){
                        Intent i = new Intent(post.this, Home.class);
                        startActivity(i);
                    }

                    Log.v("Upload", "success");
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getApplication(), "Couldn't reach the server", Toast.LENGTH_LONG).show();
                    Log.e("Upload error:", t.getMessage());
                }
            });


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        post.urls.clear();
        gallery.SelectedImgUrls.clear();
        post.NoOfSlecteImg = 0;
        views.clear();
    }

        }
















