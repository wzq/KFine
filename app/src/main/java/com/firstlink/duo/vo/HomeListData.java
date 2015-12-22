package com.firstlink.duo.vo;

import com.firstlink.duo.model.Goods;
import com.firstlink.duo.model.Pager;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wzq on 15/12/3.
 */
public class HomeListData {

    private Pager pager;

    @SerializedName("limit_list_desc")
    private String limitBuyTag;

    @SerializedName("limit_list_desc_en")
    private String limitBuyTagEn;

    @SerializedName("topic_list_desc")
    private String TopicTag;

    @SerializedName("topic_list_desc_en")
    private String TopicTagEn;

    @SerializedName("list_desc")
    private String listTag;

    @SerializedName("list_desc_en")
    private String listTagEn;

    @SerializedName("limit_list")
    private List<Goods> limitBuyList;

    @SerializedName("topic_list")
    private List<Goods> topicList;

    @SerializedName("list")
    private List<Goods> list;

    @SerializedName("server_time")
    private String serverTime;

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public String getLimitBuyTag() {
        return limitBuyTag;
    }

    public void setLimitBuyTag(String limitBuyTag) {
        this.limitBuyTag = limitBuyTag;
    }

    public String getLimitBuyTagEn() {
        return limitBuyTagEn;
    }

    public void setLimitBuyTagEn(String limitBuyTagEn) {
        this.limitBuyTagEn = limitBuyTagEn;
    }

    public String getTopicTag() {
        return TopicTag;
    }

    public void setTopicTag(String topicTag) {
        TopicTag = topicTag;
    }

    public String getTopicTagEn() {
        return TopicTagEn;
    }

    public void setTopicTagEn(String topicTagEn) {
        TopicTagEn = topicTagEn;
    }

    public String getListTag() {
        return listTag;
    }

    public void setListTag(String listTag) {
        this.listTag = listTag;
    }

    public String getListTagEn() {
        return listTagEn;
    }

    public void setListTagEn(String listTagEn) {
        this.listTagEn = listTagEn;
    }

    public List<Goods> getLimitBuyList() {
        return limitBuyList;
    }

    public void setLimitBuyList(List<Goods> limitBuyList) {
        this.limitBuyList = limitBuyList;
    }

    public List<Goods> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Goods> topicList) {
        this.topicList = topicList;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }
}
