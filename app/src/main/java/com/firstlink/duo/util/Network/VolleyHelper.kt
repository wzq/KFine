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
import com.firstlink.duo.BuildConfig
import com.firstlink.duo.util.PreferenceTools
import com.google.gson.Gson
import org.json.JSONObject
import java.util.*

/**
 * Created by wzq on 15/4/14.
 */
class VolleyHelper {

    val context: Context

    private constructor(context: Context) {
        this.context = context
        if(queue == null) {
            queue = Volley.newRequestQueue(context, OkHttpStack())
        }
    }

    private fun addRequest(request: Request<*>) {
        request.setRetryPolicy(DefaultRetryPolicy(TIMEOUT, RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT))
        queue?.add(request)
    }

    fun <T> addPost(urlSet: UrlSet, params: MutableMap<String, *>?, clazz: Class<T>?, callback: (obj: T?, urlSet : UrlSet, result: Original) -> Unit){
        addRequest(object :StringRequest(Request.Method.POST, urlSet.url, Response.Listener { s ->
            val response = JSONObject(s)
            val original = Original(response.getInt("code"), response.getString("data"), response.getString("message"))
            Log.d("HttpResponse", original.data)
            if(clazz != null && original.code == 1) {
                callback(Gson().fromJson(original.data, clazz), urlSet, original)
            }else {
                callback(null, urlSet, original)
            }

        }, Response.ErrorListener { error -> error.printStackTrace() }){
            override fun getParams(): MutableMap<String, String>? {
                val map = getHeadParams(context)
                if (urlSet.key != null) {
                    map.put(urlSet.key!!, Gson().toJson(params))
                }
                return map
            }
        })
    }

    fun stop() {
        queue?.stop()
    }

    fun cancelAll(tag : String) {
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

        fun call(context: Context): VolleyHelper{
            if(helper == null){
                helper = VolleyHelper(context)
            }
            return helper!!
        }

        fun getHeadParams(context: Context): MutableMap<String, String> {
            val params = hashMapOf<String, String>()
            params.put("d_id", getDeviceId(context))
            params.put("ts", System.currentTimeMillis().toString())
            params.put("p", "a")
            params.put("ver", BuildConfig.VERSION_NAME)
            params.put("c_id", BuildConfig.UMENG_CHANNEL)
            val u = PreferenceTools.getUser(context)
            if (u != null) {
                params.put("u_id", u.id.toString());
                params.put("tk", u.token);
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
