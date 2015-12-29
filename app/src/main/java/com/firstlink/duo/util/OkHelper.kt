package com.firstlink.duo.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.telephony.TelephonyManager
import android.text.TextUtils
import com.firstlink.duo.BuildConfig
import com.squareup.okhttp.*
import java.io.IOException
import java.util.*

/**
 * Created by wzq on 15/12/22.
 */
class OkHelper {

    val TAG = "OK_RESULT"

    val handler : Handler

    val updater : (hostSet : UrlSet, response: String?) -> Unit

    val failure : (request: Request?, e: IOException?) -> Unit

    constructor(updater: (urlSet : UrlSet, response: String?) -> Unit){
        handler = Handler(Looper.getMainLooper())
        this.updater = updater
        this.failure = { r, e -> Unit }
    }

    constructor(updater: (urlSet : UrlSet, response: String?) -> Unit, failure: (request: Request?, e: IOException?) -> Unit){
        handler = Handler(Looper.getMainLooper())
        this.updater = updater
        this.failure = failure
    }

    fun asyncPost(context: Context, urlSet : UrlSet, params : String) {
        OkHttpClient().newCall(initRequest(context, urlSet, params)).enqueue(object : Callback {
            override fun onResponse(response: Response?) {
                val result = response?.body()?.string()
                println("${urlSet.name} -> $result")
                handler.post { updater(urlSet, result) }
            }

            override fun onFailure(request: Request?, e: IOException?) {
                handler.post { failure(request, e) }
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