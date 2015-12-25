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

    val updater : (hostSet : HostSet, response: String?) -> Unit

    val failure : (request: Request?, e: IOException?) -> Unit

    constructor(updater: (hostSet : HostSet, response: String?) -> Unit){
        handler = Handler(Looper.getMainLooper())
        this.updater = updater
        this.failure = { r, e -> Unit }
    }

    constructor(updater: (hostSet : HostSet, response: String?) -> Unit, failure: (request: Request?, e: IOException?) -> Unit){
        handler = Handler(Looper.getMainLooper())
        this.updater = updater
        this.failure = failure
    }

    fun asyncGet(url: String, callback: Callback) {
        OkHttpClient().newCall(Request.Builder().url(BuildConfig.HOST.plus(url)).build()).enqueue(callback)
    }

    fun asyncPost(context: Context, hostSet : HostSet, params : String) {
        OkHttpClient().newCall(initRequest(context, hostSet, params)).enqueue(object : Callback {
            override fun onResponse(response: Response?) {
                val result = response?.body()?.string()
                handler.post { updater(hostSet, result) }
            }

            override fun onFailure(request: Request?, e: IOException?) {
                handler.post { failure(request, e) }
            }

        })
    }

    fun initRequest(context: Context, hostSet : HostSet, params : String) : Request{
       return Request.Builder().url(hostSet.url).post(fun (): RequestBody{
           val c = getCommonParams(context);
           if (hostSet.key!=null) {
               c.add(hostSet.key, params)
           }
           return c.build()
       }()).build()
    }

}