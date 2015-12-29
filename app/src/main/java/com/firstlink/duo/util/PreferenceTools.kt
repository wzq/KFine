package com.firstlink.duo.util

import android.content.Context
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


    fun setUser(context: Context, user: String?) {
        if (user != null)
            context.getSharedPreferences(APP_ID, 0).edit().putString(TAG_USER, user).commit()
    }

    fun getUser(context: Context): User? {
        return Gson().fromJson(context.getSharedPreferences(APP_ID, 0).getString(TAG_USER, null), User::class.java)
    }

    fun setDevice(context: Context, id: String) {
        context.getSharedPreferences(APP_ID, 0).edit().putString(TAG_DEVICE, id).commit()
    }

    fun getDevice(context: Context): String? {
        return context.getSharedPreferences(APP_ID, 0).getString(TAG_DEVICE, null)
    }

}