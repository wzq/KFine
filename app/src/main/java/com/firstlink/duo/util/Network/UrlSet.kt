package com.firstlink.duo.util.network

import com.firstlink.duo.BuildConfig


/**
 * Created by wzq on 15/5/4.
 */
enum class UrlSet(vararg s: String) {

    FIND_HOME_DATA("product/find_index_datas.json", "product_json"),
    FIND_GOODS_DETAIL("post/get_groupon_detail.json", "post_json"),
    FIND_NATIONS("tab_page/find_tab_pages.json"),
    FIND_TOPICS("product/find_products_by_topic.json", "topic_json");

    val url: String

    var key: String? = null

    init {
        url = BuildConfig.HOST.plus(s[0])
        if (s.size > 1) {
            key = s[1]
        }
    }

}
