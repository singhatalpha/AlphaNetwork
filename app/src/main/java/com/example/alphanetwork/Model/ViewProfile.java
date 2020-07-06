package com.example.alphanetwork.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ViewProfile
{

//    @SerializedName("image")
//    @Expose
    private String photo;
    private String username;
    private int influence;
    private int popularity;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private List<String> commitment;
    private ProfilePack pack;
    private ProfileParty party;
    private String user_id;


//    private String post_count;
//    private String follower_count;
//    private String following_count;





    public int getInfluence() {
        return influence;
    }

    public void setInfluence(int influence) {
        this.influence = influence;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    public List<String> getCommitment() {
        return commitment;
    }

    public void setCommitment(List<String> commitment) {
        this.commitment = commitment;
    }

    public ProfilePack getPack() {
        return pack;
    }

    public void setPack(ProfilePack pack) {
        this.pack = pack;
    }

    public ProfileParty getParty() {
        return party;
    }

    public void setParty(ProfileParty party) {
        this.party = party;
    }



    public String getPhoto ()
    {
        return photo;
    }

    public void setPhoto (String photo)
    {
        this.photo = photo;
    }





    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }


//
//    @Override
//    public String toString()
//    {
//        return "ClassPojo [birthdate = "+birthdate+", latitude = "+latitude+", photo = "+photo+", crew = "+crew+", highlights = "+highlights+", phone = "+phone+", following_count = "+following_count+", commits = "+commits+", location = "+location+", followed_by_count = "+followed_by_count+", id = "+id+", post_count = "+post_count+", user = "+user+", passion = "+passion+", username = "+username+", longitude = "+longitude+"]";
//    }
}
