package com.firstlink.duo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wzq on 15/10/12.
 */
public class Topic {

    private int id;

    private String name;

    @SerializedName("description")
    private String desc;

    @SerializedName("pic_url")
    private String picUrl;

    @SerializedName("target_url")
    private String targetUrl;

    @SerializedName("display_type")
    private int displayType;

    @SerializedName("position")
    private int position;

    @SerializedName("create_time")
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
