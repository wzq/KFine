package com.firstlink.duo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ruanjinjing on 15/7/24.
 */
public class ServiceUser implements Serializable{

    private static final long serialVersionUID = 1420763733536200619L;

    @SerializedName("id")
    public int userId;

    @SerializedName("nickname")
    public String nickname;

    @SerializedName("head_pic")
    public String headPic;
}
