package com.example.alphanetwork.addpost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;


import com.bumptech.glide.Glide;
import com.example.alphanetwork.Profile.EditProfileFragment;
import com.example.alphanetwork.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

import Utils.SquareImageView;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class GridviewAdapter extends RecyclerView.Adapter<ViewHolderItem> {

    private final Context mContext;
    private final ArrayList<String> imageuris;
    public static int TYPE=1;
    public static int LIMIT_IMGAES=3;
    private final  String mAppend;
    private final  String TAG = "gridview";


    // 1


    public GridviewAdapter(Context mContext,int layoutResource, ArrayList<String> imageuris, String mAppend) {
        this.mContext = mContext;
        this.imageuris = imageuris;
        this.mAppend = mAppend;
    }






    @NonNull
    @Override
    public ViewHolderItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) ((Activity) mContext).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View convertView = inflater.inflate(R.layout.layout, viewGroup, false);

        // well set up the ViewHolder
        ViewHolderItem viewHolder = new ViewHolderItem(convertView);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderItem viewHolderItem, int postion) {

        final String imgURL = imageuris.get(postion);


        int index = imgURL.lastIndexOf(".");
        String format = imgURL.substring(index+1);

        if(format.equals("MP4")){

            viewHolderItem.timeduration.setVisibility(View.VISIBLE);

            Glide.with(mContext)
                    .load(imgURL)
                    .centerCrop()
                    .placeholder(R.color.blue)
                    .transition(withCrossFade())
                    .into(viewHolderItem.imageView);
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(imgURL);
            long duration = Long.parseLong(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
//            int width = Integer.valueOf(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
//            int height = Integer.valueOf(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
            retriever.release();
            duration = duration/1000;
            int minutes = (int) (duration/60);
            int seconds = (int) (duration -(minutes * 60));
            String time = String.valueOf(minutes) + ":" + String.valueOf(seconds);
            viewHolderItem.timeduration.setText(time);

            viewHolderItem.imageView.setOnClickListener(new View.OnClickListener() {
                String TrueImageUrl = mAppend + imgURL;
                @Override
                public void onClick(View v) {

                    if(post.NoOfSlecteImg<LIMIT_IMGAES){


                        if(viewHolderItem.selected==0){
                            viewHolderItem.selected=1;
                            viewHolderItem.imageView.setAlpha(0.4f);
                            viewHolderItem.tick.setVisibility(View.VISIBLE);
                            viewHolderItem.tick.setAlpha(0.8f);
                            post.NoOfSlecteImg++;
                            gallery.SelectedImgUrls.add(TrueImageUrl);
                            ExifInterface exif = null;
                            String datetime = null;
                            try {
                                exif = new ExifInterface(TrueImageUrl);
                                datetime = exif.getAttribute(ExifInterface.TAG_DATETIME);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Log.e(TAG, "onClick: "+ datetime );








                        }

                        else {
                            viewHolderItem.selected=0;
                            viewHolderItem.imageView.setAlpha(1.0f);
                            viewHolderItem.tick.setVisibility(View.INVISIBLE);
                            gallery.SelectedImgUrls.remove(TrueImageUrl);
                            post.NoOfSlecteImg--;

                        }

                    }

                    else {
                        if(viewHolderItem.selected==1){
                            viewHolderItem.selected=0;
                            viewHolderItem.imageView.setAlpha(1.0f);
                            viewHolderItem.tick.setVisibility(View.INVISIBLE);
                            gallery.SelectedImgUrls.remove(TrueImageUrl);
                            post.NoOfSlecteImg--;


                        }

                    }
//                    int p = viewHolderItem.getPosition();
//                    post.imageCursor = mAppend + imageuris.get(p);
//                    Intent i = new Intent(mContext, post.class);
//                    mContext.startActivity(i);
//
//                    ((Activity) mContext).finish();

                    gallery.SelectImgBTn.setText("Selected"+ " " +post.NoOfSlecteImg +" Images");

                }
            });


        }

        else {
            viewHolderItem.timeduration.setVisibility(View.INVISIBLE);
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));

            imageLoader.displayImage(mAppend + imgURL, viewHolderItem.imageView, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    Log.e(TAG, "onLoadingComplete: " + imageUri);

                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {

                }
            });


            viewHolderItem.imageView.setOnClickListener(new View.OnClickListener() {
                String TrueImageUrl = mAppend + imgURL;
                @Override
                public void onClick(View v) {

                    if(post.NoOfSlecteImg<=LIMIT_IMGAES){


                        if(viewHolderItem.selected==0){
                            viewHolderItem.selected=1;
                            viewHolderItem.imageView.setAlpha(0.4f);
                            viewHolderItem.tick.setVisibility(View.VISIBLE);
                            viewHolderItem.tick.setAlpha(0.6f);
                            post.NoOfSlecteImg++;
                            gallery.SelectedImgUrls.add(TrueImageUrl);
                            post.urls.add(imgURL);
                            EditProfileFragment.urls.add(imgURL);

                            File file = new File(TrueImageUrl);
                            Date lastModDate = new Date(file.lastModified());
                            Log.e("File last modified @ : ",lastModDate.toString());





                        }

                        else {
                            viewHolderItem.selected=0;
                            viewHolderItem.imageView.setAlpha(1.0f);
                            viewHolderItem.tick.setVisibility(View.INVISIBLE);
                            post.NoOfSlecteImg--;
                            gallery.SelectedImgUrls.remove(TrueImageUrl);
                            post.urls.remove(imgURL);
                            EditProfileFragment.urls.remove(imgURL);

                        }

                    }

                    else {
                        if(viewHolderItem.selected==1){
                            viewHolderItem.selected=0;
                            viewHolderItem.imageView.setAlpha(1.0f);
                            viewHolderItem.tick.setVisibility(View.INVISIBLE);
                            post.NoOfSlecteImg--;
                            gallery.SelectedImgUrls.remove(TrueImageUrl);
                            post.urls.remove(imgURL);
                            EditProfileFragment.urls.remove(imgURL);


                        }

                    }


//                    int p = viewHolderItem.getPosition();
//                      post.urls = imageuris;
//                    Intent i = new Intent(mContext, post.class);
//                    mContext.startActivity(i);
//
//                    ((Activity) mContext).finish();

                    gallery.SelectImgBTn.setText("Selected"+ " " +post.NoOfSlecteImg +" Images");

                }
            });

        }

        if (viewHolderItem.selected==1){
            viewHolderItem.imageView.setAlpha(0.4f);
            viewHolderItem.tick.setVisibility(View.VISIBLE);
            viewHolderItem.tick.setAlpha(0.6f);


        }





    }

    @Override
    public int getItemCount() {
        return imageuris.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE;
    }
}


class ViewHolderItem extends RecyclerView.ViewHolder {
    SquareImageView imageView;
    TextView timeduration;
    static int selected=0;
    SquareImageView tick;


    public ViewHolderItem(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.gimage);
        this.timeduration= itemView.findViewById(R.id.duration);
        this.tick=itemView.findViewById(R.id.tick);

    }
}



