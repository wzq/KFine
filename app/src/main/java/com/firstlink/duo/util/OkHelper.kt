package com.firstlink.duo.util

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.squareup.okhttp.*
import java.io.IOException

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

    fun initRequest(context: Context, urlSet : UrlSet, params : String) : Request{
       return Request.Builder().url(urlSet.url).post(fun (): RequestBody{
           val c = Tools.getCommonParams(context);
           if (urlSet.key!=null) {
               c.add(urlSet.key, params)
           }
           return c.build()
       }()).build()
    }

}