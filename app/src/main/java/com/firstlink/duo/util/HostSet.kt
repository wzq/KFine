package com.firstlink.duo.util


/**
 * Created by wzq on 15/5/4.
 */
enum class HostSet (vararg s: String) {

    INIT_CONFIG("initconf.json"),
    LOGIN("user/login.json"),
    FIND_HOME_DATA("post/find_index_datas.json", POST_JSON),
    FIND_GOODS_DETAIL("post/get_groupon_detail.json", POST_JSON);

    val host: String

    var keys: List<String>? = null

    init {
        host = h
    }

}
