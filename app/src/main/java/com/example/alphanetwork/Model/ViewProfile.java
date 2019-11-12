package com.example.alphanetwork.Model;

public class ViewProfile
{
    private String birthdate;

    private String latitude;

    private String photo;

    private String[] crew;

    private String[] highlights;

    private String phone;

    private String following_count;

    private String[] commits;

    private String location;

    private String followed_by_count;

    private String id;

    private String post_count;

    private String user;

    private String[] passion;

    private String username;

    private String longitude;

    public String getBirthdate ()
{
    return birthdate;
}

    public void setBirthdate (String  birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public String getPhoto ()
    {
        return photo;
    }

    public void setPhoto (String photo)
    {
        this.photo = photo;
    }

    public String[] getCrew ()
    {
        return crew;
    }

    public void setCrew (String[] crew)
    {
        this.crew = crew;
    }

    public String[] getHighlights ()
    {
        return highlights;
    }

    public void setHighlights (String[] highlights)
    {
        this.highlights = highlights;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getFollowing_count ()
    {
        return following_count;
    }

    public void setFollowing_count (String following_count)
    {
        this.following_count = following_count;
    }

    public String[] getCommits ()
    {
        return commits;
    }

    public void setCommits (String[] commits)
    {
        this.commits = commits;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getFollowed_by_count ()
    {
        return followed_by_count;
    }

    public void setFollowed_by_count (String followed_by_count)
    {
        this.followed_by_count = followed_by_count;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPost_count ()
    {
        return post_count;
    }

    public void setPost_count (String post_count)
    {
        this.post_count = post_count;
    }

    public String getUser ()
    {
        return user;
    }

    public void setUser (String user)
    {
        this.user = user;
    }

    public String[] getPassion ()
    {
        return passion;
    }

    public void setPassion (String[] passion)
    {
        this.passion = passion;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [birthdate = "+birthdate+", latitude = "+latitude+", photo = "+photo+", crew = "+crew+", highlights = "+highlights+", phone = "+phone+", following_count = "+following_count+", commits = "+commits+", location = "+location+", followed_by_count = "+followed_by_count+", id = "+id+", post_count = "+post_count+", user = "+user+", passion = "+passion+", username = "+username+", longitude = "+longitude+"]";
    }
}
