package com.example.alphanetwork.Model;

import java.util.List;

/**
 * Created by User on 8/22/2017.
 */

public class Comments {

    private String id;
    private String comment;private String commented_date;
    private int likes_count;
    private int unlikes_count;
    private int user;
    private int feed;
    private ModelProfile profile;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommented_date() {
        return commented_date;
    }

    public void setCommented_date(String commented_date) {
        this.commented_date = commented_date;
    }

    public int getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(int likes_count) {
        this.likes_count = likes_count;
    }

    public int getUnlikes_count() {
        return unlikes_count;
    }

    public void setUnlikes_count(int unlikes_count) {
        this.unlikes_count = unlikes_count;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getFeed() {
        return feed;
    }

    public void setFeed(int feed) {
        this.feed = feed;
    }

    public ModelProfile getProfile() {
        return profile;
    }

    public void setProfile(ModelProfile profile) {
        this.profile = profile;
    }







}