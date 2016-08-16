package com.firstlink.duo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ruanjinjing on 16/6/13.
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 1420763133536200618L;

    public int displayType;

    @SerializedName("id")
    public int id;

    @SerializedName("product_id")
    public int productId;

    @SerializedName("name")
    public String name;

    @SerializedName("title")
    public String title;

    @SerializedName("description_original")
    public String description;

    @SerializedName("description_cn")
    public String descriptionCn;

    @SerializedName("first_pic")
    public String firstPic;

    @SerializedName("brand_name")
    public String brandName;

    @SerializedName("price")
    public int price;

    @SerializedName("original_price")
    public Integer originalPrice;

    @SerializedName("refer_price")
    public Integer referPrice;

    @SerializedName("url")
    public String url;

    @SerializedName("sales_count")
    public int salesCount;

    @SerializedName("status")
    public int status;

    @SerializedName("weight")
    public int weight;

    @SerializedName("supplier_pic")
    public String supplierPic;

    @SerializedName("mark_pic")
    public String markPic;
}
