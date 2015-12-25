package com.firstlink.duo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ruanjinjing on 15/6/5.
 */
public class Post implements Serializable {
    private static final long serialVersionUID = 1420763733536200522L;

    @SerializedName("id")
    public int id;

    @SerializedName("user_id")
    public int userId;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("first_pic")
    public String firstPic;

    @SerializedName("post_ext_data")
    public PostExtData postExtData;

    @SerializedName("type")
    public int type;

    @SerializedName("status")
    public int status;

    @SerializedName("shipping_type")
    public int shippingType;//发货方式(1.普通发货,2:如意仓发货,3:直邮,4:国内自发货,5:海外自发货)

    @SerializedName("is_need_id_card")
    public boolean isNeedIdCard;

    @SerializedName("begin_time")
    public String beginTime;

    @SerializedName("end_time")
    public String endTime;

    @SerializedName("create_time")
    public String createTime;

    @SerializedName("update_time")
    public String updateTime;

    @SerializedName("is_favorite")
    public boolean isFavorite;

    @SerializedName("order_count")
    public int orderCount;

    @SerializedName("item_pics")
    public List<ItemPic> itemPics;

    @SerializedName("nickname")
    public String nickname;

    @SerializedName("head_pic")
    public String headPic;

    @SerializedName("is_like")
    public boolean isLike;

    @SerializedName("is_postage_free")
    public int isPostageFress;//0:不包邮,1:包邮

    @SerializedName("stock")
    public Integer stock;

    @SerializedName("limited_num")
    public int limitedNum;

    @SerializedName("post_authority_attribute")
    public PostAuthorityAttr postAuthorityAttr;

    public class PostAuthorityAttr implements Serializable {
        @SerializedName("only_vip")
        public int onlyVip;

        @SerializedName("only_new")
        public int onlyNew;

    }

    @SerializedName("comment_count")
    public int commentCount;

    @SerializedName("is_support_groupon")
    public int isSupportGroupon;//是否支持拼团(0:不支持,1:支持)
}
