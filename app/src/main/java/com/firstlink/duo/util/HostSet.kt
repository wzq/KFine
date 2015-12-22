package com.firstlink.duo.util

import com.firstlink.duo.BuildConfig


/**
 * Created by wzq on 15/5/4.
 */
enum class HostSet (s: String) {

    INIT_CONFIG("initconf.json"),
    LOGIN("user/login.json"),
    FIND_HOME_DATA("post/find_index_datas.json");

    val host: String

    init {
        host = BuildConfig.HOST.plus(s)
    }

}
