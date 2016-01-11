package com.firstlink.duo.util.network

import android.content.Context
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.firstlink.duo.App
import com.firstlink.duo.BuildConfig
import com.firstlink.duo.model.User
import com.firstlink.duo.util.Extensions.d
import com.firstlink.duo.util.PreferenceTools
import com.google.gson.Gson
import org.json.JSONObject
import java.util.*

/**
 * Created by wzq on 15/4/14.
 */
class VolleyHelper {

    private constructor() {
        if (queue == null) {
            queue = Volley.newRequestQueue(App.instance, OkHttpStack())
        }
    }

    private fun addRequest(request: Request<*>) {
        request.setRetryPolicy(DefaultRetryPolicy(TIMEOUT, RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))
        queue?.add(request)
    }

    fun <T> addPost(urlSet: UrlSet, params: MutableMap<String, *>?, clazz: Class<T>?, callback: (obj: T?, urlSet: UrlSet, original: Original) -> Unit) {
        addRequest(object : StringRequest(Request.Method.POST, urlSet.url, Response.Listener { s ->
            val response = JSONObject(s)
            val original = Original(response.getInt("code"), response.getString("data"), response.getString("message"))
            Log.d("HttpResponse", "${urlSet.name} -> ${original.data}")
            if (clazz != null && original.code == 1) {
                callback(Gson().fromJson(original.data, clazz), urlSet, original)
            } else {
                callback(null, urlSet, original)
            }

        }, Response.ErrorListener { error -> error.printStackTrace() }) {
            override fun getParams(): MutableMap<String, String>? {
                val map = hashMapOf<String, String>()
                map.putAll(headParams!!);
                if (urlSet.key != null) {
                    map.put(urlSet.key!!, Gson().toJson(params))
                }
                d("HttpRequest", "${urlSet.name} -> ${map.toString()}")
                return map
            }
        })
    }

    fun stop() {
        queue?.stop()
    }

    fun cancelAll(tag: String) {
        queue?.cancelAll(tag)
    }

    fun start() {
        queue?.start()
    }

    companion object {

        val DATA_JSON = "data_json"

        val POST_JSON = "post_json"

        private val TIMEOUT = 20000

        private val RETRIES = 3

        private var queue: RequestQueue? = null

        private var helper: VolleyHelper? = null

        fun call(): VolleyHelper {
            if (helper == null) {
                helper = VolleyHelper()
            }
            return helper!!
        }

        var headParams: MutableMap<String, String>? = null
            get() {
                if (headParams == null) {
                    d("init head params")
                    headParams = hashMapOf<String, String>()
                    headParams?.put("d_id", getDeviceId())
                    headParams?.put("ts", System.currentTimeMillis().toString())
                    headParams?.put("p", "a")
                    headParams?.put("ver", BuildConfig.VERSION_NAME)
                    headParams?.put("c_id", BuildConfig.UMENG_CHANNEL)
                }
                return headParams
            }

        fun updateHeadParams(user: User?) {
            if(user != null) {
                headParams?.put("u_id", user.id.toString());
                headParams?.put("tk", user.token);
            }else{
                headParams?.remove("u_id")
                headParams?.remove("tk")
            }
        }

        fun getDeviceId(): String {
            var did: String? = PreferenceTools.getDevice()
            if (TextUtils.isEmpty(did)) {
                try {
                    val telephonyManager = App.instance?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager?
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

}
