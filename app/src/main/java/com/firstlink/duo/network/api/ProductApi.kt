package com.firstlink.duo.network.api

import com.google.gson.JsonObject
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by wzq on 16/5/13.
 * Be part of []
 */
interface ProductApi {

    @FormUrlEncoded
    @POST("product/find_index_datas.json")
    fun getProductList(@FieldMap map: Map<String, String>) : Observable<JsonObject>
}