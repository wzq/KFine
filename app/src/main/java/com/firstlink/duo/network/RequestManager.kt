package com.firstlink.duo.network

import android.content.Context
import android.telephony.TelephonyManager
import android.text.TextUtils
import com.firstlink.duo.App
import com.firstlink.duo.BuildConfig
import com.firstlink.duo.network.api.ProductApi
import com.firstlink.duo.util.PreferenceTools
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Created by wzq on 16/5/13.
 * Be part of []
 */
object RequestManager {

    private val retrofit = Retrofit.Builder()
            .client(OkHttpClient()).baseUrl(BuildConfig.HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build()

    val productApi by lazy {
        retrofit.create(ProductApi::class.java)!!
    }

    val requestMap by lazy {
        hashMapOf(
                "d_id" to getDeviceId(),
                "ts" to System.currentTimeMillis().toString(),
                "p" to "a",
                "ver" to BuildConfig.VERSION_NAME,
                "c_id" to BuildConfig.UMENG_CHANNEL
        )
    }

    fun getDeviceId(): String {
        var did: String? = PreferenceTools.getDevice()
        if (TextUtils.isEmpty(did)) {
            try {
                val telephonyManager = App.instance?.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager
                did = telephonyManager?.deviceId ?: null
            } catch (e: Exception) {
            }
        }
        if (TextUtils.isEmpty(did)) {
            did = UUID.randomUUID().toString()
        }
        PreferenceTools.setDevice(did!!)
        return did
    }
}