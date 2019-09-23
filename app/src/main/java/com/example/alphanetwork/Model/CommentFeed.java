package com.example.alphanetwork.Model;

import com.example.alphanetwork.Model.ModelFeed;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CommentFeed {


    private int size;

    @SerializedName("comments")
    @Expose
    private List<Comments> comments;


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }








}

