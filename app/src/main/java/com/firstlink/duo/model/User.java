package com.firstlink.duo.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by wzq on 15/3/4.
 */
public class User implements Serializable {

    @SerializedName("userid")
    private int id;

    @SerializedName("nickname")
    private String nickName;

    @SerializedName("status")
    private int status;

    @SerializedName("headurl")
    private String headUrl;

    @SerializedName("token")
    private String token;

    @SerializedName("mobile")
    private String mobile;

    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
