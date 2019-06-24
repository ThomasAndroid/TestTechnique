package com.thomas.test.object;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {

    @SerializedName("albumId")
    public int albumId;

    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("url")
    public String url;

    @SerializedName("thumbnailUrl")
    public String thumbnailUrl;

}
