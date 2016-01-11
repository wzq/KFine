package com.firstlink.duo.util

import android.text.TextUtils
import com.firstlink.duo.App
import com.firstlink.duo.BuildConfig
import com.firstlink.duo.model.User
import com.google.gson.Gson

/**
 * Created by wzq on 15/12/29.
 */
object PreferenceTools {

    val APP_ID = BuildConfig.APPLICATION_ID

    val TAG_USER = "duo_user"
    val TAG_DEVICE = "duo_device"

    fun setUser(user: String?): Boolean {
        return App.instance?.getSharedPreferences(APP_ID, 0)?.edit()?.putString(TAG_USER, user)?.commit() ?: false
    }

    fun getUser(): User? {
        val u = App.instance?.getSharedPreferences(APP_ID, 0)?.getString(TAG_USER, null)
        if(!TextUtils.isEmpty(u)) {
            return Gson().fromJson(EncryptTools.aesDecrypt(u!!, EncryptTools.X), User::class.java)
        }
        return null
    }

    fun setDevice(id: String): Boolean{
        return App.instance?.getSharedPreferences(APP_ID, 0)?.edit()?.putString(TAG_DEVICE, id)?.commit() ?: false
    }

    fun getDevice(): String? {
        return App.instance?.getSharedPreferences(APP_ID, 0)?.getString(TAG_DEVICE, null)
    }

}