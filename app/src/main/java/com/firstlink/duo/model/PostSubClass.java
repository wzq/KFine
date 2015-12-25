package com.firstlink.duo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ruanjinjing on 15/6/10.
 */
public class PostSubClass implements Serializable {

    private static final long serialVersionUID = 1420763733536200520L;

    @SerializedName("id")
    public int id;

    @SerializedName("post_id")
    public int postId;

    @SerializedName("user_id")
    public int userId;

    @SerializedName("name")
    public String name;

    @SerializedName("price")
    public int price;

    @SerializedName("vip_price")
    public Integer vipPrice;

    @SerializedName("stock")
    public Integer stock;

    @SerializedName("pic_url")
    public String picUrl;
}
