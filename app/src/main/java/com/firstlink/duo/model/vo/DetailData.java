package com.firstlink.duo.model.vo;


import com.firstlink.duo.model.Post;
import com.firstlink.duo.model.PostSubClass;
import com.firstlink.duo.model.ServiceUser;
import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Comment;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ruanjinjing on 15/6/5.
 */
public class DetailData implements Serializable {

    private static final long serialVersionUID = 1420763733536200519L;

    @SerializedName("post")
    public Post post;

    @SerializedName("post_subclass_list")
    public List<PostSubClass> postSubClassList;

    @SerializedName("comment_list")
    public List<Comment> commentList;

    @SerializedName("server_time")
    public String serverTime;

    @SerializedName("service_user")
    public ServiceUser serviceUser;

    @SerializedName("user_order_count")
    public int userOrderCount;//当前登录用户跟单数

    @SerializedName("user_level")
    public int userLevel;//当前登录用户等级(1:普通用户,2:会员用户)

    @SerializedName("url")
    public String url;

}
