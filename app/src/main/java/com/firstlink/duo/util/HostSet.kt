package com.firstlink.duo.util

import com.firstlink.duo.BuildConfig


/**
 * Created by wzq on 15/5/4.
 */
enum class HostSet (vararg s: String) {

    INIT_CONFIG("initconf.json"),
    LOGIN("user/login.json"),
    FIND_HOME_DATA("post/find_index_datas.json", POST_JSON),
    FIND_GOODS_DETAIL("post/get_groupon_detail.json", POST_JSON);

    val url: String

    var key: String? = null

    init {
        url = BuildConfig.HOST.plus(s[0])
        if (s.size > 1) {
            key = s[1]
        }
    }

}
