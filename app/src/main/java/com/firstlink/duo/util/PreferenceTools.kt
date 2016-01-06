package com.firstlink.duo.util

import android.content.Context
import android.text.TextUtils
import com.firstlink.duo.BuildConfig
import com.firstlink.duo.model.User
import com.google.gson.Gson

/**
 * Created by wzq on 15/12/29.
 */
object PreferenceTools {

    val APP_ID = BuildConfig.APPLICATION_ID

    val TAG_USER = "user"
    val TAG_DEVICE = "device"


    fun setUser(context: Context, user: String?): Boolean {
        return context.getSharedPreferences(APP_ID, 0).edit().putString(TAG_USER, user).commit()
    }

    fun getUser(context: Context): User? {
        val u = context.getSharedPreferences(APP_ID, 0).getString(TAG_USER, null)
        if(!TextUtils.isEmpty(u)) {
            return Gson().fromJson(EncryptTools.aesDecrypt(u, EncryptTools.X), User::class.java)
        }
        return null
    }

    fun setDevice(context: Context, id: String) {
        context.getSharedPreferences(APP_ID, 0).edit().putString(TAG_DEVICE, id).commit()
    }

    fun getDevice(context: Context): String? {
        return context.getSharedPreferences(APP_ID, 0).getString(TAG_DEVICE, null)
    }

}