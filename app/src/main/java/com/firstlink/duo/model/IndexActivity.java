package com.firstlink.duo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ruanjinjing on 16/7/7.
 */
public class IndexActivity {

    public int displayType;

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("pic_url")
    public String picUrl;

    @SerializedName("target_url")
    public String targetUrl;

    @SerializedName("tag_pic")
    public String tagPic;

    @SerializedName("activity_product_list")
    public List<Product> productList;
}
