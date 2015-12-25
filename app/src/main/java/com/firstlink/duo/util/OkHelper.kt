package com.firstlink.duo.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.firstlink.duo.BuildConfig
import com.squareup.okhttp.*
import java.io.IOException

/**
 * Created by wzq on 15/12/22.
 */
class OkHelper {

    val TAG = "OK_RESULT"

    val handler : Handler

    val updater : (response: String?) -> Unit

    val failure : (request: Request?, e: IOException?) -> Unit

    constructor(updater: (response: String?) -> Unit){
        handler = Handler(Looper.getMainLooper())
        this.updater = updater
        this.failure = { r, e -> Unit }
    }

    constructor(updater: (response: String?) -> Unit, failure: (request: Request?, e: IOException?) -> Unit){
        handler = Handler(Looper.getMainLooper())
        this.updater = updater
        this.failure = failure
    }

    fun asyncGet(url: String, callback: Callback) {
        OkHttpClient().newCall(Request.Builder().url(BuildConfig.HOST.plus(url)).build()).enqueue(callback)
    }

    fun asyncPost(context: Context, hostSet : HostSet, vararg params : String) = OkHttpClient().newCall(initRequest(context, hostSet, params as List<String>)).enqueue(object : Callback {
        override fun onResponse(response: Response?) {
            val result = response?.body()?.string()
            handler.post { updater(result) }
        }

        override fun onFailure(request: Request?, e: IOException?) {
            handler.post { failure(request, e) }
        }

    })

    fun initRequest(context: Context, hostSet : HostSet, params : List<String>) : Request{
       return Request.Builder().url(hostSet.keys[0]).post(fun (): RequestBody{
           val c = getCommonParams(context);
           for(s in hostSet.keys){
           }
           return c.build()
       }()).build()
    }

}