package com.firstlink.duo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ruanjinjing on 15/6/9.
 */
public class ItemPic implements Serializable{
    private static final long serialVersionUID = 1420763733536200523L;

    @SerializedName("pic_url")
    public String picUrl;

    @SerializedName("is_first_pic")
    public int isFirstPic;

    @SerializedName("description")
    public String description;
}
