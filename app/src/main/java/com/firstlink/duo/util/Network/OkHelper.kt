package com.firstlink.duo.util.network

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.telephony.TelephonyManager
import android.text.TextUtils
import com.firstlink.duo.BuildConfig
import com.firstlink.duo.util.PreferenceTools
import com.google.gson.Gson
import com.squareup.okhttp.*
import org.json.JSONObject
import java.io.IOException
import java.util.*

/**
 * Created by wzq on 15/12/22.
 */
class OkHelper {

    val TAG = "OK_RESULT"

    val handler : Handler

    val context: Context

    constructor(context: Context){
        handler = Handler(Looper.getMainLooper())
        this.context = context
    }

    fun <T> asyncPost(urlSet : UrlSet, params : String, clazz: Class<T>, callback: (result: T?, urlSet : UrlSet, resultCode: Int, msg: String) -> Unit) {
        OkHttpClient().newCall(initRequest(context, urlSet, params)).enqueue(object : Callback {
            override fun onResponse(response: Response?) {
                val s = JSONObject(response?.body()?.string())
                val code = s.getInt("code")
                val data = s.getString("data")
                val errorMsg = s.getString("message")
                if(TextUtils.isEmpty(data))
                    handler.post { callback(null , urlSet, code, errorMsg) }
                else{
                    handler.post { callback(Gson().fromJson(data, clazz), urlSet, code, errorMsg) }
                }
            }

            override fun onFailure(request: Request?, e: IOException?) {
            }

        })
    }

    fun initRequest(context: Context, urlSet: UrlSet, params: String): Request {
        return Request.Builder().url(urlSet.url).post(fun(): RequestBody {
            val c = getParams(context);
            if (urlSet.key != null) {
                c.add(urlSet.key, params)
            }
            return c.build()
        }()).tag(urlSet).build()
    }

    companion object {

        val POST_JSON = "post_json"

        val DATA_JSON = "data_json"

        fun getParams(context: Context): FormEncodingBuilder {
            val params = FormEncodingBuilder()
                    .add("d_id", getDeviceId(context))
                    .add("ts", System.currentTimeMillis().toString())
                    .add("p", "a")
                    .add("ver", BuildConfig.VERSION_NAME)
                    .add("c_id", BuildConfig.UMENG_CHANNEL)
            val u = PreferenceTools.getUser(context)
            if (u != null) {
                params?.add("u_id", u.id.toString());
                params?.add("tk", u.token);
            }
            return params
        }

        fun getDeviceId(context: Context): String {
            var did: String? = PreferenceTools.getDevice(context)
            if (TextUtils.isEmpty(did)) {
                try {
                    val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
                    did = telephonyManager?.deviceId ?: null
                } catch (e: Exception) {
                }
            }
            if (TextUtils.isEmpty(did)) {
                did = UUID.randomUUID().toString()
            }
            PreferenceTools.setDevice(context, did!!)
            return did
        }
    }

}