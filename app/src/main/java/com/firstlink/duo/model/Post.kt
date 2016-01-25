package com.firstlink.duo.model

import com.google.gson.annotations.SerializedName

/**
 * Created by wzq on 16/1/3.
 */
data class Post(
        @SerializedName("id")
        var id: Int = 0,

        @SerializedName("user_id")
        var userId: Int = 0,

        @SerializedName("title")
        var title: String,

        @SerializedName("description")
        var description: String,

        @SerializedName("first_pic")
        var firstPic: String,

        @SerializedName("post_ext_data")
        var postExtData: PostExtData,

        @SerializedName("type")
        var type: Int = 0,

        @SerializedName("status")
        var status: Int = 0,

        @SerializedName("shipping_type")
        var shippingType: Int = 0, //发货方式(1.普通发货,2:如意仓发货,3:直邮,4:国内自发货,5:海外自发货)

        @SerializedName("is_need_id_card")
        var isNeedIdCard: Boolean = false,

        @SerializedName("begin_time")
        var beginTime: String,

        @SerializedName("end_time")
        var endTime: String,

        @SerializedName("create_time")
        var createTime: String,

        @SerializedName("update_time")
        var updateTime: String,

        @SerializedName("is_favorite")
        var isFavorite: Boolean = false,

        @SerializedName("order_count")
        var orderCount: Int = 0,

        @SerializedName("item_pics")
        var itemPics: List<ItemPic>,

        @SerializedName("nickname")
        var nickname: String,

        @SerializedName("head_pic")
        var headPic: String,

        @SerializedName("is_like")
        var isLike: Boolean = false,

        @SerializedName("is_postage_free")
        var isPostageFree: Int = 0, //0:不包邮,1:包邮

        @SerializedName("stock")
        var stock: Int = -1,

        @SerializedName("limited_num")
        var limitedNum: Int = 0,

        val name: String,

        //@SerializedName("post_authority_attribute")
        //var postAuthorityAttr: PostAuthorityAttr
        //
        //inner class PostAuthorityAttr : Serializable {
        //        @SerializedName("only_vip")
        //        var onlyVip: Int = 0
        //
        //        @SerializedName("only_new")
        //        var onlyNew: Int = 0
        //
        //}

        @SerializedName("comment_count")
        var commentCount: Int = 0,

        @SerializedName("is_support_groupon")
        var isSupportGroupon: Int = 0//是否支持拼团(0:不支持,1:支持)

)