package com.firstlink.duo.util

import android.content.Context
import android.util.TypedValue
import com.firstlink.duo.BuildConfig
import com.squareup.okhttp.FormEncodingBuilder
import java.util.*

/**
 * Created by wzq on 15/12/26.
 */
object Tools {

    val POST_JSON = "post_json"

    val DATA_JSON = "data_json"

    var DENSITY = 0

    fun dp2px(context: Context, dp: Int) : Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }

    fun formatPrice(f : Float) : String{
        return "¥${(f/100.0).toString().format("%.2f")}"
    }

    fun handleByCDN(context: Context, url : String, w : Int, h : Int) : String{
        if(DENSITY < 1){
            DENSITY = (context.resources.displayMetrics.density+0.5).toInt()
        }
        return url.plus("@${w}w_${h}h_1e_1c_${DENSITY}x_1o")
    }

    fun getCommonParams(context: Context): FormEncodingBuilder {
        return FormEncodingBuilder()
                .add("d_id", UUID.randomUUID().toString())
                .add("ts", (System.currentTimeMillis() + 0).toString())
                .add("p", "a")
                .add("ver", BuildConfig.VERSION_NAME)
                .add("c_id", "1")
    }



}