package com.firstlink.duo.util

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.firstlink.duo.BuildConfig
import com.squareup.okhttp.FormEncodingBuilder
import java.util.*

/**
 * Created by wzq on 15/12/21.
 */

val POST_JSON = "post_json"

val DATA_JSON = "data_json"

fun getCommonParams(context: Context): FormEncodingBuilder {
    return FormEncodingBuilder()
            .add("d_id", UUID.randomUUID().toString())
            .add("ts", (System.currentTimeMillis() + 0).toString())
            .add("p", "a")
            .add("ver", BuildConfig.VERSION_NAME)
            .add("c_id", "1")
}

fun runOnUiThread(action: () -> Unit) {
    Handler(Looper.getMainLooper()).post(Runnable(action))
}

fun runDelayed(delayMillis: Long, action: () -> Unit) {
    Handler().postDelayed(Runnable(action), delayMillis)
}

fun runDelayedOnUiThread(delayMillis: Long, action: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(Runnable(action), delayMillis)
}

inline fun <reified T : Any> IntentFor(context: Context): Intent = Intent(context, T::class.java)

