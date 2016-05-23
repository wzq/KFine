package com.firstlink.duo.util.Extensions

import com.firstlink.duo.network.RequestManager
import com.google.gson.Gson

/**
 * Created by wzq on 16/5/21.
 * Be part of []
 */

fun buildParams(key: String, map: MutableMap<String, *>?): MutableMap<String, String> = hashMapOf<String, String>().apply { putAll(RequestManager.requestMap);put(key, Gson().toJson(map)) }
