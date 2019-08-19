package com.example.alphanetwork.Feed;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import com.example.alphanetwork.Model.ModelFeed;
import com.example.alphanetwork.R;
import Utils.Utils;



public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    private List<ModelFeed> posts;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private FragmentManager fragmentManager;

    public Adapter(List<ModelFeed> posts, Context context,FragmentManager fragmentManager) {
        this.posts = posts;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_feeditem, parent, false);
        return new MyViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        ModelFeed modelFeed = posts.get(position);
        System.out.println(modelFeed.getMedia());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();


//        if(modelFeed.getMedia().size()!=0) {
//            holder.imgView_postPic.setVisibility(View.VISIBLE);
//            System.out.println(modelFeed.getMedia().get(0).getFile_data() );
//            System.out.println("Konda is anaconda" );
//            Glide.with(context)
//                    .load(modelFeed.getMedia().get(0).getFile_data())
//                    .apply(requestOptions)
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            System.out.println("Konda is in LoadFailed" );
//                            System.out.println(e);
//                            holder.progressBar.setVisibility(View.GONE);
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            System.out.println("Konda is onResourceReady" );
////                            holder.progressBar.setVisibility(View.GONE);
//                            return false;
//                        }
//                    })
//                    .transition(DrawableTransitionOptions.withCrossFade())
//                    .dontAnimate()
//                    .into(holder.imgView_postPic);
//
////            Glide.with(context).load(modelFeed.getMedia().get(0).getFile_data()).dontAnimate().into(holder.imgView_postPic);
//        }
//        else{
////            holder.imgView_postPic.setVisibility(View.GONE);
//        }


//        MediaAdapter ma = new MediaAdapter();
//        holder.vp.setAdapter(ma);

        final ArrayList<Fragment> fragments = new ArrayList<>();
        List<String> medias = modelFeed.getMedia();

        for(String media : medias){
            MediaAdapter fragment = MediaAdapter.getInstance(media);
            fragments.add(fragment);
        }

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(fragmentManager, fragments);
        holder.vp.setAdapter(pagerAdapter);




         ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
//                System.out.println(viewPager);
//                System.out.println("view pager");
//
                MediaAdapter one = (MediaAdapter) fragments.get(holder.vp.getCurrentItem());
                System.out.println("fragement : " + one);
                if(one.link.endsWith(".mp4")){
                    one.bar.setVisibility(View.VISIBLE);
                    one.mVideo.setMediaController(one.mediacontroller);
//                System.out.println(one.uri);
                    one.mVideo.setVideoURI(Uri.parse(one.link));
                    one.mVideo.requestFocus();
                    one.mVideo.start();}
                else
                {

//                    ((MediaAdapter)fragments.get(holder.vp.getCurrentItem()-1)).mVideo.stopPlayback();
                    Glide.with(context).load(one.link).into(one.mImage);
                }

            }


        };
        holder.vp.addOnPageChangeListener(pageChangeListener);


































        holder.tl.setupWithViewPager(holder.vp, true);


        holder.tv_name.setText(modelFeed.getProfile().getUser());
        holder.tv_time.setText(Utils.DateFormat(modelFeed.getTime()));
        holder.tv_likes.setText(String.valueOf(modelFeed.getLikes()));
        holder.tv_comments.setText(modelFeed.getComments() + " comments");
        holder.tv_status.setText(modelFeed.getContent());

        Glide.with(context).load(modelFeed.getProfile().getPhoto()).dontAnimate().into(holder.imgView_proPic);


//        if (modelFeed.getProfile().getPhoto() == null) {
//            holder.imgView_postPic.setVisibility(View.GONE);
//        } else {
//            holder.imgView_postPic.setVisibility(View.VISIBLE);
//            Glide.with(context).load(modelFeed.getProfile().getPhoto()).into(holder.imgView_postPic);
//        }

    }

    @Override
    public int getItemCount() {

        return posts.size();

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView tv_name, tv_time, tv_likes, tv_comments, tv_status;
        ImageView imgView_proPic, imgView_postPic;
        ProgressBar progressBar;
        OnItemClickListener onItemClickListener;
        ViewPager vp;
        TabLayout tl;

        public MyViewHolder(View itemView, OnItemClickListener onItemClickListener) {

            super(itemView);

            itemView.setOnClickListener(this);
            imgView_proPic = (ImageView) itemView.findViewById(R.id.imgView_proPic);
//            imgView_postPic = (ImageView) itemView.findViewById(R.id.imgView_postPic);
            vp = itemView.findViewById(R.id.view_pager_media);
            tl = itemView.findViewById(R.id.tab_layout);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_likes = (TextView) itemView.findViewById(R.id.tv_like);
            tv_comments = (TextView) itemView.findViewById(R.id.tv_comment);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);

            this.onItemClickListener = onItemClickListener;

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
