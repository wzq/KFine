package com.firstlink.duo.util

import android.os.Handler
import android.os.Looper
import com.firstlink.duo.BuildConfig
import com.squareup.okhttp.*
import java.io.IOException

/**
 * Created by wzq on 15/12/22.
 */
class OkHelper {

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

    fun asyncPost(url: String, body: RequestBody) {
        OkHttpClient().newCall(Request.Builder().url(url).post(body).build()).enqueue(object : Callback {
            override fun onResponse(response: Response?) {
                val result = response?.body()?.string()
                println(result) //todo
                handler.post { updater(result) }
            }

            override fun onFailure(request: Request?, e: IOException?) {
                handler.post { failure(request, e) }
            }

        })
    }
}