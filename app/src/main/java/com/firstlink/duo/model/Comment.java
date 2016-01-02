package com.firstlink.duo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ruanjinjing on 15/6/5.
 */
public class Comment implements Serializable{
    private static final long serialVersionUID = 1420763733536200521L;

    @SerializedName("id")
    public int id;

    @SerializedName("item_id")
    public int itemId;

    @SerializedName("seller_id")
    public int sellerId;

    @SerializedName("buyer_id")
    public int buyerId;

    @SerializedName("buyer_nickname")
    public String buyerNickname;

    @SerializedName("buyer_head_pic")
    public String buyerHeadPic;

    @SerializedName("reply_to_user_id")
    public int replyToUserId;

    @SerializedName("reply_to_user_nick")
    public String replyToUserNick;

    @SerializedName("content")
    public String content;

    @SerializedName("create_time")
    public String createTime;
}
