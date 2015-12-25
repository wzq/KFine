package com.firstlink.duo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ruanjinjing on 15/6/9.
 */
public class PostExtData implements Serializable{

    private static final long serialVersionUID = 1420763733536200524L;

    @SerializedName("estimate_price")
    public int estimatePrice;

    @SerializedName("vip_price")
    public Integer vipPrice;

    @SerializedName("refer_price")
    public int referPrice;

    @SerializedName("source")
    public String source;

    @SerializedName("buy_channel")
    public String buyChannel;

    @SerializedName("weight")
    public int weight;

    @SerializedName("international_postage")
    public int internationalPostage;

    @SerializedName("arrival_time")
    public String arrivalTime;

    @SerializedName("groupon_price")
    public Integer grouponPrice;

    @SerializedName("member_count")
    public Integer memberCount;

}
