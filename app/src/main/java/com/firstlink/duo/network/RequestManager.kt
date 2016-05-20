package com.firstlink.duo.network

import com.firstlink.duo.BuildConfig
import com.firstlink.duo.network.api.ProductApi
import com.firstlink.duo.util.network.VolleyHelper
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by wzq on 16/5/13.
 * Be part of []
 */
object RequestManager {

    private val retrofit = Retrofit.Builder()
            .client(OkHttpClient()).baseUrl(BuildConfig.HOST)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    val productApi by lazy {
        retrofit.create(ProductApi::class.java)
    }

    private val requestMap by lazy {
        hashMapOf(
                "d_id" to VolleyHelper.getDeviceId(),
                "ts" to System.currentTimeMillis().toString(),
                "p" to "a",
                "ver" to BuildConfig.VERSION_NAME,
                "c_id" to BuildConfig.UMENG_CHANNEL
        )
    }


    fun getParams(key: String, map: MutableMap<String, *>?): MutableMap<String, String>{
        val temp = hashMapOf<String, String>()
        temp.putAll(requestMap)
        if (map != null){
            temp.put(key, Gson().toJson(map))
        }
        println(temp.toString())
        return temp
    }
}