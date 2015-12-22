package com.firstlink.duo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wzq on 15/7/20.
 */
public class Goods {

    @SerializedName("first_pic")
    private String first_pic;

    @SerializedName("begin_time")
    private String begin_time;

    @SerializedName("status")
    private int status;

    @SerializedName("url")
    private String url;

    @SerializedName("discount")
    private double discount;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("price")
    private int price;

    @SerializedName("source")
    private String source;

    @SerializedName("end_time")
    private String end_time;

    @SerializedName("description")
    private String description;

    @SerializedName("user_id")
    private int user_id;

    @SerializedName("refer_price")
    private int refer_price;

    @SerializedName("stock")
    private Integer stock;

    @SerializedName("index_pic")
    private String indexPic;

    @SerializedName("is_select")
    private int limitBuySelect;

    @SerializedName("pic_url")
    private String picUrl;

    @SerializedName("target_url")
    private String targetUrl;

    @SerializedName("vip_price")
    private String VipPrice;

    @SerializedName("is_history")
    private int isHistory;

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getIndexPic() {
        return indexPic;
    }

    public void setIndexPic(String indexPic) {
        this.indexPic = indexPic;
    }

    public int getLimitBuySelect() {
        return limitBuySelect;
    }

    public void setLimitBuySelect(int limitBuySelect) {
        this.limitBuySelect = limitBuySelect;
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

    public String getVipPrice() {
        return VipPrice;
    }

    public void setVipPrice(String vipPrice) {
        VipPrice = vipPrice;
    }

    public int getIsHistory() {
        return isHistory;
    }

    public void setIsHistory(int isHistory) {
        this.isHistory = isHistory;
    }

    public void setFirst_pic(String first_pic) {
        this.first_pic = first_pic;
    }

    public void setBegin_time(String begin_time) {
        this.begin_time = begin_time;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setRefer_price(int refer_price) {
        this.refer_price = refer_price;
    }

    public String getFirst_pic() {
        return first_pic;
    }

    public String getBegin_time() {
        return begin_time;
    }

    public int getStatus() {
        return status;
    }

    public String getUrl() {
        return url;
    }

    public double getDiscount() {
        return discount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getSource() {
        return source;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getDescription() {
        return description;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getRefer_price() {
        return refer_price;
    }

    public Integer getStock() {
        return stock;
    }
}
