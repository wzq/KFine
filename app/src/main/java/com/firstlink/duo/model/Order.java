package com.firstlink.duo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by wzq on 15/6/2.
 */
public class Order {

    private int id;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("first_pic")
    private String firstPic;

    private double price;

    private int status;

    @SerializedName("end_time")
    private String endTime;

    private String title;

    @SerializedName("refer_price")
    private double exPrice;

    private String source;

    private float discount;

    @SerializedName("begin_time")
    private String startTime;

    @Expose
    private String restTime;

    private Integer stock;

    @SerializedName("description")
    private String desc;

    @SerializedName("groupon_price")
    private int groupPrice;

    @SerializedName("member_count")
    private int groupCount;

    @SerializedName("is_new")
    private Integer isNew;

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public int getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(int groupPrice) {
        this.groupPrice = groupPrice;
    }

    public int getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(int groupCount) {
        this.groupCount = groupCount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getRestTime() {
        return restTime;
    }

    public void setRestTime(String restTime) {
        this.restTime = restTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getExPrice() {
        return exPrice;
    }

    public void setExPrice(double exPrice) {
        this.exPrice = exPrice;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
